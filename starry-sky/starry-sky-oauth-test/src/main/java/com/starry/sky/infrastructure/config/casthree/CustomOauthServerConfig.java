package com.starry.sky.infrastructure.config.casthree;

import com.google.common.collect.Maps;
import com.starry.sky.infrastructure.oauth.filter.CustomClientCredentialsTokenEndpointFilter;
import com.starry.sky.infrastructure.oauth.support.CustomTokenGranter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.security.KeyPair;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wax
 * @description: 自定义配置
 * @date 2021-10-22
 */
//@Slf4j
//@Configuration
//@EnableAuthorizationServer
public class CustomOauthServerConfig extends AuthorizationServerConfigurerAdapter {


    /**
     * Authorization Code Grant 的获取授权码的端点
     */
    public static final String OAUTH_AUTHORIZE_ENDPOINT = "/oauth/authorize";

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    @Qualifier("customAuthenticationEntryPoint")
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    @Qualifier("customAccessDeniedHandler")
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    @Qualifier("customClientDetailsService")
    private ClientDetailsService clientDetailsService;


    @Autowired
    @Qualifier("customUserDetailService")
    private UserDetailsService userDetailsService;


    @Autowired
    @Qualifier("customWebResponseExceptionTranslator")
    private WebResponseExceptionTranslator webResponseExceptionTranslator;



    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler)
                .addTokenEndpointAuthenticationFilter(new CustomClientCredentialsTokenEndpointFilter(passwordEncoder,
                        clientDetailsService,authenticationEntryPoint));
    }

    /**
     * 配置客户端
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService);
    }


    /**
     * 配置授权服务器端点的非安全性特性, 例如 令牌存储, 自定义. 如果是密码授权, 需要在这里提供一个
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .allowedTokenEndpointRequestMethods(HttpMethod.POST, HttpMethod.GET)
                //  自定义的 WebResponseExceptionTranslator, 默认使用 DefaultWebResponseExceptionTranslator, 在 /oauth/token 端点
                .exceptionTranslator(webResponseExceptionTranslator)
                //设置密码模式认证器
                .authenticationManager(authenticationManager)
                // 自定义的 TokenGranter
                .tokenGranter(new CustomTokenGranter(endpoints, authenticationManager))
                //设置授权码模式认证器
//                .authorizationCodeServices(this.authorizationCodeServices())
//                .tokenServices(tokenServices(endpoints))
                .userDetailsService(userDetailsService);
    }

    @Bean
    public AuthorizationCodeServices authorizationCodeServices(){
        return new InMemoryAuthorizationCodeServices();
    }


    public DefaultTokenServices tokenServices(AuthorizationServerEndpointsConfigurer endpoints) {
        // Token增强
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> tokenEnhancers = new ArrayList<>();
        tokenEnhancers.add(tokenEnhancer());
        tokenEnhancers.add(jwtAccessTokenConverter());
        tokenEnhancerChain.setTokenEnhancers(tokenEnhancers);

        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(endpoints.getTokenStore());
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setClientDetailsService(clientDetailsService);
        tokenServices.setTokenEnhancer(tokenEnhancerChain);

        //token有效期设置2个小时
        tokenServices.setAccessTokenValiditySeconds(60*60*2);
        //Refresh_token:12个小时
        tokenServices.setRefreshTokenValiditySeconds(60*60*12);

        return tokenServices;

    }


    /**
     * 使用非对称加密算法对token签名
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setKeyPair(keyPair());
        return converter;
    }

    /**
     * 密钥库中获取密钥对(公钥+私钥)
     */
    @Bean
    public KeyPair keyPair() {
        KeyStoreKeyFactory factory = new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"), "123456".toCharArray());
        return factory.getKeyPair("jwt", "123456".toCharArray());
    }

    /**
     * JWT内容增强
     */
    @Bean
    public TokenEnhancer tokenEnhancer() {
        return (accessToken, authentication) -> {
            Map<String, Object> additionalInfo = Maps.newHashMap();
            Object principal = authentication.getUserAuthentication().getPrincipal();
            additionalInfo.put("ssss","cs");
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
            return accessToken;
        };
    }



}

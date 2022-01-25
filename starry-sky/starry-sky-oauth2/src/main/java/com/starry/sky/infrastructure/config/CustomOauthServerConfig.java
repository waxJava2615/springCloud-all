package com.starry.sky.infrastructure.config;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.starry.sky.infrastructure.filter.CustomRequestFilter;
import com.starry.sky.infrastructure.security.support.CustomTokenGranter;
import com.starry.sky.infrastructure.security.support.filter.CustomClientCredentialsTokenEndpointFilter;
import com.starry.sky.infrastructure.security.support.user.CustomUserDetailService;
import com.starry.sky.infrastructure.security.support.user.CustomUserDetails;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.*;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.Filter;
import java.security.KeyPair;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wax
 * @description: 认证服务配置
 * @date 2021-10-25
 */
@Configuration
@EnableAuthorizationServer
public class CustomOauthServerConfig extends AuthorizationServerConfigurerAdapter {

    public static final String OAUTH_AUTHORIZE_ENDPOINT = "/oauth/authorize";


    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    @Qualifier("customAccessDeniedHandler")
    AccessDeniedHandler accessDeniedHandler;

    @Autowired
    @Qualifier("customAuthenticationEntryPoint")
    AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    @Qualifier("customClientDetailsService")
    ClientDetailsService clientDetailsService;

    @Autowired
    @Qualifier("customUserDetailService")
    CustomUserDetailService customUserDetailService;


    @Autowired
    @Qualifier("customWebResponseExceptionTranslator")
    WebResponseExceptionTranslator<OAuth2Exception> webResponseExceptionTranslator;

    @Autowired
    private CustomRequestFilter customRequestFilter;

    /**
     *  授权服务器端点的 请求到 TokenEndpoint 之前
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        List<Filter> listFilter = new ArrayList<>();
        listFilter.add(customRequestFilter);
        listFilter.add(new CustomClientCredentialsTokenEndpointFilter(passwordEncoder,
                clientDetailsService,authenticationEntryPoint));
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .accessDeniedHandler(accessDeniedHandler)
                .authenticationEntryPoint(authenticationEntryPoint)
                .tokenEndpointAuthenticationFilters(listFilter);
    }


    /**
     * 授权服务器端点的 请求已经到了 TokenEndpoint
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .tokenGranter(new CustomTokenGranter(endpoints,authenticationManager))
                .authenticationManager(authenticationManager)
                .accessTokenConverter(jwtAccessTokenConverter())
                .userDetailsService(customUserDetailService)
                .reuseRefreshTokens(true)
                .tokenServices(tokenServices(endpoints.getTokenStore()))
                .pathMapping("/oauth/token","/api/accessToken.do")
        ;
        endpoints.exceptionTranslator(webResponseExceptionTranslator);
    }


    /**
     * 客户端配置
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService);
    }





    /**
     * 使用非对称加密算法对token签名
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        // 设置用户信息转换器，默认getPrincipal是username
        DefaultAccessTokenConverter accessTokenConverter = (DefaultAccessTokenConverter) converter.getAccessTokenConverter();
        DefaultUserAuthenticationConverter defaultUserAuthenticationConverter =
                new DefaultUserAuthenticationConverter();
        defaultUserAuthenticationConverter.setUserDetailsService(customUserDetailService);
        accessTokenConverter.setUserTokenConverter(defaultUserAuthenticationConverter);

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


    public DefaultTokenServices tokenServices(TokenStore tokenStore) {
        // Token增强
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> tokenEnhancers = new ArrayList<>();
        tokenEnhancers.add(tokenEnhancer());
        tokenEnhancers.add(jwtAccessTokenConverter());
        tokenEnhancerChain.setTokenEnhancers(tokenEnhancers);

        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore);
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setClientDetailsService(clientDetailsService);
        tokenServices.setTokenEnhancer(tokenEnhancerChain);

//        //token有效期设置2个小时
//        tokenServices.setAccessTokenValiditySeconds(60*60*2);
//        //Refresh_token:12个小时
//        tokenServices.setRefreshTokenValiditySeconds(60*60*12);

        return tokenServices;

    }

    /**
     * JWT内容增强
     */
    @Bean
    public TokenEnhancer tokenEnhancer() {
        return (accessToken, authentication) -> {
            Map<String, Object> additionalInfo = Maps.newHashMap();
            Object principal = authentication.getPrincipal();
            CustomUserDetails customUserDetails = (CustomUserDetails) principal;
            additionalInfo.put("id",customUserDetails.getId());
            String clientId = authentication.getOAuth2Request().getClientId();
            additionalInfo.put("clientId",clientId);
            List<String> authorityList = Lists.newArrayList();
            if (CollectionUtils.isNotEmpty(customUserDetails.getAuthorities())){
                authorityList =
                        customUserDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
            }
            additionalInfo.put("authorities",authorityList);
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
            return accessToken;
        };
    }

    //授权码服务
    @Bean
    public AuthorizationCodeServices authorizationCodeServices(){
        return new InMemoryAuthorizationCodeServices();
    }

}

package com.starry.sky.infrastructure.config.castone;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.starry.sky.infrastructure.oauth.extension.PreAuthenticatedUserDetailsService;
import com.starry.sky.infrastructure.oauth.userdetails.admin.AdminUserDetails;
import com.starry.sky.infrastructure.oauth.userdetails.vip.VipUserDetails;
import com.starry.sky.infrastructure.utils.OauthConstants;
import com.starry.sky.infrastructure.utils.ResultData;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;

import java.security.KeyPair;
import java.util.*;

/**
 * @author wax
 * @description: 授权服务
 * @date 2021-10-11
 */
@Configuration
@EnableAuthorizationServer
public class OauthServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    AuthenticationManager authenticationManager;


    @Autowired
    @Qualifier(value = "adminUserDetailService")
    UserDetailsService adminUserService;

    @Autowired
    @Qualifier(value = "vipUserDetailService")
    UserDetailsService vipUserService;

    @Autowired
    private ClientDetailsService clientDetailsService;

//    @Bean
//    public ClientDetailsService clientDetailsService(){
//        return new InMemoryClientDetailsService();
//    }

    //授权码服务
    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        return new InMemoryAuthorizationCodeServices();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient("admin").secret(passwordEncoder.encode("admin")).scopes("all").redirectUris(
                "https://www.baidu.com").authorizedGrantTypes("authorization_code", "password", "refresh_token").and().withClient("vip").secret(passwordEncoder.encode("vip")).scopes("all").redirectUris("https://www.baidu.com").authorizedGrantTypes("authorization_code", "password", "refresh_token");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // Token增强
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> tokenEnhancers = new ArrayList<>();
        tokenEnhancers.add(tokenEnhancer());
        tokenEnhancers.add(jwtAccessTokenConverter());
        tokenEnhancerChain.setTokenEnhancers(tokenEnhancers);
        // 添加自定义授权模式
        List<TokenGranter> granterList = new ArrayList<>(Arrays.asList(endpoints.getTokenGranter()));

        CompositeTokenGranter compositeTokenGranter = new CompositeTokenGranter(granterList);
        endpoints.authenticationManager(authenticationManager).accessTokenConverter(jwtAccessTokenConverter())
                //授权码模式服务
                .authorizationCodeServices(authorizationCodeServices())
                //允许post方式请求
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST).tokenEnhancer(tokenEnhancerChain).tokenGranter(compositeTokenGranter)
                /** refresh token有两种使用方式：重复使用(true)、非重复使用(false)，默认为true
                 *  1 重复使用：access token过期刷新时， refresh token过期时间未改变，仍以初次生成的时间为准
                 *  2 非重复使用：access token过期刷新时， refresh token过期时间延续，在refresh token有效期内刷新便永不失效达到无需再次登录的目的
                 */.reuseRefreshTokens(true)
                // 自定义的TokenService
                .tokenServices(tokenServices(endpoints));
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //设置oauth_client_details中的密码编码器
        security.passwordEncoder(passwordEncoder).tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()").authenticationEntryPoint(authenticationEntryPoint()).allowFormAuthenticationForClients();
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
        tokenServices.setAccessTokenValiditySeconds(60 * 60 * 2);
        //Refresh_token:12个小时
        tokenServices.setRefreshTokenValiditySeconds(60 * 60 * 12);

        // 多用户体系下，刷新token再次认证客户端ID和 UserDetailService 的映射Map
        Map<String, UserDetailsService> clientUserDetailsServiceMap = new HashMap<>();
        clientUserDetailsServiceMap.put(OauthConstants.ADMIN_CLIENT_ID, adminUserService);
        clientUserDetailsServiceMap.put(OauthConstants.VIP_CLIENT_ID, vipUserService);

        // 重写刷新token再次认证的 AuthenticationManager中 的 UserDetailService，根据客户端ID和认证方式获取用户认证信息 UserDetails
        PreAuthenticatedAuthenticationProvider provider = new PreAuthenticatedAuthenticationProvider();
        provider.setPreAuthenticatedUserDetailsService(new PreAuthenticatedUserDetailsService<>(clientUserDetailsServiceMap));
        tokenServices.setAuthenticationManager(new ProviderManager(Arrays.asList(provider)));

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
        return (accessToken, authentication)->{
            Map<String, Object> additionalInfo = Maps.newHashMap();
            Object principal = authentication.getUserAuthentication().getPrincipal();
            if (principal instanceof AdminUserDetails) {
                AdminUserDetails adminUserDetails = (AdminUserDetails) principal;
                additionalInfo.put("userId", adminUserDetails.getId());
                additionalInfo.put("username", adminUserDetails.getUsername());
                if (StringUtils.isNotBlank(adminUserDetails.getAccountType())) {
                    additionalInfo.put("authenticationMethod", adminUserDetails.getAccountType());
                }
            } else if (principal instanceof VipUserDetails) {
                VipUserDetails vipUserDetails = (VipUserDetails) principal;
                additionalInfo.put("userId", vipUserDetails.getId());
                additionalInfo.put("username", vipUserDetails.getUsername());
                if (StringUtils.isNotBlank(vipUserDetails.getAccountType())) {
                    additionalInfo.put("authenticationMethod", vipUserDetails.getAccountType());
                }
            }
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
            return accessToken;
        };
    }


    /**
     * 自定义认证异常响应数据
     */
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, e)->{
            response.setStatus(HttpStatus.SC_OK);
            response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Cache-Control", "no-cache");
            ResultData resultData = ResultData.failResult();
            response.getWriter().print(objectMapper.writeValueAsString(resultData));
            response.getWriter().flush();
        };
    }


}

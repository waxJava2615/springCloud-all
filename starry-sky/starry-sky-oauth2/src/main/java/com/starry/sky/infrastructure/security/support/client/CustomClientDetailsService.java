package com.starry.sky.infrastructure.security.support.client;

import com.starry.sky.infrastructure.dto.ClientDTO;
import com.starry.sky.infrastructure.exceptions.CustomAuthenticationException;
import com.starry.sky.infrastructure.utils.enums.OauthResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author wax
 * @description: 客户端信息
 * @date 2021-10-22
 */
@Slf4j
@Component
public class CustomClientDetailsService implements ClientDetailsService {


    private Map<String,ClientDTO> dtoMap = new HashMap<>();

    @PostConstruct
    public void initDate(){
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId("admin");
        clientDTO.setClientSecret("admin");
        clientDTO.setScope("all");
        clientDTO.setAuthorizedGrantType("authorization_code,refresh_token,captcha");
        clientDTO.setRedirectUri("https://www.baidu.com");
        clientDTO.setAccessTokenValidity(360000);
        clientDTO.setRefreshTokenValidity(720000);
        clientDTO.setAutoApprove(false);
        clientDTO.setDescription("admin description");
        clientDTO.setAuthorities(new HashSet<>());
        dtoMap.put("admin",clientDTO);
        ClientDTO clientGuest = new ClientDTO();
        clientGuest.setId("guest");
        clientGuest.setClientSecret("guest");
        clientGuest.setScope("all");
        clientGuest.setAuthorizedGrantType("authorization_code,refresh_token");
        clientGuest.setRedirectUri("https://www.baidu.com");
        clientGuest.setAccessTokenValidity(360000);
        clientGuest.setRefreshTokenValidity(720000);
        clientGuest.setAutoApprove(false);
        clientGuest.setDescription("guest description");
        clientDTO.setAuthorities(new HashSet<>());
        dtoMap.put("guest",clientGuest);
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        log.debug("About to produce ClientDetails with client-id: {}", clientId);
        // 后续调用远程API 获取,测试时使用 new
        if (dtoMap.containsKey(clientId)){
            return new CustomClientDetails(dtoMap.get(clientId));
        }else {
            throw new CustomAuthenticationException(OauthResultCode.OAUTH_CLIENT_ID_INVALID);
        }
    }
}

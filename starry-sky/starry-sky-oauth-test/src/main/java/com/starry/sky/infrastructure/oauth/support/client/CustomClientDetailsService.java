package com.starry.sky.infrastructure.oauth.support.client;

import com.starry.sky.infrastructure.dto.ClientDTO;
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
        clientDTO.setId("clientAdmin");
        clientDTO.setClientSecret("clientAdmin");
        clientDTO.setScope("all");
        clientDTO.setAuthorizedGrantType("authorization_code,refresh_token");
        clientDTO.setRedirectUri("https://www.baidu.com");
        clientDTO.setAccessTokenValidity(3600);
        clientDTO.setRefreshTokenValidity(7200);
        clientDTO.setAutoApprove(false);
        clientDTO.setDescription("clientAdmin description");
        clientDTO.setAuthorities(new HashSet<>());
        dtoMap.put("clientAdmin",clientDTO);
        ClientDTO clientGuest = new ClientDTO();
        clientGuest.setId("clientGuest");
        clientGuest.setClientSecret("clientGuest");
        clientGuest.setScope("all");
        clientGuest.setAuthorizedGrantType("authorization_code,refresh_token");
        clientGuest.setRedirectUri("https://www.baidu.com");
        clientGuest.setAccessTokenValidity(3600);
        clientGuest.setRefreshTokenValidity(7200);
        clientGuest.setAutoApprove(false);
        clientGuest.setDescription("clientGuest description");
        clientDTO.setAuthorities(new HashSet<>());
        dtoMap.put("clientGuest",clientGuest);
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        log.debug("About to produce ClientDetails with client-id: {}", clientId);
        // 后续调用远程API 获取,测试时使用 new
        if (dtoMap.containsKey(clientId)){
            return new CustomClientDetails(dtoMap.get(clientId));
        }else {
            throw new ClientRegistrationException(String.format("客户端 %s 尚未注册!", clientId));
        }
    }
}

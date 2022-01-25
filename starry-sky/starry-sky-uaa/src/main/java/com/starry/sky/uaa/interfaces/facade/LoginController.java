package com.starry.sky.uaa.interfaces.facade;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.starry.sky.infrastructure.utils.ResultData;
import com.starry.sky.uaa.interfaces.dto.LoginDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author wax
 * @description: TODO
 * @date 2021-10-28
 */
@RestController
@Slf4j
public class LoginController {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/login")
    public ResultData login(@RequestBody LoginDTO loginDTO) throws JsonProcessingException {
        log.info("收到登陆请求:{}", objectMapper.writeValueAsString(loginDTO));
        String reqUrl =
                "http://localhost:50000/api/login?account="+loginDTO.getUsername() + "&password="+loginDTO.getPassword()+"&code=6666";
        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(reqUrl, null, String.class);
        log.info(stringResponseEntity.getBody());
        return ResultData.successResult();
    }

    @RequestMapping("/hello")
    public String hello(){
        log.info("访问hello接口...");
        return "hello uaa";
    }

}

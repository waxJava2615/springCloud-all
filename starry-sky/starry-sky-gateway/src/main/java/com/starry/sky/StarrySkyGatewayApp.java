package com.starry.sky;

import cn.hutool.core.io.FileUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import sun.misc.BASE64Encoder;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @author wax
 * @description: 网关启动类
 * @date 2021-08-19
 */
@EnableDiscoveryClient
@SpringBootApplication
public class StarrySkyGatewayApp {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(StarrySkyGatewayApp.class, args);
    }

    //编码返回字符串
    public static String encryptBASE64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }


    public static void encodeJWT() throws Exception {
        PrivateKey privateKey = null;
        PublicKey publicKey = null;
        InputStream inputStream =
                FileUtil.getInputStream("E:\\GITHUB\\wax-git\\branch\\springCloud-all-dev" + "-0.0" + ".1\\starry-sky" +
                        "\\starry-sky-oauth2\\src\\main\\resources\\jwt.jks");
//        InputStream inputStream = Thread.currentThread().getContextClassLoader()
//                .getResourceAsStream("/jwt.jks");
        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(inputStream, "123456".toCharArray());

        privateKey = (PrivateKey) keyStore.getKey("jwt", "123456".toCharArray());

        System.out.println(encryptBASE64(privateKey.getEncoded()));

        publicKey = keyStore.getCertificate("jwt").getPublicKey();

        System.out.println(encryptBASE64(publicKey.getEncoded()));
    }


}

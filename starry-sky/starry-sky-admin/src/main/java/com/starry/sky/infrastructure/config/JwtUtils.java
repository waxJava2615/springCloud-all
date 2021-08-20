package com.starry.sky.infrastructure.config;

import com.starry.sky.common.config.StarrySkyAdminJwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wax
 * @description: TODO
 * @date 2021-08-20
 */
@Setter
@Component
@EnableConfigurationProperties(StarrySkyAdminJwtConfig.class)
public class JwtUtils {

    @Autowired
    private StarrySkyAdminJwtConfig starrySkyAdminJwtConfig;


    public static void main(String[] args) {
        JwtUtils jwtUtils = new JwtUtils();
        StarrySkyAdminJwtConfig starrySkyAdminJwtConfig = new StarrySkyAdminJwtConfig();
        starrySkyAdminJwtConfig.setSecret("starry-sky-admin-secret-hs256-adI5iAXivKdnZrM6yetk");
        starrySkyAdminJwtConfig.setSubject("all");
        starrySkyAdminJwtConfig.setIssuer("admin");
        jwtUtils.setStarrySkyAdminJwtConfig(starrySkyAdminJwtConfig);
        Map map = new HashMap();
        map.put("123","123");
        String jwtToken = jwtUtils.createJwtToken("wax", map, 30000);
        System.out.println(jwtToken);

        System.out.println("=============>");
        Claims claims = jwtUtils.parseJWT(jwtToken);
        System.out.println(claims.get("123"));
        System.out.println(isTokenExpired(claims));
    }

    /**
     *  创建JWT-token
     * @param id
     * @param claims payload的私有声明（根据特定的业务需要添加）
     * @param ttlMillis
     * @return
     */
    public String createJwtToken(String id, Map<String, Object> claims, long ttlMillis) {

        // 签名算法 ，将对token进行签名
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        // 生成签发时间
        LocalDateTime localDateTime = LocalDateTime.now();
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        // 通过秘钥签名JWT
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(starrySkyAdminJwtConfig.getSecret());
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        JwtBuilder builder = Jwts.builder().setId(id)
                .setIssuedAt(date)
                .setSubject(starrySkyAdminJwtConfig.getSubject())
                .setIssuer(starrySkyAdminJwtConfig.getIssuer())
                .setClaims(claims)
                .signWith(signatureAlgorithm,signingKey);
        if (ttlMillis > 0) {
            long expMillis = date.getTime() + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }

    public Claims parseJWT(String jwt) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(starrySkyAdminJwtConfig.getSecret()))
                    .parseClaimsJws(jwt).getBody();
            return claims;
        } catch (Exception exception) {
            return null;
        }
    }

    public static Boolean isTokenExpired(Claims claims) {
        return claims == null || claims.getExpiration().before(new Date());
    }
}

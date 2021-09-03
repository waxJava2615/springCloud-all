package com.starry.sky.infrastructure.config.authentication;

import com.starry.sky.common.properties.StarrySkyAdminJwtProperties;
import com.starry.sky.common.exception.CustomizeRuntimeException;
import com.starry.sky.common.utils.ResultCode;
import io.jsonwebtoken.*;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

/**
 * @author wax
 * @description: JWT生产校验
 * @date 2021-08-20
 */
@Slf4j
@Setter
@Component
@EnableConfigurationProperties(StarrySkyAdminJwtProperties.class)
public class JwtGenerateProcess {

    private final long DEFAULT_EXP_TIME = 2 * 60 * 1000L;


    public static String CLAIMS_KEY_NAME_USER_ID = "userId";
    public static String CLAIMS_KEY_NAME_USER_NAME = "userName";

    @Autowired
    private StarrySkyAdminJwtProperties starrySkyAdminJwtConfig;


    /**
     *  创建JWT-token
     * @param claims payload的私有声明（根据特定的业务需要添加）
     * @return
     */
    public String createJwtToken(Map<String, Object> claims) {

        // 签名算法 ，将对token进行签名
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        // 生成签发时间
        LocalDateTime localDateTime = LocalDateTime.now();
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        // 通过秘钥签名JWT
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(starrySkyAdminJwtConfig.getSecret());
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(date)
                .signWith(signatureAlgorithm,signingKey);
        long ttlMillis = DEFAULT_EXP_TIME;
        if (starrySkyAdminJwtConfig.getExpiration() > 0){
            ttlMillis = starrySkyAdminJwtConfig.getExpiration();
        }
        long expMillis = date.getTime() + ttlMillis;
        Date exp = new Date(expMillis);
        builder.setExpiration(exp);
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

    public Boolean isTokenExpired(Claims claims) {
        return claims == null || claims.getExpiration().before(new Date());
    }

    public String refreshToken(String token) {
        String refreshedToken;
        try {
            Claims claims = parseJWT(token);
            if (!isTokenExpired(claims)){
                // TODO: 2021/8/23 后续添加
            }
            refreshedToken = createJwtToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }


    /**
     * 校验token
     * @param token
     * @return
     */
    public Boolean validate(String token) throws CustomizeRuntimeException {
        try {
            // 签名算法 ，将对token进行签名
            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
            byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(starrySkyAdminJwtConfig.getSecret());
            Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
            Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token);
            return Boolean.TRUE;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("token签名无效");
            throw new CustomizeRuntimeException(ResultCode.TOKEN_EXPIRED_OR_INVALID.getCode(),ResultCode.TOKEN_EXPIRED_OR_INVALID.getMessage());
        } catch (ExpiredJwtException e) {
            log.info("token已过期");
            throw new CustomizeRuntimeException(ResultCode.TOKEN_EXPIRED_OR_INVALID.getCode(),ResultCode.TOKEN_EXPIRED_OR_INVALID.getMessage());
        } catch (UnsupportedJwtException e) {
            log.info("无效的token");
            throw new CustomizeRuntimeException(ResultCode.TOKEN_EXPIRED_OR_INVALID.getCode(),ResultCode.TOKEN_EXPIRED_OR_INVALID.getMessage());
        } catch (IllegalArgumentException e) {
            log.info("token被篡改");
            throw new CustomizeRuntimeException(ResultCode.TOKEN_EXPIRED_OR_INVALID.getCode(),ResultCode.TOKEN_EXPIRED_OR_INVALID.getMessage());
        }
    }

}

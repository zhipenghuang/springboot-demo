package com.hzp.demo.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT工具类
 *
 * @author hackyo
 * Created on 2017/12/8 9:20.
 */
@Component
@Slf4j
public class JwtTokenUtils implements Serializable {

    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    private String generateToken(Map<String, Object> claims) {
        Date expirationDate = new Date(System.currentTimeMillis() + 1000000000);
        String secret = (String) claims.get("secret");
        claims.remove("claims");
        return Jwts.builder().setClaims(claims).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    private Claims getClaimsFromToken(String token, String secret) {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    /**
     * 生成令牌
     *
     * @return 令牌
     */
    public String generateToken(String accountName,String secret) {
        Map<String, Object> claims = new HashMap<>(2);
        claims.put("sub", accountName);
        claims.put("created", new Date());
        claims.put("secret", secret);
        return generateToken(claims);
    }

    /**
     * 从令牌中获取用户名
     *
     * @param token 令牌
     * @return 用户名
     */
    public String getAccountNameFromToken(String token) {
        String[] split = token.split("\\.");
        String s = split[1];
        byte[] decode = Base64.getDecoder().decode(s);
        String payload = new String(decode);
        JSONObject jsonObject = JSON.parseObject(payload);
        Object sub = jsonObject.get("sub");
        return (String) sub;
    }

    public String getAccountNameFromToken(String token, String secret) {
        String username;
            Claims claims = getClaimsFromToken(token, secret);
            username = claims.getSubject();

        return username;
    }

    /**
     * 判断令牌是否过期
     *
     * @param token 令牌
     * @return 是否过期
     */
    public Boolean isTokenExpired(String token, String secret) {
        try {
            Claims claims = getClaimsFromToken(token, secret);
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 刷新令牌
     *
     * @param token 原令牌
     * @return 新令牌
     */
    public String refreshToken(String token, String secret) {
        String refreshedToken;
        try {
            Claims claims = getClaimsFromToken(token, secret);
            claims.put("created", new Date());
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }
}
package com.alex.study.springsecurity.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -3301605591108950415L;

    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";

    private static final String secret = "mySecret";

    /**
     * Token http header.
     */
    public static final String TOKEN_HEADER = "Authorization";

    // 一天
    private static final Long expiration = 24 * 60 * 60 * 1000l;

    /**
     * 从JWT中解析用户uuid
     */
    public String getUserUuidFromToken(String token) {
        String userUuid;
        try {
            final Claims claims = getClaimsFromToken(token);
            userUuid = claims.getSubject();
        } catch (Exception e) {
            userUuid = null;
        }
        return userUuid;
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration);
    }

    public String generateToken(String userUuid) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userUuid);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
//                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
}
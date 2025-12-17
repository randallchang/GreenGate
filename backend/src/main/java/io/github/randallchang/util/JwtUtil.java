package io.github.randallchang.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    @Value("${jwt.sign.key}")
    private String secret;

    public static String LOGIN_USER_ID_KEY = "login_user_id";

    public String encrypt(Map<String, Object> claims) {
        return Jwts.builder()
            .claims(claims)
            .signWith(Keys.hmacShaKeyFor(Base64.getEncoder().encode(secret.getBytes(StandardCharsets.UTF_8))))
            .compact();
    }

    public Map<String, Object> decrypt(String token) {
        return Jwts.parser()
            .verifyWith(Keys.hmacShaKeyFor(Base64.getEncoder().encode(secret.getBytes(StandardCharsets.UTF_8))))
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }
}

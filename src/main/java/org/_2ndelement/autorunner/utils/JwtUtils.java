package org._2ndelement.autorunner.utils;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org._2ndelement.autorunner.properties.JwtProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtils {
    private final JwtProperties jwtProperties;


    public String generateToken(Long id) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtProperties.getExpiration() * 1000);

        return Jwts.builder().setSubject(id.toString())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes()), SignatureAlgorithm.HS512)
                .compact();
    }

    public Long getUserIdFromJwt(String token) {
        return Long.parseLong(Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes())).build().parseClaimsJws(token).getBody().getSubject());
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes())).build().parseClaimsJws(authToken);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

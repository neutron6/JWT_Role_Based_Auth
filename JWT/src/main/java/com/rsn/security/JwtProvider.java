package com.rsn.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtProvider {

    private String jwtSecret = "sIoVC8OFOgmxbk9XRYtY2zMKXuYXBGL2d3x1IV37";

    // Jwt Expiration in millis
    private Long jwtExpiration = 60000L;

    private Claims parseToken(String token) {

        // Create JwtParser------3
        JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(jwtSecret.getBytes()).build();

        Claims claims;
        try {
            claims = jwtParser.parseClaimsJws(token).getBody();
        } catch (Exception e) {
            // TODO: handle exception
        }

        return null;

    }

    public boolean validateToken(String token) {
        return parseToken(token) != null;
    }

    public String getUsernameFromToken(String token) {
        Claims claims = parseToken(token);

        // Extract subject
        if (claims != null) {
            return claims.getSubject();
        }

        return null;
    }

    public String generateToken(String username) {
        // Create signing key

        Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes());

        // Generate token
        var currentDate = new Date();
        var expiration = new Date(currentDate.getTime() + jwtExpiration);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(currentDate)
                .setExpiration(expiration)
                .signWith(key)
                .compact();
    }
}

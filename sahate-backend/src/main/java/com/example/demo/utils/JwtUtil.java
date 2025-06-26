package com.example.demo.utils;

import com.example.demo.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {

    private final String secretKey = "sahate2024";
    private final JwtParser jwtParser;

    public JwtUtil() {
        try {
            this.jwtParser = Jwts.parser().setSigningKey(secretKey);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }
    }

    public String generateToken(User user){
        Claims claims = Jwts.claims().setSubject(user.getEmail());
        claims.put("email", user.getEmail());

        Date tokenCreateTime = new Date();
        // 1 hour
        long accessTokenExpireTime = 1000L * 60L * 60L;
        Date tokenExpireTime = new Date(tokenCreateTime.getTime() + TimeUnit.MINUTES.toMillis(accessTokenExpireTime));

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(tokenExpireTime)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    private Claims getClaims(String token){
        return jwtParser.parseClaimsJws(token).getBody();
    }

    public Claims resolveClaims(HttpServletRequest request){
        try {
            String token = resolveToken(request);
            return getClaims(token);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }
    }

    public String resolveToken(HttpServletRequest request) {
        String headerPrefix = "Authorization";
        String bearerToken = request.getHeader(headerPrefix);
        String prefix = "Bearer ";
        if (bearerToken != null && bearerToken.startsWith(prefix)) {
            return bearerToken.substring(prefix.length());
        }
        return null;
    }

    public boolean validateClaims(Claims claims){

        return claims.getExpiration().after(new Date());
    }
}

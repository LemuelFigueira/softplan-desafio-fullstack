package com.br.api.service;

import com.br.api.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

@Service
public class TokenService {

    @Value("${jwt.expiration.hours}")
    private Integer expiration;

    @Value("${jwt.secret}")
    private String secret;

    @Autowired
    private UtilService utilService;

    public String generateToken(Authentication authentication) {

        var user = (User) authentication.getPrincipal();

        var now = new Date();
        var exp = this.utilService.addHoursToJavaUtilDate(now, expiration);

        return Jwts.builder().setIssuer("ProcessAPI")
                .setSubject(user.getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Integer getTokenId(String token) {
        var body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return Integer.valueOf(body.getSubject());
    }
}

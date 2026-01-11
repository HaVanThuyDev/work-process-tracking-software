package com.example.vn2_ht_student.security;


import com.example.vn2_ht_student.model.enums.Role;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;
import java.util.List;
//Sinh & verify JWT //NGƯỜI KÝ & KIỂM TRA TOKEN”
@Component
public class JwtProvider {
    private final Key key;
    private final Long expirationMs;
    public JwtProvider(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.expiration}") long expirationMs
    )
    {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.expirationMs = expirationMs;
    }
    public String generateToken(Long userId, String email, Role role, List<String> permissions) {
        return Jwts.builder().setSubject(email).claim("userId",userId).claim("roles",role).claim("permissions",permissions).setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs)).signWith(key,SignatureAlgorithm.HS256).compact();

    }
    public Claims parseToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

}

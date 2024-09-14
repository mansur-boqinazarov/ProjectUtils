package uz.pdp.projectutils.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private final String SECRET_HS256_KEY = "34e7f550e2715c958372db43db8f68c0ea686807bf7eab4f22f2ee673df0f929";
    private final Long ACCESS_TOKEN_EXPIRATION = 10 * 60 * 1000L; // 10 minutes
    private final Long REFRESH_TOKEN_EXPIRATION = 15 * 60 * 1000L; // 15 minutes

    public SecretKey getKeysHs256(){
        return Keys.hmacShaKeyFor(SECRET_HS256_KEY.getBytes());
    }

    public String generateAccessToken(String username){
        return createToken(username, new HashMap<>(), ACCESS_TOKEN_EXPIRATION);
    }

    public String generateAccessToken(String username, Authentication authenticate){
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", authenticate.getAuthorities());
        return createToken(username, claims, ACCESS_TOKEN_EXPIRATION);
    }

    public String generateRefreshToken(String username){
        return createToken(username, new HashMap<>(), REFRESH_TOKEN_EXPIRATION);
    }

    private String createToken(String subject, Map<String, Object> claims, Long expirationTime){
        return Jwts.builder()
                .signWith(getKeysHs256(), SignatureAlgorithm.HS256)
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .compact();
    }

    public String getSubject(String token){
        Claims jwtBody = getJWTBody(token);
        return jwtBody.getSubject();
    }

    public Claims getJWTBody(String token){
        JwtParser parser = Jwts.parserBuilder()
                .setSigningKey(getKeysHs256())
                .build();
        return (Claims) parser
                .parse(token)
                .getBody();
    }
}

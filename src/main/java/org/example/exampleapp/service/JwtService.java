package org.example.exampleapp.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.example.exampleapp.model.UserCredentials;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Service
public class JwtService {

    private String key = "";
    public JwtService() {
        try {
            KeyGenerator hmacSHA256 = KeyGenerator.getInstance("HmacSHA256");
            SecretKey secretKey = hmacSHA256.generateKey();
            key = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }

    public String generateToken(UserCredentials userCredentials) {
        HashMap<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .claims(claims)
                .subject(userCredentials.getUsername())
                .expiration(new Date(new Date().getTime() + 1000 * 60 * 60 * 24))
                .issuedAt(new Date())
                .signWith(getKey())
                .compact();
    }

    private SecretKey getKey() {
        byte[] bytes = Decoders.BASE64.decode(key);
        return Keys.hmacShaKeyFor(bytes);
    }

    public String extractUserName(String token) {
        // extract the username from jwt token
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}

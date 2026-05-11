package com.example.hotelmanagmentsystem.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService
{
    @Value("${Jwt.secret}")
   private String secretKey ;
    public Key getKey ()
    {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }
    public String generateToken(String email)
    {
        long expirationTime = 1000 * 60 * 60*10 ; // 10 hours
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+expirationTime))
                .signWith(getKey(),SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims extractClaims(String token)
    {
       return Jwts.parserBuilder().
        setSigningKey(getKey()).build()
                .parseClaimsJws(token)
                .getBody();
    }

    public  String extractEmail (String token)
    {
        return extractClaims(token).getSubject();
    }

    public boolean validateToken(String token , UserDetails userDetails)
    {
        try
        {
           return extractEmail(token).equals(userDetails.getUsername());
        }
        catch (Exception ex)
        {
            return  false;
        }
    }
}

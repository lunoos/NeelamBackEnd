package com.neelam.ecom;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts; // Or your preferred JWT library
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.lang.Collections;
import io.jsonwebtoken.lang.Objects;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    // Replace with your secret key or key retrieval mechanism
    private String secretKey;

    public JwtTokenFilter() {
        this.secretKey = "mySecrateKey";
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 1. Extract Token
        String token = extractTokenFromRequest(request);

        // 2. Validate Token
        try {
            if (token != null && validateToken(token)) {
                Claims claims = decodeToken(token);
                setUpSpringAuthentication(claims);
            } 
        } catch (Exception ex) {
            // Handle invalid token scenarios
            response.setStatus(HttpServletResponse.SC_FORBIDDEN); 
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN, ex.getMessage());
            return; 
        }

        filterChain.doFilter(request, response);
    }

    // Helper Methods

    private String extractTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // Remove "Bearer " 
        }
        return null;
    }

    private boolean validateToken(String token) {
        // Use your JWT library (e.g., Jwts) to validate the signature, expiration, etc.
        return Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(token).toString() != null;
    }

    private Claims decodeToken(String token) {
        return Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(token).getBody();
    }

    private void setUpSpringAuthentication(Claims claims) {
         // Extract roles/authorities from the token
        List<String> authorities = (List<String>) claims.get("authorities"); 

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(), null,getGrantedAuthority(authorities));
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
    
    private List<GrantedAuthority> getGrantedAuthority( List<String> authorities){
    	if(!Collections.isEmpty(authorities)) {
    		return authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    	}
    	return null;
    }
    public String generateToken(Map<String,Object> claims) throws NoSuchAlgorithmException, UnsupportedEncodingException {
//    	KeyGenerator keyGenerator = KeyGenerator.getInstance("AES"); 
//    	keyGenerator.init(256); // Or another key size like 128 
//    	SecretKey key = keyGenerator.generateKey();

    	// Base64 encode the key
		return Jwts.builder().setClaims(claims).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour expiration
				.signWith(SignatureAlgorithm.HS256, secretKey.getBytes()).compact();

    }
} 

package com.gestorinc.security.service.jwt;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

import static com.gestorinc.utils.Constants.*;
import static java.util.Optional.empty;
import static java.util.Optional.of;

@Component
public class JwtTokenProvider {

    private static final String CREDENTIALS = "";
    @Value($_SECURITY_JWT_TOKEN_SECRET_KEY_SECRET)
    private String secretKey = SECRET;
    @Value($_SECURITY_JWT_TOKEN_EXPIRE_LENGTH_3600000)
    private long validityInMilliseconds = 3600000; // 1h
    @Autowired
    private UserDetailsService userDetailsService;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String username) {

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(getClaims(username))
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    private Claims getClaims(String username) {
        Claims claims = Jwts.claims().setSubject(username);
        return claims;
    }

    public Authentication getAuthentication(String token) {

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));

        return new UsernamePasswordAuthenticationToken(userDetails, CREDENTIALS
                , userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public Optional<String> resolveToken(HttpServletRequest request) {
        try {
            return of(request.getHeader(AUTHORIZATION))
                    .map(this::takeToken);
        }catch (NullPointerException error) {
            return empty();
        }
    }

    private String takeToken(String token) {
        return token.substring(7, token.length());
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            throw new InvalidJwtAuthenticationException(EXPIRED_OR_INVALID_JWT_TOKEN);
        }
    }
}
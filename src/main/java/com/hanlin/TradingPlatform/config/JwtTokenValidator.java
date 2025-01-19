package com.hanlin.TradingPlatform.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.List;

public class JwtTokenValidator extends OncePerRequestFilter {
    // OncePerRequestFilter guarantees execution once per HTTP request.
    // ts main purpose is to validate a JWT from
    // incoming requests, extract user information, and set the authentication context for the request.

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Processes the incoming request to validate the JWT and set the security context.
        // FilterChain filterChain: Provides the next filter in the chain to continue processing.

        // Reads the JWT from a request header i.e. Authorization
        String jwt = request.getHeader(JwtConstant.JWT_HEADER);

        // The user provided token will be provided in this format
        // Bearer $token, so we need to extract string after bearer
        if (jwt != null) {
            jwt = jwt.substring(7);
            try {
                // The secret key (SECRET_KEY) is used to verify the token's signature.
                SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECERT_KEY.getBytes());

                // The token is parsed and validated. If it's invalid or tampered with, an exception is thrown
                // Extract the claims
                Claims claims = Jwts.parser().setSigningKey(key).build().parseClaimsJws(jwt).getBody();

                // Extract claims and Authorities
                String email = String.valueOf(claims.get("email"));
                String authorities = String.valueOf(claims.get("authorities"));
                // Convert to GrantedAuthority:
                // Converts the string of authorities into a list of Spring Security GrantedAuthority objects
                List<GrantedAuthority> authoritiesList = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);

                // Create authentication objects, construct a token with
                // Principal: the email --> user identity
                // Credentials:
                // Authorities: The list of roles or permissions
                Authentication auth = new UsernamePasswordAuthenticationToken(
                        email,
                        authorities,
                        authoritiesList
                );

                // auth represents the authenticated user
                // Sets the Authentication object in the Spring Security Context, making the user information
                // available for the request.
                // Populates Spring Security's context with the user details for use in subsequent processing.
                SecurityContextHolder.getContext().setAuthentication(auth);

            } catch (Exception e) {
                throw new RuntimeException("Invalid JWT Token");
            }
        }
        // Pass the request and response to the next filter in the chain, continuing the processing
        filterChain.doFilter(request, response);
    }
}

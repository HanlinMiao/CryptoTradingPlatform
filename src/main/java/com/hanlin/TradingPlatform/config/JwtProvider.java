package com.hanlin.TradingPlatform.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class JwtProvider {

    // Initialize a secret key using a constant string defined in JwtConstant for signing and verifying JWTs
    // The key is created using HMAC-SHA for token signing.
    private static SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECERT_KEY.getBytes());


    public static String generateToken(Authentication auth) {
        /**
         * Generate a JWT for a given Authentication object
         *
         * @return A Generated JWT Token
         */


        // Extract user roles or authorities from auth
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        String roles = populateAuthorities(authorities);

        // Build the JWT token with:
        //  IssuedAt() current datetime
        //  Expiration() 24 hours from the issued time
        //  Claims(): Additional Payload data, such as the user's email and authorities
        //  Sign the token with the secret key
        String jwt = Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 86400000))
                .claim("email", auth.getName())
                .claim("authorities", roles)
                .signWith(key)
                .compact();

        // Return the token as a String
        return jwt;
    }

    public static String getEmailFromToken(String token) {
        // Extracts the email claim from a given JWT token

        // Removes the "Bearer " prefix from the token string
        token = token.substring(7);

        // Parses the JWT using the secret key and extracts the claims
        Claims claims = Jwts.parser().setSigningKey(key).build().parseClaimsJws(token).getBody();

        // Retrieve the email claim
        String email = String.valueOf(claims.get("email"));
        return email;
    }

    private static String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {

        // Converts a collection of GrantedAuthority objects into a comma-separated string of role names
        Set<String> auth = new HashSet<>();

        // Extracts each authority's name and adds it to a Set()
        for (GrantedAuthority grantedAuthority : authorities) {
            auth.add(grantedAuthority.getAuthority());
        }

        // Join the names into a single string separated by commas
        return String.join(",", auth);
    }
}

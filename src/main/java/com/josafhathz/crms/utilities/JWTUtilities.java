package com.josafhathz.crms.utilities;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Slf4j
public class JWTUtilities {

    public static DecodedJWT decodeTokenIfValid(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("GET BACK".getBytes());
            JWTVerifier verifier = JWT.require(algorithm).build();
            return verifier.verify(token);
        } catch (Exception ex) {
            log.error("The following error was produced after decoded the JWT Token {}", ex.getMessage());
            return null;
        }
    }

    public static ArrayList<SimpleGrantedAuthority> getAuthoritiesAsArrayList(String[] roles) {
        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
        stream(roles).forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role));
        });
        return authorities;
    }

    public static String createJWTToken(User user, int days, String requestURL) {
        Algorithm algorithm = Algorithm.HMAC256("GET BACK".getBytes());
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + days * 68 * 10000))
                .withIssuer(requestURL)
                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
    }

}

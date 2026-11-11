package com.example.mtc.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    // ======================================================
    // SECRET KEY
    // ======================================================

    private static final String SECRET_KEY =
            "MTC_SECRET_KEY_2026_ME_TRUYEN_CHU_SECRET_KEY_123456";

    private final SecretKey key =
            Keys.hmacShaKeyFor(
                    SECRET_KEY.getBytes(StandardCharsets.UTF_8)
            );


    // ======================================================
    // TOKEN HẾT HẠN SAU 24 GIỜ
    // ======================================================

    private static final long EXPIRATION_TIME =
            24 * 60 * 60 * 1000L;


    // ======================================================
    // TẠO JWT
    // ======================================================

    public String generateToken(
            Long userId,
            String email
    ) {

        Date now = new Date();

        Date expiration = new Date(
                now.getTime() + EXPIRATION_TIME
        );

        return Jwts.builder()

                .subject(
                        String.valueOf(userId)
                )

                .claim(
                        "email",
                        email
                )

                .issuedAt(now)

                .expiration(expiration)

                .signWith(key)

                .compact();
    }


    // ======================================================
    // LẤY USER ID TỪ TOKEN
    // ======================================================

    public Long getUserId(
            String token
    ) {

        Claims claims = parseToken(token);

        return Long.valueOf(
                claims.getSubject()
        );
    }


    // ======================================================
    // LẤY EMAIL TỪ TOKEN
    // ======================================================

    public String getEmail(
            String token
    ) {

        Claims claims = parseToken(token);

        return claims.get(
                "email",
                String.class
        );
    }


    // ======================================================
    // KIỂM TRA TOKEN
    // ======================================================

    public boolean isTokenValid(String token) {
        try {
            parseToken(token);
            return true;

        } catch (Exception e) {
            System.out.println("========== JWT VALIDATION ERROR ==========");
            System.out.println("TYPE = " + e.getClass().getName());
            System.out.println("MESSAGE = " + e.getMessage());
            e.printStackTrace();

            return false;
        }
    }


    // ======================================================
    // GIẢI MÃ TOKEN
    // ======================================================

    private Claims parseToken(
            String token
    ) {

        return Jwts.parser()

                .verifyWith(key)

                .build()

                .parseSignedClaims(token)

                .getPayload();
    }
}
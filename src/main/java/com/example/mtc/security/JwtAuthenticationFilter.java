package com.example.mtc.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        String method = request.getMethod();

        // Bỏ qua filter JWT cho API lấy info public của user (GET /api/users/1, GET /api/users/2,...)
        // Trừ đường dẫn lấy info bản thân (/api/users/me)
        if ("GET".equalsIgnoreCase(method) && path.startsWith("/api/users/") && !path.contains("/users/me")) {
            return true;
        }

        // Bỏ qua cho auth và books
        if (path.startsWith("/api/auth/") || path.startsWith("/api/books/")) {
            return true;
        }

        return false;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        // Nếu token không hợp lệ, không block 401 cứng ở đây mà cho đi tiếp
        // để SecurityConfig quyết định permitAll hay không
        if (!jwtService.isTokenValid(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        Long userId = jwtService.getUserId(token);
        String email = jwtService.getEmail(token);

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                        userId,
                        null,
                        java.util.Collections.emptyList()
                );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        request.setAttribute("userId", userId);
        request.setAttribute("email", email);

        filterChain.doFilter(request, response);
    }
}
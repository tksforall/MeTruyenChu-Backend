package com.example.mtc.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserService {

    public Long getCurrentUserId() {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        if (authentication == null ||
                !authentication.isAuthenticated()) {

            throw new RuntimeException(
                    "Người dùng chưa đăng nhập"
            );
        }

        Object principal =
                authentication.getPrincipal();

        if (!(principal instanceof Long)) {

            throw new RuntimeException(
                    "Không xác định được user"
            );
        }

        return (Long) principal;
    }
}
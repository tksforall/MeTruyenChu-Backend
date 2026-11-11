package com.example.mtc.controller;

import com.example.mtc.dto.ChangePasswordRequest;
import com.example.mtc.dto.UpdateProfileRequest;
import com.example.mtc.dto.UserResponse;
import com.example.mtc.entity.User;
import com.example.mtc.security.CurrentUserService;
import com.example.mtc.service.UserService;
import org.springframework.web.bind.annotation.*;
import com.example.mtc.dto.ChangeEmailRequest;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final CurrentUserService currentUserService;

    public UserController(
            UserService userService,
            CurrentUserService currentUserService
    ) {
        this.userService = userService;
        this.currentUserService = currentUserService;
    }

    // ======================================================
    // LẤY USER ĐANG ĐĂNG NHẬP
    // ======================================================

    @GetMapping("/me")
    public UserResponse getCurrentUser() {

        Long userId =
                currentUserService.getCurrentUserId();

        User user =
                userService.getById(userId);

        return new UserResponse(
                user.getUserId(),
                user.getUserName(),
                user.getEmail(),
                user.getAvatarUrl(),
                user.getRecommendationCount(),
                user.getStatus(),
                user.getAuthProvider(),
                user.getEmailVerified(),
                user.getPhone(),
                user.getGender()
        );
    }

    // ======================================================
    // CẬP NHẬT THÔNG TIN USER ĐANG ĐĂNG NHẬP
    // ======================================================

    @PutMapping("/me")
    public UserResponse updateCurrentUser(
            @RequestBody UpdateProfileRequest request
    ) {

        Long userId =
                currentUserService.getCurrentUserId();

        User user =
                userService.updateProfile(
                        userId,
                        request.getPhone(),
                        request.getGender()
                );

        return new UserResponse(
                user.getUserId(),
                user.getUserName(),
                user.getEmail(),
                user.getAvatarUrl(),
                user.getRecommendationCount(),
                user.getStatus(),
                user.getAuthProvider(),
                user.getEmailVerified(),
                user.getPhone(),
                user.getGender()
        );
    }

    // ======================================================
    // ĐỔI MẬT KHẨU
    // ======================================================

    @PutMapping("/me/password")
    public String changePassword(
            @RequestBody ChangePasswordRequest request
    ) {

        Long userId =
                currentUserService.getCurrentUserId();

        userService.changePassword(
                userId,
                request.getCurrentPassword(),
                request.getNewPassword(),
                request.getConfirmPassword()
        );

        return "Đổi mật khẩu thành công";
    }

    // ======================================================
    // ĐỔI EMAIL
    // ======================================================

    @PutMapping("/me/email")
    public String changeEmail(
            @RequestBody ChangeEmailRequest request
    ) {

        System.out.println("========== CHANGE EMAIL ==========");

        System.out.println(
                "currentPassword = [" +
                        request.getCurrentPassword() +
                        "]"
        );

        System.out.println(
                "newEmail = [" +
                        request.getNewEmail() +
                        "]"
        );

        Long userId =
                currentUserService.getCurrentUserId();

        System.out.println(
                "userId = " + userId
        );

        userService.changeEmail(
                userId,
                request.getCurrentPassword(),
                request.getNewEmail()
        );

        System.out.println(
                "CHANGE EMAIL SUCCESS"
        );

        return "Đổi email thành công";
    }

}
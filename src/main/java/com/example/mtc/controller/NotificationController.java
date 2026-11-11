package com.example.mtc.controller;

import com.example.mtc.entity.Notification;
import com.example.mtc.security.CurrentUserService;
import com.example.mtc.service.NotificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService service;
    private final CurrentUserService currentUserService;

    public NotificationController(
            NotificationService service,
            CurrentUserService currentUserService
    ) {
        this.service = service;
        this.currentUserService = currentUserService;
    }


    // ======================================================
    // TẤT CẢ THÔNG BÁO CỦA USER
    // ======================================================

    @GetMapping("/user/{userId}")
    public List<Notification> getByUser(
            @PathVariable Long userId
    ) {

        return service.getByUserId(userId);
    }


    // ======================================================
    // TẤT CẢ THÔNG BÁO CỦA USER ĐANG ĐĂNG NHẬP
    // ======================================================

    @GetMapping("/me")
    public List<Notification> getMyNotifications() {

        Long userId =
                currentUserService.getCurrentUserId();

        return service.getByUserId(userId);
    }


    // ======================================================
    // THÔNG BÁO CHƯA ĐỌC CỦA USER ĐANG ĐĂNG NHẬP
    // ======================================================

    @GetMapping("/me/unread")
    public List<Notification> getMyUnreadNotifications() {

        Long userId =
                currentUserService.getCurrentUserId();

        return service.getUnread(userId);
    }


    // ======================================================
    // THÔNG BÁO CHƯA ĐỌC - API CŨ
    // ======================================================

    @GetMapping("/user/{userId}/unread")
    public List<Notification> getUnread(
            @PathVariable Long userId
    ) {

        return service.getUnread(userId);
    }


    // ======================================================
    // THÊM THÔNG BÁO
    // ======================================================

    @PostMapping
    public Notification create(
            @RequestBody Notification notification
    ) {

        return service.create(notification);
    }


    // ======================================================
    // ĐÁNH DẤU ĐÃ ĐỌC
    // ======================================================

    @PutMapping("/{notificationId}/read")
    public Notification markAsRead(
            @PathVariable Long notificationId
    ) {

        return service.markAsRead(notificationId);
    }


    // ======================================================
    // XÓA THÔNG BÁO
    // ======================================================

    @DeleteMapping("/{notificationId}")
    public void delete(
            @PathVariable Long notificationId
    ) {

        service.delete(notificationId);
    }
}
package com.example.mtc.service;

import com.example.mtc.entity.Notification;
import com.example.mtc.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository repository;

    public NotificationService(
            NotificationRepository repository
    ) {
        this.repository = repository;
    }


    // ======================================================
    // LẤY TẤT CẢ THÔNG BÁO CỦA USER
    // ======================================================

    public List<Notification> getByUserId(Long userId) {
        return repository.findByUserIdOrderByCreatedAtDesc(userId);
    }


    // ======================================================
    // LẤY THÔNG BÁO CHƯA ĐỌC
    // ======================================================

    public List<Notification> getUnread(
            Long userId
    ) {

        return repository.findByUserIdAndIsRead(
                userId,
                0
        );
    }


    // ======================================================
    // THÊM THÔNG BÁO
    // ======================================================

    public Notification create(
            Notification notification
    ) {

        if (notification.getIsRead() == null) {

            notification.setIsRead(0);
        }

        if (notification.getCreatedAt() == null) {

            notification.setCreatedAt(
                    LocalDateTime.now()
            );
        }

        return repository.save(notification);
    }


    // ======================================================
    // ĐÁNH DẤU ĐÃ ĐỌC
    // ======================================================

    public Notification markAsRead(
            Long notificationId
    ) {

        Notification notification =
                repository.findById(notificationId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Không tìm thấy notification"
                                )
                        );

        notification.setIsRead(1);

        return repository.save(notification);
    }


    // ======================================================
    // XÓA THÔNG BÁO
    // ======================================================

    public void delete(
            Long notificationId
    ) {

        repository.deleteById(notificationId);
    }
}
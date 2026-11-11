package com.example.mtc.repository;

import com.example.mtc.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository
        extends JpaRepository<Book, Long>, BookFilterRepository {

    // ==================================================
    // MỚI NHẤT
    // Truyện được cập nhật gần đây nhất
    // UPDATED_AT giảm dần
    // ==================================================
    List<Book> findAllByOrderByUpdatedAtDesc();


    // ==================================================
    // MỚI ĐĂNG
    // Truyện được tạo gần đây nhất
    // CREATED_AT giảm dần
    // ==================================================
    List<Book> findTop6ByOrderByCreatedAtDesc();


    // ==================================================
    // MỚI HOÀN THÀNH
    // STATUS = COMPLETED
    // Sắp xếp theo thời gian cập nhật
    // ==================================================
    List<Book> findTop6ByStatusIgnoreCaseOrderByUpdatedAtDesc(String status);

    // ==================================================
    // ĐỀ CỬ - HOME
    // Chỉ lấy 6 truyện nhiều lượt đề cử nhất
    // ==================================================
    List<Book> findTop6ByOrderByRecommendationCountDesc();


    // ==================================================
    // ĐỀ CỬ - TRANG ĐỀ CỬ
    // Lấy toàn bộ truyện, sắp xếp theo lượt đề cử
    // ==================================================
    List<Book> findAllByOrderByRecommendationCountDesc();


    // ==================================================
    // MỚI ĐĂNG - TRANG MỚI ĐĂNG
    // Lấy toàn bộ truyện
    // ==================================================
    List<Book> findAllByOrderByCreatedAtDesc();


    // ==================================================
    // MỚI HOÀN THÀNH - TRANG MỚI HOÀN THÀNH
    // Lấy toàn bộ truyện đã hoàn thành
    // ==================================================
    List<Book> findAllByStatusOrderByUpdatedAtDesc(String status);


    // ==================================================
    // TÌM KIẾM TRUYỆN
    // Tìm theo tên truyện hoặc tác giả
    // Không phân biệt hoa thường
    // ==================================================

    List<Book> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(
            String title,
            String author
    );

    // Filter


}


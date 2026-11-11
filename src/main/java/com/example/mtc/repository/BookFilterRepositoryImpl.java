package com.example.mtc.repository;

import com.example.mtc.entity.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class BookFilterRepositoryImpl implements BookFilterRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Book> filterBooks(
            String sort,
            String status,
            String chapterRange,
            String publishDate,
            Set<String> genres,
            Set<String> personalities,
            Set<String> worlds,
            Set<String> tags
    ) {

        // ==================================================
        // BASE QUERY
        // ==================================================

        // KHÔNG dùng DISTINCT vì Book có DESCRIPTION là CLOB
        StringBuilder jpql = new StringBuilder(
                "SELECT b FROM Book b "
        );

        List<String> conditions = new ArrayList<>();


        // ==================================================
        // STATUS
        // ==================================================

        if (status != null && !status.isBlank()) {

            conditions.add(
                    "LOWER(b.status) = LOWER(:status)"
            );
        }


        // ==================================================
        // SỐ CHƯƠNG
        // ==================================================

        if (chapterRange != null && !chapterRange.isBlank()) {

            switch (chapterRange) {

                case "< 300":

                    conditions.add(
                            "b.chapterCount < 300"
                    );

                    break;


                case "300–600":

                    conditions.add(
                            "b.chapterCount BETWEEN 300 AND 600"
                    );

                    break;


                case "600–1000":

                    conditions.add(
                            "b.chapterCount BETWEEN 600 AND 1000"
                    );

                    break;


                case "> 1000":

                    conditions.add(
                            "b.chapterCount > 1000"
                    );

                    break;
            }
        }


        // ==================================================
        // NGÀY XUẤT BẢN
        // ==================================================

        if (publishDate != null && !publishDate.isBlank()) {

            switch (publishDate) {

                case "Trong 1 tuần":

                    conditions.add(
                            "b.createdAt >= CURRENT_TIMESTAMP - 7"
                    );

                    break;


                case "Trong 1 tháng":

                    conditions.add(
                            "b.createdAt >= CURRENT_TIMESTAMP - 30"
                    );

                    break;


                case "Trong 3 tháng":

                    conditions.add(
                            "b.createdAt >= CURRENT_TIMESTAMP - 90"
                    );

                    break;


                case "Trong 1 năm":

                    conditions.add(
                            "b.createdAt >= CURRENT_TIMESTAMP - 365"
                    );

                    break;
            }
        }


        // ==================================================
        // THỂ LOẠI
        // ==================================================

        if (genres != null && !genres.isEmpty()) {

            conditions.add(
                    "EXISTS (" +
                            "SELECT 1 FROM BookGenre bg " +
                            "WHERE bg.id.bookId = b.bookId " +
                            "AND bg.id.genreId IN " +
                            "(SELECT g.genreId FROM Genre g " +
                            "WHERE g.genreName IN :genres)" +
                            ")"
            );
        }


        // ==================================================
        // TÍNH CÁCH NHÂN VẬT
        // ==================================================

        if (personalities != null && !personalities.isEmpty()) {

            conditions.add(
                    "EXISTS (" +
                            "SELECT 1 FROM BookPersonality bp " +
                            "WHERE bp.id.bookId = b.bookId " +
                            "AND bp.id.personalityId IN " +
                            "(SELECT p.personalityId " +
                            "FROM Personality p " +
                            "WHERE p.personalityName IN :personalities)" +
                            ")"
            );
        }


        // ==================================================
        // BỐI CẢNH THẾ GIỚI
        // ==================================================

        if (worlds != null && !worlds.isEmpty()) {

            conditions.add(
                    "EXISTS (" +
                            "SELECT 1 FROM BookWorld bw " +
                            "WHERE bw.id.bookId = b.bookId " +
                            "AND bw.id.worldId IN " +
                            "(SELECT w.worldId " +
                            "FROM WorldSetting w " +
                            "WHERE w.worldName IN :worlds)" +
                            ")"
            );
        }


        // ==================================================
        // LƯU PHÁI / TAG
        // ==================================================

        if (tags != null && !tags.isEmpty()) {

            conditions.add(
                    "EXISTS (" +
                            "SELECT 1 FROM BookTag bt " +
                            "WHERE bt.id.bookId = b.bookId " +
                            "AND bt.id.tagId IN " +
                            "(SELECT t.tagId " +
                            "FROM Tag t " +
                            "WHERE t.tagName IN :tags)" +
                            ")"
            );
        }


        // ==================================================
        // WHERE
        // ==================================================

        if (!conditions.isEmpty()) {

            jpql.append(" WHERE ");

            jpql.append(
                    String.join(" AND ", conditions)
            );
        }


        // ==================================================
        // ORDER BY
        // ==================================================

        jpql.append(" ORDER BY ");

        if (sort == null || sort.isBlank()) {

            jpql.append(
                    "b.updatedAt DESC"
            );

        } else {

            switch (sort) {

                case "Mới đăng":

                    jpql.append(
                            "b.createdAt DESC"
                    );

                    break;


                case "Lượt đọc":

                    jpql.append(
                            "b.viewCount DESC"
                    );

                    break;


                case "Lượt đọc tuần":

                    jpql.append(
                            "b.weeklyViewCount DESC"
                    );

                    break;


                case "Lượt đề cử":

                    jpql.append(
                            "b.recommendationCount DESC"
                    );

                    break;


                case "Lượt đề cử tuần":

                    jpql.append(
                            "b.weeklyRecommendationCount DESC"
                    );

                    break;


                case "Lượt bình luận":

                    jpql.append(
                            "b.commentCount DESC"
                    );

                    break;


                case "Lượt bình luận tuần":

                    jpql.append(
                            "b.commentCount DESC"
                    );

                    break;


                case "Lượt đánh dấu":

                    jpql.append(
                            "b.bookmarkCount DESC"
                    );

                    break;


                case "Lượt đánh giá":

                    jpql.append(
                            "b.ratingCount DESC"
                    );

                    break;


                case "Điểm đánh giá":

                    jpql.append(
                            "b.ratingScore DESC"
                    );

                    break;


                case "Số chương":

                    jpql.append(
                            "b.chapterCount DESC"
                    );

                    break;


                case "Tên truyện":

                    jpql.append(
                            "b.title ASC"
                    );

                    break;


                case "Mới lên chương":

                default:

                    jpql.append(
                            "b.updatedAt DESC"
                    );

                    break;
            }
        }


        // ==================================================
        // CREATE QUERY
        // ==================================================

        TypedQuery<Book> query =
                entityManager.createQuery(
                        jpql.toString(),
                        Book.class
                );


        // ==================================================
        // SET PARAMETERS
        // ==================================================

        if (status != null && !status.isBlank()) {

            query.setParameter(
                    "status",
                    status
            );
        }


        if (genres != null && !genres.isEmpty()) {

            query.setParameter(
                    "genres",
                    genres
            );
        }


        if (personalities != null && !personalities.isEmpty()) {

            query.setParameter(
                    "personalities",
                    personalities
            );
        }


        if (worlds != null && !worlds.isEmpty()) {

            query.setParameter(
                    "worlds",
                    worlds
            );
        }


        if (tags != null && !tags.isEmpty()) {

            query.setParameter(
                    "tags",
                    tags
            );
        }


        // ==================================================
        // RETURN RESULT
        // ==================================================

        return query.getResultList();
    }
}
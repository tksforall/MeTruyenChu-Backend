package com.example.mtc.service;

import com.example.mtc.dto.AiSearchResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    @Value("${gemini.model:gemini-3.5-flash}")
    private String model;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public AiService() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    public AiSearchResponse searchByDescription(String description) {

        // ==========================================================
        // CLEAN API KEY + MODEL
        // ==========================================================

        String cleanApiKey =
                apiKey == null
                        ? ""
                        : apiKey.trim();

        String cleanModel =
                model == null
                        ? "gemini-3.5-flash"
                        : model.trim();

        System.out.println("========== GEMINI REQUEST ==========");
        System.out.println("MODEL: " + cleanModel);

        System.out.println(
                "API KEY EXISTS: "
                        + !cleanApiKey.isBlank()
        );

        System.out.println(
                "API KEY LENGTH: "
                        + (apiKey == null ? 0 : apiKey.length())
        );

        if (apiKey != null && apiKey.length() >= 16) {
            System.out.println(
                    "KEY FIRST 8: "
                            + apiKey.substring(0, 8)
            );

            System.out.println(
                    "KEY LAST 8: "
                            + apiKey.substring(apiKey.length() - 8)
            );
        }

        // DEBUG - chỉ hiện đầu/cuối key
        if (cleanApiKey.length() >= 16) {

            System.out.println(
                    "KEY FIRST 8: "
                            + cleanApiKey.substring(0, 8)
            );

            System.out.println(
                    "KEY LAST 8: "
                            + cleanApiKey.substring(
                            cleanApiKey.length() - 8
                    )
            );
        }

        try {

            // ======================================================
            // GEMINI URL
            // ======================================================

            String url =
                    "https://generativelanguage.googleapis.com/v1beta/models/"
                            + model
                            + ":generateContent";


            // ======================================================
            // PROMPT
            // ======================================================

            String prompt = """
                    Bạn là AI hỗ trợ tìm kiếm truyện cho ứng dụng đọc truyện
                    "Mê Truyện Chữ".

                    Người dùng mô tả loại truyện mà họ muốn tìm.

                    Hãy phân tích mô tả và trả về ĐÚNG JSON theo cấu trúc:

                    {
                      "summary": "Mô tả ngắn gọn về kiểu truyện người dùng đang tìm",
                      "keywords": [
                        "từ khóa 1",
                        "từ khóa 2",
                        "từ khóa 3"
                      ],
                      "genres": [
                        "thể loại 1",
                        "thể loại 2"
                      ]
                    }

                    Quy tắc:

                    1. keywords phải là các từ hoặc cụm từ ngắn,
                       hữu ích để người dùng tìm truyện.

                    2. Ưu tiên các đặc điểm như:
                       - cốt truyện
                       - nhân vật
                       - năng lực
                       - bối cảnh
                       - hệ thống
                       - xuyên không
                       - tu luyện
                       - thế giới
                       - kiểu phát triển nhân vật

                    3. genres chỉ đưa ra những thể loại phù hợp
                       với mô tả.

                    4. Không được đưa ra giải thích bên ngoài JSON.

                    5. Không được bịa tên truyện cụ thể nếu người dùng
                       không cung cấp tên truyện.

                    6. keywords nên có khoảng 5-10 từ khóa.

                    Mô tả của người dùng:

                    %s
                    """.formatted(description);


            // ======================================================
            // TEXT PART
            // ======================================================

            Map<String, Object> textPart =
                    new HashMap<>();

            textPart.put(
                    "text",
                    prompt
            );


            // ======================================================
            // PART WRAPPER
            // ======================================================

            Map<String, Object> partWrapper =
                    new HashMap<>();

            partWrapper.put(
                    "parts",
                    List.of(textPart)
            );


            // ======================================================
            // GENERATION CONFIG
            // ======================================================

            Map<String, Object> generationConfig =
                    new HashMap<>();

            generationConfig.put(
                    "temperature",
                    0.3
            );

            generationConfig.put(
                    "responseMimeType",
                    "application/json"
            );


            // ======================================================
            // REQUEST BODY
            // ======================================================

            Map<String, Object> requestBodyMap =
                    new HashMap<>();

            requestBodyMap.put(
                    "contents",
                    List.of(partWrapper)
            );

            requestBodyMap.put(
                    "generationConfig",
                    generationConfig
            );


            // ======================================================
            // CONVERT TO JSON
            // ======================================================

            String requestBody =
                    objectMapper.writeValueAsString(
                            requestBodyMap
                    );


            System.out.println(
                    "========== GEMINI JSON =========="
            );

            System.out.println(requestBody);

            System.out.println(
                    "================================="
            );


            // ======================================================
            // HTTP HEADERS
            // ======================================================

            HttpHeaders headers = new HttpHeaders();

            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("x-goog-api-key", apiKey);

            // QUAN TRỌNG:
            // Gemini API key được gửi bằng header này.
            // KHÔNG dùng Authorization: Bearer ở đây.

            headers.set(
                    "x-goog-api-key",
                    cleanApiKey
            );


            // ======================================================
            // HTTP ENTITY
            // ======================================================

            HttpEntity<String> entity =
                    new HttpEntity<>(
                            requestBody,
                            headers
                    );


            // ======================================================
            // CALL GEMINI
            // ======================================================

            System.out.println(
                    "========== CALLING GEMINI =========="
            );

            ResponseEntity<String> response =
                    restTemplate.exchange(
                            url,
                            HttpMethod.POST,
                            entity,
                            String.class
                    );


            // ======================================================
            // RESPONSE
            // ======================================================

            System.out.println(
                    "========== GEMINI RESPONSE =========="
            );

            System.out.println(
                    "STATUS: "
                            + response.getStatusCode()
            );

            System.out.println(
                    "BODY:"
            );

            System.out.println(
                    response.getBody()
            );

            System.out.println(
                    "====================================="
            );


            // ======================================================
            // PARSE
            // ======================================================

            return parseGeminiResponse(
                    response.getBody()
            );

        } catch (HttpStatusCodeException e) {

            // ======================================================
            // HTTP ERROR
            // ======================================================

            System.out.println(
                    "========== GEMINI HTTP ERROR =========="
            );

            System.out.println(
                    "STATUS: "
                            + e.getStatusCode()
            );

            System.out.println(
                    "STATUS CODE: "
                            + e.getStatusCode().value()
            );

            System.out.println(
                    "RESPONSE BODY:"
            );

            System.out.println(
                    e.getResponseBodyAsString()
            );

            System.out.println(
                    "========================================"
            );

            throw new RuntimeException(
                    "Gemini API lỗi "
                            + e.getStatusCode().value()
                            + ": "
                            + e.getResponseBodyAsString(),
                    e
            );

        } catch (Exception e) {

            // ======================================================
            // GENERAL ERROR
            // ======================================================

            System.out.println(
                    "========== GEMINI ERROR =========="
            );

            System.out.println(
                    "ERROR TYPE: "
                            + e.getClass().getName()
            );

            System.out.println(
                    "ERROR: "
                            + e.getMessage()
            );

            System.out.println(
                    "================================="
            );

            throw new RuntimeException(
                    "Không thể gọi Gemini API: "
                            + e.getMessage(),
                    e
            );
        }
    }


    // ==========================================================
    // PARSE GEMINI RESPONSE
    // ==========================================================

    private AiSearchResponse parseGeminiResponse(
            String responseBody
    ) {

        try {

            JsonNode root =
                    objectMapper.readTree(
                            responseBody
                    );


            // ======================================================
            // CANDIDATES
            // ======================================================

            JsonNode candidates =
                    root.path("candidates");


            if (!candidates.isArray()
                    || candidates.isEmpty()) {

                throw new RuntimeException(
                        "Gemini không trả về kết quả"
                );
            }


            // ======================================================
            // CONTENT
            // ======================================================

            JsonNode parts =
                    candidates
                            .get(0)
                            .path("content")
                            .path("parts");


            if (!parts.isArray()
                    || parts.isEmpty()) {

                throw new RuntimeException(
                        "Gemini không trả về nội dung"
                );
            }


            // ======================================================
            // TEXT
            // ======================================================

            String text =
                    parts
                            .get(0)
                            .path("text")
                            .asText();


            System.out.println(
                    "========== GEMINI TEXT =========="
            );

            System.out.println(text);

            System.out.println(
                    "================================="
            );


            // ======================================================
            // PARSE AI JSON
            // ======================================================

            JsonNode aiResult =
                    objectMapper.readTree(
                            text
                    );


            // ======================================================
            // SUMMARY
            // ======================================================

            String summary =
                    aiResult
                            .path("summary")
                            .asText("");


            // ======================================================
            // KEYWORDS
            // ======================================================

            List<String> keywords =
                    new ArrayList<>();

            JsonNode keywordNode =
                    aiResult.path("keywords");


            if (keywordNode.isArray()) {

                for (JsonNode node :
                        keywordNode) {

                    String keyword =
                            node.asText();

                    if (!keyword.isBlank()) {

                        keywords.add(
                                keyword
                        );
                    }
                }
            }


            // ======================================================
            // GENRES
            // ======================================================

            List<String> genres =
                    new ArrayList<>();

            JsonNode genreNode =
                    aiResult.path("genres");


            if (genreNode.isArray()) {

                for (JsonNode node :
                        genreNode) {

                    String genre =
                            node.asText();

                    if (!genre.isBlank()) {

                        genres.add(
                                genre
                        );
                    }
                }
            }


            // ======================================================
            // RETURN
            // ======================================================

            return new AiSearchResponse(
                    summary,
                    keywords,
                    genres
            );


        } catch (Exception e) {

            throw new RuntimeException(
                    "Không thể đọc kết quả từ Gemini: "
                            + e.getMessage(),
                    e
            );
        }
    }
}
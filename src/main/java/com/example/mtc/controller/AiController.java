package com.example.mtc.controller;

import com.example.mtc.dto.AiSearchRequest;
import com.example.mtc.dto.AiSearchResponse;
import com.example.mtc.service.AiService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
public class AiController {

    private final AiService aiService;

    public AiController(AiService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/search")
    public ResponseEntity<AiSearchResponse> searchByDescription(
            @Valid @RequestBody AiSearchRequest request
    ) {

        AiSearchResponse response =
                aiService.searchByDescription(
                        request.getDescription()
                );

        return ResponseEntity.ok(response);
    }
}
package com.example.mtc.controller;

import com.example.mtc.entity.Personality;
import com.example.mtc.service.PersonalityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personalities")
public class PersonalityController {

    private final PersonalityService personalityService;

    public PersonalityController(
            PersonalityService personalityService
    ) {
        this.personalityService = personalityService;
    }

    // GET /api/personalities
    @GetMapping
    public List<Personality> getAllPersonalities() {
        return personalityService.getAllPersonalities();
    }

    // GET /api/personalities/{id}
    @GetMapping("/{id}")
    public Personality getPersonalityById(
            @PathVariable Long id
    ) {
        return personalityService.getPersonalityById(id);
    }
}
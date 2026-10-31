package com.practice.daily_routine_backend.controller;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practice.daily_routine_backend.entity.DailyReview;
import com.practice.daily_routine_backend.repository.DailyReviewRepository;

@RestController 
@CrossOrigin(origins = "http://localhost:3000")
public class DailyReviewController {
    
    private final DailyReviewRepository dailyReviewRepository;
    public DailyReviewController(DailyReviewRepository dailyReviewRepository){
        this.dailyReviewRepository = dailyReviewRepository;
    }

    @GetMapping("/api/review")
    public DailyReview getReview(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
    return dailyReviewRepository.findByReviewDate(date).orElse(new DailyReview());
    }

    @PutMapping("/api/review")
    public DailyReview saveReview(@RequestBody DailyReview review){
        DailyReview target = dailyReviewRepository.findByReviewDate(review.getReviewDate()).orElse(new DailyReview());
        target.setReviewDate(review.getReviewDate());
        target.setContent(review.getContent());
        return dailyReviewRepository.save(target);
    }
}


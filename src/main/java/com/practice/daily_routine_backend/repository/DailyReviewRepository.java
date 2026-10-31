package com.practice.daily_routine_backend.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import com.practice.daily_routine_backend.entity.DailyReview;
import com.practice.daily_routine_backend.entity.DaySticker;

public interface DailyReviewRepository extends JpaRepository<DailyReview, Long>{
    Optional<DailyReview> findByReviewDate(LocalDate reviewDate);

    ResponseEntity<?> save(DaySticker sticker);
}
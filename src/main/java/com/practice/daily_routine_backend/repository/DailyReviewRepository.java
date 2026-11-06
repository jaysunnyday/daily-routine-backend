package com.practice.daily_routine_backend.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.daily_routine_backend.entity.DailyReview;

public interface DailyReviewRepository extends JpaRepository<DailyReview, Long> {
    Optional<DailyReview> findByReviewDate(LocalDate reviewDate);
}
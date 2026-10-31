package com.practice.daily_routine_backend.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.daily_routine_backend.entity.DaySticker;

public interface DayStickerRepository extends JpaRepository<DaySticker, Long> {

List<DaySticker> findByStickerDateBetween(LocalDate startDay, LocalDate endDay);
Long countByStickerDate(LocalDate stickerDate);
}

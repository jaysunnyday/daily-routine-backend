package com.practice.daily_routine_backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.daily_routine_backend.entity.TimeBlock;
import java.util.List;
import java.time.LocalDate;


public interface TimeBlockRepository extends JpaRepository<TimeBlock, Long>{
    List<TimeBlock> findByBlockDateOrderByStartMinute(LocalDate blockDate);
}

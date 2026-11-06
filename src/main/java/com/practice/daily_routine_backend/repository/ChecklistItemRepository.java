package com.practice.daily_routine_backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.daily_routine_backend.entity.ChecklistItem;

import java.time.LocalDate;
import java.util.List;

public interface ChecklistItemRepository extends JpaRepository<ChecklistItem, Long> {
    List<ChecklistItem> findByChecklistDate(LocalDate checklistDate);

}

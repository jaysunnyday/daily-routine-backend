package com.practice.daily_routine_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.daily_routine_backend.entity.ChecklistItem;

public interface ChecklistItemRepository extends JpaRepository<ChecklistItem, Long> {

}

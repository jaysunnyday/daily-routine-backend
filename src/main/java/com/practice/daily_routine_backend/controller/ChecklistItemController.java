package com.practice.daily_routine_backend.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.daily_routine_backend.entity.ChecklistItem;
import com.practice.daily_routine_backend.repository.ChecklistItemRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ChecklistItemController {
    private final ChecklistItemRepository checklistItemRepository;

    public ChecklistItemController(ChecklistItemRepository checklistItemRepository) {
        this.checklistItemRepository = checklistItemRepository;
    }

    @GetMapping("/api/checklist")
    public List<ChecklistItem> getChecklistItem(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checklistDate) {
        return checklistItemRepository.findByChecklistDate(checklistDate);
    }

    @PostMapping("/api/checklist")
    public ChecklistItem createChecklistItem(@RequestBody ChecklistItem item) {
        return checklistItemRepository.save(item);
    }

    @PatchMapping("/api/checklist/{id}")
    public ChecklistItem toggleChecklistItem(@PathVariable Long id, @RequestBody ChecklistItem item) {
        ChecklistItem target = checklistItemRepository.findById(id).orElseThrow();
        target.setChecked(item.getChecked());
        return checklistItemRepository.save(target);
    }

    @PatchMapping("/api/checklist/{id}/content")
    public ChecklistItem updateChecklistItem(@PathVariable Long id, @RequestBody ChecklistItem item) {
        ChecklistItem target = checklistItemRepository.findById(id).orElseThrow();
        target.setContent(item.getContent());
        return checklistItemRepository.save(target);
    }

    @DeleteMapping("/api/checklist/{id}")
    public void delete(@PathVariable Long id) {
        checklistItemRepository.deleteById(id);
    }
}

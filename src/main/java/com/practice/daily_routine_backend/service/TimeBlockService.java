package com.practice.daily_routine_backend.service;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.practice.daily_routine_backend.entity.ChecklistItem;
import com.practice.daily_routine_backend.entity.TimeBlock;
import com.practice.daily_routine_backend.repository.ChecklistItemRepository;

@Service
public class TimeBlockService {

    private final ChecklistItemRepository checklistItemRepository;

    public TimeBlockService(ChecklistItemRepository checklistItemRepository) {
        this.checklistItemRepository = checklistItemRepository;
    }

    public enum AUTO_CATEGORIES {
        日本語の勉強, 開発の勉強, 皿洗い, トイレ掃除, 部屋の掃除, 片付け, 洗濯
    }

    public void createChecklistItem(TimeBlock timeBlock) {

        LocalDate blockDate = timeBlock.getBlockDate();
        int startMinute = timeBlock.getStartMinute();
        int endMinute = timeBlock.getEndMinute();
        String category = timeBlock.getCategory();

        if (Arrays.stream(AUTO_CATEGORIES.values())
                .anyMatch(e -> e.name().equals(category))) {
            ChecklistItem checklistItem = new ChecklistItem();
            String starttime = String.format("%d:%02d", startMinute / 60, startMinute % 60);
            String endtime = String.format("%d:%02d", endMinute / 60, endMinute % 60);
            checklistItem.setContent(starttime + "～" + endtime);
            checklistItem.setCategory(category);
            checklistItem.setChecklistDate(blockDate);
            checklistItem.setChecked(false);

            checklistItemRepository.save(checklistItem);
        }
    }

}

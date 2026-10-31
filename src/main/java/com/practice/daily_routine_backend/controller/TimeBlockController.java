package com.practice.daily_routine_backend.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practice.daily_routine_backend.repository.TimeBlockRepository;
import com.practice.daily_routine_backend.entity.TimeBlock;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class TimeBlockController {
    private final TimeBlockRepository timeBlockRepository;
    public TimeBlockController(TimeBlockRepository timeBlockRepository){
        this.timeBlockRepository = timeBlockRepository;
    }
    private static final int MINUTES_PER_DAY = 1440;
    private static final int UNIT_MINUTES = 15;

    @GetMapping("/api/timeblock")
    public List<TimeBlock> getTimeBlock(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate blockDate){
        return timeBlockRepository.findByBlockDateOrderByStartMinute(blockDate);
    }

    @PostMapping("/api/timeblock")
    public ResponseEntity<?> addTimeBlock(@RequestBody TimeBlock timeBlock){

        LocalDate blockDate = timeBlock.getBlockDate();
        int startMinute = timeBlock.getStartMinute();
        int endMinute = timeBlock.getEndMinute();
        String category = timeBlock.getCategory();

        if(category ==null || category.isBlank()){
            return ResponseEntity.badRequest().body("カテゴリを選んでください");
        }

        if(blockDate == null ){
            return ResponseEntity.badRequest().body("日付がありません");
        }

        if(startMinute < 0 || endMinute > MINUTES_PER_DAY || startMinute >= endMinute){
           return ResponseEntity.badRequest().body("時間の範囲が正しくありません");
        }

        if(startMinute % UNIT_MINUTES != 0 || endMinute % UNIT_MINUTES != 0){
            return ResponseEntity.badRequest().body("15分単位で入力してください");
        }

        List<TimeBlock> existing = timeBlockRepository.findByBlockDateOrderByStartMinute(blockDate);

        for(TimeBlock b: existing){
            int existStart = b.getStartMinute();
            int existEnd = b.getEndMinute();
            if(startMinute < existEnd && endMinute > existStart){
                return ResponseEntity.badRequest().body("他の時間と重なっています");
            }
        }

        timeBlock.setId(null);         

        return ResponseEntity.ok(timeBlockRepository.save(timeBlock));
    }

    @DeleteMapping("/api/timeblock/{id}")
    public void delete(@PathVariable Long id) {
        timeBlockRepository.deleteById(id);
    }
}

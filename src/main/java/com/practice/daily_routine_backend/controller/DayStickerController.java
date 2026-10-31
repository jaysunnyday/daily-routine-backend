package com.practice.daily_routine_backend.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.daily_routine_backend.entity.DaySticker;
import com.practice.daily_routine_backend.repository.DayStickerRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class DayStickerController {
    private final DayStickerRepository dayStickerRepository;

    public DayStickerController(DayStickerRepository dayStickerRepository){
        this.dayStickerRepository = dayStickerRepository;
    }

    private static final int MAX_PER_DAY=2;

    @GetMapping("/api/sticker")
    public List<DaySticker> getStickers(@RequestParam int year, @RequestParam int month) {
        LocalDate startDay = LocalDate.of(year, month, 1);
        LocalDate endDay = startDay.plusMonths(1).minusDays(1);
        return dayStickerRepository.findByStickerDateBetween(startDay, endDay);
    }

    @PostMapping("/api/sticker")
    public ResponseEntity<?> addSticker(@RequestBody DaySticker sticker){

        if(sticker.getStickerKey() == null || sticker.getStickerKey().isBlank()){
           return ResponseEntity.badRequest().body("スタンプを選んでください");
        }

        if(sticker.getStickerDate() == null){
            return ResponseEntity.badRequest().body("日付がありません");
        }

        if(dayStickerRepository.countByStickerDate(sticker.getStickerDate())>= MAX_PER_DAY){
            return ResponseEntity.badRequest().body("この日のスタンプは上限です");
        }
        
        sticker.setId(null);

        return ResponseEntity.ok(dayStickerRepository.save(sticker));
    }

    @DeleteMapping("/api/sticker/{id}")
    public void delete(@PathVariable Long id) {
        dayStickerRepository.deleteById(id);
    }
}

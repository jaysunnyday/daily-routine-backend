package com.practice.daily_routine_backend.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DaySticker {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate stickerDate;
    private String stickerKey;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public LocalDate getStickerDate(){
        return stickerDate;
    }

    public void setStickerDate(LocalDate stickerDate){
        this.stickerDate = stickerDate;
    }

    public String getStickerKey(){
        return stickerKey;
    }

    public void setStickerKey(String stickerKey){
        this.stickerKey = stickerKey;
    }
}

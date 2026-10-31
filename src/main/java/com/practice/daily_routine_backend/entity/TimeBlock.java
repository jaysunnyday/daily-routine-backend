package com.practice.daily_routine_backend.entity;
import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity 
public class TimeBlock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate blockDate;
    private int startMinute;
    private int endMinute;
    private String category;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public LocalDate getBlockDate(){
        return blockDate;
    }

    public void setBlockDate(LocalDate blockDate){
        this.blockDate = blockDate;
    }

    public int getStartMinute(){
        return startMinute;
    }

    public void setStartMinute(int startMinute){
        this.startMinute = startMinute;
    }

    public int getEndMinute(){
        return endMinute;
    }
    
    public void setEndMinute(int endMinute){
        this.endMinute = endMinute;
    }
    
    public String getCategory(){
        return category;
    }

    public void setCategory(String category){
        this.category=category;
    }
}

package com.practice.daily_routine_backend.entity;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ChecklistItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;
    private String content;
    private Boolean checked;
    private LocalDate checklistDate;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getCategory(){
        return category;
    }

    public void setCategory(String category){
        this.category = category;
    }

    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content = content;
    }

    public Boolean getChecked(){
        return checked;
    }

    public void setChecked(Boolean checked){
        this.checked = checked;
    }

    public LocalDate getChecklistDate (){
        return checklistDate;
    }

    public void setChecklistDate(LocalDate checklistDate){
        this.checklistDate = checklistDate;
    }
}

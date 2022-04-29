package com.educ.services.dto;

import com.educ.entity.Language;
import com.educ.entity.Lesson;
import com.educ.entity.Level;
import com.educ.entity.Modulee;
import org.springframework.beans.BeanUtils;

import java.util.List;

public class LessonDTO {
    private Long id;
    private String name;
    private String description;
    private float price;
    private List<Modulee> modules;
    private Level level;
    private Language language;

    public LessonDTO() {

    }

    public LessonDTO(String name, String description, float price, Language language, Level level) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.language = language;
        this.level = level;
        this.modules=null;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }

    public List<Modulee> getModules() {
        return modules;
    }

    public Level getLevel() {
        return level;
    }

    public Language getLanguage() {
        return language;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    /*
    public void setModules(List<Modulee> modules) {
        this.modules = modules;
    }

     */

    public void setLevel(Level level) {
        this.level = level;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public LessonDTO convertTo(Lesson lesson){
        BeanUtils.copyProperties(lesson, this);
        return this;
    }

}

package com.educ.services.dto;

import com.educ.entity.Language;
import com.educ.entity.Level;
import com.educ.entity.Modulee;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
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

}

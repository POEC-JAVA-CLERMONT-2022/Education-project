package com.educ.services.dto;

import com.educ.entity.Language;
import com.educ.entity.Lesson;
import com.educ.entity.Level;
import com.educ.entity.Modulee;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class LessonDTO implements Serializable {
    private Long id;
    private String name;
    private String description;
    private float price;
    private Level level;
    private Language language;
    private List<ModuleeDTO> moduleeDTOS;

    public LessonDTO() {

    }

    public LessonDTO(Long id, String name, String description, float price, Level level, Language language, List<ModuleeDTO> moduleeDTOS) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.level = level;
        this.language = language;
        this.moduleeDTOS = moduleeDTOS;
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

    public void setLevel(Level level) {
        this.level = level;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ModuleeDTO> getModuleeDTOS() {
        return moduleeDTOS;
    }

    public void setModuleeDTOS(List<ModuleeDTO> moduleeDTOS) {
        this.moduleeDTOS = moduleeDTOS;
    }

    public LessonDTO convertTo(Lesson lesson){
        List<Modulee> modulees=lesson.getModulees();
        List<ModuleeDTO> moduleeDTOList=new LinkedList<ModuleeDTO>();
        ModuleeDTO moduleeDTO=new ModuleeDTO();
        for (Modulee m:modulees){
            moduleeDTOList.add(moduleeDTO.convertTo(m));
        }
        LessonDTO lessonDTO=new LessonDTO(lesson.getId(),lesson.getName(),lesson.getDescription(),lesson.getPrice(), lesson.getLevel(),lesson.getLanguage(),moduleeDTOList);
        //BeanUtils.copyProperties(lesson, this);
        return lessonDTO;
    }

}

package com.educ.services.dto;

import com.educ.entity.Lesson;
import com.educ.entity.Modulee;
import com.educ.entity.Review;
import com.educ.entity.Video;
import org.springframework.beans.BeanUtils;

import java.util.List;

public class ModuleeDTO {
    private Long id;
    private String title;
    private Lesson lesson;
    private List<Review> reviews;
    private Video video;

    public ModuleeDTO() {
    }

    public ModuleeDTO(String title) {
        super();
        this.title = title;
        this.reviews=null;
        this.lesson=null;
        this.video=null;
    }

    public String getTitle() {
        return title;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public Video getVideo() {
        return video;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public ModuleeDTO convertTo(Modulee modulee){
        BeanUtils.copyProperties(modulee, this);
        return this;
    }
}

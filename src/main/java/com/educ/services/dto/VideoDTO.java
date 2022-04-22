package com.educ.services.dto;

import com.educ.entity.Modulee;
import com.educ.entity.Video;
import org.springframework.beans.BeanUtils;

import java.time.LocalTime;

public class VideoDTO {
    private Long id;
    private String title;
    private String url;
    private LocalTime duration;
    private Modulee module;

    public VideoDTO() {
    }

    public VideoDTO(String title, String url, LocalTime duration) {
        this.title = title;
        this.url = url;
        this.duration = duration;
        this.module=null;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public Modulee getModule() {
        return module;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    public void setModule(Modulee module) {
        this.module = module;
    }

    public VideoDTO copyVideo(Video video){
        BeanUtils.copyProperties(video, this);
        return this;
    }
}

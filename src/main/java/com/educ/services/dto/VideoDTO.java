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


    public VideoDTO() {
    }


    public VideoDTO(Long id,String title, String url, LocalTime duration) {
        this.id=id;
        this.title = title;
        this.url = url;
        this.duration = duration;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    public VideoDTO convertTo(Video video){
        VideoDTO videoDTO=new VideoDTO();
        BeanUtils.copyProperties(video, this);
        return videoDTO;

    }
}

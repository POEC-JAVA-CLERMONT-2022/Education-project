package com.educ.services.dto;

import com.educ.entity.Modulee;

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
}

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
    //private ModuleeDTO moduleeDTO;

    public VideoDTO() {
    }

    /*
    public VideoDTO(String title, String url, LocalTime duration, ModuleeDTO moduleeDTO) {

        this.title = title;
        this.url = url;
        this.duration = duration;
        //this.moduleeDTO = moduleeDTO;
    }

     */

    public VideoDTO(String title, String url, LocalTime duration) {
        this.title = title;
        this.url = url;
        this.duration = duration;
       // this.moduleeDTO=new ModuleeDTO();
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
    /*
    public ModuleeDTO getModuleeDTO() {
        return moduleeDTO;
    }
    */

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }
/*
    public void setModuleeDTO(ModuleeDTO moduleeDTO) {
        this.moduleeDTO = moduleeDTO;
    }
*/
    public VideoDTO convertTo(Video video){
        BeanUtils.copyProperties(video, this);
        return this;
        //VideoDTO videoDTO=new VideoDTO();
        //BeanUtils.copyProperties(video, videoDTO);

        //ModuleeDTO moduleeDTO=new ModuleeDTO();
        //moduleeDTO.convertTo(video.getModule());
       // videoDTO.setModuleeDTO(moduleeDTO);
       // return videoDTO;
    }
}

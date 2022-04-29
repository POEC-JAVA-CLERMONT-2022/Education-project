package com.educ.services.dto;

import com.educ.entity.Lesson;
import com.educ.entity.Modulee;
import com.educ.entity.Review;
import com.educ.entity.Video;
import org.springframework.beans.BeanUtils;

import java.util.LinkedList;
import java.util.List;

public class ModuleeDTO {
    private Long id;
    private String title;
    //private List<Lesson> lessons;
    //private List<Review> reviews;
    private VideoDTO videoDTO;

    public ModuleeDTO() {
    }

    public ModuleeDTO(String title) {
        super();
        this.title = title;
        //this.reviews=new LinkedList<Review>();
        //this.lessons=new LinkedList<Lesson>();
        this.videoDTO=null;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    /*public List<Lesson> getLessons() {
        return lessons;
    }

    public List<Review> getReviews() {
        return reviews;
    }*/

    public VideoDTO getVideoDTO() {
        return videoDTO;
    }

    public void setTitle(String title) {
        this.title = title;
    }

   /* public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    } */

    public void setVideoDTO(VideoDTO videoDTO) {
        this.videoDTO = videoDTO;
    }

    public ModuleeDTO convertTo(Modulee modulee){
        ModuleeDTO moduleeDTO=new ModuleeDTO();
        BeanUtils.copyProperties(modulee, moduleeDTO);

        VideoDTO videoDTO=new VideoDTO();
        videoDTO.convertTo(modulee.getVideo());
        moduleeDTO.setVideoDTO(videoDTO);
        return moduleeDTO;
    }
}

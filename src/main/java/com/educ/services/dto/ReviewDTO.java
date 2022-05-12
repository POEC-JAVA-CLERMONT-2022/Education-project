package com.educ.services.dto;

import com.educ.entity.*;
import com.educ.services.UserService;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;


public class ReviewDTO implements Serializable {
    private Long id;
    private int note;
    private String comment;
    private String moduleTitle;

    public ReviewDTO() {

    }
    public ReviewDTO(Long id, int note, String comment) {
        this.id = id;
        this.note = note;
        this.comment = comment;

    }

    public ReviewDTO(Long id, int note, String comment, String moduleTitle) {
        this.id = id;
        this.note = note;
        this.comment = comment;
        this.moduleTitle=moduleTitle;
    }


    public int getNote() {
        return note;
    }

    public String getComment() {
        return comment;
    }

    public String getModuleTitle() {
        return moduleTitle;
    }

    public void setModuleTitle(String moduleTitle) {
        this.moduleTitle = moduleTitle;
    }

    public Long getId() {
        return id;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public ReviewDTO convertTo(Review review){
        ReviewDTO reviewDTO;
        if(review!=null){
            reviewDTO=new ReviewDTO(review.getId(),review.getNote(),review.getComment());
            BeanUtils.copyProperties(review, reviewDTO);

            reviewDTO.setModuleTitle(review.getModule().getTitle());

            return reviewDTO;
        }else{
            reviewDTO=new ReviewDTO();
            return reviewDTO;
        }

    }
}

package com.educ.services.dto;

import com.educ.entity.*;
import org.springframework.beans.BeanUtils;

import java.util.LinkedList;
import java.util.List;


public class ReviewDTO {
    private Long id;
    private int note;
    private String comment;
    private User user;
    private Modulee modulee;


    public ReviewDTO() {

    }

    public ReviewDTO(int note, String comment) {
        super();
        this.note = note;
        this.comment = comment;
        this.user= null;
        this.modulee = null;

    }

    public int getNote() {
        return note;
    }

    public String getComment() {
        return comment;
    }

    public User getUser() {
        return user;
    }

    public Modulee getModulee() {
        return modulee;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }



    public ReviewDTO convertTo(Review review){
        BeanUtils.copyProperties(review, this);
        return this;
    }
}

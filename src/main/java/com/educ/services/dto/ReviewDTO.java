package com.educ.services.dto;

import com.educ.entity.*;
import org.springframework.beans.BeanUtils;


public class ReviewDTO {
    private Long id;
    private int note;
    private String comment;
    private User user;
    private Modulee modulee;


    public ReviewDTO() {

    }

    public ReviewDTO(Long id, int note, String comment, User user, Modulee modulee) {
        this.id = id;
        this.note = note;
        this.comment = comment;
        this.user = user;
        this.modulee = modulee;
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

    public void setUser(User user) {
        this.user = user;
    }

    public void setModulee(Modulee modulee) {
        this.modulee = modulee;
    }

    public ReviewDTO convertTo(Review review){
        BeanUtils.copyProperties(review, this);
        return this;
    }
}

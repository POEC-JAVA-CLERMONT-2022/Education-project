package com.educ.services.dto;

import com.educ.entity.Modulee;
import com.educ.entity.Review;
import com.educ.entity.User;
import com.educ.entity.Video;
import org.springframework.beans.BeanUtils;


public class ReviewDTO {
    private Long id;
    private int note;
    private String comment;
    private Modulee module;
    private User user;

    public ReviewDTO() {

    }

    public ReviewDTO(int note, String comment) {
        super();
        this.note = note;
        this.comment = comment;
        this.module=null;
        this.user=null;
    }

    public int getNote() {
        return note;
    }

    public String getComment() {
        return comment;
    }

    public Modulee getModule() {
        return module;
    }

    public User getUser() {
        return user;
    }

    public ReviewDTO convertTo(Review review){
        BeanUtils.copyProperties(review, this);
        return this;
    }
}

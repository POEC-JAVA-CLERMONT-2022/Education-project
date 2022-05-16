package com.educ.services.dto;

import com.educ.entity.Modulee;
import com.educ.entity.Review;
import com.educ.entity.User;

import java.io.Serializable;

public class CreationReviewDTo implements Serializable {
    private int note;
    private String comment;
    private Long modulee_id;
    private Long user_id;

    public CreationReviewDTo() {

    }

    public CreationReviewDTo(int note, String comment, Long modulee_id, Long user_id) {
        this.note = note;
        this.comment = comment;
        this.modulee_id = modulee_id;
        this.user_id = user_id;
    }

    public int getNote() {
        return note;
    }

    public String getComment() {
        return comment;
    }

    public Long getModulee_id() {
        return modulee_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public CreationReviewDTo convertTo(Review review){
        Modulee modulee=review.getModule();
        User user=review.getUser();
        CreationReviewDTo creationReviewDTo;
        if(review!=null){
           creationReviewDTo=new CreationReviewDTo(review.getNote(),review.getComment(),modulee.getId(),user.getId());
           return creationReviewDTo;
        }else{
            creationReviewDTo=new CreationReviewDTo();
            return creationReviewDTo;
        }
    }

}

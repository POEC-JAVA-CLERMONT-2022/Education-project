package com.educ.services.dto;

import com.educ.entity.*;
import com.educ.services.UserService;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;


public class ReviewDTO implements Serializable {
    private Long id;
    private int note;
    private String comment;
    private ModuleeDTO moduleeDTO;

    public ReviewDTO() {

    }

    public ReviewDTO(Long id, int note, String comment, ModuleeDTO moduleeDTO) {
        this.id = id;
        this.note = note;
        this.comment = comment;
        this.moduleeDTO = moduleeDTO;
    }

    public int getNote() {
        return note;
    }

    public String getComment() {
        return comment;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ModuleeDTO getModuleeDTO() {
        return moduleeDTO;
    }

    public void setModuleeDTO(ModuleeDTO moduleeDTO) {
        this.moduleeDTO = moduleeDTO;
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
        Modulee modulee=review.getModule();
        ModuleeDTO moduleeDTO=new ModuleeDTO();
        moduleeDTO=moduleeDTO.convertTo(modulee);

        ReviewDTO reviewDTO;
        if(review!=null){
            reviewDTO=new ReviewDTO(review.getId(),review.getNote(),review.getComment(),moduleeDTO);
            //BeanUtils.copyProperties(review, reviewDTO);

            // reviewDTO.setModuleTitle(review.getModule().getTitle());

            return reviewDTO;
        }else{
            reviewDTO=new ReviewDTO();
            return reviewDTO;
        }

    }
}

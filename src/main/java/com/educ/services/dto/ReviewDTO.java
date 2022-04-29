package com.educ.services.dto;

import com.educ.entity.*;
import com.educ.services.UserService;
import org.springframework.beans.BeanUtils;


public class ReviewDTO {
    private Long id;
    private int note;
    private String comment;
    private UserDTO userDTO;
    private ModuleeDTO moduleeDTO;

    private UserService userService;

    public ReviewDTO() {

    }

    public ReviewDTO(Long id, int note, String comment, UserDTO user, ModuleeDTO modulee) {
        this.id = id;
        this.note = note;
        this.comment = comment;
        this.userDTO = user;
        this.moduleeDTO = modulee;
    }



    public int getNote() {
        return note;
    }

    public String getComment() {
        return comment;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public ModuleeDTO getModuleeDTO() {
        return moduleeDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
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
        ReviewDTO reviewDTO=new ReviewDTO();
        BeanUtils.copyProperties(review, reviewDTO);

        UserDTO userDTO=new UserDTO();
        userDTO.copyUser(review.getUser());
        reviewDTO.setUserDTO(userDTO);

        ModuleeDTO moduleeDTO=new ModuleeDTO();
        moduleeDTO.convertTo(review.getModule());
        reviewDTO.setModuleeDTO(moduleeDTO);
        return reviewDTO;
    }
}

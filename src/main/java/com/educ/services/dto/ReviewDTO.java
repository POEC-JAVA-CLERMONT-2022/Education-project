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

    public ReviewDTO() {

    }
    public ReviewDTO(Long id, int note, String comment) {
        this.id = id;
        this.note = note;
        this.comment = comment;

    }

    public ReviewDTO(Long id, int note, String comment, UserDTO userDTO, ModuleeDTO moduleeDTO) {
        this.id = id;
        this.note = note;
        this.comment = comment;
        this.userDTO = userDTO;
        this.moduleeDTO = moduleeDTO;
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


}

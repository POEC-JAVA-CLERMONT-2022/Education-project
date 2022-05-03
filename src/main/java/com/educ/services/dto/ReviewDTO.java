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

    public ReviewDTO convertTo(Review review){
        ReviewDTO reviewDTO;
        if(review!=null){
            reviewDTO=new ReviewDTO(review.getId(),review.getNote(),review.getComment());
            BeanUtils.copyProperties(review, reviewDTO);

            UserDTO userDTO=new UserDTO(review.getUser().getFirstName(),review.getUser().getLastName(),
                    review.getUser().getBirthAt(),review.getUser().getUrlImage(), review.getUser().getEmail(),
                    review.getUser().getPassword(),review.getUser().getStatus());
            //userDTO.copyUser(review.getUser());
            reviewDTO.setUserDTO(userDTO);

            //ModuleeDTO moduleeDTO=new ModuleeDTO(review.getModule().getTitle());
            //moduleeDTO.convertTo(review.getModule());
            //reviewDTO.setModuleeDTO(moduleeDTO);

            return reviewDTO;
        }else{
            reviewDTO=new ReviewDTO();
            return reviewDTO;
        }

    }
}

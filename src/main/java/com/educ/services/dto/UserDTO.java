package com.educ.services.dto;
import com.educ.entity.Review;
import com.educ.entity.Role;
import com.educ.entity.User;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class UserDTO {

    private User user;
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthAt;
    private String urlImage;
    private String email;
    private String password;
    private String status;
   //private List<Role> roles;
    //private List<Review> reviews;

    public UserDTO(String firstName, String lastName, LocalDate birthAt, String urlImage, String email, String password, String status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthAt = birthAt;
        this.urlImage = urlImage;
        this.email = email;
        this.password = password;
        this.status = status;
        //this.roles=new LinkedList<Role>();
        //this.reviews=new LinkedList<Review>();
    }

    public UserDTO(){

    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthAt() {
        return birthAt;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getStatus() {
        return status;
    }

    /* public List<Role> getRoles() {
        return roles;
    }

    public List<Review> getReviews() {
        return reviews;
    } */

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthAt(LocalDate birthAt) {
        this.birthAt = birthAt;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /* public void setRoles(List<Role> roles) {
        this.roles = roles;
    } */
/*
    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
*/
    public UserDTO copyUser(User user){
        BeanUtils.copyProperties(user, this);
        return this;
    }

}

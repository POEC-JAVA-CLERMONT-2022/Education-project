package com.educ.services.dto;
import com.educ.entity.Review;
import com.educ.entity.Role;
import com.educ.entity.User;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class UserDTO implements Serializable {
    
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthAt;
    private String urlImage;
    private String email;
    private String password;
    private String status;
   private List<RoleDTO> roleDTOS;

    public UserDTO() { }
/*
    public UserDTO(Long id, String firstName, String lastName, LocalDate birthAt, String urlImage, String email, String password, String status) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthAt = birthAt;
        this.urlImage = urlImage;
        this.email = email;
        this.password = password;
        this.status = status;
    }*/

    public UserDTO(Long id, String firstName, String lastName, LocalDate birthAt, String urlImage, String email, String password, String status, List<RoleDTO> roleDTOS) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthAt = birthAt;
        this.urlImage = urlImage;
        this.email = email;
        this.password = password;
        this.status = status;
        this.roleDTOS = roleDTOS;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthAt() {
        return birthAt;
    }

    public void setBirthAt(LocalDate birthAt) {
        this.birthAt = birthAt;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<RoleDTO> getRoleDTOS() {
        return roleDTOS;
    }

    public void setRoleDTOS(List<RoleDTO> roleDTOS) {
        this.roleDTOS = roleDTOS;
    }

    public UserDTO copyUser(User user){
        List<Role> roles=user.getRoles();
        List<RoleDTO> roleDTOS=new LinkedList<RoleDTO>();
        RoleDTO roleDTO=new RoleDTO();
        for(Role r:roles){
            roleDTOS.add(roleDTO.convertTo(r));
        }
        UserDTO userDTO=new UserDTO(user.getId(),user.getFirstName(),user.getLastName(),user.getBirthAt(),user.getUrlImage(),user.getEmail(),user.getPassword(),user.getStatus(),roleDTOS);

        return userDTO;
    }

}

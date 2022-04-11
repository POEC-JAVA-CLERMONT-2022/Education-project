package com.educ.services.dto;
import com.educ.entity.Review;
import com.educ.entity.Role;
import java.time.LocalDate;
import java.util.List;

public class UserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthAt;
    private String urlImage;
    private String email;
    private String password;
    private String status;
    private List<Role> roles;
    private List<Review> reviews;

    public UserDTO(String firstName, String lastName, LocalDate birthAt, String urlImage, String email, String password, String status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthAt = birthAt;
        this.urlImage = urlImage;
        this.email = email;
        this.password = password;
        this.status = status;
        this.roles=null;
        this.reviews=null;
    }
}

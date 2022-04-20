package com.educ.services.dto;

public class AddUserRoleDTO {
    private String mail;
    private String name;

    public AddUserRoleDTO(String mail, String name) {
        this.mail = mail;
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public String getName() {
        return name;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setName(String name) {
        this.name = name;
    }
}

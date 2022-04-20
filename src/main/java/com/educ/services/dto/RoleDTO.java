package com.educ.services.dto;

public class RoleDTO {
    private Long id;
    private String name;

    public RoleDTO() {
        //this.name = "";
    }

    public RoleDTO(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

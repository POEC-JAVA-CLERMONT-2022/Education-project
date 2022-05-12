package com.educ.services.dto;

import com.educ.entity.Role;
import com.educ.entity.Video;
import org.springframework.beans.BeanUtils;

public class RoleDTO {
    private Long id;
    private String name;

    public RoleDTO() {

    }

    public RoleDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RoleDTO convertTo(Role role){
        RoleDTO roleDTO=new RoleDTO(role.getId(), role.getName());
        //BeanUtils.copyProperties(role,this);
        return roleDTO;
    }


}

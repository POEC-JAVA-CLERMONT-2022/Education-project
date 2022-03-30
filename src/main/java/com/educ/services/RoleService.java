package com.educ.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educ.data.RoleRepository;
import com.educ.entity.Role;

@Service
public class RoleService {
	@Autowired
	RoleRepository roleRepository;
	public List<Role> findAll(){
		return roleRepository.findAll();
	}

	public Role createRole(String roleName){
		Role role=new Role(roleName);
		this.roleRepository.save(role);
		return role;
	}

	public void updateRole (Role role){
		Role existing=this.roleRepository.getById(role.getId());
		if (existing!=null){
			this.roleRepository.save(role);
		}


	}



}

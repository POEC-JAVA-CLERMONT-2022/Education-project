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

}

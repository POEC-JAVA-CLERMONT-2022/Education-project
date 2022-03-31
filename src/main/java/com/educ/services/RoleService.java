package com.educ.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educ.data.RoleRepository;
import com.educ.entity.Role;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleService {

	@Autowired
	RoleRepository roleRepository;

	@Transactional(readOnly = true)
	public List<Role> findAll(){
		return roleRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Role getById(Long id) {return roleRepository.getById(id);}

	@Transactional
	public Role createRole(String name){
		Role role=new Role(name);
		this.roleRepository.save(role);
		return role;
	}

	@Transactional
	public void updateRole (Long id, String name){
		//this.roleRepository.updateRole(id,name);
		Role role=this.roleRepository.getById(id);
		role.setName(name);
		this.roleRepository.save(role);
	}

	@Transactional
	public void deleteRole(Long id){
		Role role=this.roleRepository.getById(id);
		if(role !=null){
			this.roleRepository.delete(role);
		}
	}



}

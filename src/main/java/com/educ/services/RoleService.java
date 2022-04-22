package com.educ.services;

import java.util.List;

import com.educ.entity.Review;
import com.educ.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educ.data.RoleRepository;
import com.educ.entity.Role;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleService {
	RoleRepository roleRepository;

	@Autowired
	public RoleService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	public List<Role> findAll(){
		return roleRepository.findAll();
	}

	public boolean existId(Long id) {
		List<Role> roles=this.roleRepository.findAll();
		for(Role role:roles){
			if(role.getId()==id){
				return true;
			}
		}
		return false;
	}

	public Role getById(Long id) {
		if(this.existId(id)){
			return roleRepository.getById(id);
		}else{
			return null;
		}
	}

	public Role findByName(String name) {
		if (name == null) {
			return null;
		}
		return this.roleRepository.findByName(name);
	}

	@Transactional
	public Role createRole(String name){
		if(name==null){ return null;}
		if(this.findByName(name) != null){ return this.findByName(name);}
		Role role=new Role(name);
		role=this.roleRepository.save(role);
		return role;
	}

	@Transactional
	public void updateRole (Long id, String name){
		if(name!=null && this.existId(id)) {
			Role r = this.findByName(name);
			if (r == null || r.getId() == id) {
				Role role = this.getById(id);
				role.setName(name);
				role = this.roleRepository.save(role);
			}
		}
	}

	@Transactional
	public void deleteRole(Long id){
		Role role=this.getById(id);
		if(role !=null){
			this.roleRepository.delete(role);
		}
	}

}

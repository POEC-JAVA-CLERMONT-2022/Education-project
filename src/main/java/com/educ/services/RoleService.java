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

	@Autowired
	RoleRepository roleRepository;

	@Transactional(readOnly = true)
	public List<Role> findAll(){
		return roleRepository.findAll();
	}

	@Transactional(readOnly = true)
	public boolean existId(Long id) {
		List<Role> roles=this.roleRepository.findAll();
		for(Role role:roles){
			if(role.getId()==id){
				return true;
			}
		}
		return false;
	}

	@Transactional(readOnly = true)
	public Role getById(Long id) {
		if(this.existId(id)){
			return roleRepository.getById(id);
		}else{
			return null;
		}
	}

	@Transactional(readOnly = true)
	public Role findByName(String name){
		if(name!=null){
			List<Role> roles=this.findAll();
			//TODO: utilisation des repo
			for(Role role:roles){
				if (role.getName().equals(name)){
					return role;
				}
			}
			return null;
		}else {
			return null;
		}
		//return this.roleRepository.findByName(name);
	}



	@Transactional
	public Role createRole(String name){
		if(name != null){
			if(this.findByName(name) == null){
				Role role=new Role(name);
				role=this.roleRepository.save(role);
				return role;
			}else{
				return this.findByName(name);
			}
		}else{
			return null;
		}
	}

	@Transactional
	public void updateRole (Long id, String name){
		if(name!=null){
			if(this.existId(id) && (this.findByName(name)==null)){
				Role role=this.getById(id);
				role.setName(name);
				role=this.roleRepository.save(role);
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

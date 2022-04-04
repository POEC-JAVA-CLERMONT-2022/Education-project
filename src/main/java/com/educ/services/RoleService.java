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
	public Role getById(Long id) {
		return roleRepository.getById(id);
	}

	@Transactional(readOnly = true)
	public Role findByName(String name){
		return this.roleRepository.findByName(name);
	}

	@Transactional
	public Role createRole(String name){
		if(name != null){
			if(this.roleRepository.findByName(name) == null){
				Role role=new Role(name);
				this.roleRepository.save(role);
				return role;
			}else{
				return null;
			}
		}else{
			return null;
		}
		/*catch (RuntimeException e){
			System.out.println("Erreur create role name null");
			return null;
		}*/
	}

	@Transactional
	public void updateRole (Long id, String name){
		if(name!=null){
			if((this.roleRepository.getById(id) != null) &&  (this.roleRepository.findByName(name)==null)){
				Role role=this.roleRepository.getById(id);
				role.setName(name);
				this.roleRepository.save(role);
			}
		}
		else{

		}
		/*catch (RuntimeException e){
			System.out.println("Erreur update role name null");
		}finally {
			System.out.println("fin update role name null");
		}*/
	}

	@Transactional
	public void deleteRole(Long id){
		Role role=this.roleRepository.getById(id);
		if(role !=null){
			this.roleRepository.delete(role);
		}
	}

}

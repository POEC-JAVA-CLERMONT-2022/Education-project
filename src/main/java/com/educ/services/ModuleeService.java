package com.educ.services;


import com.educ.entity.Modulee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educ.data.ModuleeRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ModuleeService {
	
	@Autowired
	private ModuleeRepository moduleRepository;

	@Transactional(readOnly = true)
	public List<Modulee> findAll(){
		return moduleRepository.findAll();
	}

	@Transactional(readOnly = true)
	public boolean existId(Long id) {
		List<Modulee> modulees=this.moduleRepository.findAll();
		for(Modulee modulee:modulees){
			if(modulee.getId()==id){
				return true;
			}
		}
		return false;
	}

	@Transactional(readOnly = true)
	public Modulee getById(Long id) {
		if(this.existId(id)){
			Modulee modulee=this.moduleRepository.getById(id);
			return modulee;
		}else{
			return null;
		}
	}

	@Transactional(readOnly = true)
	Modulee findByTitle(String title) { return moduleRepository.findByTitle(title);
	}

	@Transactional
	public Modulee createModule(String title) {
		if((title!=null) && this.moduleRepository.findByTitle(title) == null){
			Modulee module = new Modulee(title);
			this.moduleRepository.save(module);
			return module;
		}else {
			return null;
		}
	}

	@Transactional
	public Modulee updateModule(Long id, String title) {
		if(title!=null && this.existId(id)){
			if ((this.getById(id) != null) && (this.moduleRepository.findByTitle(title)==null)){
				Modulee modulee=this.getById(id);
				modulee.setTitle(title);
				this.moduleRepository.save(modulee);
				return modulee;
			}
		}
		return null;
	}

	@Transactional
	public void deleteModule(Long id) {
		Modulee modulee = this.getById(id);
		if(modulee !=null){
			this.moduleRepository.delete(modulee);
		}
	}

	@Transactional(readOnly = true)
	public void calculRating() {
		
	}

	/*
	@Transactional(readOnly = true)
	public List<Lesson> findAllByLesson(){ return moduleRepository.findAllByLesson();
	}
	*/

}


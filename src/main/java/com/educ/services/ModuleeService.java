package com.educ.services;


import com.educ.entity.Modulee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educ.data.ModuleeRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ModuleeService {
	
	@Autowired
	private ModuleeRepository moduleRepository;


	@Transactional(readOnly = true)
	public Modulee findById(Long id) { return moduleRepository.getById(id);
	}

	@Transactional(readOnly = true)
	Modulee findByTitle(String title) { return moduleRepository.findByTitle(title);
	}

	@Transactional
	public Modulee createModule(String title) {
		if(this.moduleRepository.findByTitle(title) == null){
			Modulee module = new Modulee(title);
			this.moduleRepository.save(module);
			return module;
		}else {
			return null;
		}
	}

	@Transactional
	public Modulee updateModule(Long id, String title) {
		if ((this.moduleRepository.getById(id) != null) && (this.moduleRepository.findByTitle(title)==null)){
			Modulee modulee=this.moduleRepository.getById(id);
			modulee.setTitle(title);
			this.moduleRepository.save(modulee);
		}
		return null;
	}

	@Transactional
	public void deleteModule(Long id) {
		Modulee modulee = this.moduleRepository.getById(id);
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


package com.educ.services;

import java.util.List;

import com.educ.entity.Language;
import com.educ.entity.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educ.data.LessonRepository;
import com.educ.entity.Lesson;
import org.springframework.transaction.annotation.Transactional;


@Service
public class LessonService {
	@Autowired
	private LessonRepository lessonRepository;

	@Transactional (readOnly = true)
	public List<Lesson> findAll(){ return lessonRepository.findAll();
	}

	@Transactional(readOnly = true)
	public boolean existId(Long id) {
		List<Lesson> lessons=this.lessonRepository.findAll();
		for(Lesson lesson:lessons){
			if(lesson.getId()==id){
				return true;
			}
		}
		return false;
	}

	@Transactional(readOnly = true)
	public Lesson getById(Long id) {
		if(this.existId(id)){
			Lesson lesson=this.lessonRepository.getById(id);
			return lesson;
		}else{
			return null;
		}
	}


	//TODO : utiliser le repo
	@Transactional(readOnly = true)
	Lesson findByNameAndLevelAndLanguage(String name, Level level, Language language){
		List<Lesson> lessons=this.lessonRepository.findAll();
		for(Lesson lesson:lessons){
			if(lesson.getName().equals(name) &&(lesson.getLanguage().equals(language) && lesson.getLevel().equals(level))){
				return lesson;
			}
		}
		return null;
		//return this.lessonRepository.findByNameAndLevelAndLanguage(name, level, language);
	}

	//TODO : à supprimer
	/*
	@Transactional(readOnly = true)
	public List<Lesson> findAllLessonsModules(){
		return lessonRepository.findAllLessonsModules();
	}

	 */

	@Transactional
	public Lesson createLesson(String name, String description, Float price, Language language, Level level) {

		if(this.findByNameAndLevelAndLanguage(name, level, language) == null){
			Lesson lesson=new Lesson(name, description, price, language, level);
			this.lessonRepository.save(lesson);
			return lesson;
		}else {
			return null;
		}

	}

	@Transactional
	public void updateLesson(Long id, String name, String description, Float price, Language language, Level level){
		if (this.existId(id) && (this.lessonRepository.getById(id) != null && this.findByNameAndLevelAndLanguage(name, level, language)==null)){
			Lesson lesson=this.lessonRepository.getById(id);
			lesson.setName(name);
			lesson.setDescription(description);
			lesson.setLevel(level);
			lesson.setPrice(price);
			lesson.setLanguage(language);
			this.lessonRepository.save(lesson);
		}
	}

	@Transactional
	public void deleteLesson(Long id) {
		Lesson lesson = this.getById(id);
		if(lesson !=null){
			this.lessonRepository.delete(lesson);
		}

	}
	//TODO : à supprimer
	/*private List<Lesson> lessons;
	
	public LessonService() {
		super();
		this.lessons = new LinkedList<Lesson>();
	}
	

	*/

}

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


	private LessonRepository lessonRepository;
	@Autowired
	public LessonService(LessonRepository lessonRepository) {
		this.lessonRepository = lessonRepository;
	}

	public List<Lesson> findAll(){ return lessonRepository.findAll();
	}


	public boolean existId(Long id) {
		List<Lesson> lessons=this.lessonRepository.findAll();
		for(Lesson lesson:lessons){
			if(lesson.getId()==id){
				return true;
			}
		}
		return false;
	}

	public Lesson getById(Long id) {
		if(this.existId(id)){
			Lesson lesson=this.lessonRepository.getById(id);
			return lesson;
		}else{
			return null;
		}
	}

	Lesson findByNameAndLevelAndLanguage(String name, Level level, Language language){
		return this.lessonRepository.findByNameAndLevelAndLanguage(name,level,language);
	}

	@Transactional
	public Lesson createLesson(String name, String description, Float price, Language language, Level level) {
		if(this.findByNameAndLevelAndLanguage(name, level, language) == null){
			Lesson lesson=new Lesson(name, description, price, language, level);
			this.lessonRepository.save(lesson);
			return lesson;
		}else {
			return this.findByNameAndLevelAndLanguage(name, level, language);
		}
	}

	@Transactional
	public void updateLesson(Long id, String name, String description, Float price, Language language, Level level){
		if(this.existId(id) ){
			Lesson l=this.findByNameAndLevelAndLanguage(name, level, language);
			if (l==null || l.getId()==id){
				Lesson lesson=this.lessonRepository.getById(id);
				lesson.setName(name);
				lesson.setDescription(description);
				lesson.setLevel(level);
				lesson.setPrice(price);
				lesson.setLanguage(language);
				this.lessonRepository.save(lesson);
			}
		}
	}

	@Transactional
	public void deleteLesson(Long id) {
		Lesson lesson = this.getById(id);
		if(lesson !=null){
			this.lessonRepository.delete(lesson);
		}

	}
}

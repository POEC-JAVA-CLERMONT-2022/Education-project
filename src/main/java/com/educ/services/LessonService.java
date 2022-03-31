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

	@Transactional(readOnly = true)
	public List<Lesson> findAll(){ return lessonRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Lesson findById(Long id) { return lessonRepository.getById(id);
	}

	@Transactional
	public Lesson create(String name, String description, Float price, Language language, Level level) {
		Lesson lesson=new Lesson(name, description, price, language, level);
		this.lessonRepository.save(lesson);
		return lesson;
	}
	
	
	/*private List<Lesson> lessons;
	
	public LessonService() {
		super();
		this.lessons = new LinkedList<Lesson>();
	}

	public void createLesson() {
		Lesson lesson=new Lesson();
		lessons.add(lesson);
	}
	
	public Lesson researchLesson() {
		
		for (Lesson l:lessons) {
			if(l.getName().equals(name)) {
				return l;
			}else {
				
			}
		}
		return null;
		
	}
	
	public void modifLesson(Lesson lesson) {
		
	}
	public void deleteLesson() {
		
	}
	
	public void selectModule() {
		///Composition
	}
	*/

}

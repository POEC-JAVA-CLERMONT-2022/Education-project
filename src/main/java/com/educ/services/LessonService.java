package com.educ.services;



import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educ.data.LessonRepository;
import com.educ.data.UserRepository;
import com.educ.models.Lesson;
import com.educ.models.User;


@Service
public class LessonService {
	@Autowired
	private LessonRepository lessonRepository;
	
	public List<Lesson> findAll(){
		return lessonRepository.findAll();
	}
	
	public Lesson findById(Long id) {
		return lessonRepository.getById(id);
	}
	
	public Lesson create() {
		Lesson lesson=new Lesson();
		return this.lessonRepository.save(lesson);
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

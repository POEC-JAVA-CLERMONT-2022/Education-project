package educationProject.service;

import java.util.LinkedList;
import java.util.List;

import educationProject.model.Lesson;

public class LessonService {
	private List<Lesson> lessons;
	
	public LessonService() {
		super();
		this.lessons = new LinkedList<Lesson>();
	}

	public void createLesson(String name) {
		Lesson lesson=new Lesson(name);
		lessons.add(lesson);
	}
	
	public Lesson researchLesson(String name) {
		
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
	

}

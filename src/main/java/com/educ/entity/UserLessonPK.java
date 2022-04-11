package com.educ.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Embeddable
public class UserLessonPK implements Serializable {
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Lesson lesson;

	protected UserLessonPK() {
		
	}
	
	public UserLessonPK(User user, Lesson lesson) {
		this.user=user;
		this.lesson =lesson;
	}

	public User getUser() {
		return user;
	}

	public Lesson getLesson() {
		return lesson;
	}
}
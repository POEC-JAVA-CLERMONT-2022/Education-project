package com.educ.entity;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Table(name="user_lesson")
@AssociationOverrides({
		@AssociationOverride(name="users.user", joinColumns = @JoinColumn(name="user_id") ),
		@AssociationOverride(name="lessons.lesson", joinColumns = @JoinColumn(name="lesson_id") )
})

public class UserLesson {
	@EmbeddedId
	private UserLessonPK userLessonPK;

	@Column(name = "result")
	@PositiveOrZero
	private Float resultat;

	public  UserLesson() {

	}

	public  UserLesson(UserLessonPK userLessonPK) {
		this.userLessonPK=userLessonPK;
	}

	public UserLessonPK getUserLessonPK() {
		return userLessonPK;
	}

	public Float getResultat() {
		return resultat;
	}

	public void setResultat(Float resultat) {
		this.resultat = resultat;
	}
}

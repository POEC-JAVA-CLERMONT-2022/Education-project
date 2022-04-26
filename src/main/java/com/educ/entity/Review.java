package com.educ.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="reviews")
public class Review {
	
	@Id
	@Column(name="id", nullable=false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="note")
	private int note;
	
	@Column(name="comment")
	private String comment;
	/*
	@ManyToOne()
	private Modulee module;
	
	@ManyToOne()
	private User user;
	*/
	
	public Review() {
	}

	public Review(int note, String comment) {
		super();
		this.note = note;
		this.comment = comment;
	//	this.module=null;
	//	this.user=null;
	}

	public Long getId() {
		return id;
	}

	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	/* public Modulee getModule() {
		return module;
	}

	public User getUser() {
		return user;
	}

	public void setModule(Modulee module) {
		this.module = module;
	}

	public void setUser(User user) {
		this.user = user;
	} */

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Review other = (Review) obj;
		return Objects.equals(id, other.id);
	}


	@Override
	public String toString() {
		return "Review{" +
				"id=" + id +
				", note=" + note +
				", comment='" + comment + '\'' +
				 /*", module=" + module +
				", user=" + user +*/
				'}';
	}
}


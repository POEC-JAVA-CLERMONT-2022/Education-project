package com.educ.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;

import java.util.Objects;

import javax.persistence.*;

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


	@ManyToOne(fetch = FetchType.LAZY, optional = false) //@JoinColumn(name="idModule", nullable = false)
	@JoinColumn(name="module_id", nullable = false)
	//@OnDelete(action = onDeleteAction.CASCADE)
	//@JsonIgnore
	private Modulee module;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;


	
	public Review() {
	}

	public Review(int note, String comment) {
		super();
		this.note = note;
		this.comment = comment;
		this.module=new Modulee();
		this.user=new User();
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

	public Modulee getModule() {
		return module;
	}

	public User getUser() {
		return user;
	}
/*
	public void setModule(Modulee module) {
		this.module = module;
	}

 */

	public void setUser(User user) {
		this.user = user;
	}

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
				", module=" + module +
				", user=" + user +
				'}';
	}
}


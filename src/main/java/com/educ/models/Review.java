package com.educ.models;

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
	
	@ManyToOne()
	private Module module;
	
	public Review() {
		
	}
	
}


package com.educ.models;


import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="videos")
public class Video {
	@Id
	@Column(name="id", nullable=false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="url")
	private String url;
	
	@Column(name="duration")
	private LocalTime duration;
	
	
	@OneToOne(mappedBy = "video")
	private Module module;
	
	public Video() {
	}
	

}

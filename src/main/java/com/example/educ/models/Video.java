package com.example.educ.models;


import java.time.LocalTime;

public class Video {
	private String title;
	private LocalTime duration;
	private Module module;
	public Video(String title, LocalTime duration) {
		super();
		this.title = title;
		this.duration = duration;
	}
	

}

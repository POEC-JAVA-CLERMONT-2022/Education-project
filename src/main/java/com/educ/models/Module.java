package com.educ.models;



import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="modules")
public class Module {
	
	@Id
	@Column(name="id", nullable=false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="title")
	private String title;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Lesson lesson;
	
	@OneToMany(mappedBy = "module", cascade=CascadeType.REMOVE, orphanRemoval = true)
	private List<Review> reviews; 
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "video_id", referencedColumnName = "id")
	private Video video;

	public Module() {
		
		
	}
	
	

}


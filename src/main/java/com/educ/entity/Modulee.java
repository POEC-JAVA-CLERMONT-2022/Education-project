package com.educ.entity;

import java.util.List;
import java.util.Objects;

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
public class Modulee {
	
	@Id
	@Column(name="id", nullable=false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="title", unique = true)
	private String title;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Lesson lesson;
	
	@OneToMany(mappedBy = "module", fetch = FetchType.LAZY, cascade=CascadeType.REMOVE, orphanRemoval = true)
	private List<Review> reviews; 
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "video_id", referencedColumnName = "id")
	private Video video;

	public Modulee() {
	}
	
	public Modulee(String title) {
		super();
		this.title = title;
		this.reviews=null;
		this.lesson=null;
		this.video=null;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	/*
	public Video getVideo() {
		return video;
	}

	 */

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public int hashCode() {
		return Objects.hash(lesson, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Modulee other = (Modulee) obj;
		return Objects.equals(lesson, other.lesson) && Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return "Module {id=" + id + ", title=" + title + ", lesson=" + lesson + ", reviews=" + reviews + ", video="
				+ video + "}";
	}
}


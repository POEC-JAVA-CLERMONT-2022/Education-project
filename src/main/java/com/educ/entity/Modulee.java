package com.educ.entity;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name="modules")
public class Modulee {
	
	@Id
	@Column(name="id", nullable=false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="title", unique = true, nullable = false)
	private String title;
	
	//@ManyToOne(fetch = FetchType.LAZY)
	@ManyToOne
	@JoinColumn(name = "lesson_id")
	private Lesson lesson;
	
	//@OneToMany(mappedBy = "module", fetch = FetchType.LAZY, cascade=CascadeType.REMOVE, orphanRemoval = true)
	@OneToMany
	@JoinColumn(name = "module_id")
	//private Review review;
	private List<Review> reviews;

	/*@OneToOne(mappedBy = "module")
	private Review review;*/

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "video_id", referencedColumnName = "id")
	private Video video;

	public Modulee() {
	}
	
	public Modulee(String title) {
		super();
		this.title = title;
		this.reviews=new LinkedList<Review>();
		//this.review=null;
		this.lesson=null;
		this.video=null;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}


	public Video getVideo() {
		return video;
	}

	public Lesson getLesson() {
		return lesson;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	/*public Review getReview() {
		return review;
	}*/

	public void setTitle(String title) {
		this.title = title;
	}



	@Override
	public int hashCode() {
		return Objects.hash(title);
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
		return  Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return "Modulee{" +
				"id=" + id +
				", title='" + title + '\'' +
				", lesson=" + lesson +
				", reviews=" + reviews +
				", video=" + video +
				'}';
	}
}


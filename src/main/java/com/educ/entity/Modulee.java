package com.educ.entity;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "modules")
public class Modulee {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", unique = true, nullable = false)
    private String title;

    //@ManyToOne(fetch = FetchType.LAZY)
    @ManyToMany
    @JoinColumn(name = "lesson_id")
    private List<Lesson> lessons;

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
        this.reviews = new LinkedList<Review>();

        this.lessons = new LinkedList<Lesson>();
        this.video = null;
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

    public List<Lesson> getLessons() {
        return lessons;
    }

    public List<Review> getReviews() {
        return reviews;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void setVideo(Video video) {
        this.video = video;
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
        return Objects.equals(title, other.title);
    }

    @Override
    public String toString() {
        return "Modulee{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", lessons=" + lessons +
                ", reviews=" + reviews +
                ", video=" + video +
                '}';
    }
}


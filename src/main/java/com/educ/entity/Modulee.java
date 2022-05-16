package com.educ.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

//@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "modules")
public class Modulee {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", unique = true, nullable = false)
    @NotBlank
    private String title;

    /* create video_id in table modulees */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "video_id", referencedColumnName = "id")
    private Video video;

    public Modulee() {
    }

    public Modulee(String title) {
        super();
        this.title = title;
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

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Modulee{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", video=" + video +
                '}';
    }

    /*

    public void setVideo(Video video) {
        this.video = video;
    } */

}


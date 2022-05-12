package com.educ.entity;


import java.time.LocalTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "videos")
public class Video {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "url", unique = true, nullable = false)
    private String url;

    @Column(name = "duration")
    private LocalTime duration;

    /* create association in table modulee */
    @OneToOne(mappedBy = "video")
    private Modulee module;

    public Video() {
        this.url = "";
    }

    public Video(String title, String url, LocalTime duration) {
        super();
        this.title = title;
        this.url = url;
        this.duration = duration;

    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Video other = (Video) obj;
        return Objects.equals(url, other.url);
    }

    @Override
    public String toString() {
        return "Video {id=" + id + ", title=" + title + ", url=" + url + ", duration=" + duration

                + "}";
    }

}

package com.educ.services;


import com.educ.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educ.data.ModuleeRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ModuleeService {

    private ModuleeRepository moduleRepository;

    private LessonService lessonService;

    private ReviewService reviewService;

    private VideoService videoService;

    @Autowired
    public ModuleeService(ModuleeRepository moduleRepository, LessonService lessonService, ReviewService reviewService, VideoService videoService) {
        this.moduleRepository = moduleRepository;
        this.lessonService = lessonService;
        this.reviewService = reviewService;
        this.videoService = videoService;
    }

    public List<Modulee> findAll() {
        return moduleRepository.findAll();
    }

    public boolean existId(Long id) {
        List<Modulee> modulees = this.moduleRepository.findAll();
        for (Modulee modulee : modulees) {
            if (modulee.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public Modulee getById(Long id) {
        if (this.existId(id)) {
            Modulee modulee = this.moduleRepository.getById(id);
            return modulee;
        } else {
            return null;
        }
    }

    public Modulee findByTitle(String title) {
        if (title == null) {
            return null;
        }
        return moduleRepository.findByTitle(title);
    }

    @Transactional
    public Modulee createModule(String title, String name, Level level, Language language, Long id, String url) {
        if (title == null) {
            return null;
        }
        if (this.findByTitle(title) != null) {
            return this.findByTitle(title);
        }
        Modulee module = new Modulee(title);
        module = this.addModuleeLesson(module, name, level, language);
        module = this.addModuleeReview(module, id);
        module = this.addModuleeVideo(module, url);
        this.moduleRepository.save(module);
        return module;
    }

    @Transactional
    public Modulee updateModule(Long id, String title) {
        if (title == null || !this.existId(id)) {
            return null;
        }

        Modulee m = this.moduleRepository.findByTitle(title);
        if (m == null || m.getId() == id) {
            Modulee modulee = this.getById(id);
            modulee.setTitle(title);
            this.moduleRepository.save(modulee);
            return modulee;
        }
        return null;
    }

    @Transactional
    public void deleteModule(Long id) {
        Modulee modulee = this.getById(id);
        if (modulee != null) {
            this.moduleRepository.delete(modulee);
        }
    }

    public Modulee addModuleeLesson(Modulee modulee, String name, Level level, Language language) {
        try {
            List<Lesson> lessons;
            Lesson lesson = lessonService.findByNameAndLevelAndLanguage(name, level, language);
            if (modulee == null) {
                return null;
            }
            if (lesson == null) {
                return modulee;
            }
            lessons = modulee.getLessons();
            lessons.add(lesson);
            modulee.setLessons(lessons);
            return modulee;
        } catch (NullPointerException e) {
            System.out.println("Erreur relation Module lesson null");
            return modulee;
        }
    }

    private Modulee addModuleeReview(Modulee modulee, Long id) {
        try {
            List<Review> reviews;
            Review review = reviewService.getById(id);
            if (modulee == null) {
                return null;
            }
            if (review == null) {
                return modulee;
            }
            reviews = modulee.getReviews();
            reviews.add(review);
            modulee.setReviews(reviews);
            return modulee;
        } catch (NullPointerException e) {
            System.out.println("Erreur relation Module Review null");
            return modulee;
        }

    }

    private Modulee addModuleeVideo(Modulee modulee, String url) {
        try {
            Video video = videoService.findByUrl(url);
            if (modulee == null) {
                return null;
            }
            if (video == null) {
                return modulee;
            }
            modulee.setVideo(video);
            return modulee;
        } catch (NullPointerException e) {
            System.out.println("Erreur relation Module Video null");
            return modulee;
        }
    }

    public Double calculRating(String title) {
        Modulee modulee = this.findByTitle(title);
        if (modulee == null) {
            return null;
        }
        List<Review> reviews = modulee.getReviews();
        if (reviews == null) {
            return 0.0;
        }
        int somme = 0;
        for (Review review : reviews) {
            somme += review.getNote();
        }
        return Double.valueOf(somme / reviews.size());
    }
}


package com.educ.services.dto;

import com.educ.entity.Lesson;
import com.educ.entity.Review;
import com.educ.entity.Video;
import java.util.List;

public class ModuleeDTO {
    private Long id;
    private String title;
    private Lesson lesson;
    private List<Review> reviews;
    private Video video;

    public ModuleeDTO() {
    }

    public ModuleeDTO(String title) {
        super();
        this.title = title;
        this.reviews=null;
        this.lesson=null;
        this.video=null;
    }
}

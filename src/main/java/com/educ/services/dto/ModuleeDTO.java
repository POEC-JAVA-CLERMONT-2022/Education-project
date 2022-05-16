package com.educ.services.dto;

import com.educ.entity.Lesson;
import com.educ.entity.Modulee;
import com.educ.entity.Review;
import com.educ.entity.Video;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class ModuleeDTO implements Serializable {
    private Long id;
    private String title;
    private VideoDTO videoDTO;

    public ModuleeDTO() {
    }

    public ModuleeDTO(Long id, String title, VideoDTO videoDTO) {
        this.id = id;
        this.title = title;
        this.videoDTO = videoDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public VideoDTO getVideoDTO() {
        return videoDTO;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public void setVideoDTO(VideoDTO videoDTO) {
        this.videoDTO = videoDTO;
    }

    public ModuleeDTO convertTo(Modulee modulee){
        VideoDTO videoDTO;
        if (modulee.getVideo()!=null){
            videoDTO=new VideoDTO(modulee.getVideo().getId(), modulee.getVideo().getTitle(), modulee.getVideo().getUrl(), modulee.getVideo().getDuration());
        }else{
            videoDTO=null;
        }

        ModuleeDTO moduleeDTO=new ModuleeDTO(modulee.getId(), modulee.getTitle(), videoDTO);
        return moduleeDTO;
    }
}

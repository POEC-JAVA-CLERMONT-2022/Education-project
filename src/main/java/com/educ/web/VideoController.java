package com.educ.web;

import com.educ.data.VideoRepository;
import com.educ.entity.Video;
import com.educ.services.VideoService;
import com.educ.services.dto.VideoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@RestController
public class VideoController {

    Logger logger = LoggerFactory.getLogger(VideoController.class);

    @Autowired
    private VideoService videoService;

    @Autowired
    VideoRepository videoRepository;

    @GetMapping("/videos")
    public List<Video> getVideos(){
        List<Video> videos = videoRepository.findAll();
        return videos;
    }

    @GetMapping("videos/{id}")
    public Video getVideoById(@PathVariable Long id){
        logger.info("Given video {}",id);
        Video video = videoService.getById(id);
        return video;
    }

    @PostMapping("videos/add")
    public Video addVideo(@RequestBody VideoDTO videoDTO){
        return videoService.createVideo(videoDTO);
    }

    /* mouvaise pratique :
    @PutMapping("videos/{id}")
    public Video updateVideo(@PathVariable Long id, @RequestBody Video videoInfo){
        Video video  = videoRepository.getById(id);
        video.setTitle(videoInfo.getTitle());
        video.setDuration(videoInfo.getDuration());
        video.setUrl(videoInfo.getUrl());
        videoRepository.save(video);
        return video;
    }
     */

    @PutMapping("videos/{id}")
    public void updateVideo(@PathVariable Long id, @RequestParam VideoDTO videoDTO){
        videoService.updateVideo(id, videoDTO);
    }

    @DeleteMapping("videos/{id}")
    public void deleteVideo(@PathVariable Long id){
        videoRepository.deleteById(id);
    }
}

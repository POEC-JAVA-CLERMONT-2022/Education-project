package com.educ.web;

import com.educ.data.VideoRepository;
import com.educ.entity.Video;
import com.educ.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@RestController
public class VideoController {

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
        Video video = videoRepository.getById(id);
        return video;
    }

    @PostMapping("videos/add")
    public Video addVideo(@RequestBody Video video){
        return videoRepository.save(video);
    }

    /*
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
    public void updateVideo(@PathVariable Long id, @RequestParam String title, @RequestParam String url, @RequestParam @DateTimeFormat(pattern = "HH:mm:ss") LocalTime duration){
        videoService.updateVideo(id, title, url, duration);
    }

    @DeleteMapping("videos/{id}")
    public void deleteVideo(@PathVariable Long id){
        videoRepository.deleteById(id);
    }
}

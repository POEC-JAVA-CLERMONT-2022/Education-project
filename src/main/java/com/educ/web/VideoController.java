package com.educ.web;

import com.educ.entity.Video;
import com.educ.services.VideoService;
import com.educ.services.dto.VideoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/videos")
public class VideoController {

    Logger logger = LoggerFactory.getLogger(VideoController.class);

    private VideoService videoService;

    @Autowired
    public VideoController(VideoService videoService){
        this.videoService = videoService;
    }

    @GetMapping()
    public List<VideoDTO> getVideos(){
        List<Video> videos = videoService.findAll();
        List<VideoDTO> videoDTOS = new LinkedList<VideoDTO>();
        for (Video video:videos){
            VideoDTO videoDTO = new VideoDTO();
            videoDTOS.add(videoDTO.copyVideo(video));
        }
        return videoDTOS;
    }

    @GetMapping("{id}")
    public VideoDTO getVideoById(@PathVariable Long id){
        logger.info("Given video {}",id);
        VideoDTO videoDTO = new VideoDTO();
        videoDTO.copyVideo(videoService.getById(id));
        return videoDTO;
    }
    /*
    @PostMapping("add")
    public Video addVideo(@RequestBody VideoDTO videoDTO){
        VideoDTO videoDTO1 = new VideoDTO();
        Video video = videoService.createVideo(videoDTO);
        return videoDTO1.copyVideo(video);
    }

     */

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

    @PutMapping("{id}")
    public void updateVideo(@PathVariable Long id, @RequestParam VideoDTO videoDTO){
        //videoService.updateVideo(id, videoDTO);
    }

    @DeleteMapping("{id}")
    public void deleteVideo(@PathVariable Long id){
        videoService.deleteVideo(id);
    }
}

package com.educ.web;

import com.educ.entity.Video;
import com.educ.services.VideoService;
import com.educ.services.dto.VideoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
            videoDTOS.add(videoDTO.convertTo(video));
        }
        return videoDTOS;
    }

    @GetMapping("{id}")
    public ResponseEntity<VideoDTO> getVideoById(@PathVariable Long id){
        logger.info("Given video {}",id);
        VideoDTO videoDTO = new VideoDTO();
        videoDTO.convertTo(videoService.getById(id));
        return ResponseEntity.ok(videoDTO);

    }


    /*
    @PostMapping("add")
    public ResponseEntity<VideoDTO> addVideo(@RequestBody VideoDTO videoDTO){
        Video videoRequest = new Video();
        Video video = videoService.createVideo(videoRequest);
        VideoDTO videoResponse = videoDTO.convertTo(video);
        return new ResponseEntity<VideoDTO>(videoResponse, HttpStatus.CREATED);
    }
    */

    @PostMapping("add")
    public ResponseEntity<VideoDTO> addVideo(@RequestBody VideoDTO videoDTO){
        VideoDTO newVideo = new VideoDTO();
        //Video video = videoService.createVideo(videoDTO.getTitle(), videoDTO.getUrl(), videoDTO.getDuration());
        newVideo.convertTo(videoService.createVideo(videoDTO.getTitle(), videoDTO.getUrl(), videoDTO.getDuration()));
        return new ResponseEntity<>(newVideo, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public void updateVideo(@PathVariable Long id, @RequestBody VideoDTO videoDTO){
        videoService.updateVideo(id, videoDTO.getTitle(), videoDTO.getUrl(), videoDTO.getDuration());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Video> deleteVideo(@PathVariable Long id){
        videoService.deleteVideo(id);
        return ResponseEntity.ok().build();
    }
}

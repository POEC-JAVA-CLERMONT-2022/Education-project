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
import org.springframework.web.server.ResponseStatusException;

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
    public ResponseEntity<?> getVideos(){
        try {
            List<Video> videos = videoService.findAll();
            List<VideoDTO> videoDTOS = new LinkedList<VideoDTO>();
            for (Video video:videos){
                VideoDTO videoDTO = new VideoDTO();
                videoDTOS.add(videoDTO.convertTo(video));
            }
            return new ResponseEntity<>(videoDTOS, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Video not found");
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getVideoById(@PathVariable Long id){
        try {
            logger.info("Given video {}",id);
            if(id == null){
                return new ResponseEntity<> (HttpStatus.NOT_FOUND);
            }
            VideoDTO videoDTO = new VideoDTO();
            videoDTO.convertTo(videoService.getById(id));
            return new ResponseEntity<> (videoDTO, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Video not found");
        }
    }

    @PostMapping
    public ResponseEntity<?> addVideo(@RequestBody VideoDTO videoDTO){
        try{
            VideoDTO newVideo = new VideoDTO();
            newVideo.convertTo(videoService.createVideo(videoDTO.getTitle(), videoDTO.getUrl(), videoDTO.getDuration()));
            return new ResponseEntity<>(newVideo, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error to create video : ", e);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateVideo(@PathVariable Long id, @RequestBody VideoDTO videoDTO){
        try {
            if(id != null){
                videoService.updateVideo(id, videoDTO.getTitle(), videoDTO.getUrl(), videoDTO.getDuration());
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteVideo(@PathVariable Long id){
        try{
            Video video = videoService.getById(id);
            if(video == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            videoService.deleteVideo(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
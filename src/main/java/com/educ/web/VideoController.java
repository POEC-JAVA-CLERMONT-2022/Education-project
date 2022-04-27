package com.educ.web;

import com.educ.entity.Video;
import com.educ.services.VideoService;
import com.educ.services.dto.VideoDTO;
import com.educ.web.advice.ResponseCodeMessage;
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
    public ResponseEntity<List<VideoDTO>> getVideos(){
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
    public ResponseEntity<VideoDTO> getVideoById(@PathVariable Long id){
        try {
            logger.info("Given video {}",id);
            VideoDTO videoDTO = new VideoDTO();
            videoDTO.convertTo(videoService.getById(id));
            //return ResponseEntity.ok(videoDTO);
            return new ResponseEntity<> (videoDTO, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Video not found");
        }
    }

    @PostMapping("add")
    public ResponseEntity<VideoDTO> addVideo(@RequestBody VideoDTO videoDTO){
        try{
            VideoDTO newVideo = new VideoDTO();
            newVideo.convertTo(videoService.createVideo(videoDTO.getTitle(), videoDTO.getUrl(), videoDTO.getDuration()));
            return new ResponseEntity<>(newVideo, HttpStatus.CREATED); /* CODE 201 */
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error to create video : ", e);
            //return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseCodeMessage(409, e.getMessage()));
        }
    }

    @PutMapping("{id}")
    public void updateVideo(@PathVariable Long id, @RequestBody VideoDTO videoDTO){
        try {
            videoService.updateVideo(id, videoDTO.getTitle(), videoDTO.getUrl(), videoDTO.getDuration());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Video> deleteVideo(@PathVariable Long id){
        try{
            videoService.deleteVideo(id);
            return ResponseEntity.ok().build();
        } catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Video not found");
        }
    }
}
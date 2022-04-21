package com.educ.services;

import com.educ.data.VideoRepository;
import com.educ.entity.Review;
import com.educ.entity.Video;
import com.educ.services.dto.VideoDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@DisplayName("test du video service")
public class VideoServiceTest {

    @InjectMocks
    VideoService videoService;

    @Mock
    VideoRepository mockVideoRepository;

    @Test
    @DisplayName("creation video test")
    public void testCreationVideo(){
        //create data

        //ARRANGE
        String title="Spring Boot Tutorial";
        String url="https://www.youtube.com/watch?v=c3gKseNAs9w&ab_channel=DailyCodeBuffer";
        LocalTime duration=LocalTime.of(3,11,17);
        when(mockVideoRepository.save(Mockito.any(Video.class))).thenReturn(new Video(title, url, duration));
        VideoDTO videoDTO=new VideoDTO(title, url, duration);

        //ACT
        Video testVideo = videoService.createVideo(title, url, duration);

        //ASSERT
        assertNotNull(testVideo);
        assertEquals(testVideo.getId(), null);
        assertThat(testVideo).isNotNull();
        assertThat(testVideo.getId()).isNull();
        assertThat(testVideo).isEqualTo(new Video(title, url, duration));
        assertThat(testVideo).usingRecursiveComparison().isEqualTo(new Video(title, url, duration));
        verify(mockVideoRepository, times(1)).save(any(Video.class));
    }
/*
    @Test
    @DisplayName("update video test")
    public void testUpdateVideo(){
        String title="Spring Boot Tutorial";
        String url="https://www.youtube.com/watch?v=c3gKseNAs9w&ab_channel=DailyCodeBuffer";
        LocalTime duration=LocalTime.of(3,11,17);
        Video video=this.videoService.createVideo(title,url,duration);
        List<Video> videos=videoService.findAll();
        this.videoService.updateVideo(1L,"Spring Data JPA Tutorial","https://www.youtube.com/watch?v=XszpXoII9Sg&ab_channel=DailyCodeBuffer",LocalTime.of(1,11,40));
        List<Video> videosUpdated=this.videoService.findAll();
        assertEquals(videos.size(),videosUpdated.size());
        assertTrue(videosUpdated.get(0).getTitle().equals("Spring Data JPA Tutorial")) ;
        this.videoService.updateVideo(1L,"Spring Data ",null,LocalTime.of(1,11,40));
        videosUpdated=videoService.findAll();
        assertFalse(videosUpdated.get(0).getTitle().equals("Spring Data"));
        this.videoService.updateVideo(9L,"Spring Data JPA Tutorial","https://www.youtube.com/watch?v=XszpXoII9Sg&ab_channel=DailyCodeBuffer",LocalTime.of(1,11,40));
        videos=this.videoService.findAll();
        assertTrue(videosUpdated.size()==1 && videosUpdated.get(0).getId()!=9L);
    }

    @Test
    @DisplayName("delete video test")
    public void testDeleteVideo(){
        String title="Spring Boot Tutorial";
        String url="https://www.youtube.com/watch?v=c3gKseNAs9w&ab_channel=DailyCodeBuffer";
        LocalTime duration=LocalTime.of(3,11,17);
        Video video=this.videoService.createVideo(title,url,duration);
        List<Video> videos=videoService.findAll();
        assertNotNull(video);
        assertEquals(videos.size(),1);
        this.videoService.deleteVideo(1L);
        videos=videoService.findAll();
        assertEquals(videos.size(),0);
        assertFalse(videos.contains(video));
        video=this.videoService.createVideo("JAVA","https://java.com",duration);
        videos=this.videoService.findAll();
        this.videoService.deleteVideo(10L);
        videos=this.videoService.findAll();
        assertTrue(videos.contains(video));


    }

 */


}

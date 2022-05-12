package com.educ.services;

import com.educ.data.VideoRepository;
import com.educ.entity.Video;
import com.educ.services.dto.VideoDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@DisplayName("test du video service Mockito")
public class VideoServiceTestMockito {

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
        VideoDTO videoDTO=new VideoDTO(1L,title, url, duration);

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
}

package com.educ.services;

import com.educ.entity.Role;
import com.educ.entity.Video;
import com.educ.services.dto.VideoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educ.data.VideoRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;

@Service
public class VideoService {
	@Autowired
	private VideoRepository videoRepository;

	@Transactional(readOnly = true)
	public List<Video> findAll(){
		return videoRepository.findAll();
	}

	@Transactional(readOnly = true)
	public boolean existId(Long id) {
		List<Video> videos=this.videoRepository.findAll();
		for(Video video:videos){
			if(video.getId()==id){
				return true;
			}
		}
		return false;
	}

	@Transactional(readOnly = true)
	public Video getById(Long id) {
		if(this.existId(id)){
			return videoRepository.getById(id);
		}else{
			return null;
		}
	}

	@Transactional(readOnly = true)
	public Video findByUrl(String url){
		if(url!=null){
			List<Video> videos=this.findAll();
			for (Video video:videos){
				if (video.getUrl().equals(url)){
					return video;
				}
			}
			return null;
		}else { return null;}
	}


	@Transactional
	//public Video createVideo(String title, String url, LocalTime duration){
	public Video createVideo(VideoDTO videoDTO){
			if ((videoDTO.getUrl() != null) && this.findByUrl(videoDTO.getUrl()) == null){
				Video video=new Video(videoDTO.getTitle(), videoDTO.getUrl(), videoDTO.getDuration());
				this.videoRepository.save(video);
				return video;
			}else{
				return null;
			}
	}

	@Transactional
	//public void updateVideo (Long id, String title, String url, LocalTime duration){
	public void updateVideo (Long id, VideoDTO videoDTO){
		if(videoDTO.getUrl() != null){
			if (this.existId(id) &&  (this.findByUrl(videoDTO.getUrl())==null)){
				Video video=this.getById(id);
				video.setTitle(videoDTO.getTitle());
				video.setUrl(videoDTO.getUrl());
				video.setDuration(videoDTO.getDuration());
				this.videoRepository.save(video);
			}
		}

	}

	@Transactional
	public void deleteVideo(Long id){
		Video video=this.getById(id);
		if(video !=null){
			this.videoRepository.delete(video);
		}
	}

}

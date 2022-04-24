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

	private VideoRepository videoRepository;

	@Autowired
	public VideoService(VideoRepository videoRepository) {
		this.videoRepository = videoRepository;
	}

	public List<Video> findAll(){
		return videoRepository.findAll();
	}

	public boolean existId(Long id) {
		List<Video> videos=this.videoRepository.findAll();
		for(Video video:videos){
			if(video.getId()==id){
				return true;
			}
		}
		return false;
	}

	public Video getById(Long id) {
		if(this.existId(id)){
			return videoRepository.getById(id);
		}
		return null;

	}

	public Video findByUrl(String url){
		if (url==null){
			return null;
		}
		return this.videoRepository.findByUrl(url);
	}

	@Transactional
	public Video createVideo(String title, String url, LocalTime duration){
	//public Video createVideo(Video video){
		if(url==null){ return null; }
		if(this.findByUrl(url) != null){ return this.findByUrl(url); }
		Video video=new Video(title, url, duration);
		video=this.videoRepository.save(video);
		return video;
		//return videoRepository.save(video);
	}

	@Transactional
	public void updateVideo (Long id, String title, String url, LocalTime duration){
		if(url!=null && this.existId(id)){
			Video v=this.findByUrl(url);
			if(v==null || v.getId()==id){
				Video video=this.getById(id);
				video.setTitle(title);
				video.setUrl(url);
				video.setDuration(duration);
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

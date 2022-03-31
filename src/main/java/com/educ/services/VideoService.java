package com.educ.services;

import com.educ.entity.Role;
import com.educ.entity.Video;
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
	public Video getById(Long id) {return videoRepository.getById(id);}

	@Transactional(readOnly = true)
	public Video findByUrl(String url){
		return this.videoRepository.findByUrl(url);
	}

	@Transactional
	public Video createVideo(String title, String url, LocalTime duration){
		if (this.videoRepository.findByUrl(url) == null){
			Video video=new Video(title,url,duration);
			this.videoRepository.save(video);
			return video;
		}else{
			return null;
		}
	}

	@Transactional
	public void updateVideo (Long id, String title, String url, LocalTime duration){

		if ((this.videoRepository.getById(id) != null) &&  (this.videoRepository.findByUrl(url)==null)){
			Video video=this.videoRepository.getById(id);
			video.setTitle(title);
			video.setUrl(url);
			video.setDuration(duration);
			this.videoRepository.save(video);
		}
	}

	@Transactional
	public void deleteRole(Long id){
		Video video=this.videoRepository.getById(id);
		if(video !=null){
			this.videoRepository.delete(video);
		}
	}

}

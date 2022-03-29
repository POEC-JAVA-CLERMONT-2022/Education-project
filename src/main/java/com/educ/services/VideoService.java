package com.educ.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educ.data.VideoRepository;

@Service
public class VideoService {
	@Autowired
	private VideoRepository videoRepository;

}

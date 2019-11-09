package com.wp.video.service;

import com.wp.video.domain.Video;

import java.util.List;


public interface VideoService {

    public List<Video> findAll();

    public Video findById(int id);

    public int update(Video video);

    public Object delete(int id);

    public Object save(Video video);
}

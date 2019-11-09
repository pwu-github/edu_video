package com.wp.video.service.impl;

import com.wp.video.domain.Video;
import com.wp.video.mapper.VideoMapper;
import com.wp.video.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;

    @Override
    public List<Video> findAll() {
        return videoMapper.findAll();
    }

    @Override
    public Video findById(int id) {
        return videoMapper.findById(id);
    }

    @Override
    public int update(Video video) {
        return videoMapper.update(video);
    }

    @Override
    public Object delete(int id) {
        return videoMapper.delete(id);
    }

    @Override
    public Object save(Video video) {
        return videoMapper.save(video);
    }
}

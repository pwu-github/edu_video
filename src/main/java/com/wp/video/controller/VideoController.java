package com.wp.video.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wp.video.domain.Video;
import com.wp.video.service.VideoService;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    /**
     * 分页查询
     * @param page 默认第一页
     * @param size 默认显示10条
     * @return
     */
    @GetMapping("/findAll")
    public Object findAll(@RequestParam(value = "page",defaultValue = "1")int page,
                                @RequestParam(value = "size",defaultValue = "10")int size){
        PageHelper.startPage(page,size);
        List<Video> videoList = videoService.findAll();
        PageInfo<Video> pageInfo = new PageInfo<>(videoList);
        Map<String, Object> map = new HashMap<>();
        map.put("total_size",pageInfo.getTotal());
        map.put("total_page",pageInfo.getPages());
        map.put("current_page",page);
        map.put("data",pageInfo.getList());
        return map;
    }

    /**
     * 根据videoId查询
     * @param videoId 视频ID，video_id最好用下划线模式，因为有些情况下不支持驼峰模式。required = true表示此参数是必须的
     * @return
     */
    @GetMapping("/findById")
    public Video findById(@RequestParam(value = "video_id",required = true) int videoId){
        return videoService.findById(videoId);
    }


}

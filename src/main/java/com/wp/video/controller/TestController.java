package com.wp.video.controller;

import com.wp.video.config.WeChatConfig;
import com.wp.video.domain.JsonData;
import com.wp.video.domain.Video;
import com.wp.video.mapper.VideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    private WeChatConfig weChatConfig;
    @Autowired
    private VideoMapper videoMapper;

    @RequestMapping("/hello")
    public String hello(){

        return "hello world!!!";
    }

    @RequestMapping("/testConfig")
    public JsonData testConfig(){
        return JsonData.buildSuccess(weChatConfig.getAppid());
    }

    //测试数据库是否链接成功
    @RequestMapping("/findAll")
    public List<Video> findAll(){
        return videoMapper.findAll();
    }
}

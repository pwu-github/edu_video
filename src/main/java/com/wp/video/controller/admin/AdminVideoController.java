package com.wp.video.controller.admin;

import com.wp.video.domain.Video;
import com.wp.video.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 只有管理员用户才可以进行更新、插入、删除操作。方便后续权限管理
 */
@RestController
@RequestMapping("/admin/api/v1/video")
public class AdminVideoController {

    @Autowired
    private VideoService videoService;

    /**
     * 根据ID更新视频
     * @param video
     * @return
     */
    @PutMapping("/update")
    public int update(@RequestBody Video video){

        return videoService.update(video);
    }

    /**
     * 删除视频
     * @param videoId
     * @return
     */
    @DeleteMapping("/delete")
    public Object deleteById(@RequestParam(value = "video_id",required = true)int videoId){
        return videoService.delete(videoId);
    }

    /**
     * 插入视频对象
     * @param video
     * @return
     */
    @PostMapping("/save")
    public Object save(@RequestBody Video video){
        return videoService.save(video);
    }
}

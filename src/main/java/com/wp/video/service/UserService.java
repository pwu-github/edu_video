package com.wp.video.service;

import com.wp.video.domain.User;

/**
 * 用户业务接口
 */
public interface UserService {

    public User saveWechatUser(String code);

}

package com.wp.video.service.impl;

import com.wp.video.config.WeChatConfig;
import com.wp.video.domain.User;
import com.wp.video.mapper.UserMapper;
import com.wp.video.service.UserService;
import com.wp.video.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private WeChatConfig weChatConfig;
    @Autowired
    private UserMapper userMapper;

    @Override
    public User saveWechatUser(String code) {
        String accessTokenUrl = String.format(WeChatConfig.getOpenAccessTokenUrl(),weChatConfig.getOpenAppid(),weChatConfig.getOpenAppsecret(),code);
        //获取access_token
        Map<String,Object> map = HttpUtils.doGet(accessTokenUrl);
        if(map == null || map.isEmpty()){
            return null;
        }
        String accessToken = (String)map.get("access_token");
        String openid = (String)map.get("openid");
        /**
         * 根据openid查找用户，如果用户不是空，直接返回
         */
        User dbUser = userMapper.findByOpenid(openid);
        if(dbUser != null){
            return dbUser;
        }
        //获取用户基本信息
        String userInfoUrl = String.format(WeChatConfig.getOpenUserInfoUrl(),accessToken,openid);
        Map<String,Object> userMap = HttpUtils.doGet(accessTokenUrl);
        if(userMap == null || userMap.isEmpty()){
            return null;
        }
        String nickname = (String)userMap.get("nickname");

        Double sexDou = (Double)userMap.get("sex");
        int sex = sexDou.intValue();
        String province = (String)userMap.get("province");
        String city = (String)userMap.get("city");
        String country = (String)userMap.get("country");
        String headimgurl = (String)userMap.get("headimgurl");
        /**
         * 城市信息,包括国家和省份，用 || 分割方便后面切分出国家 省份 城市信息   中国||湖北||十堰
         */
        StringBuilder sb = new StringBuilder(country).append("||").append(province).append("||").append(city);
        String address = sb.toString();
        try {
            //解决乱码
            nickname = new String(nickname.getBytes("ISO-8859-1"),"UTF-8");
            address = new String(address.getBytes("ISO-8859-1"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //封装user
        User user = new User();
        user.setName(nickname);
        user.setHeadImg(headimgurl);
        user.setCity(address);
        user.setOpenid(openid);
        user.setSex(sex);
        user.setCreateTime(new Date());
        userMapper.save(user);
        return user;
    }
}

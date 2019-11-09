package com.wp.video.controller;

import com.wp.video.config.WeChatConfig;
import com.wp.video.domain.JsonData;
import com.wp.video.domain.User;
import com.wp.video.service.UserService;
import com.wp.video.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
@RequestMapping("/api/v1/wechat")
public class WeChatController {

    @Autowired
    private WeChatConfig weChatConfig;

    @Autowired
    private UserService userService;

    /**
     * 拼装微信扫一扫登录URL
     */
    @GetMapping("/login_url")
    @ResponseBody
    public JsonData loginUrl(@RequestParam(value = "access_page",required = true)String access_page) throws UnsupportedEncodingException {
        //获取开放平台重定向地址，微信扫码登陆成功后返回的开放平台地址
        String redirectUrl = weChatConfig.getOpenRedirectUrl();
        //编码
        String callbackUrl = URLEncoder.encode(redirectUrl,"GBK");
        String qrcodeUrl = String.format(weChatConfig.getOpenQrcodeUrl(),weChatConfig.getOpenAppid(),callbackUrl,access_page);
        return JsonData.buildSuccess(qrcodeUrl);
    }

    /**
     * 微信扫码用户回调地址
     */
    @GetMapping("/user/callback")
    public void wechatUserCallback(@RequestParam(value = "code",required = true)String code,
                                   String state, HttpServletResponse response) throws Exception{
        User user = userService.saveWechatUser(code);
        if(user != null){
            //生成jwt
            String token = JwtUtils.geneJsonWebToken(user);
            response.sendRedirect(state+"?token="+token+"&head_img="+user.getHeadImg()+"&name="+URLEncoder.encode(user.getName(),"UTF-8"));
        }
    }

}

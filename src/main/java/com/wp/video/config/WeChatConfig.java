package com.wp.video.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value="classpath:application.properties")
public class WeChatConfig {
    /**
     * 微信开放平台二维码连接
     */
    private final static String OPEN_QRCODE_URL= "https://open.weixin.qq.com/connect/qrconnect?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_login&state=%s#wechat_redirect";

    /**
     * 开放平台获取access_token
     */
    private static final String OPEN_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";

    /**
     * 获取用户信息URL
     */
    private static final String OPEN_USER_INFO_URL = "https://api.weixin.qq.com/sns/auth?access_token=%s&openid=%s&lang=zh_CN";

    //公众号appid
    @Value("${wxpay.appid}")
    private String appid;

    //公众号秘钥
    @Value("${wxpay.appsecret}")
    private String appsecret;

    //开放平台appid
    @Value("${wxopen.appid}")
    private String openAppid;

    //开放平台appsecret
    @Value("${wxopen.appsecret}")
    private String openAppsecret;

    //开放平台redirect_url
    @Value("${wxopen.redirect_url}")
    private String openRedirectUrl;

    public String getOpenAppid() {
        return openAppid;
    }

    public void setOpenAppid(String openAppid) {
        this.openAppid = openAppid;
    }

    public String getOpenAppsecret() {
        return openAppsecret;
    }

    public void setOpenAppsecret(String openAppsecret) {
        this.openAppsecret = openAppsecret;
    }

    public String getOpenRedirectUrl() {
        return openRedirectUrl;
    }

    public void setOpenRedirectUrl(String openRedirectUrl) {
        this.openRedirectUrl = openRedirectUrl;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }

    public static String getOpenQrcodeUrl() {
        return OPEN_QRCODE_URL;
    }

    public static String getOpenAccessTokenUrl() {
        return OPEN_ACCESS_TOKEN_URL;
    }

    public static String getOpenUserInfoUrl() {
        return OPEN_USER_INFO_URL;
    }
}

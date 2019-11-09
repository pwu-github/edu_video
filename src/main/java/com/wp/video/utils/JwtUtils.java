package com.wp.video.utils;

import com.wp.video.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * JWT工具类
 */
public class JwtUtils {

    public static final String SUBJECT = "xdclass";
    public static final long EXPIRE = 1000 * 60 * 60 * 24 * 7; //过期时间 一周
    public static final String APPSECRET = "wp666";  //设置秘钥

    /**
     * 生成token
     * @param user
     * @return
     */
    public static String geneJsonWebToken(User user){
        if(user == null || user.getId() == null || user.getName() == null || user.getHeadImg() == null){
            return null;
        }
        String token = Jwts.builder().setSubject(SUBJECT)
                        .claim("id",user.getId())
                        .claim("name",user.getName())
                        .claim("img",user.getHeadImg())
                        .setIssuedAt(new Date())
                        .setExpiration(new Date(System.currentTimeMillis()+EXPIRE))
                        .signWith(SignatureAlgorithm.HS256,APPSECRET).compact();
        return token;
    }

    /**
     * 解密,校验token
     */
    public static Claims checkJwt(String token){
        try{
            final Claims claims = Jwts.parser().setSigningKey(APPSECRET).parseClaimsJws(token).getBody();
            return claims;
        }catch (Exception e){

        }
        return null;
    }

}

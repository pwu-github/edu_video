package com.example.demo;

import com.wp.video.domain.User;
import com.wp.video.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;

public class CommonTest {

    @Test
    public void testGeneJwt(){
        User user = new User();
        user.setId(999);
        user.setHeadImg("wrwrwrefs");
        user.setName("xd");
        String token = JwtUtils.geneJsonWebToken(user);
        System.out.println(token);
    }

    @Test
    public void testCHeck(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ4ZGNsYXNzIiwiaWQiOjk5OSwibmFtZSI6InhkIiwiaW1nIjoid3J3cndyZWZzIiwiaWF0IjoxNTczMDM2NzA0LCJleHAiOjE1NzM2NDE1MDR9.RPquFFHRUgEssd7WYIwQ1dOcbHks2o38yvsiUkrkxiA";
        Claims claims = JwtUtils.checkJwt(token);
        if(claims != null){
            String name = (String)claims.get("name");
            int id = (Integer)claims.get("id");
            String img = (String)claims.get("img");
            System.out.println(id);
            System.out.println(name);
            System.out.println(img);
        }else{
            System.out.println("非法token，解密失败！");
        }

    }
}

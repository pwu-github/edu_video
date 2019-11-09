package com.wp.video.interceptor;

import com.google.gson.Gson;
import com.wp.video.domain.JsonData;
import com.wp.video.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

//用户登陆拦截
public class LoginInterceptor implements HandlerInterceptor {

    private static final Gson gson = new Gson();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if(token == null){
            token = request.getParameter("token");
        }
        if(token != null){
            Claims claims = JwtUtils.checkJwt(token);
            if(claims != null){
                Integer userId = (Integer)claims.get("id");
                String name = (String)claims.get("name");
                request.setAttribute("user_id",userId);
                request.setAttribute("naem",name);
                return true;
            }
        }
        sendJsonMessage(response, JsonData.buildError("请登录"));
        return false;
    }

    /**
     * 响应数据给前端
     */
    public static void sendJsonMessage(HttpServletResponse response,Object object){

        try{
            response.setContentType("application/json; charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.print(gson.toJson(object));
            writer.close();
            response.flushBuffer();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

package com.wp.video.mapper;

import com.wp.video.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

//user数据访问，相当于dao层
public interface UserMapper {

    /**
     * 根据userId查找
     * @param userId
     * @return
     */
    @Select("select * from user where id = #{userId}")
    public User findById(@Param("id") int userId);

    /**
     * 根据openid查找
     * @param openid
     * @return
     */
    @Select("select * from user where openid = #{openid}")
    public User findByOpenid(@Param("openid") String openid);

    /**
     * 保存用户
     * @param user  #{headImg}表示从user对象中取出对应的属性，用的是驼峰模式，数据库字段是下划线模式
     * @return
     */
    @Insert("INSERT INTO `user` VALUES (#{openid}, #{name}, #{headImg}, #{phone}, #{sign}, #{sex}, #{city}, #{createTime});")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    public int save(User user);


}

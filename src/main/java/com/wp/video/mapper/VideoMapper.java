package com.wp.video.mapper;

import com.wp.video.domain.Video;
import com.wp.video.provider.VideoProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

//video数据访问，相当于dao层
public interface VideoMapper {

    @Select("select * from video")
    public List<Video> findAll();

    @Select("select * from video where id = #{id}")
    public Video findById(int id);

    //@Update("update video set title = #{title} where id = #{id}")
    //利用动态sql更新video
    @UpdateProvider(type= VideoProvider.class,method="updateVideo")
    public int update(Video video);

    @Delete("delete from video where id = #{id}")
    public Object delete(int id);

    @Insert("INSERT INTO `video` ( `title`, `summary`, " +
            "`cover_img`, `view_num`, `price`, `create_time`," +
            " `online`, `point`)" +
            "VALUES" +
            "(#{title}, #{summary}, #{coverImg}, #{viewNum}, #{price},#{createTime}" +
            ",#{online},#{point});")
    //保存对象，获取数据库自增id,需要返回给微信，确认订单相关信息
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    public Object save(Video video);

}

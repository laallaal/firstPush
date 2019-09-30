package com.qfedu.mapper;

import com.qfedu.entry.Video;
import com.qfedu.entry.VideoQueryVo;

import java.util.List;

public interface VideoMapper {

    List<Video> selectAllVideo(VideoQueryVo videoQueryVo);

    int addVideo(Video video);

    int selectAllVideoNum(VideoQueryVo videoQueryVo);

    int delBatchVideos(VideoQueryVo videoQueryVo);

    int updateVideo(Video video);

    List<Video> selectAllVideoByCourseId(int courseId);

    Video selectVideoById(Integer videoId);





    void addVideoPlayNum(Video video);
}

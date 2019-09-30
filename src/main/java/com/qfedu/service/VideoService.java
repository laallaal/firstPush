package com.qfedu.service;

import com.qfedu.entry.Video;
import com.qfedu.entry.VideoQueryVo;

import java.util.List;

public interface VideoService {




    List<Video> getVideoList(VideoQueryVo videoQueryVo);


    Boolean addVideo(Video video);

    int getTotelNum(VideoQueryVo videoQueryVo);

    int delBatchVideos(Integer[] ids);

    boolean updateVideo(Video video);

    List<Video> selectAllVideoByCourseId(int courseId);

    Video selectVideoById(Integer videoId);




    void addVideoPlayNum(Video video);
}

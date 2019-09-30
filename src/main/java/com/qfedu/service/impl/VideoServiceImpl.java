package com.qfedu.service.impl;

import com.qfedu.entry.Video;
import com.qfedu.entry.VideoQueryVo;
import com.qfedu.mapper.VideoMapper;
import com.qfedu.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class  VideoServiceImpl implements VideoService {

    @Autowired
    VideoMapper mapper;

    @Override
    public List<Video> getVideoList(VideoQueryVo videoQueryVo) {

        List<Video> list = mapper.selectAllVideo(videoQueryVo);

        return list;
    }

    @Override
    public Boolean addVideo(Video video) {

        int i = mapper.addVideo(video);

        return i > 0 ? true : false;
    }

    @Override
    public int getTotelNum(VideoQueryVo videoQueryVo) {

        int num = mapper.selectAllVideoNum(videoQueryVo);

        return num;
    }

    @Override
    public int delBatchVideos(Integer[] ids) {

        VideoQueryVo videoQueryVo = new VideoQueryVo();
        videoQueryVo.setIds(Arrays.asList(ids));
        /*System.out.println(videoQueryVo.getIds());
        System.out.println("222222222222222");*/
        int rows = mapper.delBatchVideos(videoQueryVo);

        return rows;
    }

    @Override
    public boolean updateVideo(Video video) {

        int rows = mapper.updateVideo(video);

        return rows > 0 ? true : false;
    }

    @Override
    public List<Video> selectAllVideoByCourseId(int courseId) {

        List<Video> videoList = mapper.selectAllVideoByCourseId(courseId);

        for (Video video : videoList) {
            System.out.println(video);
        }

        return videoList;
    }

    @Override
    public Video selectVideoById(Integer videoId) {

        System.out.println("111111111111111111111");

        Video video = mapper.selectVideoById(videoId);

        System.out.println("videoaaaaaaaaaaaaaaaa:"+video);

        return video;
    }

    @Override
    public void addVideoPlayNum(Video video) {
        mapper.addVideoPlayNum(video);
    }


}

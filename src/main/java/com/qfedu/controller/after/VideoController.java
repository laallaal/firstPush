package com.qfedu.controller.after;


import com.qfedu.entry.Course;
import com.qfedu.entry.Speaker;
import com.qfedu.entry.Video;
import com.qfedu.entry.VideoQueryVo;
import com.qfedu.service.CourseService;
import com.qfedu.service.SpeakerService;
import com.qfedu.service.VideoService;
import com.qfedu.utils.InfoUtils;
import com.qfedu.utils.Page;
import com.qfedu.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/video")
public class VideoController {

   /* static List<Video> list =new ArrayList<Video>();

    static {

        list.add(new Video(1,"qqqqqqqq","里面还有大量猛料，欢迎观看！！","老闫","01:36:00",100));
        list.add(new Video(2,"wwwwwwwwwww","里面还有大量猛料，欢迎观看！！","老闫","01:36:00",100));
        list.add(new Video(3,"eeeeeeeeeee","里面还有大量猛料，欢迎观看！！","老闫","01:36:00",100));
        list.add(new Video(4,"rrrrrrrrrr","里面还有大量猛料，欢迎观看！！","老闫","01:36:00",100));
        list.add(new Video(5,"tttttttttt","里面还有大量猛料，欢迎观看！！","老闫","01:36:00",100));
        list.add(new Video(6,"yyyyyyyyyyy","里面还有大量猛料，欢迎观看！！","老闫","01:36:00",100));
        list.add(new Video(7,"uuuuuuuuuuuuuuu","里面还有大量猛料，欢迎观看！！","老闫","01:36:00",100));
    }*/

    @Autowired
    SpeakerService speakerService;

    @Autowired
    CourseService courseService;

    @Autowired
    VideoService videoService;


    @RequestMapping("/list")
    public String showList(VideoQueryVo videoQueryVo, Model model) {

        videoQueryVo.setBegin((videoQueryVo.getPage() - 1) * videoQueryVo.getRows());
       /* System.out.println(videoQueryVo.getTitle());
        System.out.println(videoQueryVo.getSpeakerId());
        System.out.println(videoQueryVo.getCourseId());*/


        List<Video> list = videoService.getVideoList(videoQueryVo);
        //System.out.println("list的长度"+list.size());


        int totelNum = videoService.getTotelNum(videoQueryVo);


        Page<Video> page = new Page<>();
        page.setTotal(totelNum);
        page.setPage(videoQueryVo.getPage());
        page.setSize(videoQueryVo.getRows());
        page.setRows(list);
        //System.out.println(page.toString());
        model.addAttribute("page", page);

        List<Speaker> speakers = speakerService.selectAllSpeaker();
        model.addAttribute("speakerList", speakers);

        List<Course> courses = courseService.selectAllCourse();
        model.addAttribute("courseList", courses);


        return "behind/videoList";
    }

    @RequestMapping("/addVideo")
    public String addVideo(Model model) {
        List<Speaker> speakers = speakerService.selectAllSpeaker();
        model.addAttribute("speakerList", speakers);

        List<Course> courses = courseService.selectAllCourse();
        model.addAttribute("courseList", courses);
        return "behind/addVideo";
    }


    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public String saveOrUpdate(Video video) {
        System.out.println("11111111111111111111111" + video);

        if (video.getId() != 0) {
            if (videoService.updateVideo(video)) {
                return "success";
            }

        } else {
            if (videoService.addVideo(video)) {
                return "success";
            }

        }
        return "false";

    }

    @RequestMapping("/upload")
    @ResponseBody
    public String upload(MultipartFile headImg) {

        System.out.println(headImg.getOriginalFilename());
        String oldName = headImg.getOriginalFilename();
        String houzhui = oldName.substring(oldName.lastIndexOf("."));

        String newName = UUIDUtils.getUUID() + houzhui;

        String imgUrl = InfoUtils.getProperties("IMG_URL") + newName;

        try {
            headImg.transferTo(new File(InfoUtils.getProperties("IMG_PATH"), newName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return imgUrl;

    }

    @RequestMapping("/videoDel")
    @ResponseBody
    public String videoDel(Integer id) {

        System.out.println("id:" + id);

        return "success";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {

        System.out.println("editID:" + id);

        VideoQueryVo videoQueryVo = new VideoQueryVo();
        List<Video> list = videoService.getVideoList(videoQueryVo);
        for (Video video : list) {
            if (video.getId() == id) {
                model.addAttribute("video", video);

                System.out.println("videoId:" + video.getId());
                break;
            }
        }

        List<Speaker> speakers = speakerService.selectAllSpeaker();
        model.addAttribute("speakerList", speakers);

        List<Course> courses = courseService.selectAllCourse();
        model.addAttribute("courseList", courses);


        return "/behind/addVideo";

    }


    @RequestMapping("/delBatchVideos")
    public String delBatchVideos(Integer[] ids) {

        System.out.println(Arrays.toString(ids));

        int rows = videoService.delBatchVideos(ids);

        return "redirect:/video/list";

    }


    @RequestMapping("/showVideo")
    public String showVideo(Integer videoId, String subjectName, Model model) {

        Video video = videoService.selectVideoById(videoId);

        model.addAttribute("video", video);
        model.addAttribute("subjectName", subjectName);

        List<Video> videoList = videoService.selectAllVideoByCourseId(video.getCourseId());



        Course course = courseService.selectAllCourseById(video.getCourseId());

        System.out.println("course::::::::" + course);

        model.addAttribute("course", course);



        model.addAttribute("videoList", videoList);
        return "before/section";

    }


    @RequestMapping("/updatePlayNum")
    @ResponseBody
    public void playNumByClick(int videoId, int playNum) {

        System.out.println("6666666666666666666666666666666");

        System.out.println(playNum +":::::::::::::"+ videoId);

        int playNum1 = playNum +1;

        Video video = new Video();
        video.setId(videoId);
        video.setPlayNum(playNum1);

        videoService.addVideoPlayNum(video);




    }

















}

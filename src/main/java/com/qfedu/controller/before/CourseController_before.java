package com.qfedu.controller.before;


import com.qfedu.entry.Course;
import com.qfedu.entry.Subject;
import com.qfedu.entry.Video;
import com.qfedu.service.CourseService;
import com.qfedu.service.SubjectService;
import com.qfedu.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/beforeCourse")
public class CourseController_before {

    @Autowired
    SubjectService subjectService;

    @Autowired
    CourseService courseService;

    @Autowired
    VideoService videoService;



    @RequestMapping("/list")
    public String showCourseList(Integer subjectId, Model model) {


        System.out.println("11111111111111111111111111111111111111111111111111111111111111111");

        Subject subject = subjectService.selectSubjectById(subjectId);

        List<Course> courseList = courseService.selectAllCourseBySubjectId(subjectId);

        for (Course course : courseList) {

            int courseId = course.getId();

            List<Video> videoList = videoService.selectAllVideoByCourseId(courseId);

            course.setVideoList(videoList);

        }



        subject.setCourseList(courseList);

        System.out.println(subject);

        model.addAttribute("subject",subject);


        return "before/course";

    }

}

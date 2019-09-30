package com.qfedu.controller.after;

import com.github.pagehelper.PageInfo;
import com.qfedu.entry.Course;
import com.qfedu.entry.Subject;
import com.qfedu.service.CourseService;
import com.qfedu.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController_after {


    @Autowired
    CourseService courseService;

    @Autowired
    SubjectService subjectService;

    @RequestMapping("/list")
    public String showCourseList(Model model, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int pageSize) {
        List<Course> coursesList = courseService.selectAllCourse(page, pageSize);

        for (Course course : coursesList) {
            int id = course.getSubjectId();
            //System.out.println("id:  " + id);
            Subject subject = subjectService.selectSubjectById(id);
            //System.out.println(subject);


            course.setSubjectName(subject.getSubjectName());
        }

        //model.addAttribute("courseList", coursesList);

        PageInfo<Course> pageInfo = new PageInfo<>(coursesList);

        model.addAttribute("pageInfo", pageInfo);




        return "behind/courseList";
    }


    @RequestMapping("/addCourse")
    public String saveOrUpdate(Model model) {

        List<Subject> subjectList = subjectService.selectAllSubject();
        model.addAttribute("subjectList", subjectList);

        return "behind/addCourse";
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public String saveOrUpdate(Model model, Course course) {
        System.out.println("11111111111111111111");
        System.out.println(course);

        if (course.getId() != 0) {
            System.out.println("修改");

            Boolean isUpdate = courseService.updateCourseById(course);

            System.out.println(isUpdate);
            if (isUpdate) {
                return "success";
            }

        } else {
            System.out.println("添加");

            Boolean isAdd = courseService.addCourse(course);

            if (isAdd) {
                return "success";
            }

        }

        return "false";
    }


    @RequestMapping("/edit/{id}")
    public String editCourse(@PathVariable("id") Integer id, Model model) {


        List<Course> courses = courseService.selectAllCourse();

        for (Course course : courses) {

            if (course.getId() == id) {
                model.addAttribute("course", course);
            }

        }

        List<Subject> subjectList = subjectService.selectAllSubject();
        model.addAttribute("subjectList", subjectList);


        return "behind/addCourse";

    }

    @RequestMapping("courseDel")
    @ResponseBody
    public String courseDel(int id) {

        boolean isDel = courseService.deleteCourseById(id);

        if (isDel) {
            return "success";
        }
        return "false";


    }





    //************************************************************************************************************************************************************************** **************************************
    //以下是前端的course操作
    // ****************************************************************************************************************************************************************************************************************











}

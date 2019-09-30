package com.qfedu.service.impl;

import com.github.pagehelper.PageHelper;
import com.qfedu.entry.Course;
import com.qfedu.mapper.CourseMapper;
import com.qfedu.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

   @Autowired
   CourseMapper mapper;

    @Override
    public List<Course> selectAllCourse() {

        List<Course> list = mapper.selectAllCourse();

        return list;
    }

    @Override
    public List<Course> selectAllCourse(int page, int pageSize) {

        PageHelper.startPage(page,pageSize);
        List<Course> list = mapper.selectAllCourse();

        return list;
    }

    @Override
    public boolean deleteCourseById(int id) {

        int rows = mapper.deleteCourseById(id);


        return rows > 0 ? true : false;
    }

    @Override
    public Boolean addCourse(Course course) {


        System.out.println("11111"+course);

        int rows = mapper.addCourse(course);


        return rows > 0 ? true : false;
    }

    @Override
    public Course selectAllCourseById(Integer id) {

        Course course = mapper.selectAllCourseById(id);

        return course;
    }

    @Override
    public Boolean updateCourseById(Course course) {


        int rows = mapper.updateCourseById(course);


        return rows > 0 ? true : false;
    }







    //**********************************************************************************************************************************************************************
    // **********************************************************************************************************************************************************************


    @Override
    public List<Course> selectAllCourseBySubjectId(Integer subjectId) {

        List<Course> courseList = mapper.selectAllCourseBySubjectId(subjectId);
        return courseList;
    }
}

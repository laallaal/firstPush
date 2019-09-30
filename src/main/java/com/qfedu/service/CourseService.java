package com.qfedu.service;

import com.qfedu.entry.Course;

import java.util.List;

public interface CourseService {
    List<Course> selectAllCourse();
    List<Course> selectAllCourse(int page, int pageSize);

    boolean deleteCourseById(int id);

    Boolean addCourse(Course course);

    Course selectAllCourseById(Integer id);

    Boolean updateCourseById(Course course);


    //**********************************************************************************************************************************************************************

    // **********************************************************************************************************************************************************************


    List<Course> selectAllCourseBySubjectId(Integer subjectId);
}

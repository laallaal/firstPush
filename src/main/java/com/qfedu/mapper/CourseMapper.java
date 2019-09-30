package com.qfedu.mapper;

import com.qfedu.entry.Course;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@CacheNamespace(blocking = true)
public interface CourseMapper {

    @Select("select * from course")
    @Results(id = "CourseMap",
            value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "courseTitle", column = "course_title"),
            @Result(property = "courseDesc", column = "course_desc"),
            @Result(property = "subjectId", column = "subject_id"),

    }
    )
    List<Course> selectAllCourse();



    @Delete("delete from course where id = #{id}")
    int deleteCourseById(int id);


    @Insert("insert into course(course_title, course_desc, subject_id) values(#{courseTitle}, #{courseDesc}, #{subjectId})")
    int addCourse(Course course);



    @Select("select * from course where id = #{id}")
    @ResultMap("CourseMap")
    Course selectAllCourseById(Integer id);


    @Update("update course set course_title = #{courseTitle}, course_desc = #{courseDesc}, subject_id = #{subjectId} where id = #{id} ")
    int updateCourseById(Course course);



    @Select("select * from course where subject_id = #{subjectId} ")
    @ResultMap("CourseMap")
    List<Course> selectAllCourseBySubjectId(Integer subjectId);
}

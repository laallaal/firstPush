package com.qfedu.entry;

import java.io.Serializable;
import java.util.List;

public class Subject implements Serializable {

    private int id;
    private String subjectName;
    private List<Course> courseList;


    public Subject() {
    }

    public Subject(int id, String subjectName, List<Course> courseList) {
        this.id = id;
        this.subjectName = subjectName;
        this.courseList = courseList;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }


    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", subjectName='" + subjectName + '\'' +
                ", courseList=" + courseList +
                '}';
    }
}

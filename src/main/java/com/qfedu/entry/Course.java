package com.qfedu.entry;

import java.io.Serializable;
import java.util.List;

public class Course implements Serializable {
    private int id;
    private String courseTitle;
    private String courseDesc;
    private int subjectId;
    private String subjectName;
    private List<Video> videoList;


    public Course() {
    }

    public Course(int id, String courseTitle, String courseDesc, int subjectId, String subjectName) {
        this.id = id;
        this.courseTitle = courseTitle;
        this.courseDesc = courseDesc;
        this.subjectId = subjectId;
        this.subjectName = subjectName;
    }


    public List<Video> getVideoList() {
        return videoList;
    }

    public void setVideoList(List<Video> videoList) {
        this.videoList = videoList;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseDesc() {
        return courseDesc;
    }

    public void setCourseDesc(String courseDesc) {
        this.courseDesc = courseDesc;
    }


    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseTitle='" + courseTitle + '\'' +
                ", courseDesc='" + courseDesc + '\'' +
                ", subjectId=" + subjectId +
                ", subjectName='" + subjectName + '\'' +
                ", videoList=" + videoList +
                '}';
    }
}

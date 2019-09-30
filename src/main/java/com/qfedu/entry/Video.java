package com.qfedu.entry;

import com.qfedu.utils.DateChangeUtils;

public class Video {


    private int id;
    private String title;
    private String detail;

    private String speakerName;
    private int speakerId;
    private int courseId;
    private int time;
    private String videoUrl;
    private String videoImgUrl;

    private String showTime;
    private int playNum;

    private Speaker speaker;

    public Video() {

    }

    public Video(int id, String title, String detail, String speakerName, String showTime, int playNum) {
        this.id = id;
        this.title = title;
        this.detail = detail;
        this.speakerName = speakerName;
        this.showTime = showTime;
        this.playNum = playNum;
    }


    public Speaker getSpeaker() {
        return speaker;
    }

    public void setSpeaker(Speaker speaker) {
        this.speaker = speaker;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getSpeakerName() {
        return speakerName;
    }

    public void setSpeakerName(String speakerName) {
        this.speakerName = speakerName;
    }

    public String getShowTime() {
        return DateChangeUtils.dateChange(time);
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public int getPlayNum() {
        return playNum;
    }

    public void setPlayNum(int playNum) {
        this.playNum = playNum;
    }

    public int getSpeakerId() {
        return speakerId;
    }

    public void setSpeakerId(int speakerId) {
        this.speakerId = speakerId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoImgUrl() {
        return videoImgUrl;
    }

    public void setVideoImgUrl(String videoImgUrl) {
        this.videoImgUrl = videoImgUrl;
    }


    @Override
    public String toString() {
        return "Video{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", detail='" + detail + '\'' +
                ", speakerName='" + speakerName + '\'' +
                ", speakerId=" + speakerId +
                ", courseId=" + courseId +
                ", time=" + time +
                ", videoUrl='" + videoUrl + '\'' +
                ", videoImgUrl='" + videoImgUrl + '\'' +
                ", showTime='" + showTime + '\'' +
                ", playNum=" + playNum +
                ", speaker=" + speaker +
                '}';
    }
}

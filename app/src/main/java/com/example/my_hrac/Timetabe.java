package com.example.my_hrac;

import java.util.List;

public class Timetabe {
    private  String lecture;
    private  String subjectCode;
    private String academicYear;
    private String startingtime;
    private String endingtime;
    private String lectureHall;
    private List<CustomDate> Dates;

    public String getLecture() {
        return lecture;
    }

    public void setLecture(String lecture) {
        this.lecture = lecture;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public String getStartingtime() {
        return startingtime;
    }

    public void setStartingtime(String startingtime) {
        this.startingtime = startingtime;
    }

    public String getEndingtime() {
        return endingtime;
    }

    public void setEndingtime(String endingtime) {
        this.endingtime = endingtime;
    }

    public String getLectureHall() {
        return lectureHall;
    }

    public void setLectureHall(String lectureHall) {
        this.lectureHall = lectureHall;
    }

    public List<CustomDate> getDates() {
        return Dates;
    }

    public void setDates(List<CustomDate> dates) {
        Dates = dates;
    }
}

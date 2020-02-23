package com.example.my_hrac;

import java.util.List;

public class Timetabe {
    private String lecturer;
    private  String subjectCode;
    private String academicYear;
    private String startingtime;
    private String endingtime;
    private String lectureHall;
    private String stream;
    private List<CustomDate> Dates;





   private Timetabe(String lecturer,String subjectCode,String academicYear,String startingtime,String stream,String endingtime,String lectureHall,List<CustomDate> dates){
        this.lecturer=lecturer;
        this.subjectCode=subjectCode;
        this.academicYear=academicYear;
        this.startingtime=startingtime;
        this.lectureHall=lectureHall;
        this.Dates=dates;
        this.stream=stream;
        this.endingtime=endingtime;
    }

    private Timetabe(){

    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }



    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;    }

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

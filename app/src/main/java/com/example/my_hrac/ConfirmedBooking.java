package com.example.my_hrac;

import java.util.Date;

public class ConfirmedBooking {

    private String endingtime;
    private String lectureHall;
    private String subjectCode;
    private String date;
    private String startingtime;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    private ConfirmedBooking(String endingtime, String lecturehall, String subjectCode, String Date, String startingtime)
    {

        this.endingtime=endingtime;
        this.lectureHall =lecturehall;
        this.subjectCode=subjectCode;

        this.date=Date;
        this.startingtime=startingtime;
    }

    private ConfirmedBooking(){

    }


    public String getEndingtime() {
        return endingtime;
    }

    public void setEndingtime(String endingtime) {
        this.endingtime = endingtime;
    }

    public String getLecturehall() {
        return lectureHall;
    }

    public void setLecturehall(String lecturehall) {
        this.lectureHall = lecturehall;
    }






    public String getStartingtime() {
        return startingtime;
    }

    public void setStartingtime(String startingtime) {
        this.startingtime = startingtime;
    }

}

package com.example.my_hrac;

import java.util.Date;

public class ConfirmedBooking {

    private String date;
    private String endingtime;
    private String startingtime;
    private String subjectCode;

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




    private ConfirmedBooking(String date,String endingtime,String startingtime,String subjectCode){

        this.startingtime=startingtime;
        this.endingtime=endingtime;
        this.startingtime=startingtime;
        this.subjectCode=subjectCode;

    }


    private ConfirmedBooking(){

    }



    public String getEndingtime() {
        return endingtime;
    }

    public void setEndingtime(String endingtime) {
        this.endingtime = endingtime;
    }



    public String getStartingtime() {
        return startingtime;
    }

    public void setStartingtime(String startingtime) {
        this.startingtime = startingtime;
    }
}

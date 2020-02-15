package com.example.my_hrac;

import java.util.Date;

public class ConfirmedBooking {
    private Boolean confirmed;
    private String endingtime;
    private String reason;
    private Date requestedDate;
    private String selectDate;
    private String startingtime;

    private ConfirmedBooking(String reason,Date requestedDate,String startingtime,String endingtime){
        this.reason=reason;
        this.requestedDate=requestedDate;
        this.startingtime=startingtime;
        this.endingtime=endingtime;

    }


    private ConfirmedBooking(){

    }

    public Boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }

    public String getEndingtime() {
        return endingtime;
    }

    public void setEndingtime(String endingtime) {
        this.endingtime = endingtime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(Date requestedDate) {
        this.requestedDate = requestedDate;
    }

    public String getSelectDate() {
        return selectDate;
    }

    public void setSelectDate(String selectDate) {
        this.selectDate = selectDate;
    }

    public String getStartingtime() {
        return startingtime;
    }

    public void setStartingtime(String startingtime) {
        this.startingtime = startingtime;
    }
}

package com.example.my_hrac;

public class Attendance {
    private String IndexNumber;
    private String date;
    private  boolean isAttended;

    public String getIndexNumber() {
        return IndexNumber;
    }

    public void setIndexNumber(String indexNumber) {
        IndexNumber = indexNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isAttended() {
        return isAttended;
    }

    public void setAttended(boolean attended) {
        isAttended = attended;
    }
}

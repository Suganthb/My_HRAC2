package com.example.my_hrac;

public class Attendance {
    private String indexNumber;
    private String date;
    private  boolean attended;
    //private boolean done;


    public String getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(String indexNumber) {
        this.indexNumber = indexNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isAttended() {
        return attended;
    }

    public void setAttended(boolean attended) {
        this.attended = attended;
    }

    /*public boolean done() {
        return done;
    }

    public void setDone(boolean dones) {
        done = dones;
    }*/

}

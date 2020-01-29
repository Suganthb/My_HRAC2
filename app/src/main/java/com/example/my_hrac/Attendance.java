package com.example.my_hrac;

public class Attendance {
    private String IndexNumber;
    private String date;
    private  boolean isAttended;
    //private boolean done;

    private Attendance(String indexNumber,String date,boolean isAttended){
        this.IndexNumber=indexNumber;
        this.date=date;
        this.isAttended=isAttended;
    }

    private Attendance(){

    }

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

    /*public boolean done() {
        return done;
    }

    public void setDone(boolean dones) {
        done = dones;
    }*/

}

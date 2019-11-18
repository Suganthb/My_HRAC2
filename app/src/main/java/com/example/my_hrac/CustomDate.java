package com.example.my_hrac;

import android.util.Log;

public class CustomDate {
    Integer day;
    String id;
    Integer month;
    Integer year;

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    //compare two dates
    public  boolean compareDate(int day,int month,int year){

       boolean res = day==this.day && month ==this.month && year==this.year;
        Log.d("TIME_COMPARE", day +": " +this.day + "_ "+month+":"+this.month+"_ "+year+":"+this.year+"::"+res);

        return res;

    }
}

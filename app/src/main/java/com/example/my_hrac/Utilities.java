package com.example.my_hrac;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utilities {

    //check current date sent in this fromat
    public static int getDay(long times){
        Date date =  new Date(times);
        SimpleDateFormat format = new SimpleDateFormat("dd");
        return Integer.parseInt(format.format(date));
    }

    //check current month send it in this format
    public static int getMonth(long times){
        Date date =  new Date(times);
        SimpleDateFormat format = new SimpleDateFormat("MM");
        return Integer.parseInt(format.format(date));
    }

    //check current year send it in form
    public static int getYear(long times){
        Date date =  new Date(times);
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        return Integer.parseInt(format.format(date));
    }

    //display current date in view
    public static String getDate(long times){
        Date date =  new Date(times);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    //display current time in view
    public static String getTime(long times){
        Date date =  new Date(times);
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(date);
    }

    //check time between the two time usind calendar
    public static boolean isTimeBetween(String d1, String d2,String d3){
        try {
            String string1 = d1;
            Date time1 = new SimpleDateFormat("HH:mm").parse(string1);
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(time1);
            calendar1.add(Calendar.DATE, 1);


            String string2 = d2;
            Date time2 = new SimpleDateFormat("HH:mm").parse(string2);
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(time2);
            calendar2.add(Calendar.DATE, 1);

            String someRandomTime = d3;
            Date d = new SimpleDateFormat("HH:mm").parse(someRandomTime);
            Calendar calendar3 = Calendar.getInstance();
            calendar3.setTime(d);
            calendar3.add(Calendar.DATE, 1);

            Date x = calendar3.getTime();
            return x.after(calendar1.getTime()) && x.before(calendar2.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

}

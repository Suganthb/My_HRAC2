package com.example.my_hrac;

import com.google.firebase.firestore.Exclude;

import java.util.HashMap;
import java.util.List;

public class GetLecDe {
    private String subjectCode;
    private String startingtime;
    private String lecture;
    private String lectureHall;
    private String endingtime;
    private String academicYear;
   // List<String> Dates;
    List<HashMap<String,String>> Dates;
    private String documentId;

    public GetLecDe(){

    }

    public GetLecDe(String subjectcoede,String startingtime,String lecture,String lectureHall,String endingtime,String academicYear){
        this.subjectCode=subjectcoede;
        this.startingtime=startingtime;
        this.lecture=lecture;
        this.lectureHall=lectureHall;
        this.endingtime=endingtime;
        this.academicYear=academicYear;
        //this.Dates=Dates;
    }

    @Exclude
    public String getDocumentId(){
        return documentId;
    }

    public void setDocumentId(String documentId){
        this.documentId = documentId;
    }

    public String getSubjectCode(){
        return subjectCode;
    }
    public String getStartingtime(){
        return startingtime;
    }
    public String getLecture(){
        return  lecture;
    }
    public String getLectureHall(){
        return lectureHall;
    }
    public String getEndingtime(){
        return endingtime;
    }
    public String getAcademicYear(){
        return academicYear;
    }
   // public List<String> getDates() {
     //   return Dates;
    //}
}


package com.example.my_hrac;

public class AddCheck {
    private String indexNumber;
    private String date;
    private  boolean attended;
    private String documentId;


    public AddCheck(String indexNumber,String date,boolean isAttended,String documentId){
        this.indexNumber=indexNumber;
        this.date=date;
        this.attended=isAttended;
        this.documentId=documentId;

    }
    public AddCheck(){}



    public String getDocumentId() { return documentId; }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(String indexNumber) {
        indexNumber = indexNumber;
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
        attended = attended;
    }
}

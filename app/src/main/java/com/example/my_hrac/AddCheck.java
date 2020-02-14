package com.example.my_hrac;

public class AddCheck {
    private String IndexNumber;
    private String date;
    private  boolean isAttended;
    private String documentId;


    private AddCheck(String indexNumber,String date,boolean isAttended,String documentId){
        this.IndexNumber=indexNumber;
        this.date=date;
        this.isAttended=isAttended;
        this.documentId=documentId;

    }

    public String getDocumentId() { return documentId; }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
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
}

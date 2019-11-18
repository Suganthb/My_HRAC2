package com.example.my_hrac;

public class InRfid {
    private String IndexNumber;
    private String RFIDNumber;

    public InRfid(String indexNumber,String rfidnumber){
        this.IndexNumber=indexNumber;
        this.RFIDNumber=rfidnumber;
    }

    public InRfid() {
    }

    public String getIndexNumber(){
        return IndexNumber;
    }
    public void setIndexNumber(String indexNumber){
         IndexNumber=indexNumber;
    }
    public String getRFIDNumber(){
        return RFIDNumber;
    }
    public void setRFIDNumber(String rfidNumber){
        RFIDNumber=rfidNumber;
    }
}

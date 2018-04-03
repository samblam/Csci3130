package com.example.saikishoreeppalagudem.csci3130;

/**
 * Created by Sowmya Umesh on 4/3/2018.
 */

public class Terms {

     String termID;
     String regDeadline;

    public String getTermID() {
        return termID;
    }

    public void setTermID(String termID) {
        this.termID = termID;
    }

    public String getRegDeadline() {
        return regDeadline;
    }

    public void setRegDeadline(String regDeadline) {
        this.regDeadline = regDeadline;
    }

    @Override
    public String toString() {
        return
                termID + ','+ regDeadline;
    }

    public Terms(String termID, String regDeadline){
        this.termID = termID;
        this.regDeadline = regDeadline;


    }
}



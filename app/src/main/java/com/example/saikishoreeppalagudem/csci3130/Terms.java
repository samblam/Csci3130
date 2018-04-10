package com.example.saikishoreeppalagudem.csci3130;

/**
 * @author Sowmya Umesh on 4/3/2018.
 * @author Documented by Sam
 */

public class Terms {
    /**
     * String that stores the Id for a term
     */
     String termID;
    /**
     * String that stores the registration deadline for a specific term
     */
    String regDeadline;

     public Terms(){}

    /**
     *
     * @return termID
     */
    public String getTermID() {
        return termID;
    }

    /**
     *
     * @param termID
     */
    public void setTermID(String termID) {
        this.termID = termID;
    }

    /**
     *
     * @return registration deadline
     */
    public String getRegDeadline() {
        return regDeadline;
    }

    /**
     *
     * @param regDeadline
     */
    public void setRegDeadline(String regDeadline) {
        this.regDeadline = regDeadline;
    }

    @Override
    /**
     * Converts object variables to a string
     */
    public String toString() {
        return termID + ','+ regDeadline;
    }

    /**
     *
     * @param termID
     * @param regDeadline
     */
    public Terms(String termID, String regDeadline){
        this.termID = termID;
        this.regDeadline = regDeadline;


    }
}



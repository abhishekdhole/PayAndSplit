package com.example.paynsplit;

public class userTransaction {

    public String groupID;
    public String userID;
    public String emailID;
    public userTransaction() {
    }

    public userTransaction(String userID,String groupID,String emailID) {

        this.userID=userID;
        this.groupID=groupID;
        this.emailID=emailID;

    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }
}

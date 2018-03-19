package com.example.saikishoreeppalagudem.csci3130;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;

/**
 * @author Sam Barefoot
 * @author Documented by Sam Barefoot
 */


public class User{

    private String pass;
    private String login;
    private Boolean login_state;

    public User(String loginin, String passin, Boolean login) {

       this.login = loginin;
       this.pass = passin;
       this.login_state = login;
    }
    public void setPass(String input){pass = input;}
    public void setLogin(String input){login = input;}
    public void setLogin_state(Boolean input){
            login_state = input;
        }

     public String getPass(){return pass;}
     public String getLogin(){return login;}
     public Boolean getLogin_state(){return login_state;}








}

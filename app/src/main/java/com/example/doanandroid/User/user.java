package com.example.doanandroid.User;

import java.io.Serializable;

public class user implements Serializable {
        private String gmail,pass,name;
        private int id;


    public user() {
    }

    public user(String gmail, String pass, String name, int id) {
        this.gmail = gmail;
        this.pass = pass;
        this.name = name;
        this.id = id;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

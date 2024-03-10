package com.example.doanandroid.DB.Truyen;

import java.io.Serializable;

public class Truyen implements Serializable {
    int id;
    String name;
    String avatar;
    int theloai_ID;
    String theloai_Name;

    public int getTheloai_ID() {
        return theloai_ID;
    }

    public void setTheloai_ID(int theloai_ID) {
        this.theloai_ID = theloai_ID;
    }

    public String getTheloai_Name() {
        return theloai_Name;
    }

    public void setTheloai_Name(String theloai_Name) {
        this.theloai_Name = theloai_Name;
    }



    public Truyen(int id, String name, String avatar) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        if (id > 0) {
            return (name + "-" + theloai_Name);
        }
        return name;
    }
}

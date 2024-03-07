package com.example.doanandroid.DB.Theloai;

public class TheLoai {
    int id;
    String name;
    public TheLoai() {}
    public TheLoai(int id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return name.toString();
    }
}

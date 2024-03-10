package com.example.doanandroid.DB.Chapter;

public class Chapter {
    int id;
    int stt;
    String name;
    String content;
    int id_truyen;

    public Chapter(int id, String name, String content, int stt)
    {
        this.id = id;
        this.name = name;
        this.content = content;
        this.stt = stt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId_truyen() {
        return id_truyen;
    }

    public void setId_truyen(int id_truyen) {
        this.id_truyen = id_truyen;
    }
}

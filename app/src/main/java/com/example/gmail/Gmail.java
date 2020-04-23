package com.example.gmail;

import java.util.Random;

public class Gmail {
    String name, subject, content, time;
    boolean isFavourite;
    int color;

    public Gmail(String name, String time, String subject, String content ) {
        this.name = name;
        this.subject = subject;
        this.content = content;
        this.time = time;
        isFavourite = false;
        Random random = new Random();
        color = random.nextInt();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}

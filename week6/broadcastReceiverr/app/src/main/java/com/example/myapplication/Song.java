package com.example.myapplication;

import java.io.Serializable;

public class Song implements Serializable {
    private String title;
    private String single;
    private int imgae;
    private int resource;


    public Song(String title, String single, int imgae, int resource) {
        this.title = title;
        this.single = single;
        this.imgae = imgae;
        this.resource = resource;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSingle() {
        return single;
    }

    public void setSingle(String single) {
        this.single = single;
    }

    public int getImgae() {
        return imgae;
    }

    public void setImgae(int imgae) {
        this.imgae = imgae;
    }

    public int getResource() {
        return resource;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }
}

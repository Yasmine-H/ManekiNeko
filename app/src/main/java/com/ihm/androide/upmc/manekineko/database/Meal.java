package com.ihm.androide.upmc.manekineko.database;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.io.Serializable;

/**
 * Created by HAMDANI on 28/04/2018.
 */

public class Meal implements Serializable {

    private int id;
    private String name;
    private float price;
    private String photo;
    //private Drawable drawable;
    private String description;
    //private ArrayList<String> tagList;
    private String[] tagList;
    private int totalVotes;
    private int nbVotes;
    private String type;
    private transient Bitmap bitmap;

    public Meal(String name, String description, /*ArrayList<String> tagList,*/String[] tagList, int totalVotes, int nbVotes, String type) {
        this(0, name, null, description, tagList, totalVotes, nbVotes, type);
    }

    public Meal(int id, String name, String photo, String description, /*ArrayList<String> tagList,*/String[] tagList, int totalVotes, int nbVotes, String type) {
        this.id = id;
        this.name = name;
        this.photo = photo;
        this.description = description;
        this.tagList = tagList;
        this.totalVotes = totalVotes;
        this.nbVotes = nbVotes;
        this.type = type;
        this.bitmap = null;

    }

    public String getName() {
        return name;
    }

    public String getPhoto() {
        return photo;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }


    public void setBitmap(Bitmap bitmap) {
        Log.d(getClass().getName(), "meal ===================" + name);
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }

    public String[] getTagList() {
        return tagList;
    }

}
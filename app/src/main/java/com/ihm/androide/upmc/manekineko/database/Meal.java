package com.ihm.androide.upmc.manekineko.database;

import java.util.ArrayList;

/**
 * Created by HAMDANI on 28/04/2018.
 */

public class Meal {

    private int id;
    private String name;
    private String photo;
    private String description;
    private ArrayList<String> tagList;

    /*
    public Meal(String name, String description, ArrayList<String> tagList){
        this(name, null, description, tagList);
    }

    public Meal(String name, String photo, String description, ArrayList<String> tagList){
        this(new Generator().generateMealId(), name, photo, description, tagList);
    }

    public Meal(int id, String name, String photo, String description, ArrayList<String> tagList){
        this.id=id;
        this.name=name;
        this.photo=photo;
        this.description=description;
        this.tagList=tagList;
    }
    */
    public String getName(){
        return name;
    }

    public String getPhoto(){
        return photo;
    }

    public int getId(){
        return id;
    }

}

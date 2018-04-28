package com.ihm.androide.upmc.manekineko.database;

import android.util.ArrayMap;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by HAMDANI on 28/04/2018.
 */

public class User {

    private long id;
    private String name;
    private String password;
    private String email;
    private String photo;
    private Map<Meal, Integer> favoriteMeals;

    public User(String name, String password){
        //this(new Generator().generateUserId(), name, password, null, null, new HashMap<Meal, Integer>());
        this(0, name, password, null, null, new HashMap<Meal, Integer>());
    }

    public User(String name, String password, String email){
        //this(new Generator().generateUserId(), name, password, email, null, new HashMap<Meal, Integer>());
        this(0, name, password, email, null, new HashMap<Meal, Integer>());
    }

    public User(String name, String password, String email, String photo){
        this(0, name, password, email, photo, new HashMap<Meal, Integer>());
    }
    public User(long id, String name, String password, String email, String photo, Map<Meal, Integer> favoriteMeals){
        this.name = name;
        this.password = password;
        this.id = id;
        this.email = email;
        this.photo = photo;
        this.favoriteMeals = favoriteMeals;
    }

    public String getName(){
        return name;
    }

    public String getPassword(){
        return password;
    }

    public String getEmail(){
        return email;
    }

    public String getPhoto(){
        return photo;
    }

    public long getId(){
        return id;
    }

    public Map<Meal, Integer> getFavoriteMealsMeals(){
        return favoriteMeals;
    }

    public void setName(String name){
        this.name = name;
        //update database ?
    }

    public void setPhoto(String photo){
        this.photo=photo;
    }

    public void setEmail(String email){
        this.email=email;
    }

    public void addFavoriteMeal(Meal meal, int stars){
        favoriteMeals.put(meal, stars);
    }

    public boolean checkUserValidity(){
        if(name != null && name.length()>=3){
            if(password!=null && password.length()>=8){
                if(email==null){
                    return true;
                }
                else
                {
                    String[] emailSplit = email.split("@");
                    if(emailSplit.length == 2 && !emailSplit[0].equals("") && emailSplit[1].split("\\.").length>=2){
                        return true;
                    }
                }
            }
        }

        return false;
    }

}

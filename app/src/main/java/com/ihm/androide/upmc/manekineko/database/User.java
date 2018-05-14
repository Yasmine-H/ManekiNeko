package com.ihm.androide.upmc.manekineko.database;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by HAMDANI on 28/04/2018.
 */

public class User implements Serializable{


    private String name;
    private String email;
    private String unique_id;
    private String password;
    private String old_password;
    private String new_password;
    private String photo;
    private Map<Meal, Integer> favoriteMeals;
    private ArrayList<Meal> order;


    public User(){
        name = "";
        email = "";
        order=new ArrayList<>();
    }

    public User(String name){
        this();
        this.name=name;
    }

    public User(String name, String email){
        this();
        this.name=name;
        this.email=email;
    }
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUnique_id() {
        return unique_id;
    }

    public Map<Meal, Integer> getFavoriteMealsMeals(){
        return favoriteMeals;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setOld_password(String old_password) {
        this.old_password = old_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }

    public String toString(){
        return  "id : "+unique_id+
                "name : "+name+
                "password : "+password+
                "email : "+email+
                "photo : "+photo;//+
                //"favorite meals : "+username; //TODO
    }

    public void addFavoriteMeal(Meal meal, int stars){
        favoriteMeals.put(meal, stars);
    }

    public void addMealToOrderList(Meal meal){
        order.add(meal);
    }

    public void removeMealFromOrderList(String name){
        int i;
        for(i=0; i<order.size(); i++){
            if(order.get(i).getName().equals(name)){
             break;
            }
        }
        if(i<order.size()){
            order.remove(i);
        }
    }
    /*
    public boolean checkUserValidity(){
        if(username != null && username.length()>=3){
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
    */
}

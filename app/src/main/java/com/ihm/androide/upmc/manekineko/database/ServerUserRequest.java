package com.ihm.androide.upmc.manekineko.database;

/**
 * Created by HAMDANI on 29/04/2018.
 */

public class ServerUserRequest {

    private String operation;
    private User user;
    //private Meal meal;

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setUser(User user) {
        this.user = user;
    }

    //public void setMeal(Meal meal) { this.meal = meal; }
}

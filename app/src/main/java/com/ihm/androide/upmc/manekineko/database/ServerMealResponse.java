package com.ihm.androide.upmc.manekineko.database;

import java.util.ArrayList;

/**
 * Structure of a response (meals) received from the server after sending a request
 */

class ServerMealResponse {
    private String result;
    private String message;
    private ArrayList<Meal> meals;

    public String getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<Meal> getMeals() {
        return meals;
    }

}

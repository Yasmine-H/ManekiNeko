package com.ihm.androide.upmc.manekineko.database;

import java.util.ArrayList;

/**
 * Created by HAMDANI on 28/04/2018.
 */

public class Generator {

    private ArrayList<String> userIdList;
    private ArrayList<Meal> mealIdList;

    public Generator(){
        //get userList
    }

    public int generateUserId(){
        int id = userIdList.size();
        return id;
    }

    public int generateMealId(){
        int id = mealIdList.size();
        return id;
    }
}

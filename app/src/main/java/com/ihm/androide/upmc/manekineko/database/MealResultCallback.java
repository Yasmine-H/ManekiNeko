package com.ihm.androide.upmc.manekineko.database;

import android.support.annotation.NonNull;

import java.util.ArrayList;

/**
 * Created by HAMDANI on 06/05/2018.
 */

public interface MealResultCallback {

    void onSuccess(ArrayList<Meal> meals);

    void onError(Throwable throwable);
}

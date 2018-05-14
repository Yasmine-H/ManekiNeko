package com.ihm.androide.upmc.manekineko.database;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Class for loading meals from database
 */

public class MealsLoader {

    Gson gson;
    Retrofit retrofit;
    RequestInterface requestInterface;
    ArrayList<Meal> meals;
    boolean done;
    Context context;






    public MealsLoader(Context context){
        gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        */
        requestInterface = retrofit.create(RequestInterface.class);
        meals = null;
        this.context=context;
    }


    public void askForAppetizerList(MealResultCallback mealResultCallback){
        askForMealsOfType(Constants.appetizer, mealResultCallback);
    }

    public void askForFirstCourseList(MealResultCallback mealResultCallback){
        askForMealsOfType(Constants.firstCourse, mealResultCallback);
    }

    public void askForMainCourseList(MealResultCallback mealResultCallback){
        askForMealsOfType(Constants.mainCourse, mealResultCallback);
    }

    public void askForDessertList(MealResultCallback mealResultCallback){
        askForMealsOfType(Constants.dessert, mealResultCallback);
    }

    public void askForDrinkList(MealResultCallback mealResultCallback){
        askForMealsOfType(Constants.drink, mealResultCallback);
    }

    public void askForMealsOfType(String type, final MealResultCallback mealResultCallback){

        done = false;
        meals = null;
        Call<ServerMealResponse> response = requestInterface.fetchMealsOfType(Constants.FETCH_MEALS_OF_TYPE_OPERATION, type);
        //Call<ServerMealResponse> response = requestInterface.fetchAllMeals(Constants.FETCH_MEALS_OPERATION);
        Log.d(getClass().getName(), "Loading data...");
        response.enqueue(new Callback<ServerMealResponse>() {
            @Override
            public void onResponse(Call<ServerMealResponse> call, retrofit2.Response<ServerMealResponse> response) {

                ServerMealResponse resp = response.body();

                Log.d(getClass().getName(),resp.getMessage()+" and value : ");
                //ArrayList<Meal> meals;
                if(resp != null)
                {
                    Log.d(getClass().getName(),resp.getMessage());//+resp.getMeals().size());

                    //saveMeals(resp.getMeals());

                    meals = resp.getMeals();
                    if(meals == null){
                        Log.d(getClass().getName(),"\nnull returned");
                    }
                    else {
                        Log.d(getClass().getName(),"\nnumber of results : "+meals.size());
                        for (Meal meal : meals) {
                            Log.d(getClass().getName(),"\nmeal : " + meal.getName()+" type : "+meal.getType());
                        }


                        /*ImageView imageView = findViewById(R.id.imageView);
                        String[] photo = meals.get(0).getPhoto().split("\\\\");

                        userInfoView.append("\n"+photo);
                        Resources resources = getBaseContext().getResources();
                        final int resourceId = resources.getIdentifier(photo[1], photo[0],
                                getBaseContext().getPackageName());
                        imageView.setImageResource(resourceId);
                        */
                    }

                }

                else
                {
                    Log.d(getClass().getName(),"resp is null");
                    //errorMsg("null");
                }
                //Snackbar.make(getView(), , Snackbar.LENGTH_LONG).show();
                //progress.setVisibility(View.INVISIBLE);
                //done = true;
                mealResultCallback.onSuccess(meals);

            }

            @Override
            public void onFailure(Call<ServerMealResponse> call, Throwable t) {

                //progress.setVisibility(View.INVISIBLE);
                Log.d(Constants.TAG,"failed "+t.getMessage());
                t.printStackTrace();
                Log.d(getClass().getName(),t.getLocalizedMessage());
                //Snackbar.make(getView(), t.getLocalizedMessage(), Snackbar.LENGTH_LONG).show();
                //done = true;
                //errorMsg(t.getLocalizedMessage());
                mealResultCallback.onError(t);
            }
        });

        //while(!done){
            //Log.d("ggggggggggg", "waitiiiiing meals = "+meals);
        //
    }

    public void askForAllMeals(final MealResultCallback mealResultCallback){
        meals = null;
        Call<ServerMealResponse> response = requestInterface.fetchAllMeals(Constants.FETCH_MEALS_OPERATION);
        Log.d(getClass().getName(), "Loading data...");
        response.enqueue(new Callback<ServerMealResponse>() {
            @Override
            public void onResponse(Call<ServerMealResponse> call, retrofit2.Response<ServerMealResponse> response) {

                ServerMealResponse resp = response.body();

                Log.d(getClass().getName(),resp.getMessage()+" and value : ");
                //ArrayList<Meal> meals;
                if(resp != null)
                {
                    Log.d(getClass().getName(),resp.getMessage());//+resp.getMeals().size());

                    //saveMeals(resp.getMeals());

                    meals = resp.getMeals();
                    if(meals == null){
                        Log.d(getClass().getName(),"\nnull returned");
                    }
                    else {
                        Log.d(getClass().getName(),"\nnumber of results : "+meals.size());
                        for (Meal meal : meals) {
                            String[] photo = meal.getPhoto().split("\\\\");
                            if(photo.length == 2)
                            {
                                Log.d(getClass().getName(),"\nmeal : " + meal.getName()+" type : "+meal.getType()+" titre : 0 : "+photo[0]+" 1 :"+photo[1]);
                            }
                            else
                            {
                                Log.d(getClass().getName(),"\n!!!!!!!!!meal : " + meal.getName()+" type : "+meal.getType()+" titre : 0 : "+photo[0]);
                            }
                            /*

                            String[] photo = meals.get(0).getPhoto().split("\\\\");

                            Resources resources = context.getResources();
                            final int resourceId = resources.getIdentifier(photo[1], photo[0],
                                    context.getPackageName());

                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                                Drawable drawable =context.getDrawable(resourceId);
                                meal.setDrawable(drawable);
                            }

                            */

                            //meal.setBmp();
                            //imageView.setImageResource(resourceId);
                        }


                        /*ImageView imageView = findViewById(R.id.imageView);
                        */


                    }

                }

                else
                {
                    Log.d(getClass().getName(),"resp is null");
                    //errorMsg("null");
                }
                //Snackbar.make(getView(), , Snackbar.LENGTH_LONG).show();
                //progress.setVisibility(View.INVISIBLE);
                //done = true;
                mealResultCallback.onSuccess(meals);

            }

            @Override
            public void onFailure(Call<ServerMealResponse> call, Throwable t) {

                //progress.setVisibility(View.INVISIBLE);
                Log.d(Constants.TAG,"failed "+t.getMessage());
                t.printStackTrace();
                Log.d(getClass().getName(),t.getLocalizedMessage());
                //Snackbar.make(getView(), t.getLocalizedMessage(), Snackbar.LENGTH_LONG).show();
                //done = true;
                //errorMsg(t.getLocalizedMessage());
                mealResultCallback.onError(t);
            }
        });

    }
    /*
    private void saveMeals(ArrayList<Meal> meals) {
        this.meals = meals;
        done = true;
    }

    private void errorMsg(String aNull) {
        meals=null;
        done = true;
    }
    */
}

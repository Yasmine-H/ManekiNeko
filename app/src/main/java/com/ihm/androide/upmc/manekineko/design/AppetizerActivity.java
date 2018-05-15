package com.ihm.androide.upmc.manekineko.design;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ihm.androide.upmc.manekineko.R;
import com.ihm.androide.upmc.manekineko.database.Meal;
import com.ihm.androide.upmc.manekineko.database.MealListAdapter2;
import com.ihm.androide.upmc.manekineko.database.MealResultCallback;
import com.ihm.androide.upmc.manekineko.database.MealsLoader;

import java.util.ArrayList;

/*
 Test Activity that loads the appetizers from the database and shows them on the app
 */
public class AppetizerActivity extends AppCompatActivity {

    TextView userInfoView;
    TextView descriptionView;
    TextView titleView;
    ImageView imageView;
    ListView mealList;
    ArrayList<Meal> mealsList;
    Activity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appetizer);

        mealsList = null;
        userInfoView = findViewById(R.id.textView);
        context = this;

        userInfoView.setText("Loading data...");
        MealsLoader mealsLoader = new MealsLoader(context);
        mealsLoader.askForAppetizerList(new MealResultCallback() {
        //mealsLoader.askForDessertList(new MealResultCallback() {
            @Override
            public void onSuccess(ArrayList<Meal> meals) {
                if (meals == null) {
                    userInfoView.setText("Error Loading data");
                } else{
                    userInfoView.append("Data successfully loaded !");
                    for (Meal meal : meals) {
                        //userInfoView.append("\nMeal >>>> " + meal.getName());
                    }
                    mealsList = meals;
                    MealListAdapter2 adapter=new MealListAdapter2(context, meals, new MealRemoved() {
                        @Override
                        public void onRemove(Meal meal) {

                        }
                    });
                    mealList=(ListView)findViewById(R.id.mealList);
                    mealList.setAdapter(adapter);

                }


            }

            @Override
            public void onError(Throwable throwable) {
                userInfoView.setText(throwable.getLocalizedMessage());
            }
        });






        /*

        /////////////////
        Gson gson;
        Retrofit retrofit;
        RequestInterface requestInterface;
        ArrayList<Meal> meals;

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
        * /
        requestInterface = retrofit.create(RequestInterface.class);
        Call<ServerTagResponse> response = requestInterface.fetchAllTags(Constants.FETCH_TAGS_OPERATION);
        //Call<ServerMealResponse> response = requestInterface.fetchAllMeals(Constants.FETCH_MEALS_OPERATION);
        Log.d(getClass().getName(), "Loading data...");
        response.enqueue(new Callback<ServerTagResponse>() {
            @Override
            public void onResponse(Call<ServerTagResponse> call, retrofit2.Response<ServerTagResponse> response) {

                ServerTagResponse resp = response.body();

                Log.d(getClass().getName(),resp.getMessage()+" and value : ");
                //ArrayList<Meal> meals;
                if(resp != null)
                {
                    Log.d(getClass().getName(),resp.getMessage());//+resp.getMeals().size());

                    //saveMeals(resp.getMeals());

                    String[] tags = resp.getTags();
                    if(tags == null){
                        Log.d(getClass().getName(),"\nnull returned");
                    }
                    else {
                        Log.d(getClass().getName(),"\nnumber of results : "+tags.length);
                        for (String tag : tags) {
                            Log.d(getClass().getName(),"\ntag : " + tag);
                            userInfoView.append("tag : " + tag);
                        }


                        /*ImageView imageView = findViewById(R.id.imageView);
                        String[] photo = meals.get(0).getPhoto().split("\\\\");

                        userInfoView.append("\n"+photo);
                        Resources resources = getBaseContext().getResources();
                        final int resourceId = resources.getIdentifier(photo[1], photo[0],
                                getBaseContext().getPackageName());
                        imageView.setImageResource(resourceId);
                        * /
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


            }

            @Override
            public void onFailure(Call<ServerTagResponse> call, Throwable t) {

                //progress.setVisibility(View.INVISIBLE);
                Log.d(Constants.TAG,"failed "+t.getMessage());
                t.printStackTrace();
                Log.d(getClass().getName(),t.getLocalizedMessage());
                //Snackbar.make(getView(), t.getLocalizedMessage(), Snackbar.LENGTH_LONG).show();
                //done = true;
                //errorMsg(t.getLocalizedMessage());

            }
        });

*/

    }
}

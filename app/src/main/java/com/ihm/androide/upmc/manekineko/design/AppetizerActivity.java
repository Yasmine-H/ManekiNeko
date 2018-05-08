package com.ihm.androide.upmc.manekineko.design;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.ihm.androide.upmc.manekineko.R;
import com.ihm.androide.upmc.manekineko.database.Meal;
import com.ihm.androide.upmc.manekineko.database.MealListAdapter;
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
        MealsLoader mealsLoader = new MealsLoader();
        mealsLoader.askForAppetizerList(new MealResultCallback() {
            @Override
            public void onSuccess(ArrayList<Meal> meals) {
                if (meals == null) {
                    userInfoView.setText("Error Loading data");
                } else{
                    userInfoView.setText("Data successfully loaded !");
                    for (Meal meal : meals) {
                        //userInfoView.append("\nMeal >>>> " + meal.getName());
                    }
                    mealsList = meals;
                    MealListAdapter adapter=new MealListAdapter(context, meals);
                    mealList=(ListView)findViewById(R.id.mealList);
                    mealList.setAdapter(adapter);

                }


            }

            @Override
            public void onError(Throwable throwable) {
                userInfoView.setText(throwable.getLocalizedMessage());
            }
        });



    }
}

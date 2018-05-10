package com.ihm.androide.upmc.manekineko.design;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.ihm.androide.upmc.manekineko.R;
import com.ihm.androide.upmc.manekineko.database.Constants;
import com.ihm.androide.upmc.manekineko.database.Meal;
import com.ihm.androide.upmc.manekineko.database.MealListAdapter;
import com.ihm.androide.upmc.manekineko.database.MealResultCallback;
import com.ihm.androide.upmc.manekineko.database.MealsLoader;
import com.ihm.androide.upmc.manekineko.database.User;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {



    TextView userInfoView;
    TextView descriptionView;
    TextView titleView;
    ImageView imageView;
    ListView mealList;
    ArrayList<Meal> mealsList;
    Activity context;
    Spinner mealTypesSpinner;
    User user;
    ArrayList<Meal> orderList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        mealsList = null;
        userInfoView = findViewById(R.id.orderView);
        context = this;
        mealTypesSpinner = findViewById(R.id.mealTypeSpinner);
        user = (User) getIntent().getSerializableExtra("user");

        userInfoView.setText(user.getName());

        ArrayList<String> mealTypes = new ArrayList<>();
        mealTypes.add(Constants.appetizer);
        mealTypes.add(Constants.drink);
        mealTypes.add(Constants.firstCourse);
        mealTypes.add(Constants.mainCourse);
        mealTypes.add(Constants.dessert);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, mealTypes);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mealTypesSpinner.setAdapter(dataAdapter);


        //userInfoView.setText("Loading data...");
        final MealsLoader mealsLoader = new MealsLoader();
        mealsLoader.askForAppetizerList(new MealResultCallback() {
            //mealsLoader.askForDessertList(new MealResultCallback() {
            @Override
            public void onSuccess(ArrayList<Meal> meals) {
                if (meals == null) {
                    //userInfoView.setText("Error Loading data");
                } else {
                    //userInfoView.append("Data successfully loaded !");
                    for (Meal meal : meals) {
                        //userInfoView.append("\nMeal >>>> " + meal.getName());
                    }
                    mealsList = meals;
                    MealListAdapter adapter = new MealListAdapter(context, meals);
                    mealList = (ListView) findViewById(R.id.mealList);
                    mealList.setAdapter(adapter);

                }


            }

            @Override
            public void onError(Throwable throwable) {
                //userInfoView.setText(throwable.getLocalizedMessage());
            }
        });



        mealTypesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedMealType = (String) parent.getItemAtPosition(position);
                mealsLoader.askForMealsOfType(selectedMealType, new MealResultCallback() {
                    @Override
                    public void onSuccess(ArrayList<Meal> meals) {
                        if(meals!=null)
                        {
                            mealsList = meals;
                            MealListAdapter adapter = new MealListAdapter(context, meals);
                            mealList = (ListView) findViewById(R.id.mealList);
                            mealList.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    public void addToOrderList(View view) {

    }

    public void suggesToFriend(View view) {
    }
}

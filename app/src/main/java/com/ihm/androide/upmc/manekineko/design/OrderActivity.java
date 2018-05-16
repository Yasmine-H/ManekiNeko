package com.ihm.androide.upmc.manekineko.design;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ihm.androide.upmc.manekineko.R;
import com.ihm.androide.upmc.manekineko.database.Meal;
import com.ihm.androide.upmc.manekineko.database.MealResultCallback;
import com.ihm.androide.upmc.manekineko.database.MealsLoader;
import com.ihm.androide.upmc.manekineko.database.User;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {



   // TextView userInfoView;
    TextView descriptionView;
    TextView titleView;
    ImageView imageView;
    ListView mealList;
    ArrayList<Meal> mealsList;
    Activity context;
 //   Spinner mealTypesSpinner;
    static User user;
    ArrayList<Meal> orderList;
    ListView categoryListView;
    ImageButton cart_button;
    public static final int UPDATED_USER = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        mealsList = null;
        //userInfoView = findViewById(R.id.orderView);
        context = this;
        //mealTypesSpinner = findViewById(R.id.mealTypeSpinner);
        user = (User) getIntent().getSerializableExtra("user");



/*
        Toolbar toolbar = findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolbar);

*/

        // Inflate your custom layout
        final ViewGroup actionBarLayout = (ViewGroup) getLayoutInflater().inflate(
                R.layout.toolbar,
                null);

        // Set up your ActionBar
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(actionBarLayout);

        TextView username_view = findViewById(R.id.username);
        username_view.setText(user.getName());

//        userInfoView.setText(user.getName());
/*
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
*/

        //userInfoView.setText("Loading data...");
        runOnUiThread(new Runnable() {

            @Override
            public void run() {

                final MealsLoader mealsLoader = new MealsLoader(getBaseContext());
                mealsLoader.askForAllMeals(new MealResultCallback() {
                    //mealsLoader.askForDessertList(new MealResultCallback() {
                    @Override
                    public void onSuccess(ArrayList<Meal> meals) {
                        if (meals == null) {
                            //userInfoView.setText("Error Loading data");
                        } else {

                            ArrayList<Meal> mealsList_copy = new ArrayList<>(meals);
                            ArrayList<ArrayList<Meal>> category_meals_list = new ArrayList<>();
                            while (!mealsList_copy.isEmpty()) {
                                Meal meal = mealsList_copy.get(0);
                                int position = getPositionOf(category_meals_list, meal.getType());

                                if (position == -1) {
                                    ArrayList<Meal> newCat_list = new ArrayList<>();
                                    newCat_list.add(meal);
                                    category_meals_list.add(newCat_list);
                                } else {
                                    category_meals_list.get(position).add(meal);
                                }
                                mealsList_copy.remove(0);
                            }


                            CategoryListAdapter adapter = new CategoryListAdapter(context, category_meals_list);
                            categoryListView = findViewById(R.id.category_list);
                            categoryListView.setAdapter(adapter);
                    /*
                    //userInfoView.append("Data successfully loaded !");
                    for (Meal meal : meals) {
                        //userInfoView.append("\nMeal >>>> " + meal.getName());
                    }
                    mealsList = meals;
                    MealListViewAdapter adapter = new MealListViewAdapter(context, meals);
                    mealList = (ListView) findViewById(R.id.mealList);
                    mealList.setAdapter(adapter);
                    */
                        }


                    }

                    @Override
                    public void onError(Throwable throwable) {
                        //userInfoView.setText(throwable.getLocalizedMessage());
                    }
                });


            }
        });

    }
/*
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
                            MealListViewAdapter adapter = new MealListViewAdapter(context, meals);
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
*/



    public static void addToOrderList(Meal meal) {

       user.getOrder().add(meal);
        Log.d("getOrder", "new meal added : "+meal.getName());
    }




    public void suggesToFriend(View view) {
    }

    private int getPositionOf(ArrayList<ArrayList<Meal>> type_recipes_list, String mealCat) {

        for(int i=0; i<type_recipes_list.size(); i++){
            if(type_recipes_list.get(i).get(0).getType().equals(mealCat)){
                return i;
            }
        }

        return -1;
    }


    public void displayCart(View view){
        Intent myIntent = new Intent(OrderActivity.this, CartActivity.class);
        myIntent.putExtra("user", user); //Optional parameters
        OrderActivity.this.startActivityForResult(myIntent, UPDATED_USER);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case UPDATED_USER : user = (User) data.getSerializableExtra("user");
            Log.d(getClass().getName(), "user : "+user.getName());
            break;
        }
    }
}

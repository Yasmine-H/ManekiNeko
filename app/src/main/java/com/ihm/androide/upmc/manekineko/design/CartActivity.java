package com.ihm.androide.upmc.manekineko.design;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.ihm.androide.upmc.manekineko.R;
import com.ihm.androide.upmc.manekineko.database.Meal;
import com.ihm.androide.upmc.manekineko.database.MealListAdapter2;
import com.ihm.androide.upmc.manekineko.database.User;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity implements MealRemoved{

    static User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        user = (User) getIntent().getSerializableExtra("user");

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
        TextView  order_info = findViewById(R.id.order_info);
        Button button = findViewById(R.id.confirm);


        Log.d(getClass().getName(), " user : "+user);
        if(user!=null){
            Log.d(getClass().getName(), " user : "+user.getOrder().toString());
        }
        ArrayList<Meal> meals_list = user.getOrder();
        if(meals_list.isEmpty()){
            order_info.setText("Oups ! Il n'y a rien de marqué sur votre carnet :o");
            button.setVisibility(View.INVISIBLE);
        }
        else
        {
            float price = 0;
            for(Meal meal : meals_list){
                price += meal.getPrice();
            }
            order_info.setText("Prix total : "+String.valueOf(price)+" €");
        }

        MealListAdapter2 adapter = new MealListAdapter2(this, meals_list, new MealRemoved() {
            @Override
            public void onRemove(Meal meal) {
                removeFromOrderList(meal);
            }
        });
        ListView mealListView = findViewById(R.id.meal_list);
        mealListView.setAdapter(adapter);


    }

    public void removeFromOrderList(Meal meal) {

        user.getOrder().remove(meal);
        Log.d("getOrder", "meal removed : "+meal.getName());

        Intent intent = getIntent();
        finish();
        startActivity(intent);

    }

    @Override
    public void onRemove(Meal meal) {
        user.getOrder().remove(meal);
        Log.d("getOrder", "meal removed : "+meal.getName());

        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    @Override
    public void finish() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("user",user);
        setResult(Activity.RESULT_OK,returnIntent);
        super.finish();
    }
}

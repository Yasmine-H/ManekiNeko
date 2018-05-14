package com.ihm.androide.upmc.manekineko.design;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.ihm.androide.upmc.manekineko.R;
import com.ihm.androide.upmc.manekineko.database.Meal;
import com.ihm.androide.upmc.manekineko.database.MealListAdapter2;
import com.ihm.androide.upmc.manekineko.database.User;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    static User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        user = (User) getIntent().getSerializableExtra("user");

        Log.d(getClass().getName(), " user : "+user);
        if(user!=null){
            Log.d(getClass().getName(), " user : "+user.getOrder().toString());
        }
        ArrayList<Meal> meals_list = user.getOrder();
        MealListAdapter2 adapter = new MealListAdapter2(this, meals_list);
        ListView mealListView = findViewById(R.id.meal_list);
        mealListView.setAdapter(adapter);


    }

    public static void removeFromOrderList(Meal meal) {

        user.getOrder().remove(meal);
        Log.d("getOrder", "meal removed : "+meal.getName());
        /*Intent intent = getIntent();
        finish();
        startActivity(intent);
        */
    }
}

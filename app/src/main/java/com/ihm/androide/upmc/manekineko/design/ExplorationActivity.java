package com.ihm.androide.upmc.manekineko.design;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ihm.androide.upmc.manekineko.R;

public class ExplorationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exploration);

        //Adding the fragments (tableView + meals) to the activity
        FragmentManager fragmentManager = getSupportFragmentManager();
        // Adding the Table Fragment
        TableFragment table_fragment = new TableFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.tableLayout, table_fragment);
        fragmentTransaction.commit();
        // Adding the Meals browser Fragment
        MealsFragment meals_fragment = new MealsFragment();
        fragmentTransaction.add(R.id.mealsLayout, meals_fragment);
        fragmentTransaction.commit();

        
    }
}

package com.ihm.androide.upmc.manekineko.design;

import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ihm.androide.upmc.manekineko.R;
import com.ihm.androide.upmc.manekineko.database.Meal;

import java.util.ArrayList;
import java.util.List;


public class MealListAdapter extends RecyclerView.Adapter<MealViewHolder> {

    private final List<Meal> meals;

    public MealListAdapter(ArrayList<Meal> meals){
        super();
        this.meals=meals;
    }



    @Override
    public int getItemCount() {
        //return meals == null ? 0 : meals.size()*2;
        return meals.size();
    }


    @Override
    public MealViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.meal_list, parent, false);
        return new MealViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MealViewHolder holder, int position) {
        Meal meal = meals.get(position);// % meals.size());
        holder.display(meal);

    }
}

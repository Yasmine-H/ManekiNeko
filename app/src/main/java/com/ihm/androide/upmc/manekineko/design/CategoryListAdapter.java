package com.ihm.androide.upmc.manekineko.design;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.ihm.androide.upmc.manekineko.R;
import com.ihm.androide.upmc.manekineko.database.Meal;

import java.util.ArrayList;


public class CategoryListAdapter extends ArrayAdapter {

    private final Activity context;
    private ArrayList<ArrayList<Meal>> meals;
    boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;


    public CategoryListAdapter(Activity context, ArrayList meals){
        super(context, R.layout.meal_list, meals);

        this.context = context;
        this.meals = meals;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();

        View rowView=inflater.inflate(R.layout.category_view, null,true);


        final ArrayList<Meal> mealList = meals.get(position);


        TextView typetxt = rowView.findViewById(R.id.category_name);
        String title = mealList.get(0).getType().substring(0,1).toUpperCase()+mealList.get(0).getType().substring(1)+"s";

        typetxt.setText(title);


        final RecyclerView rv = rowView.findViewById(R.id.meal_list);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(rowView.getContext(), LinearLayoutManager.HORIZONTAL, false);//LinearLayoutManager.HORIZONTAL, false);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(new MealListAdapter(mealList));

        //rv.setNestedScrollingEnabled(false);

        /*
        SnapHelper helper = new LinearSnapHelper();
        helper.attachToRecyclerView(rv);
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                visibleItemCount = layoutManager.getChildCount();
                totalItemCount = layoutManager.getItemCount();
                pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();

                super.onScrolled(recyclerView, dx, dy);
                int firstItemVisible = layoutManager.findFirstVisibleItemPosition();
                if (firstItemVisible != 0 && firstItemVisible % meals.get(position).size() == 0) {
                    recyclerView.getLayoutManager().scrollToPosition(0);
                }

            }
        });
        */
        return rowView;

    }
}

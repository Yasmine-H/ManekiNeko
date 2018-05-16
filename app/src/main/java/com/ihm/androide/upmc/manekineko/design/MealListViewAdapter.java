package com.ihm.androide.upmc.manekineko.design;

import android.app.Activity;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ihm.androide.upmc.manekineko.R;
import com.ihm.androide.upmc.manekineko.database.Meal;
import com.ihm.androide.upmc.manekineko.design.MealRemoved;


import java.util.ArrayList;

/**
 * Custom ListAdapter for printing a List of meals in a ListView
 */

public class MealListViewAdapter extends ArrayAdapter {

    private final Activity context;
    private final ArrayList<Meal> meals;
    MealRemoved mealRemoved;

    public MealListViewAdapter(Activity context, ArrayList<Meal> meals, MealRemoved mealRemoved){
        super(context, R.layout.meal_list, meals);
        this.context = context;
        this.meals = meals;
        this.mealRemoved=mealRemoved;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.meal_view, null,true);

        if(meals.get(position)!=null)
        {
            TextView txtTitle =  rowView.findViewById(R.id.meal_name);
            ImageView imageView = rowView.findViewById(R.id.meal_image);
            TextView extratxt = rowView.findViewById(R.id.meal_description);
            TextView pricetxt = rowView.findViewById(R.id.meal_price);
            ImageButton remove_button = rowView.findViewById(R.id.remove_meal);
            //TextView tagstxt = rowView.findViewById(R.id.mealTags);

            final int pos = position;

            remove_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //CartActivity.removeFromOrderList(meals.get(pos));
                    mealRemoved.onRemove(meals.get(position));

                }
            });
            //add the photo
            String[] photo = meals.get(position).getPhoto().split("\\\\");
            if(photo.length==2)
            {
                Resources resources = context.getResources();
                final int resourceId = resources.getIdentifier(photo[1], photo[0],
                    context.getPackageName());
                imageView.setImageResource(resourceId);
            }

            //add meal title
            txtTitle.setText(meals.get(position).getName());
            //add meal description
            extratxt.setText("Description : " + meals.get(position).getDescription());
            //add price
            pricetxt.setText(String.valueOf(meals.get(position).getPrice()) + " €");
            //add tags
            String[] taglist = meals.get(position).getTagList();
            if (taglist != null) {
                for(int i=0; i< taglist.length; i++)
                {
                    LinearLayout layout = rowView.findViewById(R.id.general_info_layout);
                    View tagView=inflater.inflate(R.layout.tag_layout, null,true);
                    TextView tagtxt = tagView.findViewById(R.id.mealTagView);
                    tagtxt.setText("#"+taglist[i]+" ");
                    layout.addView(tagView);
                }
            }
        }
        return rowView;

    };
}

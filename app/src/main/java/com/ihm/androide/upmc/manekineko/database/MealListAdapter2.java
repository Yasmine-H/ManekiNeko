package com.ihm.androide.upmc.manekineko.database;

import android.app.Activity;
import android.content.res.Resources;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ihm.androide.upmc.manekineko.R;
import com.ihm.androide.upmc.manekineko.design.CartActivity;
import com.ihm.androide.upmc.manekineko.design.OrderActivity;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Custom ListAdapter for printing a List of meals in a ListView
 */

public class MealListAdapter2 extends ArrayAdapter {

    private final Activity context;
    private final ArrayList<Meal> meals;

    public MealListAdapter2(Activity context, ArrayList<Meal> meals){
        super(context, R.layout.meal_list, meals);
        this.context = context;
        this.meals = meals;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.meal_view, null,true);

        if(meals.get(position)!=null)
        {
            TextView txtTitle = (TextView) rowView.findViewById(R.id.meal_name);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.meal_image);
            TextView extratxt = (TextView) rowView.findViewById(R.id.meal_description);
            TextView pricetxt = rowView.findViewById(R.id.meal_price);
            ImageButton remove_button = rowView.findViewById(R.id.remove_meal);
            //TextView tagstxt = rowView.findViewById(R.id.mealTags);

            final int pos = position;

            remove_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CartActivity.removeFromOrderList(meals.get(pos));
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
                    LinearLayout layout = (LinearLayout) rowView.findViewById(R.id.general_info_layout);
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

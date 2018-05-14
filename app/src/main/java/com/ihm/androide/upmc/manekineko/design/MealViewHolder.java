package com.ihm.androide.upmc.manekineko.design;

import android.app.AlertDialog;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ihm.androide.upmc.manekineko.R;
import com.ihm.androide.upmc.manekineko.database.Constants;
import com.ihm.androide.upmc.manekineko.database.Meal;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Locale;


public class MealViewHolder extends RecyclerView.ViewHolder {


    private final TextView title;
    private final ImageView img;
    private final TextView description;
    private final TextView price;
    private final ImageButton addButton;

    //private ArrayList<>

    private Meal currentMeal;

    public MealViewHolder(final View itemView) {
        super(itemView);

        title =  itemView.findViewById(R.id.meal_name);
        img =  itemView.findViewById(R.id.meal_image);
        description = itemView.findViewById(R.id.meal_description);
        price = itemView.findViewById(R.id.meal_price);
        addButton = itemView.findViewById(R.id.add_meal);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(itemView.getContext())
                        .setTitle(currentMeal.getName())
                        .setMessage("tululu : 10 mL\nbululu : 20 g")
                        .show();
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderActivity.addToOrderList(currentMeal);

            }
        });
    }


    public void display(Meal meal) {

        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);

            if(meal!=null)
            {

                if(meal.getBitmap()==null) {
                    //add the photo
                    final String[] photo = meal.getPhoto().split("\\\\");

                    if (photo.length == 2) {
                        Resources resources = itemView.getResources();
                        final int resourceId = resources.getIdentifier(photo[1], photo[0], itemView.getContext().getPackageName());
                        //img.setImageResource(resourceId);
                        Log.d(getClass().getName(), "meal ===================" + meal.getName() + " typee==" + meal.getType()+" id===="+resourceId+" titre : 0 : "+photo[0]+" 1 :"+photo[1]);
                        meal.setBitmap(BitmapFactory.decodeResource(itemView.getResources(), resourceId));
                        Log.d(getClass().getName(), " id ::::: " + resourceId);


                            /*

                            int imageId = getResources().getIdentifier(panel.toLowerCase(Locale.getDefault()),
                                    "drawable", getActivity().getPackageName());

                            InputStream is = getActivity().getResources().openRawResource(imageId);
                            final Bitmap imageBitmap = BitmapFactory.decodeStream(is);
                            rootView.post( new Runnable() {
                                public void run() {
                                    ((ImageView) rootView.findViewById(R.id.screen_image)).setImageBitmap(imageBitmap);
                                    getActivity().setTitle(panel);
                                }


                            });
                        */


                        //Resources resources = itemView.getResources();
/*
                    try {

                        final int resourceId = R.drawable.class.getField(photo[0]).getInt(null);
                        Log.d(getClass().getName(), "id : "+resourceId);
                        img.setImageResource(resourceId);

                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }
*/

                    }
                }

                img.setImageBitmap(meal.getBitmap());
                //img.setImageBitmap(meal.getBmp());
                //add Meal title

                //img.setImageDrawable(meal.getDrawable());
                //Log.d(getClass().getName(), ">>>>>>>>>>>>>>>>>>>>>>>>>>>"+meal.getDrawable());
                String price_value = String.valueOf(meal.getPrice())+" €";
                price.setText(price_value);
                description.setText(meal.getDescription());
                title.setText(meal.getName());
                currentMeal=meal;

                String[] taglist = meal.getTagList();
                if (taglist != null) {
                    for(int i=0; i< taglist.length; i++)
                    {
                        LinearLayout layout = itemView.findViewById(R.id.general_info_layout);
                        View tagView=View.inflate(itemView.getContext(), R.layout.tag_layout, null);
                        TextView tagtxt = tagView.findViewById(R.id.mealTagView);
                        String tag = "#"+taglist[i]+" ";
                        tagtxt.setText(tag);
                        layout.addView(tagView);

                    }
                }

            }

        }

    }
}

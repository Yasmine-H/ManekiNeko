package com.ihm.androide.upmc.manekineko.database;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by HAMDANI on 29/04/2018.
 */

public interface RequestInterface {

    //@POST("posts/")
    @POST("meals/")
    //@POST("ManekiNeko/")
    Call<ServerUserResponse> operation(@Body ServerUserRequest request);


    @GET("meals/")
        //@POST("ManekiNeko/")
    Call<ServerMealResponse> fetchAllMeals(@Query("method") String method);

    @GET("meals/")
        //@POST("ManekiNeko/")
    Call<ServerMealResponse> fetchMealsOfType(@Query("method") String method,
                                @Query("type") String type);

}

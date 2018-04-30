package com.ihm.androide.upmc.manekineko.database;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by HAMDANI on 29/04/2018.
 */

public interface RequestInterface {

    @POST("posts/")
    //@POST("ManekiNeko/")
    Call<ServerResponse> operation(@Body ServerRequest request);
}

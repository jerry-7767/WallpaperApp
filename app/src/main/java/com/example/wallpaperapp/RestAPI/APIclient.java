package com.example.wallpaperapp.RestAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIclient {

//    http://mapi.trycatchtech.com/v1/wallpaper_one/wallpaper_one_category_list
//    http://mapi.trycatchtech.com/v1/wallpaper_one/wallpaper_one_post_list?category_id=4
    
    public static final String BASE_URL = "http://mapi.trycatchtech.com/";
    public static Retrofit retrofit = null;

    public static Retrofit getClient(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }

}

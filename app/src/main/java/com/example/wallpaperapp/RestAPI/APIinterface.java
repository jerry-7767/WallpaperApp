package com.example.wallpaperapp.RestAPI;

import com.example.wallpaperapp.DATA.CategoryNxtList;
import com.example.wallpaperapp.DATA.CategoryResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIinterface {

    @GET("v1/wallpaper_one/wallpaper_one_category_list")
    Call<CategoryResponse> getResponse();

    @GET("v1/wallpaper_one/wallpaper_one_post_list")
    Call<List<CategoryNxtList>> getCategoryNxtList(@Query("category_id") String CatId);

}

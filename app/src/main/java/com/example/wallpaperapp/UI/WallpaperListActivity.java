package com.example.wallpaperapp.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.wallpaperapp.ADAPTER.CategoryNxtAdapter;
import com.example.wallpaperapp.DATA.CategoryNxtList;
import com.example.wallpaperapp.R;
import com.example.wallpaperapp.RestAPI.APIclient;
import com.example.wallpaperapp.RestAPI.APIinterface;
import com.google.android.material.appbar.MaterialToolbar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WallpaperListActivity extends AppCompatActivity{

    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private ProgressDialog progressDialog;
    private CategoryNxtAdapter categoryNxtAdapter;
    private String id;
    private MaterialToolbar materialToolbar;
    private DisplayMetrics displayMetrics;
    private int width, height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallpaper_list);

        materialToolbar = findViewById(R.id.wallpaper_list_toolbar);
        materialToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        int numcolumns = 3;
        recyclerView = findViewById(R.id.recyclerveiw_wallpaperlist);
        gridLayoutManager = new GridLayoutManager(WallpaperListActivity.this, numcolumns);
        recyclerView.setLayoutManager(gridLayoutManager);

        id = getIntent().getStringExtra("ID");
        showProgDig();
        onResponse();
    }

    private void onResponse() {
        APIinterface apIinterface = APIclient.getClient().create(APIinterface.class);
        Call<List<CategoryNxtList>> call = apIinterface.getCategoryNxtList(id);
        call.enqueue(new Callback<List<CategoryNxtList>>() {
            @Override
            public void onResponse(Call<List<CategoryNxtList>> call, Response<List<CategoryNxtList>> response) {
                if (progressDialog.isShowing())
                    progressDialog.dismiss();
                final List<CategoryNxtList> categoryNxtLists;
                categoryNxtLists = response.body();
                if (categoryNxtLists != null){
                    categoryNxtAdapter = new CategoryNxtAdapter(WallpaperListActivity.this, categoryNxtLists);
                    recyclerView.setAdapter(categoryNxtAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<CategoryNxtList>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showProgDig(){
        progressDialog = new ProgressDialog(WallpaperListActivity.this);
        progressDialog.setMessage("Please wait....");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

//    public void getScreenWidthHeght(){
//        displayMetrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//        width = displayMetrics.widthPixels;
//        height = displayMetrics.heightPixels;
//    }
//    public Bitmap SetBitmapSize(Bitmap bitmap1){
//        return Bitmap.createScaledBitmap(bitmap1, width, height, false);
//    }

//    public void setWallpaperM(View view) {
//        getScreenWidthHeght();
//        CategoryNxtAdapter categoryNxtAdapter = (CategoryNxtAdapter) parent.getAdapter();
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),(int))
//        Bitmap temp = SetBitmapSize(bitmap);
//
//        WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
//        try {
//            wallpaperManager.setBitmap(temp);
//            wallpaperManager.suggestDesiredDimensions(width,height);
//            Toast.makeText(WallpaperListActivity.this, "Wallpaper set", Toast.LENGTH_SHORT).show();
//        } catch (IOException e) {
//            Toast.makeText(WallpaperListActivity.this, "Can't set wallpaper", Toast.LENGTH_SHORT).show();
//        }
//    }
}
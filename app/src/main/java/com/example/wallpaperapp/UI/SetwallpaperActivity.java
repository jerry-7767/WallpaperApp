package com.example.wallpaperapp.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.wallpaperapp.ADAPTER.CategoryNxtAdapter;
import com.example.wallpaperapp.DATA.CategoryNxtList;
import com.example.wallpaperapp.R;
import com.example.wallpaperapp.RestAPI.APIclient;
import com.example.wallpaperapp.RestAPI.APIinterface;
import com.google.android.material.appbar.MaterialToolbar;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SetwallpaperActivity extends AppCompatActivity {

    private MaterialToolbar materialToolbar;
    private ImageView imageView;
    private Button button;
    private DisplayMetrics displayMetrics;
    private int width, height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setwallpaper);

        imageView = findViewById(R.id.fullimg_imageview);
        button = findViewById(R.id.btn_setwallpaper);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // setWallpaperbtn();
            }
        });
        materialToolbar = findViewById(R.id.setwallpaper_toolbar);
        materialToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent = getIntent();
        String imgurl = intent.getStringExtra("fullimg");
        Glide.with(getApplicationContext()).load(imgurl).into(imageView);

    }
//
//    private void setWallpaperbtn(){
//        getScreenWidthHeght();
//    }

//    private void setWallpaperbtn(AdapterView<?> adapterView, final int position) {
//        getScreenWidthHeght();
//        CategoryNxtAdapter categoryNxtAdapter = (CategoryNxtAdapter) adapterView.getAdapter();
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),(int)categoryNxtAdapter.getItemId(position));
//        Bitmap temp = SetBitmapSize(bitmap);
//        WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
//        try {
//            wallpaperManager.setBitmap(temp);
//            wallpaperManager.suggestDesiredDimensions(width, height);
//            Toast.makeText(SetwallpaperActivity.this, "Wallpaper set", Toast.LENGTH_SHORT).show();
//        }catch (IOException e) {
//            Toast.makeText(SetwallpaperActivity.this, "Can't set wallpaper", Toast.LENGTH_SHORT).show();
//        }
//    }
//    public void getScreenWidthHeght(){
//        displayMetrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//        width = displayMetrics.widthPixels;
//        height = displayMetrics.heightPixels;
//    }
//    public Bitmap SetBitmapSize(Bitmap bitmap1){
//        return Bitmap.createScaledBitmap(bitmap1, width, height, false);
//    }
}
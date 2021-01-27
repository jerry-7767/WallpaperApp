package com.example.wallpaperapp.ADAPTER;

import android.Manifest;
import android.app.Activity;
import android.app.DownloadManager;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.wallpaperapp.DATA.CategoryNxtList;
import com.example.wallpaperapp.R;
import com.example.wallpaperapp.UI.SetwallpaperActivity;
import com.example.wallpaperapp.UI.WallpaperListActivity;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static android.bluetooth.BluetoothGattCharacteristic.PERMISSION_WRITE;
import static android.content.ContentValues.TAG;

public class CategoryNxtAdapter extends RecyclerView.Adapter<CategoryNxtAdapter.MynxtViewholder> {

    public List<CategoryNxtList> categoryNxtLists;
    private Context mContext;

    private DisplayMetrics displayMetrics;
    private int width, height;

    public CategoryNxtAdapter(WallpaperListActivity wallpaperListActivity, List<CategoryNxtList> categoryNxtLists) {
        this.mContext = wallpaperListActivity;
        this.categoryNxtLists = categoryNxtLists;
    }

    @NonNull
    @Override
    public MynxtViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_wallpaperlist_rv_layout,parent, false);
        MynxtViewholder mynxtViewholder = new MynxtViewholder(view);
        return mynxtViewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull MynxtViewholder holder, int position) {
        String imgurl = categoryNxtLists.get(position).getImages();
        Glide.with(mContext).load(imgurl).into(holder.imageView);
        setOnclicki((holder).cardView,position);
    }

    @Override
    public int getItemCount() {
        return categoryNxtLists.size();
    }

    public class MynxtViewholder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private CardView cardView;

        public MynxtViewholder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.wallpaper_single_img);
            cardView = itemView.findViewById(R.id.img_wallpaper_cardview);
        }
    }

    public void setOnclicki(CardView cardView,final int pos){
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getScreenWidthHeght();
                String imgurl = categoryNxtLists.get(pos).getImages();
                Target target = new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        WallpaperManager wallpaperManager = WallpaperManager.getInstance(mContext);
                        try {
                            wallpaperManager.setBitmap(bitmap);
                            wallpaperManager.suggestDesiredDimensions(width,height);
                            Toast.makeText(mContext, "Wallpaper set successfully", Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(mContext, "error", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {
                    }
                };
                Picasso.get().load(imgurl).into(target);
            }
        });
    }

    public void getScreenWidthHeght(){
        displayMetrics = new DisplayMetrics();
        ((Activity)mContext).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        width = displayMetrics.widthPixels;
        height = displayMetrics.heightPixels;
    }
    public Bitmap SetBitmapSize(Bitmap bitmap1){
        return Bitmap.createScaledBitmap(bitmap1, width, height, false);
    }
}
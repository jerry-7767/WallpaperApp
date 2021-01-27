package com.example.wallpaperapp.ADAPTER;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.wallpaperapp.DATA.CategoryList;
import com.example.wallpaperapp.DATA.CategoryResponse;
import com.example.wallpaperapp.R;
import com.example.wallpaperapp.UI.HomeActivity;
import com.example.wallpaperapp.UI.WallpaperListActivity;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyCategoryViewHolder> {

    public List<CategoryList> categoryResponses;
    private Context mContext;

    public CategoryAdapter(HomeActivity homeActivity, List<CategoryList> categoryResponses) {
        this.mContext = homeActivity;
        this.categoryResponses = categoryResponses;
    }


    @NonNull
    @Override
    public MyCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_rv_layout, parent, false);
        MyCategoryViewHolder myCategoryViewHolder = new MyCategoryViewHolder(view);
        return myCategoryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyCategoryViewHolder holder, int position) {
        holder.textView.setText(categoryResponses.get(position).getCat_name());
        String imgurl = categoryResponses.get(position).getCat_image();
        Glide.with(mContext).load(imgurl).into(holder.imageView);
        setOnclick((holder).cardView, position);
    }

    @Override
    public int getItemCount() {
        return categoryResponses.size();
    }

    public class MyCategoryViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView;
        private CardView cardView;

        public MyCategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.single_img);
            textView = itemView.findViewById(R.id.single_text);
            cardView = itemView.findViewById(R.id.img_cardview);
        }
    }

    public void setOnclick(CardView cardView, final int position){
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, WallpaperListActivity.class);
                String string = categoryResponses.get(position).getId();
                intent.putExtra("ID", string);
                mContext.startActivity(intent);
            }
        });
    }
}

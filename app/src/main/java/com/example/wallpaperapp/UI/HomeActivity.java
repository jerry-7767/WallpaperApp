package com.example.wallpaperapp.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.example.wallpaperapp.ADAPTER.CategoryAdapter;
import com.example.wallpaperapp.DATA.CategoryList;
import com.example.wallpaperapp.DATA.CategoryResponse;
import com.example.wallpaperapp.R;
import com.example.wallpaperapp.RestAPI.APIclient;
import com.example.wallpaperapp.RestAPI.APIinterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private CategoryAdapter categoryAdapter;
    private List<CategoryResponse> categoryResponseList;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        int numcolumn = 3;

        recyclerView = findViewById(R.id.recyclerviewid);
        gridLayoutManager = new GridLayoutManager(HomeActivity.this, 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        showProgDig();
        onResponse();
    }

    private void onResponse() {

        APIinterface apIinterface = APIclient.getClient().create(APIinterface.class);
//        Call<List<CategoryResponse>> call = apIinterface.getResponse();
        Call<CategoryResponse> categoryResponseCall = apIinterface.getResponse();
        categoryResponseCall.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if (progressDialog.isShowing())
                    progressDialog.dismiss();
                final List<CategoryList> categoryResponses;
                categoryResponses = response.body().getData();
                if (categoryResponses != null){
                    categoryAdapter = new CategoryAdapter(HomeActivity.this, categoryResponses);
                    recyclerView.setAdapter(categoryAdapter);
                }
            }
            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showProgDig() {
        progressDialog = new ProgressDialog(HomeActivity.this);
        progressDialog.setMessage("Please wait....");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }
}
package com.example.testtask;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.OnImageListener {

    public static ArrayList<String> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this);
        recyclerView.setAdapter(recyclerViewAdapter);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        else
            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        if (savedInstanceState != null) {
            arrayList = savedInstanceState.getStringArrayList("array");
            recyclerViewAdapter.setItems(arrayList);
        } else {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://dev-tasks.alef.im/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
            retrofitInterface.someResponse().enqueue(new Callback<List<String>>() {
                @Override
                public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                    arrayList.addAll(response.body());
                    recyclerViewAdapter.setItems(arrayList);
                }

                @Override
                public void onFailure(Call<List<String>> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }

    @Override
    public void onImageClick(int position) {
        Intent intent = new Intent(".Fullscreen_image");
        intent.putExtra("url", arrayList.get(position));
        startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putStringArrayList("array", arrayList);
    }
}
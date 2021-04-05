package com.example.testtask;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitInterface {

    @GET("task-m-001/list.php")
    Call<List<String>> someResponse();
}

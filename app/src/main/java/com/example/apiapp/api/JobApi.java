package com.example.apiapp.api;

import com.example.apiapp.models.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JobApi{

    @GET("common/jobs")
    Call<ApiResponse> getJobs(@Query("page") int page);
}

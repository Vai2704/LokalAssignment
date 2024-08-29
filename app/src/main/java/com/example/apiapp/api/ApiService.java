package com.example.apiapp.api;

import com.example.apiapp.models.ApiResponse;
import com.example.apiapp.models.Job;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("common/jobs/{id}")
    Call<ApiResponse> getJobDetails(@Path("id") int jobId);
}

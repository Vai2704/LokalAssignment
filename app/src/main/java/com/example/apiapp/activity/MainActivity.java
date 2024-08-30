package com.example.apiapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apiapp.EndlessRecyclerViewScrollListener;
import com.example.apiapp.R;
import com.example.apiapp.adapter.JobsAdapter;
import com.example.apiapp.api.JobApi;
import com.example.apiapp.models.ApiResponse;
import com.example.apiapp.models.Job;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private List<Job> jobList;
    JobsAdapter jobsAdapter;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    private int currentPage = 1;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    Button jobsBtn, bookmarkBtn;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        linearLayoutManager =  new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        jobList = new ArrayList<>();
        jobsBtn = findViewById(R.id.jobsBtn);
        bookmarkBtn = findViewById(R.id.bookmarksBtn);
        progressBar = findViewById(R.id.progressBar);

        jobsAdapter = new JobsAdapter( jobList, this);
        recyclerView.setAdapter(jobsAdapter);

        fetchJobsFromApi(currentPage);

        recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                if (!isLoading && !isLastPage) {
                    fetchJobsFromApi(page);
                }
            }
        });

        bookmarkBtn.setOnClickListener(v->{
            Intent i = new Intent(this, BookmarkActivity.class);
            startActivity(i);
        });
    }

    private void fetchJobsFromApi(int page) {
        isLoading = true;
        progressBar.setVisibility(View.VISIBLE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://testapi.getlokalapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JobApi jobApi = retrofit.create(JobApi.class);
        Call<ApiResponse> call = jobApi.getJobs(page);

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Job> newJobs = response.body().getResults();
                    jobsAdapter.addJobs(newJobs);

                    if (newJobs.isEmpty()) {
                        isLastPage = true;
                    }

                } else {
                    Toast.makeText(MainActivity.this, "Failed to retrieve jobs", Toast.LENGTH_SHORT).show();
                }
                isLoading = false;
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("MainActivity", "onFailure: ", t);
                isLoading = false;
            }
        });
    }
}


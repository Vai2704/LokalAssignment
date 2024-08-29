package com.example.apiapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.apiapp.api.ApiService;
import com.example.apiapp.database.JobEntity;
import com.example.apiapp.models.ApiResponse;
import com.example.apiapp.models.Job;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JobDetail extends AppCompatActivity {

    TextView detailTitleTv, companyName, jobHours, jobRole, noOfOpenings, salary, phoneNumber;
    Retrofit retrofit;
    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_detail);

        int jobId = getIntent().getIntExtra("job_id", -1);


        detailTitleTv = findViewById(R.id.detailTitleTv);
        companyName = findViewById(R.id.companyName);
        jobHours = findViewById(R.id.jobHours);
        jobRole = findViewById(R.id.jobRole);
        noOfOpenings = findViewById(R.id.noOfOpenings);
        salary = findViewById(R.id.salaryLL);
        phoneNumber = findViewById(R.id.phonenumber);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://testapi.getlokalapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);

        fetchJobDetails(jobId);
    }


    private void fetchJobDetails(int jobId) {
        Call<ApiResponse> call = apiService.getJobDetails(jobId);

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponse apiResponse = response.body();
                    List<Job> jobs = apiResponse.getResults();
                    if (jobs != null && !jobs.isEmpty()){
                        Job job = jobs.get(0);
                        // Set the job details in the views
                        detailTitleTv.setText(job.getTitle());
                        companyName.setText(job.getCompanyName());
                        jobHours.setText(job.getJobHours());
                        jobRole.setText(job.getJobRole());
                        noOfOpenings.setText(job.getNumberOfOpening());
                        salary.setText(job.getPrimary_details().getSalary());
                        phoneNumber.setText(job.getPhoneNumber());
                    }
                } else {
                    Toast.makeText(JobDetail.this, "Failed to load job details", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(JobDetail.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("TAG", "onFailure: "+t.getMessage());
            }
        });
    }
}
package com.example.apiapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apiapp.JobDetail;
import com.example.apiapp.MyApp;
import com.example.apiapp.R;
import com.example.apiapp.database.JobEntity;
import com.example.apiapp.models.Job;
import com.example.apiapp.models.PrimaryDetails;

import java.io.Serializable;
import java.util.List;

public class JobsAdapter extends RecyclerView.Adapter<JobsAdapter.JobsViewHolder> {

    private List<Job> jobList;
    Context context;

    public JobsAdapter(List<Job> jobList, Context context) {
        this.jobList = jobList;
        this.context = context;
    }

    @NonNull
    @Override
    public JobsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.each_job_item,
                parent,
                false
        );

        return new JobsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobsViewHolder holder, int position) {
        Job job = jobList.get(position);

        PrimaryDetails primaryDetails = job.getPrimary_details();
        if (primaryDetails != null) {
            holder.jobLocation.setText(primaryDetails.getPlace());
            holder.jobSalary.setText(primaryDetails.getSalary());
        } else {

            holder.jobLocation.setText("N/A");
            holder.jobSalary.setText("N/A");
        }
        holder.jobTitle.setText(job.getTitle());
        holder.PhoneNumber.setText(job.getPhoneNumber());
        
        holder.itemView.setOnClickListener(v->{
            Intent i = new Intent(context, JobDetail.class);
            i.putExtra("job_id", job.getId());
            context.startActivity(i);
        });

        new Thread(() -> {
            JobEntity existingJob = MyApp.getDatabase().jobDAO().getJobById(job.getId());
            if (existingJob != null) {
                holder.itemView.post(() -> holder.bookmarkBtn.setText("Bookmarked"));
            } else {
                holder.itemView.post(() -> holder.bookmarkBtn.setText("Bookmark"));
            }
        }).start();

        // Bookmark button click listener
        holder.bookmarkBtn.setOnClickListener(v -> {
            new Thread(() -> {
                JobEntity existingJob = MyApp.getDatabase().jobDAO().getJobById(job.getId());
                if (existingJob == null) {
                    JobEntity jobEntity = new JobEntity();
                    jobEntity.setJobId(job.getId());
                    jobEntity.setTitle(job.getTitle());
                    jobEntity.setLocation(job.getPrimary_details().getPlace());
                    jobEntity.setSalary(job.getPrimary_details().getSalary());
                    jobEntity.setPhoneNumber(job.getPhoneNumber());
                    MyApp.getDatabase().jobDAO().insertJob(jobEntity);

                    holder.itemView.post(() -> {
                        holder.bookmarkBtn.setText("Bookmarked");
                        Toast.makeText(context, "Job bookmarked!", Toast.LENGTH_SHORT).show();
                    });
                } else {
                    holder.itemView.post(() -> Toast.makeText(context, "Job already bookmarked!", Toast.LENGTH_SHORT).show());
                }
            }).start();
        });
    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }

    public void addJobs(List<Job> newJobs) {
        jobList.addAll(newJobs);
        notifyDataSetChanged();
    }

    class JobsViewHolder extends RecyclerView.ViewHolder{
        TextView jobTitle, jobLocation, jobSalary, PhoneNumber;
        Button bookmarkBtn;
        public JobsViewHolder(@NonNull View itemView) {
            super(itemView);
            jobTitle = itemView.findViewById(R.id.titleTv);
            jobLocation = itemView.findViewById(R.id.locationTv);
            jobSalary = itemView.findViewById(R.id.salaryTv);
            PhoneNumber = itemView.findViewById(R.id.phoneNumber);
            bookmarkBtn = itemView.findViewById(R.id.bookmarkBtn);

        }
    }
}

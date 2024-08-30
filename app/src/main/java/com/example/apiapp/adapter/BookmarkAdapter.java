package com.example.apiapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apiapp.activity.JobDetail;
import com.example.apiapp.MyApp;
import com.example.apiapp.R;
import com.example.apiapp.database.JobEntity;

import java.util.List;

public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkAdapter.BookmarkedViewHolder> {

    List<JobEntity> bookmarkedJobs;
    Context context;

    public BookmarkAdapter(List<JobEntity> bookmarkedJobs, Context context) {
        this.bookmarkedJobs = bookmarkedJobs;
        this.context = context;
    }

    @NonNull
    @Override
    public BookmarkedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.each_job_item,
                parent,
                false
        );
        return new BookmarkedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookmarkedViewHolder holder, int position) {
       JobEntity jobs = bookmarkedJobs.get(position);

        holder.salary.setText(jobs.getSalary());
        holder.title.setText(jobs.getTitle());
        holder.location.setText(jobs.getLocation());
        holder.phoneNumber.setText(jobs.getPhoneNumber());
        holder.bookmarkBtn.setText("Bookmarked");

        holder.bookmarkBtn.setOnClickListener(v->{
            new Thread(()->{
                MyApp.getDatabase().jobDAO().deleteJob(jobs.getJobId());

            }).start();
            Toast.makeText(context, "Job removed from bookmark", Toast.LENGTH_SHORT).show();
        });

        holder.itemView.setOnClickListener(v->{
            Intent i = new Intent(context, JobDetail.class);
            i.putExtra("job_id", jobs.getJobId());
            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return bookmarkedJobs.size();
    }

    class BookmarkedViewHolder extends RecyclerView.ViewHolder{
        TextView title, phoneNumber, salary, location;
        Button bookmarkBtn;
        public BookmarkedViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleTv);
            phoneNumber = itemView.findViewById(R.id.phoneNumber);
            location = itemView.findViewById(R.id.locationTv);
            salary = itemView.findViewById(R.id.salaryTv);
            bookmarkBtn = itemView.findViewById(R.id.bookmarkBtn);
        }
    }
}

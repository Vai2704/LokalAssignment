package com.example.apiapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apiapp.MyApp;
import com.example.apiapp.R;
import com.example.apiapp.adapter.BookmarkAdapter;
import com.example.apiapp.database.JobEntity;

import java.util.ArrayList;
import java.util.List;

public class BookmarkActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button jobsBtn, bookmarkBtn;
    BookmarkAdapter bookmarkAdapter;
    List<JobEntity> bookmarkedJobs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);

        recyclerView = findViewById(R.id.bookmark_recyclerView);
        jobsBtn = findViewById(R.id.jobsBtn);
        bookmarkBtn = findViewById(R.id.bookmarksBtn);
        bookmarkedJobs = new ArrayList<>();

        jobsBtn.setOnClickListener(v->{
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        });

        new Thread(() -> {
            bookmarkedJobs = MyApp.getDatabase().jobDAO().getAllBookmarkedJobs();
            runOnUiThread(()->{
                bookmarkAdapter = new BookmarkAdapter(bookmarkedJobs, this);
                recyclerView.setAdapter(bookmarkAdapter);
            });
        }).start();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
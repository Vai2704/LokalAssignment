package com.example.apiapp.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface JobDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertJob(JobEntity job);

    @Query("SELECT * FROM bookmarked_jobs WHERE jobId = :jobId LIMIT 1")
    JobEntity getJobById(int jobId);

    @Query("SELECT * FROM bookmarked_jobs")
    List<JobEntity> getAllBookmarkedJobs();

    @Query("DELETE FROM bookmarked_jobs WHERE jobId = :jobId")
    void deleteJob(int jobId);
}

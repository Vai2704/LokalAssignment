package com.example.apiapp.models;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Job implements Serializable {
    @SerializedName("id")
    @Expose
    private int id;
    private String title;
    private int type;
    @SerializedName("primary_details")
    @Expose
    private PrimaryDetails primary_details;
    @SerializedName("whatsapp_no")
    @Expose
    private String phoneNumber;
    @SerializedName("company_name")
    @Expose
    private String companyName;
    @SerializedName("job_role")
    @Expose
    private String jobRole;
    @SerializedName("openings_count")
    @Expose
    private String numberOfOpening;
    @SerializedName("job_hours")
    @Expose
    private String jobHours;


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getJobRole() {
        return jobRole;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    public String getNumberOfOpening() {
        return numberOfOpening;
    }

    public void setNumberOfOpening(String numberOfOpening) {
        this.numberOfOpening = numberOfOpening;
    }

    public String getJobHours() {
        return jobHours;
    }

    public void setJobHours(String jobHours) {
        this.jobHours = jobHours;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public PrimaryDetails getPrimary_details() {
        return primary_details;
    }

    public void setPrimary_details(PrimaryDetails primary_details) {
        this.primary_details = primary_details;
    }
}

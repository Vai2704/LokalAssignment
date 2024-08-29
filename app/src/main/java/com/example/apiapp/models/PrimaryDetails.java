package com.example.apiapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PrimaryDetails {
    @SerializedName("Place")
    @Expose
    private String Place;
    @SerializedName("Salary")
    @Expose
    private String Salary;
    private String Job_Type;
    private String Experience;
    private String Fees_Charged;
    private String Qualification;

    public String getPlace() {
        return Place;
    }

    public void setPlace(String place) {
        Place = place;
    }

    public String getSalary() {
        return Salary;
    }

    public void setSalary(String salary) {
        Salary = salary;
    }

    public String getJob_Type() {
        return Job_Type;
    }

    public void setJob_Type(String job_Type) {
        Job_Type = job_Type;
    }

    public String getExperience() {
        return Experience;
    }

    public void setExperience(String experience) {
        Experience = experience;
    }

    public String getFees_Charged() {
        return Fees_Charged;
    }

    public void setFees_Charged(String fees_Charged) {
        Fees_Charged = fees_Charged;
    }

    public String getQualification() {
        return Qualification;
    }

    public void setQualification(String qualification) {
        Qualification = qualification;
    }
}

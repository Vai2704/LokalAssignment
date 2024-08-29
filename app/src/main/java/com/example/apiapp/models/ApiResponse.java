package com.example.apiapp.models;

import java.util.List;

public class ApiResponse {
    private List<Job> results;

    public List<Job> getResults() {
        return results;
    }

    public void setResults(List<Job> results) {
        this.results = results;
    }
}

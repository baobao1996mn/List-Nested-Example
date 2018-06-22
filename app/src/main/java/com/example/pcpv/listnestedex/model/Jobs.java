package com.example.pcpv.listnestedex.model;

import java.util.List;

public class Jobs {
    private String title;
    private List<Job> jobs;
    private boolean isExpanded;

    public Jobs(String title, List<Job> jobs) {
        this.title = title;
        this.jobs = jobs;
        this.isExpanded = false;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
}

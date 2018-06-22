package com.example.pcpv.listnestedex.model;

import com.google.gson.annotations.SerializedName;

public class Job {
    @SerializedName("id")
    private String id;

    @SerializedName("title")
    private String title;

    @SerializedName("location")
    private String location;

    @SerializedName("type")
    private String type;

    @SerializedName("company")
    private String company;

    @SerializedName("company_logo")
    private String logo;

    private boolean checked = false;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}

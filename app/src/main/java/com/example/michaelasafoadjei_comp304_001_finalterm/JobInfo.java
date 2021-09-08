package com.example.michaelasafoadjei_comp304_001_finalterm;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class JobInfo {
    @PrimaryKey
    @NonNull
    private String jobCode;
    private String jobTitle;
    private String companyName;
    private double companyLat;
    private double companyLag;
    private String salary;


    public JobInfo(String jobCode, String jobTitle, String companyName, double companyLat, double companyLag, String salary) {
        this.jobCode = jobCode;
        this.jobTitle = jobTitle;
        this.companyName = companyName;
        this.companyLat = companyLat;
        this.companyLag = companyLag;
        this.salary = salary;
    }

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public double getCompanyLat() {
        return companyLat;
    }

    public void setCompanyLat(double companyLat) {
        this.companyLat = companyLat;
    }

    public double getCompanyLag() {
        return companyLag;
    }

    public void setCompanyLag(double companyLag) {
        this.companyLag = companyLag;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
}

package com.example.michaelasafoadjei_comp304_001_finalterm;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class JobInfoViewModel extends AndroidViewModel
{

    private JobInfoRepository repository;
    private LiveData<List<JobInfo>> allJobInfos;
    private MutableLiveData<List<JobInfo>> searchResults;

    //
    public JobInfoViewModel(Application application) {
        super(application);
        repository = new JobInfoRepository(application);
        allJobInfos = repository.getAllJobInfos();
        searchResults = repository.getSearchResults();
    }
    //
    //implement the methods that will be called from
    // within the UI controller in response to button
    // clicks and when setting up observers on the
    // LiveData objects

    MutableLiveData<List<JobInfo>> getSearchResults() {
        return searchResults;
    }

    LiveData<List<JobInfo>> getAllJobInfos() {
        return allJobInfos;
    }

    public void insertJobInfo(JobInfo jobInfo) {
        repository.insertJobInfo(jobInfo);
    }

    public void findJobInfo(String jobInfoId) {
        repository.findJobInfo(jobInfoId);
    }

    public void deleteJobInfo(String jobInfoId) {
        repository.deleteJobInfo(jobInfoId);
    }

}


package com.example.michaelasafoadjei_comp304_001_finalterm;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.lifecycle.LiveData;


import java.util.List;
@Dao
public interface JobInfoDao {


    @Insert
    void insertJobInfo(JobInfo jobInfo);
    //
    @Query("SELECT * FROM JobInfo WHERE jobCode = :jobCode")
    List<JobInfo> findJobInfo(int jobCode);
    //
    @Query("DELETE FROM JobInfo WHERE jobCode = :jobCode")
    void deleteJobInfo(int jobCode);
    //
    @Query("SELECT * FROM JobInfo")
    LiveData<List<JobInfo>> getAllJobInfos();

}
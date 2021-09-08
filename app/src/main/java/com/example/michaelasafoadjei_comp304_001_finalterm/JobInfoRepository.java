package com.example.michaelasafoadjei_comp304_001_finalterm;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
public class JobInfoRepository {
    private MutableLiveData<List<JobInfo>> searchResults = new MutableLiveData<>();
    private LiveData<List<JobInfo>> allInfoJobs;
    private JobInfoDao jobInfoDao;
    //
    public JobInfoRepository(Application application) {           //constructor for taskrepository
        JobInfoRoomDatabase db = JobInfoRoomDatabase.getDatabase(application);
        jobInfoDao = db.jobInfoDao();                             //creates database
        allInfoJobs = jobInfoDao.getAllJobInfos();                   //assigns list from database
    }
    //
    public void insertJobInfo(JobInfo jobinfo) {                                 //inserts the object task with InsertAsyncTask
        InsertAsyncJobInfo insertJobInfo = new InsertAsyncJobInfo(jobInfoDao);      //inserts the object into the database
        insertJobInfo.execute(jobinfo);
    }
    //
    public void deleteJobInfo(String jobCode) {                                //deletes task object from database
        DeleteAsyncJobInfo deleteJobInfo = new DeleteAsyncJobInfo(jobInfoDao);
        deleteJobInfo.execute(jobCode);
    }
    //
    public void findJobInfo(String jobCode) {                                     //queries task from database
        QueryAsyncJobInfo queryJobInfo = new QueryAsyncJobInfo(jobInfoDao);             //creates a new class object querytask to assign database to synchronize
        queryJobInfo.jobInfoRepository = this;                                    //assigns repository
        queryJobInfo.execute(jobCode);                                          //searches repository using taskID
    }
    // add methods that the ViewModel can call to obtain references
    // to the allTasks and searchResults live data objects:
    public LiveData<List<JobInfo>> getAllJobInfos() {                           //returns LiveData from the Task list
        return allInfoJobs;
    }

    public MutableLiveData<List<JobInfo>> getSearchResults() {                //gets all search results from Task list
        return searchResults;
    }
    // results of a search operation are stored whenever
    // an asynchronous search task completes
    private void asyncFinished(List<JobInfo> results) {                        //sets the values after searching
        searchResults.setValue(results);
    }
    //
    private static class QueryAsyncJobInfo extends
            AsyncTask<String, Void, List<JobInfo>> {

        private JobInfoDao asyncJobInfoDao;                                           //creates database for tasks
        private JobInfoRepository jobInfoRepository = null;                           //

        QueryAsyncJobInfo(JobInfoDao dao) {                 //synchronizes database for querying
            asyncJobInfoDao = dao;
        }

        @Override
        protected List<JobInfo> doInBackground(final String... params) {
            return asyncJobInfoDao.findJobInfo(Integer.parseInt(params[0]));
        }

        @Override
        protected void onPostExecute(List<JobInfo> result) {
            jobInfoRepository.asyncFinished(result);
        }
    }

    private static class InsertAsyncJobInfo extends AsyncTask<JobInfo, Void, Void> {

        private JobInfoDao asyncJobInfoDao;

        InsertAsyncJobInfo(JobInfoDao dao) {
            asyncJobInfoDao = dao;
        }

        @Override
        protected Void doInBackground(final JobInfo... params) {
            asyncJobInfoDao.insertJobInfo(params[0]);
            return null;
        }
    }
    private static class DeleteAsyncJobInfo extends AsyncTask<String, Void, Void> {

        private JobInfoDao asyncJobInfoDao;

        DeleteAsyncJobInfo(JobInfoDao dao) {
            asyncJobInfoDao = dao;
        }

        @Override
        protected Void doInBackground(final String... params) {
            asyncJobInfoDao.deleteJobInfo(Integer.parseInt(params[0]));
            return null;
        }
    }
}

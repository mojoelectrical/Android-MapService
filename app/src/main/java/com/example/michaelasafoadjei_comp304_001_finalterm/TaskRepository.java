package com.example.michaelasafoadjei_comp304_001_finalterm;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class TaskRepository {
    //
    private MutableLiveData<List<Task>> searchResults = new MutableLiveData<>();
    private LiveData<List<Task>> allTasks;
    private TaskDao taskDao;
    //
    public TaskRepository(Application application) {           //constructor for taskrepository
        TaskRoomDatabase db = TaskRoomDatabase.getDatabase(application);
        taskDao = db.taskDao();                             //creates database
        allTasks = taskDao.getAllTasks();                   //assigns list from database
    }
    //
    public void insertTask(Task task) {                                 //inserts the object task with InsertAsyncTask
        InsertAsyncTask insertTask = new InsertAsyncTask(taskDao);      //inserts the object into the database
        insertTask.execute(task);
    }
    //
    public void deleteTask(int taskId) {                                //deletes task object from database
        DeleteAsyncTask deleteTask = new DeleteAsyncTask(taskDao);
        deleteTask.execute(taskId);
    }
    //
    public void findTask(int taskId) {                                     //queries task from database
        QueryAsyncTask queryTask = new QueryAsyncTask(taskDao);             //creates a new class object querytask to assign database to synchronize
        queryTask.taskRepository = this;                                    //assigns repository
        queryTask.execute(taskId);                                          //searches repository using taskID
    }
    // add methods that the ViewModel can call to obtain references
    // to the allTasks and searchResults live data objects:
    public LiveData<List<Task>> getAllTasks() {                           //returns LiveData from the Task list
        return allTasks;
    }

    public MutableLiveData<List<Task>> getSearchResults() {                //gets all search results from Task list
        return searchResults;
    }
    // results of a search operation are stored whenever
    // an asynchronous search task completes
    private void asyncFinished(List<Task> results) {                        //sets the values after searching
        searchResults.setValue(results);
    }
    //
    private static class QueryAsyncTask extends
            AsyncTask<Integer, Void, List<Task>> {

        private TaskDao asyncTaskDao;                                           //creates database for tasks
        private TaskRepository taskRepository = null;                           //

        QueryAsyncTask(TaskDao dao) {                 //synchronizes database for querying
            asyncTaskDao = dao;
        }

        @Override
        protected List<Task> doInBackground(final Integer... params) {
            return asyncTaskDao.findTask(params[0]);
        }

        @Override
        protected void onPostExecute(List<Task> result) {
            taskRepository.asyncFinished(result);
        }
    }
    // include the following AsyncTask implementation for
    // inserting tasks into the database
    private static class InsertAsyncTask extends AsyncTask<Task, Void, Void> {

        private TaskDao asyncTaskDao;

        InsertAsyncTask(TaskDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Task... params) {
            asyncTaskDao.insertTask(params[0]);
            return null;
        }
    }
    // This is used when deleting tasks from the database
    private static class DeleteAsyncTask extends AsyncTask<Integer, Void, Void> {

        private TaskDao asyncTaskDao;

        DeleteAsyncTask(TaskDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Integer... params) {
            asyncTaskDao.deleteTask(params[0]);
            return null;
        }
    }
}

package com.example.michaelasafoadjei_comp304_001_finalterm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TaskViewModel mViewModel;
    private EditText txtTaskId, txtTaskName, txtTaskDescription;
    private ListView lstTasks;
    ArrayAdapter<String> tasksAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewModel = ViewModelProviders.of(this).get(TaskViewModel.class);        //
        txtTaskId = findViewById(R.id.txtTaskId);
        txtTaskName = findViewById(R.id.txtTaskName);
        txtTaskDescription = findViewById(R.id.txtTaskDescription);
        lstTasks = findViewById(R.id.lstTasks);


        //Adding LiveData Observers
        mViewModel.getAllTasks().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(@Nullable final List<Task> tasks) {
                ArrayList<String> list = new ArrayList<>();
                for (Task task : tasks )
                {
                    list.add(task.getTaskDescription());
                    txtTaskId.setText(String.valueOf(task.getTaskId()));
                }
                tasksAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, list);
                lstTasks.setAdapter(tasksAdapter);
            }
        });
        //
        mViewModel.getSearchResults().observe(this,
                new Observer<List<Task>>() {
                    @Override
                    public void onChanged(@Nullable final List<Task> tasks) {

                        if (tasks.size() > 0) {
                            txtTaskId.setText(String.valueOf(tasks.get(0).getTaskId()));
                            txtTaskName.setText(tasks.get(0).getTaskName());
                            txtTaskDescription.setText(tasks.get(0).getTaskDescription());
                        } else {
                            txtTaskId.setText("No Match");
                        }
                    }
                });

    }

    private void clearFields() {
        txtTaskId.setText("");
        txtTaskName.setText("");
        txtTaskDescription.setText("");
    }
    public void insertTask(View view)
    {
        String taskName = txtTaskName.getText().toString();
        String taskDescription = txtTaskDescription.getText().toString();
        //
        if (!taskName.equals("") && !taskDescription.equals("")) {
            Task task = new Task(taskName,taskDescription);
            mViewModel.insertTask(task);
            clearFields();
        } else {
            txtTaskId.setText("Incomplete information");
            JobInfo developerJob = new JobInfo("1001","Developer","Amazon",43.64344110398808,-79.38318174664629,"125K");

            UIActivity.jobInfoDB.jobInfoDao().insertJobInfo(developerJob);
        }
    }
    //
    public void findTask(View view)
    {
        mViewModel.findTask(Integer.parseInt(txtTaskId.getText().toString()));

    }
    public void listAll(View view)
    {
        mViewModel.getAllTasks();

    }
}
package com.example.michaelasafoadjei_comp304_001_finalterm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class UIActivity extends AppCompatActivity {

    private JobInfoViewModel mViewModel;
    private Button getDataBtn, displayBtn, mapBtn;
    private CheckBox developer, bAnalyst, sDesigner;
    private TextView showDisplayView;
    public static JobInfoRoomDatabase jobInfoDB;
    ArrayAdapter<String> tasksAdapter;
    public JobInfo developerJob;
    public JobInfo businessAnalystJob;
    public JobInfo solutionsDesignerJob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u_i);

        mViewModel = ViewModelProviders.of(this).get(JobInfoViewModel.class);
        //getDataBtn =(Button) findViewById(R.id.dataBtn);
        displayBtn =(Button) findViewById(R.id.displayBtn);
        mapBtn = (Button)  findViewById(R.id.showMapBtn);

        developer = findViewById(R.id.developerCB);
        bAnalyst = findViewById(R.id.bAnalystCB);
        sDesigner = findViewById(R.id.sDesignerCB);
        showDisplayView = findViewById(R.id.displaytView);

        developerJob = new JobInfo("1001","Developer","Amazon",43.64344110398808,-79.38318174664629,"125K");
        businessAnalystJob = new JobInfo("1002","Business Analyst","Google",43.4544934567389,-80.49914808660397, "140K");
        solutionsDesignerJob = new JobInfo("1003","Solution Designer","IBM", 43.8514336376069,-79.3386178639796, "160K");



    }

    public void INSERTJOBS(View view){
        mViewModel.insertJobInfo(developerJob);
        mViewModel.insertJobInfo(businessAnalystJob);
        mViewModel.insertJobInfo(solutionsDesignerJob);
    }
}
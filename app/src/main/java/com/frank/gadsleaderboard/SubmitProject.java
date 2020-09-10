package com.frank.gadsleaderboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

public class SubmitProject extends AppCompatActivity {
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_project);

        mToolbar = (Toolbar) findViewById(R.id.submit_activity_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Project Submission");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


}
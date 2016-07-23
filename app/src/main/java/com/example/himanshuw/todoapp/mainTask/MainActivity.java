package com.example.himanshuw.todoapp.mainTask;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.himanshuw.todoapp.R;
import com.example.himanshuw.todoapp.Util.ActivityUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maintask_activity);

        MainTaskFragment tasksFragment =
                (MainTaskFragment) getSupportFragmentManager().findFragmentById(R.id.mainTaskFragmentPlaceholder);
        if (tasksFragment == null) {
            // Create the fragment
            tasksFragment = MainTaskFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), tasksFragment, R.id.mainTaskFragmentPlaceholder);
        }
    }

}


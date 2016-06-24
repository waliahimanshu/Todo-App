package com.example.himanshuw.todoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }


    public void AddItem(View view)
    {
        List<String> stringList = new ArrayList<String>();

        EditText  editText =(EditText)findViewById(R.id.editTextView);

        String text = editText.getText().toString();

        stringList.add(text);

        for (String item : stringList)
        {
            Log.i("Test","---------->>"+item);
        }

    }
}

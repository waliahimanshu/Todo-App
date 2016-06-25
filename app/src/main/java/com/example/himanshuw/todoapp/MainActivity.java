package com.example.himanshuw.todoapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayAdapter<String> adapter= null;
    ListView listView =null;
    private ArrayList<String> items;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        listView =(ListView)findViewById(R.id.listView);

        items = new ArrayList<>();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);

        listView.setAdapter(adapter);

        ListViewListener();

    }

    public void AddItem(View view)
    {

        EditText  editText =(EditText)findViewById(R.id.editTextView);
        String text = editText.getText().toString();
        adapter.add(text);
        editText.setText("");
        Log.i(MainActivity.class.getSimpleName(),"---Item Added------->>"+text);

    }

    public void ListViewListener(){
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                items.remove(position);
                adapter.notifyDataSetChanged();

                return true;
            }
        });
    }
}

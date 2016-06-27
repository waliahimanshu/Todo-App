package com.example.himanshuw.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.himanshuw.todoapp.R.string.item_delete_message;

public class MainActivity extends AppCompatActivity implements MainView{

    ArrayAdapter<String> adapter = null;
    ListView listView = null;
    private ArrayList<String> items;
    private int requestCode = 007;
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mainPresenter = new MainPresenter(this,new StorageProvider());

        LoadSavedData();

        DeleteItemListViewListener();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, EditItemActivity.class);
                intent.putExtra("Position", position);
                intent.putExtra("SelectedItem", items.get(position));
                startActivityForResult(intent, requestCode);

            }
        });

    }

    private void LoadSavedData() {

        mainPresenter.OnAppLoad();


        ReadItemsFromFile();

        listView = (ListView) findViewById(R.id.listView);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);

        listView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK && requestCode==007) {
            String name = data.getExtras().getString("UpdatedItem");
            int position = data.getExtras().getInt("Position", 0);

            items.set(position, name);
            adapter.notifyDataSetChanged();

            Toast.makeText(this, "Item Updated to : " + name , Toast.LENGTH_LONG).show();
        }
    }

    public void AddItem(View view) {

        EditText editText = (EditText) findViewById(R.id.editTextView);
        String text = editText.getText().toString();
        adapter.add(text);
        editText.setText("");
        WriteItems();
        Log.i(MainActivity.class.getSimpleName(), "---Item Added------->>" + text);

    }

    public void DeleteItemListViewListener() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                items.remove(position);
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, item_delete_message, Toast.LENGTH_SHORT).show();
                WriteItems();
                return true;
            }
        });
    }

    public void ReadItemsFromFile() {
        File filesDir = getFilesDir();
        File toDoFile = new File(filesDir, "todo.txt");

        try {
            List list = FileUtils.readLines(toDoFile);
            items = new ArrayList<>(list);

        } catch (IOException ex) {
            items = new ArrayList<>();
            ex.printStackTrace();
        }
    }

    public void WriteItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            FileUtils.writeLines(todoFile, items);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


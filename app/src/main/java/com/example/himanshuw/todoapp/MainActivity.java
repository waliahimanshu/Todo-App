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
import java.util.ArrayList;

import static com.example.himanshuw.todoapp.R.string.item_delete_message;

public class MainActivity extends AppCompatActivity implements MainView, View.OnClickListener {

    ArrayAdapter<String> adapter = null;
    ListView listView = null;
    private ArrayList<String> items;
    private int requestCode = 007;
    MainPresenter mainPresenter;

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        findViewById(R.id.AddButton).setOnClickListener(this);

        mainPresenter = new MainPresenter(this, new StorageProvider(getApplicationContext()));



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
        mainPresenter.OnAppLoadShowStoredListData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK && requestCode == 007) {
            String name = data.getExtras().getString("UpdatedItem");
            int position = data.getExtras().getInt("Position", 0);

            items.set(position, name);
            adapter.notifyDataSetChanged();

            Toast.makeText(this, "Item Updated to : " + name, Toast.LENGTH_LONG).show();
        }
    }

    public void AddItem(View view) {

//        EditText editText = (EditText) findViewById(R.id.editTextView);
//        String text = editText.getText().toString();
//        adapter.add(text);
//        editText.setText("");
//
//        mainPresenter.WriteData(items);
//
//                Log.i(MainActivity.class.getSimpleName(), "---Item Added------->>" + text);

    }

    public void DeleteItemListViewListener() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                items.remove(position);
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, item_delete_message, Toast.LENGTH_SHORT).show();
                mainPresenter.WriteData(items);
                return true;
            }
        });
    }

    @Override
    public String getUserEnteredItem() {
        editText = (EditText) findViewById(R.id.editTextView); //// TODO: 27/06/2016  inject view

        String text = editText.getText().toString();

        return text;
    }

    @Override
    public void showEmptyItemError(int resourceId) {

        editText.setError(getString(resourceId));
    }

    @Override
    public void PopulateListViewOnAdapter(ArrayList<String> Item) {

        items = Item;
        listView = (ListView) findViewById(R.id.listView);//// TODO: 28/06/2016 inject view

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Item);

        listView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        EditText editText = (EditText) findViewById(R.id.editTextView);
        String text = editText.getText().toString();
        adapter.add(text);
        editText.setText("");

        mainPresenter.WriteData(items);

        Log.i(MainActivity.class.getSimpleName(), "---Item Added------->>" + text);
    }
}


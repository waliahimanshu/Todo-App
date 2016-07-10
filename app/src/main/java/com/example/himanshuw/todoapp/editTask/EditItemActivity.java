package com.example.himanshuw.todoapp.editTask;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.himanshuw.todoapp.R;

public class EditItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.edittask_activity);

        String selectedItem = getIntent().getStringExtra("SelectedItem");

        Log.i(EditItemActivity.class.getSimpleName(),"SelectedItem "+ selectedItem);

        EditText edit = (EditText) findViewById(R.id.editItemText);
        edit.setText(selectedItem);
        edit.setSelection(edit.getText().length());

    }

    public void UpdateItem(View view) {
        EditText edit = (EditText) findViewById(R.id.editItemText);

        Intent updatedData = new Intent();

        updatedData.putExtra("UpdatedItem", edit.getText().toString());
        Integer position = getIntent().getIntExtra("Position",0);
        updatedData.putExtra("Position",position);

        Log.i(EditItemActivity.class.getSimpleName(),"UpdateClicked "+ edit.getText().toString());

        setResult(RESULT_OK, updatedData);
        finish();

    }
}

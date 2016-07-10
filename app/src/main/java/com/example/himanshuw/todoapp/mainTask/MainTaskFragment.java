package com.example.himanshuw.todoapp.mainTask;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.himanshuw.todoapp.R;
import com.example.himanshuw.todoapp.data.StorageInteractor;
import com.example.himanshuw.todoapp.editTask.EditItemActivity;

import java.util.ArrayList;

import static com.example.himanshuw.todoapp.R.string.item_delete_message;

public class MainTaskFragment extends Fragment implements MainTaskContract.View,
                                                          View.OnClickListener,
                                                          AdapterView.OnItemClickListener,
                                                          AdapterView.OnItemLongClickListener {

    private static final int RESULT_OK = 7;
    EditText editText=null;
    ListView listView = null;
    MainPresenter mainPresenter;
    private ArrayList<String> items;
    ArrayAdapter<String> adapter = null;
    View root;

    public MainTaskFragment() {
        // Required empty public constructor
    }

    public static MainTaskFragment newInstance() {
        return new MainTaskFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.maintask_fragment, container, false);

        editText = (EditText) root.findViewById(R.id.editTextView);


        Button button = (Button) root.findViewById(R.id.AddButton);
        button.setOnClickListener(this);


        listView = (ListView) root.findViewById(R.id.listView);

        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);

        Context applicationContext = getActivity().getApplicationContext();

        mainPresenter = new MainPresenter(this, new StorageInteractor(applicationContext));
        LoadSavedData();

        return root;
    }

    private void LoadSavedData() {
        mainPresenter.OnAppLoadShowStoredListData();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public String getUserEnteredItem() {
        return editText.getText().toString();
    }

    @Override
    public void showEmptyItemError(int resourceId) {
        editText.setError(getString(resourceId));
    }

    @Override
    public void PopulateListViewOnAdapter(ArrayList<String> Item) { //on load present saved data works
        items = Item;
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, Item);
        listView.setAdapter(adapter);
    }

    public void onClick(View v) { //add save item working
        editText = (EditText) root.findViewById(R.id.editTextView);
        String text = editText.getText().toString();
        adapter.add(text);
        editText.setText("");
        mainPresenter.WriteData(items);
        Log.i(MainActivity.class.getSimpleName(), "---Item Added------->>" + text);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) { //navigate to new activity works
        Intent intent = new Intent(getActivity(), EditItemActivity.class);
        intent.putExtra("Position", position);
        intent.putExtra("SelectedItem", items.get(position));
        startActivityForResult(intent, 7);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) { //delete item
        items.remove(position);
        adapter.notifyDataSetChanged();
        Toast.makeText(getActivity(), item_delete_message, Toast.LENGTH_SHORT).show();
        mainPresenter.WriteData(items);
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

       // if (resultCode == RESULT_OK && requestCode == 7) {
            String name = data.getExtras().getString("UpdatedItem");
            int position = data.getExtras().getInt("Position", 0);
            items.set(position, name);
            adapter.notifyDataSetChanged();
            Toast.makeText(getContext(), "Item Updated to : " + name, Toast.LENGTH_LONG).show();
       // }
    }
}

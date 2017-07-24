package com.example.himanshuw.todoapp.data;

import android.content.Context;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class StorageInteractor {

    private Context applicationContext;

    public StorageInteractor(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    public ArrayList<String> getSavedDataFromFile() {
        File filesDir = applicationContext.getFilesDir();
        File toDoFile = new File(filesDir, "todo.txt");
        ArrayList items;
        try {
            List list = FileUtils.readLines(toDoFile);
            items = new ArrayList<>(list);
        } catch (IOException ex) {
            items = new ArrayList<>();
            ex.printStackTrace();
        }
        return items;
    }

    public void writeToFile(ArrayList<String> items) {
        File filesDir = applicationContext.getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            FileUtils.writeLines(todoFile, items);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

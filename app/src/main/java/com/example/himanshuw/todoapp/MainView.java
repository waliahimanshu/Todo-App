package com.example.himanshuw.todoapp;


import java.util.ArrayList;

/**
 * Created by HimanshuW on 27/06/2016.
 */
public interface MainView {

    String getUserEnteredItem();

    void showEmptyItemError(int resourceId);

    void PopulateListViewOnAdapter(ArrayList<String> Item);
}

package com.example.himanshuw.todoapp;

import java.util.ArrayList;

/**
 * Created by HimanshuW on 27/06/2016.
 */
public class MainPresenter {


    private MainView mainView;
    private StorageProvider storageProvider;

    public MainPresenter(MainView mainView, StorageProvider storageProvider) {
        this.mainView = mainView;
        this.storageProvider = storageProvider;
    }

    public void OnClickSaveItemButton() {

        String item = mainView.getUserEnteredItem();
        if(item.isEmpty())
        {
            mainView.showEmptyItemError(R.string.empty_item_error_message);
        }
    }

    public void OnAppLoadShowStoredListData() {
        ArrayList<String> savedData = storageProvider.getSavedDataFromFile();
        mainView.PopulateListViewOnAdapter(savedData);
    }


    public void WriteData(ArrayList<String> items) {
        storageProvider.WriteToFile(items);
    }
}





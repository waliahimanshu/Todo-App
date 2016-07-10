package com.example.himanshuw.todoapp.mainTask;

import com.example.himanshuw.todoapp.R;
import com.example.himanshuw.todoapp.data.StorageInteractor;

import java.util.ArrayList;

/**
 * Created by HimanshuW on 27/06/2016.
 */
public class MainPresenter {


    private MainTaskFragment mainView;
    private StorageInteractor storageInteractor;

    public MainPresenter(MainTaskFragment mainView, StorageInteractor storageInteractor) {
        this.mainView = mainView;
        this.storageInteractor = storageInteractor;
    }

    public void ValidateItemEntered() {

        String item = mainView.getUserEnteredItem();
                if(item.isEmpty()) //// TODO: 28/06/2016 if check can be done in Interactor ?
        {
            mainView.showEmptyItemError(R.string.empty_item_error_message);
        }
    }

    public void OnAppLoadShowStoredListData() {
        ArrayList<String> savedData = storageInteractor.getSavedDataFromFile();
        mainView.PopulateListViewOnAdapter(savedData);
    }



    public void WriteData(ArrayList<String> items) {
        storageInteractor.WriteToFile(items);
    }
}





package com.example.himanshuw.todoapp.mainTask;

import com.example.himanshuw.todoapp.R;
import com.example.himanshuw.todoapp.data.StorageInteractor;

import java.util.ArrayList;

public class MainPresenter {


    private MainTaskContract.View mainView;
    private StorageInteractor storageInteractor;

    public MainPresenter(MainTaskContract.View mainView, StorageInteractor storageInteractor) {
        this.mainView = mainView;
        this.storageInteractor = storageInteractor;
    }

    public void validateItemEntered() {

        String item = mainView.getUserEnteredItem();
                if(item.isEmpty()) //// TODO: 28/06/2016 if check can be done in Interactor ?
        {
            mainView.showEmptyItemError(R.string.empty_item_error_message);
        }
    }

    public void onAppLoadShowStoredListData() {
        ArrayList<String> savedData = storageInteractor.getSavedDataFromFile();
        mainView.populateListViewOnAdapter(savedData);
    }

    void writeData(ArrayList<String> items) {
        storageInteractor.WriteToFile(items);
    }
}


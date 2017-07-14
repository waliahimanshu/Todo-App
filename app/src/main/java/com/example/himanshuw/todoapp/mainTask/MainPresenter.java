package com.example.himanshuw.todoapp.mainTask;

import com.example.himanshuw.todoapp.R;
import com.example.himanshuw.todoapp.data.StorageInteractor;

import java.util.ArrayList;

public class MainPresenter {


    private MainTaskContract.View mainView;
    private StorageInteractor storageInteractor;
    private MainTaskContract.View view;

    public MainPresenter(MainTaskFragment mainView, StorageInteractor storageInteractor, MainTaskContract.View mainTaskContractView) {
        this.mainView = mainView;
        this.storageInteractor = storageInteractor;
        this.view = mainTaskContractView;
    }

    @Override
    public void ValidateItemEntered() {

        String item = mainView.getUserEnteredItem();
        if (item.isEmpty()) //// TODO: 28/06/2016 if check can be done in Interactor ?
        {
//            mainView.(R.string.empty_item_error_message);
        }
    }

    @Override
    public void getUserEnteredTask() {

    }

    @Override
    public void getSavedTasks() {

        ArrayList<String> savedData = storageInteractor.getSavedDataFromFile();
        mainView.ShowSavedTasksOnLoad(savedData);
    }

    @Override
    public void UpdateSavedTasks(ArrayList<String> items) {
        storageInteractor.WriteToFile(items);

    }
}
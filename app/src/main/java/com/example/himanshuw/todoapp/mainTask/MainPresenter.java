package com.example.himanshuw.todoapp.mainTask;

import com.example.himanshuw.todoapp.data.StorageInteractor;

import java.util.ArrayList;

public class MainPresenter implements MainTaskContract.Presenter {

    private StorageInteractor storageInteractor;
    private MainTaskContract.View view;

    public MainPresenter(StorageInteractor storageInteractor, MainTaskContract.View mainTaskContractView) {
        this.storageInteractor = storageInteractor;
        this.view = mainTaskContractView;
    }

    @Override
    public void validateItemEntered() {
        String item = view.getUserEnteredItem();
        if (item.isEmpty()) {
//            view.(R.string.empty_item_error_message);
        }
    }

    @Override
    public void getUserEnteredTask() {

    }

    @Override
    public void getSavedTasks() {
        ArrayList<String> savedData = storageInteractor.getSavedDataFromFile();
        view.showSavedTasksOnLoad(savedData);
    }

    @Override
    public void updateSavedTasks(ArrayList<String> items) {
        storageInteractor.writeToFile(items);
    }
}
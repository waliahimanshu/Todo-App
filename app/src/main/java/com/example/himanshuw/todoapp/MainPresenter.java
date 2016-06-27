package com.example.himanshuw.todoapp;

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

    public void OnAppLoad() {

    }
}





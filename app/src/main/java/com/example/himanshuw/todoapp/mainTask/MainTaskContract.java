package com.example.himanshuw.todoapp.mainTask;

import java.util.ArrayList;

public interface MainTaskContract {

    interface View {

        String getUserEnteredItem();

        void ShowSavedTasksOnLoad(ArrayList<String> Item);

        void populateListViewOnAdapter(ArrayList<String> Item);
    }

    interface Presenter {

        void getUserEnteredTask();

        void getSavedTasks();

        void UpdateSavedTasks(ArrayList<String> items);

       void ValidateItemEntered();


    }
}

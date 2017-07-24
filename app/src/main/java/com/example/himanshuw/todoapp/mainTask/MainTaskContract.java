package com.example.himanshuw.todoapp.mainTask;

import java.util.ArrayList;

public interface MainTaskContract {

    interface View {

        String getUserEnteredItem();

        void showSavedTasksOnLoad(ArrayList<String> Item);

        void populateListViewOnAdapter(ArrayList<String> Item);

        void showEmptyItemError(int empty_item_error_message);
    }

    interface Presenter {

        void getUserEnteredTask();

        void getSavedTasks();

        void updateSavedTasks(ArrayList<String> items);

        void validateItemEntered();
    }
}

package com.example.himanshuw.todoapp.mainTask;

import java.util.ArrayList;

/**
 * Created by waliahimanshu on 10/07/2016.
 */
public interface MainTaskContract {

    interface View {

        String getUserEnteredItem();

        void ShowSavedTasksOnLoad(ArrayList<String> Item);


    }

    interface Presenter {

        void getUserEnteredTask();

        void getSavedTasks();

        void UpdateSavedTasks(ArrayList<String> items);

       void ValidateItemEntered();


    }
}

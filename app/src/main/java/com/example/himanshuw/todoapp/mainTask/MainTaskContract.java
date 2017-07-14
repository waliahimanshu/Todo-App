package com.example.himanshuw.todoapp.mainTask;

import java.util.ArrayList;

public interface MainTaskContract {

    interface View {
        String getUserEnteredItem();

        void showEmptyItemError(int resourceId);

        void populateListViewOnAdapter(ArrayList<String> Item);
    }

    interface  Presenter {

    }
}

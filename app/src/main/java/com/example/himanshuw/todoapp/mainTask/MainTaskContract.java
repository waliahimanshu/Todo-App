package com.example.himanshuw.todoapp.mainTask;

import java.util.ArrayList;

/**
 * Created by waliahimanshu on 10/07/2016.
 */
public interface MainTaskContract {

    interface View {
        String getUserEnteredItem();

        void showEmptyItemError(int resourceId);

        void PopulateListViewOnAdapter(ArrayList<String> Item);
    }

    interface  Presenter {

    }
}

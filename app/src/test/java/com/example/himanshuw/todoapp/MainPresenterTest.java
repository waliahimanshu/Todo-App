package com.example.himanshuw.todoapp;

import com.example.himanshuw.todoapp.data.StorageInteractor;
import com.example.himanshuw.todoapp.mainTask.MainPresenter;
import com.example.himanshuw.todoapp.mainTask.MainTaskContract;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    @Mock
    MainTaskContract.View mainView;

    @Mock
    StorageInteractor storageInteractor;
    private MainPresenter mainPresenter;

    @Before
    public void setUp() throws Exception {
        mainPresenter = new MainPresenter(mainView, storageInteractor);

    }

    @Test
    public void testPassSavedDataToView() throws Exception {

        ArrayList<String> items = new ArrayList<>();
        items.add("Item1");
        items.add("Item2");
        items.add("Item3");

        when(storageInteractor.getSavedDataFromFile()).thenReturn(items);

        mainPresenter.onAppLoadShowStoredListData();

        verify(mainView).populateListViewOnAdapter(items);
    }
}
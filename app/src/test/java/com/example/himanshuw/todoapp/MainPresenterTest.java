package com.example.himanshuw.todoapp;

import com.example.himanshuw.todoapp.data.StorageInteractor;
import com.example.himanshuw.todoapp.mainTask.MainPresenter;
import com.example.himanshuw.todoapp.mainTask.MainTaskFragment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

/**
 * Created by HimanshuW on 28/06/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    @Mock
    MainTaskFragment mainTaskFragment;
    @Mock
    MainPresenter mainPresenter;

    @Mock
    StorageInteractor storageInteractor;

    @Test
    public void testPassSavedDataToView() throws Exception {

        ArrayList<String> items = new ArrayList<>();
        items.add("Item1");
        items.add("Item2");
        items.add("Item3");

       // MainPresenter mainPresenter = new MainPresenter(mainView, storageInteractor);

        when(storageInteractor.getSavedDataFromFile()).thenReturn(items);

      //  mainPresenter.PassSavedDataToView(items);

        //verify(mainView.)




    }
}
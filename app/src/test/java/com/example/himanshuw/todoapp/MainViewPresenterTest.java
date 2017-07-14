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
public class MainViewPresenterTest {

    @Mock
    MainTaskContract.View view;

    @Mock
    StorageInteractor dataProvide;

    private MainPresenter presenter;

    @Before
    public void SetUp() throws Exception {
        presenter = new MainPresenter(dataProvide,view);
    }

    @Test
    public void shouldShowErrorMessageWhenItemIsBlank() throws Exception {
        when(view.getUserEnteredItem()).thenReturn("");

        presenter.validateItemEntered();

//        verify(view).showEmptyItemError(R.string.empty_item_error_message);
    }

    @Test
    public void shouldLoadSavedDataOnAppStart() throws Exception {

        ArrayList<String> items = new ArrayList<>();
        items.add("Item1");
        items.add("Item2");
        items.add("Item3");

        when(dataProvide.getSavedDataFromFile()).thenReturn(items);

        presenter.getSavedTasks();

        verify(view).showSavedTasksOnLoad(items);

    }
}

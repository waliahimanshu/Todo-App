package com.example.himanshuw.todoapp;

import android.text.TextUtils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.w3c.dom.Text;

import static org.mockito.Mockito.when;

/**
 * Created by HimanshuW on 27/06/2016.
 */

@RunWith(MockitoJUnitRunner.class)
public class MainViewPresenterTest {

    @Mock
    MainView view ;

    @Mock
    StorageProvider dataProvide ;

    MainPresenter presenter;

    @Before
    public void  SetUp() throws  Exception
    {
        presenter = new MainPresenter(view, dataProvide);
    }


    @Test
    public void shouldShowErrorMessageWhenItemIsBlank() throws  Exception{

        when(view.getItem()).thenReturn("");

        presenter.OnAppLoad();



    }
}

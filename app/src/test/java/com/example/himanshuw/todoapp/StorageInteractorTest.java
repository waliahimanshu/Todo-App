package com.example.himanshuw.todoapp;

import android.app.Activity;
import android.content.Context;

import com.example.himanshuw.todoapp.data.StorageInteractor;

import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by HimanshuW on 28/06/2016.
 */
public class StorageInteractorTest extends Activity{

    @Mock
    Context context;

    StorageInteractor storageInteractor;

    @Test
    @Ignore
    public void testGetSavedData() throws Exception { //// TODO: 28/06/2016 this test throws exception

        context = mock(Context.class);

        when(context.getFilesDir()).thenReturn(new File("/data/data/com.example.himanshuw.todoapp/files/"));

         storageInteractor = new StorageInteractor(context);

        ArrayList<String> savedData = storageInteractor.getSavedDataFromFile();

        assertNotNull(savedData);

    }

    @Test
    @Ignore
    public void testWriteToFile() throws Exception {

        ArrayList<String> items = new ArrayList<>();

        items.add("NewItem");

        storageInteractor = new StorageInteractor(context);
        storageInteractor.WriteToFile(items);





    }
}
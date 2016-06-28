package com.example.himanshuw.todoapp;

import android.app.Activity;
import android.content.Context;

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
public class StorageProviderTest extends Activity{

    @Mock
    Context context;

    StorageProvider storageProvider;

    @Test
    @Ignore
    public void testGetSavedData() throws Exception { //// TODO: 28/06/2016 this test throws exception

        context = mock(Context.class);

        when(context.getFilesDir()).thenReturn(new File("/data/data/com.example.himanshuw.todoapp/files/"));

         storageProvider = new StorageProvider(context);

        ArrayList<String> savedData = storageProvider.getSavedDataFromFile();

        assertNotNull(savedData);

    }

    @Test
    @Ignore
    public void testWriteToFile() throws Exception {

        ArrayList<String> items = new ArrayList<>();

        items.add("NewItem");

        storageProvider = new StorageProvider(context);
        storageProvider.WriteToFile(items);





    }
}
package com.example.infosec;

import com.example.infosec.viewmodel.AppViewModel;

import org.junit.Test;
import org.mockito.Mockito;

public class AppViewModelTest {

    AppViewModel appViewModel;

    @Test
    public void testModel(){
        appViewModel.getNewsFeed();
       // Mockito.verify(appViewModel)
    }
}

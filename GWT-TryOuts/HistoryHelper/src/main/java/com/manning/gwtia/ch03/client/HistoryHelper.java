package com.manning.gwtia.ch03.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class HistoryHelper implements EntryPoint {

    ExamplePanel examples = new ExamplePanel();
    
    /**
     * Entry Point. Add an ExamplePanel to the RootPanel, and handle any history token that might
     * have been present when application started (this might lead to starting an example immediately, 
     * or leaving the introduction screen displayed).
     */
    @Override
    public void onModuleLoad(){
        RootPanel.get().add(examples, 0, 0);
    }
        
}

package com.manning.gwtia.ch02.client;

import java.util.Date;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public class HelloWorld implements EntryPoint {

	@Override
	public void onModuleLoad(){
		Label theGreeting = new Label("Hello World, " + new Date() + "! This is my first migrated GWT project!!!");
		RootPanel.get().add(theGreeting);
	}
}

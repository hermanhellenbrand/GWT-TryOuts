package com.manning.gwtia.ch03.client;

import java.util.Date;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public class BasicProject implements EntryPoint {

	@Override
	public void onModuleLoad(){
		Label theGreeting = new Label("BasicProject on " + new Date());
		RootPanel.get().add(theGreeting);
	}

}

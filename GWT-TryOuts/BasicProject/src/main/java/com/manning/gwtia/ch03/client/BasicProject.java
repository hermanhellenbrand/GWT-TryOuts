package com.manning.gwtia.ch03.client;

/*
 * The import list is longer in this example than the HelloWorld in Ch2.
 * This is simply because we do more things
 */
import com.google.gwt.core.client.EntryPoint;
import com.manning.gwtia.ch03.client.ui.EventHandling;
import com.manning.gwtia.ch03.client.ui.HistoryManagement;
import com.manning.gwtia.ch03.client.ui.UserInterface;

public class BasicProject implements EntryPoint {

	/* ============
     * Class fields
     * ============
     */
	private UserInterface ui;

	/* ==============
     * Constructor(s)
     * ============== 
     */
    /**
     * Default constructor.
     */
	public BasicProject() {
		// do nothing
	}
	
	/* =============
     * Class methods
     * =============
     */
	/**
	 * This is the entry point method which will create the GUI and set up the
	 * History handling.
	 */
	@Override
	public void onModuleLoad() {
		// Create the user interface
		ui = UserInterface.setUpGui();
		// Set up history management
		HistoryManagement.setUpHistoryManagement(ui);
		// Set up all the event handling required for the application.
		EventHandling.setUpEventHandling(ui);
	}
	
	
	
}

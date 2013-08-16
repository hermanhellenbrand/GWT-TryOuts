package com.manning.gwtia.ch03.client.ui;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.ClosingEvent;
import com.google.gwt.user.client.Window.ClosingHandler;
import com.manning.gwtia.ch03.client.types.Pages;

public class HistoryManagement implements ValueChangeHandler<String> {

    private UserInterface ui;

    private HistoryManagement(final UserInterface userInterface) {
        this.ui = userInterface;
    }

    public static void setUpHistoryManagement(final UserInterface userInterface) {
        // Make this class your history manager (see onValueChange method)
        History.addValueChangeHandler(new HistoryManagement(userInterface));
        // Handle any existing history token
        History.fireCurrentHistoryState();
        // Trap user hitting back button too many times.
        Window.addWindowClosingHandler(new ClosingHandler() {
            public void onWindowClosing(ClosingEvent event) {
                event.setMessage("Ran out of history.  Now leaving application, is that OK?");
            }
        });
    }

    /**
     * This is the function that handles history change events.
     * 
     * When the history token is changed in the URL, GWT fires a
     * ValueChangeEvent that is handled in this method (since we called
     * History.addValueChangeHandler(this) in the onModuleLoad method).
     * 
     * The history token is the part of the URL that follows the hash symbol.
     * For example http://www.someurl.se/MyApp.html#home has the token "home".
     */
    public void onValueChange(ValueChangeEvent<String> event) {
        // Get the token from the event
        String page = event.getValue().trim();
        // Check if the token is null or empty
        if ((page == null) || (page.equals("")))
            showHomePage();
        // Else check what the token is and cal the appropriate method.
        else if (page.equals(Pages.PRODUCTS.getText()))
            showProducts();
        else if (page.equals(Pages.CONTACT.getText()))
            showContact();
        else
            showHomePage();
    }

    /**
     * Show the contact page - i.e. place a new label on the current screen
     */
    private void showContact() {
        ui.getContent().selectTab(Pages.CONTACT.getVal());
    }

    /**
     * Show the home page - i.e. place a new label on the current screen
     */
    private void showHomePage() {
        ui.getContent().selectTab(Pages.HOME.getVal());
    }

    /**
     * Show the products page - i.e. place a new label on the current screen
     */
    private void showProducts() {
        ui.getContent().selectTab(Pages.PRODUCTS.getVal());
    }
}

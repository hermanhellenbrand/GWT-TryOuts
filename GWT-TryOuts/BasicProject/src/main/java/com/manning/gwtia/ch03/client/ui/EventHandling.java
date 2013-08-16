package com.manning.gwtia.ch03.client.ui;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.manning.gwtia.ch03.client.types.Pages;

public class EventHandling {

    /**
     * Here we set up the event handling that we will drive user interaction.
     * 
     * 1. A SelectionHandler for when a new tab is selected. 2. A ClickHandler
     * for if the search button is clicked. 3. Some Mouse handlers and
     * ClickHandler if the feedback tab is interacted with.
     * 
     * You don't have to follow this style of programming and put all your event
     * handling code into one method, we do it here as it makes sense and helps
     * us examine particular aspects of code in one place (however, by doing it
     * this way instead of, for example adding handlers directly after defining
     * widgets, means we should check each widget is not null before adding the
     * handler - we won't as by inspection we know all widgets are instantiated
     * elsewhere before this method is called; but you should be aware of these
     * type of dependencies in your own code).
     * 
     */
    public static void setUpEventHandling(final UserInterface ui) {

        /**
         * If a tab is selected then we want to add a new history item to the
         * History object. (this effectively changes the token in the URL, which
         * is detected and handled by GWT's History sub-system.
         */
        ui.getContent().addSelectionHandler(new SelectionHandler<Integer>() {
            public void onSelection(SelectionEvent<Integer> event) {
                // Determine the tab that has been selected by interrogating the
                // event object.
                Integer tabSelected = event.getSelectedItem();

                // Create a new history item for this tab (using data retrieved
                // from Pages enumeration)
                History.newItem(Pages.values()[tabSelected].getText());
            }
        });

        /**
         * If the search button is clicked, we want to display a little pop-up
         * panel which allows the user to type in a search term. The TextBox
         * where the user types search terms should automatically gain focus to
         * make it more user friendly.
         */
        ui.getSearch().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                FlowPanel qAnswer;
                final TextBox searchTerm = new TextBox();

                // If search button is clicked for the first time then the
                // searchRequest Pop-up panel does not yet exist
                // so we'll build it first as follows:
                if (ui.getSearchRequest() == null) {
                    // Create the PopupPanel widget
                    ui.setSearchRequest(new PopupPanel());

                    // Create a FlowPanel to hold the question and answer for
                    // the search term
                    qAnswer = new FlowPanel();
                    // Add a Label to the Flow Panel that represents the
                    // "Search For" text
                    qAnswer.add(new Label("Search For:"));
                    // Add the answer TextBox (which we declared above) to the
                    // FlowPanel
                    qAnswer.add(searchTerm);

                    // Add a change handler to the TextBox so that when there is
                    // a change to search term
                    // we would "start" the search (we don't implement the
                    // search capability in this simple example)
                    searchTerm.addChangeHandler(new ChangeHandler() {
                        public void onChange(ChangeEvent event) {
                            // Hide the popup panel from the screen
                            ui.getSearchRequest().hide();
                            // "start" the search
                            Window.alert("If implemented, now we would search for: " + searchTerm.getText());
                        }
                    });

                    // Add the question/answer to the search pop-up.
                    ui.getSearchRequest().add(qAnswer);

                    // Now we'll set some properties on the pop up panel, we'll:
                    // * indicate that the popup should be animated
                    // * show it relative to the search button widget
                    // * close it if the user clicks outside of it popup panel,
                    // or if the history token is changed
                    ui.getSearchRequest().setAnimationEnabled(true);
                    ui.getSearchRequest().showRelativeTo(ui.getSearch());
                    ui.getSearchRequest().setAutoHideEnabled(true);
                    ui.getSearchRequest().setAutoHideOnHistoryEventsEnabled(true);
                } else {
                    // search popup already exists, so clear the TextBox
                    // contents...
                    searchTerm.setText("");
                    // ... and simply show it.
                    ui.getSearchRequest().show();
                }

                // Set the TextBox of the popup Panel to have focus - this means
                // that once the pop up is displayed
                // then any keypresses the user makes will appear directly inthe
                // TextBox. If we didn't do this, then
                // who knows where the text would appear.
                searchTerm.setFocus(true);
            }
        });

        /**
         * If the user moves mouse over feedback tab, change its style
         * (increases its size and changes colour - styles are in
         * BasicProject.css)
         */
        ui.getFeedback().addMouseOverHandler(new MouseOverHandler() {
            public void onMouseOver(MouseOverEvent event) {
                // Remove existing normal style
                ui.getFeedback().removeStyleName("normal");
                // Add the active style
                ui.getFeedback().addStyleName("active");
                // Set overflow of whole HTML page to hidden to minimise display
                // of scroll bars.
                RootPanel.getBodyElement().getStyle().setProperty("overflow", "hidden");
            }
        });

        /**
         * If use moves mouse out of the feedback panel, return its style to
         * normal (decreases its size and changes colour - styles are in
         * BasicProject.css)
         */
        ui.getFeedback().addMouseOutHandler(new MouseOutHandler() {
            public void onMouseOut(MouseOutEvent event) {
                ui.getFeedback().removeStyleName("active");
                ui.getFeedback().addStyleName("normal");
                RootPanel.getBodyElement().getStyle().setProperty("overflow", "auto");
            }
        });

        /**
         * If user clicks on the feedback tab we should start some feedback
         * functionality. In this example, it simply displays an alert to the
         * user.
         */
        ui.getFeedback().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                Window.alert("You could provide feedback if this was implemented");
            }
        });
    }

}

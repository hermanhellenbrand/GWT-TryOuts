package com.manning.gwtia.ch03.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SimpleHtmlSanitizer;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.manning.gwtia.ch03.client.types.BP;
import com.manning.gwtia.ch03.client.types.Pages;

public class UserInterface {

	/** A popup panel that will be displayed if the search button is selected. */
	PopupPanel searchRequest;
	/** This TabLayoutPanel will hold the application's 3 "pages" of content. */
	TabLayoutPanel content;
	/** This button will wrap the existing HTML button defined in the HTML page and is used for the dummy search capability. */
	Button search;
	/** This panel sits on the right hand side of the page to allow user feedback. It will slide in when the mouse is over it and slides back out again if the mouse moves off it. */
	FocusPanel feedback;
	
	/** The image logo. */
	Image logo;
	/** The HTMLPanels that will hold the content we want to display in the TabPanel. */
	HTMLPanel homePanel;
	HTMLPanel productsPanel;
	HTMLPanel contactPanel;
	
	public static UserInterface ui;
	
	public static UserInterface setUpGui() {
		ui = new UserInterface();
		// Build the TabPanel content from existing HTML page text
		ui.buildTabContent();
		// Wrap the existing search button
		ui.wrapExisitngSearchButton();
		// Insert a logo into a defined slot in the HTML page
		ui.insertLogo();
		// Create the Feedback tab on the right of the page
		ui.createFeedbackTab();

		// Style the TabPanel using methods from the UIObject it inherits
		ui.styleTabPanelUsingUIObject();
		// Style the Button using low level DOM access
		ui.styleButtonUsingDOM();

		// Add the feedback panel directly to the page
		RootPanel.get().add(ui.feedback);
		
		// Add the logo to the DOM element with id of "logo"
		RootPanel logoSlot = RootPanel.get("logo");
		if (logoSlot != null)
			logoSlot.add(ui.logo);
		// Add the TabPanel to the DOM element with the id of "content"
		RootPanel contentSlot = RootPanel.get("content");
		if (contentSlot != null)
			contentSlot.add(ui.content);
		// There's no need to add the button, as it is already in the original
		// HTML page.
		return ui;
	}
	
	/**
	 * We'll build the tab panel's content from the HTML that is already in the
	 * HTML page.
	 */
	private void buildTabContent() {
		// Create the main content widget
		// First retrieve the existing content for the pages from the HTML page
		homePanel = new HTMLPanel(getContent(Pages.HOME.getText()));
		productsPanel = new HTMLPanel(getContent(Pages.PRODUCTS.getText()));
		contactPanel = new HTMLPanel(getContent(Pages.CONTACT.getText()));

		// set the style of HTMLPanels
		homePanel.addStyleName("htmlPanel");
		productsPanel.addStyleName("htmlPanel");
		contactPanel.addStyleName("htmlPanel");

		// Create the tab panel widget
		content = new TabLayoutPanel(40, Unit.PX);

		// Add the content we have just created to the tab panel widget
		content.add(homePanel, Pages.HOME.getText());
		content.add(productsPanel, Pages.PRODUCTS.getText());
		content.add(contactPanel, Pages.CONTACT.getText());

		// Indicate that we should show the HOME tab initially.
		content.selectTab(BP.DECK_HOME);
	}
	
	/**
	 * Wrap the search button that already exists on the HTML page and store it
	 * as the previously declared search Button widget. I the button doesn't
	 * exist (you could, for example, edit the HTML page to take it away or
	 * change its id value to something else) then we'll log that fact and
	 * create it to avoid null pointer exceptions when accessing the button
	 * elsewhere in the application (like when adding event handlers to it).
	 */
	private void wrapExisitngSearchButton() {
		// Try and find the DOM element
		Element el = DOM.getElementById("search");

		// If the element is not null, then we've found it, so let's wrap it
		if (el != null) {
			search = Button.wrap(el);
		} else {
			// The search button is missing in the underlying HTML page, so we
			// can't wrap it...
			// Let's log the fact it is missing - in development mode this will
			// appear
			// in the console, in web mode the code will be compiled out
			GWT.log("The search button is missing in the underlying HTML page, so we can't wrap it...trying to create it instead");
			// We should play safe and create it manually and throw it on the
			// page somewhere - otherwise we risk having
			// null pointer exceptions elsewhere in our application as the
			// button doesn't exist yet.
			search = new Button("search");
			RootPanel.get().add(search);
		}
	}
	
	/**
	 * Here we set up the logo by creating a new Image widget, and prevent the
	 * default browser action from occuring on it.
	 */
	private void insertLogo() {
		// Create the logo image and prevent being able to drag it to browser
		// location bar
		// by overriding its onBrowserEvent method.
		logo = new Image(GWT.getModuleBaseURL() + "../" + BP.LOGO_IMAGE_NAME) {
		    
			public void onBrowserEvent(Event evt) {
				// Comment out the next line to be able to drag logo to the
				// browser location
				// bar; leave it in to prevent the default browser action.
				evt.preventDefault();

				// Play nice with the event system by bubbling the event upwards
				super.onBrowserEvent(evt);
			}
		};
		logo.setWidth(logo.getWidth() + "px");
		logo.setHeight(logo.getHeight() + "px");
	}
	
	/**
	 * Creating the Feedback tab
	 */
	private void createFeedbackTab() {
		// Create the FeedBack tab
		feedback = new FocusPanel();
		feedback.setStyleName("feedback");
		feedback.addStyleName("normal");
		// Create VerticalPanel that holds two labels "feed" and "back"
		VerticalPanel text = new VerticalPanel();
		text.add(new Label("Feed"));
		text.add(new Label("Back"));
		feedback.add(text);
	}
	
	/**
	 * Style the tab panel using methods in the UIObject class.
	 */
	private void styleTabPanelUsingUIObject() {
		// Set up the heights of the pages.
		homePanel.setHeight("400px");
		productsPanel.setHeight("400px");
		contactPanel.setHeight("400px");
		content.setHeight("440px");
	}

	/**
	 * Style the search button using DOM methods available through the
	 * Widget.getElement().getStyle() method.
	 */
	private void styleButtonUsingDOM() {
		// Set up some styling on the button
		search.getElement().getStyle()
				.setProperty("backgroundColor", "#ff0000");
		search.getElement().getStyle().setProperty("border", "2px solid");
		search.getElement().getStyle().setOpacity(0.7);
	}
	
	/**
	 * Returns the HTML content of an existing DOM element on the HTML page.
	 * 
	 * Should be careful with these type of methods if you are going to use the
	 * data later to ensure people are not injecting scripts into your code. In
	 * our example, we control the HTML that the data is retrieved from.
	 * 
	 * @param id
	 *            The id of the DOM element we wish to get the content for.
	 * @return The HTML content of the DOM element.
	 */
	private String getContent(String id) {
		// Initialise the return string.
		String toReturn = "";

		// Find the DOM element by the id passed in.
		Element element = DOM.getElementById(id);

		// Make sure we've found the DOM element and then manipulate it.
		if (element != null) {

			// Get the inner HTML content of the DOM element.
			toReturn = DOM.getInnerHTML(element);

			// Set the inner value of the DOM element to an empty string
			// if we don't do this, then it is still displayed on the screen.
			DOM.setInnerText(element, "");

			// Comment the following two lines of code out to not use SafeHTML
			// to create the response.
			// If we use it, then this makes sure the HTML we have from the HTML
			// page is sanitized against
			// any XSS attacks. In this example's case, the hyperlink in
			// contacts page is sanitized, i.e.
			// you cannot click on it.
			// This can be seen as overkill in this case, but security should
			// always be at the heart of
			// your development (it is too large a topic for us to cover within
			// the GWT in Action book).
			SafeHtml sfHtml = SimpleHtmlSanitizer.sanitizeHtml(toReturn);
			toReturn = sfHtml.asString();
		} else {
			// If we can't find the content then let's just put an error message
			// in the content
			// (You can test this by changing the id of the DOM elements in the
			// HTML page - you probably need
			// to clear your browser's cache to see the impact of any changes
			// you make).
			toReturn = "Unable to find " + id + " content in HTML page";
		}
		return toReturn;
	}

	public static UserInterface getUi() {
		return ui;
	}

	public static void setUi(UserInterface ui) {
		UserInterface.ui = ui;
	}

	public PopupPanel getSearchRequest() {
		return searchRequest;
	}
	
	public void setSearchRequest(PopupPanel panel)
	{
		this.searchRequest = panel;
	}

	public TabLayoutPanel getContent() {
		return content;
	}

	public Button getSearch() {
		return search;
	}

	public FocusPanel getFeedback() {
		return feedback;
	}

	public Image getLogo() {
		return logo;
	}

	public HTMLPanel getHomePanel() {
		return homePanel;
	}

	public HTMLPanel getProductsPanel() {
		return productsPanel;
	}

	public HTMLPanel getContactPanel() {
		return contactPanel;
	}
	
}

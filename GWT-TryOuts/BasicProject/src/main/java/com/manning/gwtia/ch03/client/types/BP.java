package com.manning.gwtia.ch03.client.types;

public interface BP {

	/** Numerical values to reference the tabs the content pages are held in. */
	int DECK_HOME = 0;
	int DECK_PRODUCTS = 1;
	int DECK_CONTACT = 2;
	/** Strings representing the history tokens we will use to indicate which tab content the user is viewing. */
	String TOKEN_HOME = "Home";
	String TOKEN_PRODUCTS = "Products";
	String TOKEN_CONTACT = "Contact";
	
	/** The filename of our logo image */
	String LOGO_IMAGE_NAME = "gwtia.png";
	
}

package com.manning.gwtia.ch03.client.types;

public enum Pages {

	HOME(BP.DECK_HOME, BP.TOKEN_HOME), PRODUCTS(BP.DECK_PRODUCTS,
			BP.TOKEN_PRODUCTS), CONTACT(BP.DECK_CONTACT, BP.TOKEN_CONTACT);

	// Holds the card number in the deck this enumeration relates to.
	private int val;
	// Holds the history token value this enumeration relates to.
	private String text;

	// Simple method to get the card number in the deck this enumeration
	// relates to.
	public int getVal() {
		return val;
	}

	// Simple method to get the history token this enumeration relates to.
	public String getText() {
		return text;
	}

	// Enumeration constructor that stores the card number and history token
	// for this enumeration.
	Pages(int val, String text) {
		this.val = val;
		this.text = text;
	};

}

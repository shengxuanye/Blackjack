package com.shengxuanye.blackjack.deck;

public class Card {
	
	/*
	 * The class implements a card (one of the 52 cards), with some useful methods for the blackjack game. 
	 * 
	 * @author Shengxuan Ye
	 */
	
	/*
	 * Define suit, rank, and value in a list. Note that A can be either 1 or 11. See useAceAs1() and 
	 * useAceAs11().
	 */
	
	private final String[] SUIT_NAME = {"Clubs_", "Diamonds", "Hearts", "Spades"};
	private final String[] RANK_NAME = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"}; 
	private int[] CARD_VALUE =         { 1,   2,   3,   4,   5,   6,   7,   8,   9,   10,   10,  10,  10}; 
	
	private int cardID;		// each card has an ID from 1-52. 
	
	public Card() {
		super();
		this.cardID = -1; 
	} 

	public Card(int cardID) {
		super();
		this.cardID = cardID;
	}

	/*
	 * Getters and setters. 
	 */
	
	public int getID() {
		return cardID;
	}

	public void setID(int cardID) {
		this.cardID = cardID;
	} 
	
	public String getName(){
		if (cardID < 0 || cardID > 51)
			return "Empty_card"; 
		else 
			return SUIT_NAME[cardID / 13] + "_" + RANK_NAME[cardID % 13];
	}
	
	public int getValue() {
		if (cardID < 0 || cardID > 51)
			return 0; 
		else 
			return CARD_VALUE[cardID % 13];
	}
	
	/*
	 * Some utility function dealing with Ace. useAceAs1() defines the card value to 1, and useAceAs11() defines
	 * the card value to 11. 
	 */
	
	public boolean isAce() {
		return cardID % 13 == 0; 
	}
	
	public void useAceAs1() {
		if (isAce()) CARD_VALUE[0] = 1; 
	}
	
	public void useAceAs11() {
		if (isAce()) CARD_VALUE[0] = 11; 
	}

}

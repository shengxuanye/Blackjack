package com.shengxuanye.blackjack.deck;

public class Card {
	
	private final String[] SUIT_NAME = {"Clubs_", "Diamonds", "Hearts", "Spades"};
	private final String[] RANK_NAME = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"}; 
	private int[] CARD_VALUE =         { 1,   2,   3,   4,   5,   6,   7,   8,   9,   10,   10,  10,  10}; 
	
	private int cardID;
	
	public Card() {
		super();
		this.cardID = -1; 
	} 

	public Card(int cardID) {
		super();
		this.cardID = cardID;
	}

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

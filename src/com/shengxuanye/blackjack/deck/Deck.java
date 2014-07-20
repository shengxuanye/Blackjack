package com.shengxuanye.blackjack.deck;

import java.util.Collections;
import java.util.Random;
import java.util.Stack;

public class Deck {
	
	/*
	 * This class implements a deck or decks of of 52 cards, with the ability to shuffle, pop, check size, 
	 * and reinitialize. The deck can be more than 1 deck, specified in numDecks in constructor. 
	 * 
	 * @author Shengxuan Ye
	 */
	
	private final int CARD_NUMBER = 52; 
	
	private Stack<Card> cards;
	private int numDecks; 

	public Deck() {
		super();
		numDecks = 1; 
		initialize(); 
	} 
	
	public Deck(int numDecks) {
		super();
		this.numDecks = numDecks; 
		initialize(); 
	}	
	
	/*
	 * Shuffle all cards. Please call this after each initialization. 
	 */
	
	public void shuffle() {
		long seed = System.nanoTime(); 
		Collections.shuffle(cards, new Random(seed));
	}
	
	/*
	 * Get top card from the deck
	 */
	
	public Card pop() {
		return cards.pop();  
	}
	
	/*
	 * Check size of the deck. It is dangerous to have a too small size in the game. 
	 */
	
	public int size() {
		return cards.size(); 
	}
	
	/*
	 * Reinitialize the deck with a print out. 
	 */
	
	public void reinitialize() {
		initialize(); 
		System.out.println("deck reinitialized. "); 
	}
	
	/*
	 * Initialize with a new deck of cards. The new deck is in sequence. 
	 */
	
	private void initialize() {
		cards = new Stack<Card>(); 
		for (int j = 0; j < numDecks; j++)
			for (int i = 0; i < CARD_NUMBER; i++) 
				cards.push(new Card(i)); 
	}

}

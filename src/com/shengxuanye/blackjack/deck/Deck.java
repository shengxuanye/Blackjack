package com.shengxuanye.blackjack.deck;

import java.util.Collections;
import java.util.Random;
import java.util.Stack;

public class Deck {
	
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
	
	public void shuffle() {
		long seed = System.nanoTime(); 
		Collections.shuffle(cards, new Random(seed));
	}
	
	public Card pop() {
		return cards.pop();  
	}
	
	public int size() {
		return cards.size(); 
	}
	
	public void reinitialize() {
		initialize(); 
		System.out.println("deck reinitialized. "); 
	}
	
	private void initialize() {
		cards = new Stack<Card>(); 
		for (int j = 0; j < numDecks; j++)
			for (int i = 0; i < CARD_NUMBER; i++) 
				cards.push(new Card(i)); 
	}

}

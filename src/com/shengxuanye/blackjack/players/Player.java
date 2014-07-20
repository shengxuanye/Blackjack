package com.shengxuanye.blackjack.players;

import com.shengxuanye.blackjack.deck.Deck;

public abstract class Player {
	
	protected int balance; 
	
	public int getBalance() {
		return balance;
	}
	
	public abstract void startRound(Deck d); 
	public abstract void executeRound(Deck d); 

}

package com.shengxuanye.blackjack.players;

import com.shengxuanye.blackjack.deck.Deck;

public abstract class Player {
	
	/*
	 * This class defines some basic structure of a general player (dealer or human player). 
	 * 
	 * @author Shengxuan Ye
	 */
	
	protected int balance; 	// the balance of the player 
	
	public int getBalance() {
		return balance;
	}
	
	/*
	 * This method implements what a player do to start a round (just obtain 2 cards from the deck)
	 */
	public abstract void startRound(Deck d); 
	
	/*
	 * This method implements the strategy of the player. For dealer, it obtains cards until hitting 17, 
	 * and for players, it asks for the user input. 
	 */
	public abstract void executeRound(Deck d); 
	
	/*
	 * Helper functions to calculate the balance when winning and losing
	 */
	public abstract void win(int money);
	public abstract void lose(int money);
	

}

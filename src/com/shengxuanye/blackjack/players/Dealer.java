package com.shengxuanye.blackjack.players;

import com.shengxuanye.blackjack.deck.*;

public class Dealer extends Player{
	
	/*
	 * This class implements a dealer. Note this implements "Dealer stands on all 17th" (from my own underanding). 
	 * 
	 * @author Shengxuan Ye
	 * 
	 */

	private final int MAX_NUMBER = 17; 	// the threshold for stand

	private Hand hand; 
	
	public Dealer () {
		balance = 0; 
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.shengxuanye.blackjack.players.Player#startRound(com.shengxuanye.blackjack.deck.Deck)
	 */
	
	public void startRound(Deck d) {
		
		hand = new Hand(0); 
		hand.addCard(d.pop());
		hand.addCard(d.pop());
		
		System.out.println(String.format(">> DEALER:\t first card = %s", hand.getFirstCardName()));	
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.shengxuanye.blackjack.players.Player#executeRound(com.shengxuanye.blackjack.deck.Deck)
	 */
	
	public void executeRound(Deck d) {
		printCards(); 
		while (hand.getLargestNumLessThan21() < MAX_NUMBER && !hand.isBusted()) {
			hand.addCard(d.pop());
			printCards(); 
		}
		if (hand.isBusted()) {
			System.out.println("DEALER:\t Busted :-)"); 
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.shengxuanye.blackjack.players.Player#win(int)
	 */
		
	public void win(int money) {
		balance += money; 
		System.out.println(String.format(">> DEALER:\t win = %d, total = %d", money, balance));	
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.shengxuanye.blackjack.players.Player#lose(int)
	 */
	
	public void lose(int money) {
		balance -= money; 
		System.out.println(String.format(">> DEALER:\t lose = %d, total = %d", money, balance));	
	}
	
	/*
	 * Helper function to print cards
	 */
	
	private void printCards() {
		System.out.print(">> DEALER:\t ");	
		hand.printCards();
	}
	
	/*
	 * Getters and setters 
	 */

	public Hand getHand() {
		return hand;
	}
	
}

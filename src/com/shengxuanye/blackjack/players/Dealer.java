package com.shengxuanye.blackjack.players;

import com.shengxuanye.blackjack.deck.*;

public class Dealer extends Player{

	private final int MAX_NUMBER = 17; 

	private Hand hand; 
	
	public Dealer () {
		balance = 0; 
	}
	
	
	public void startRound(Deck d) {
		
		hand = new Hand(0); 
		hand.addCard(d.pop());
		hand.addCard(d.pop());
		
		System.out.println(String.format(">> DEALER:\t first card = %s", hand.getFirstCardName()));	
	}
	
	
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
	
	
	public void win(int money) {
		balance += money; 
		System.out.println(String.format(">> DEALER:\t win = %d, total = %d", money, balance));	
	}
	
	
	public void lose(int money) {
		balance -= money; 
		System.out.println(String.format(">> DEALER:\t lose = %d, total = %d", money, balance));	
	}
	
	
	private void printCards() {
		System.out.print(">> DEALER:\t ");	
		hand.printCards();
	}
	

	public Hand getHand() {
		return hand;
	}
	
}

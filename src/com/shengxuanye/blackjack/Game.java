package com.shengxuanye.blackjack;

import com.shengxuanye.blackjack.deck.Deck;
import com.shengxuanye.blackjack.deck.Hand;
import com.shengxuanye.blackjack.players.*;

public class Game {
	
	int DECK_THOLD = 30; 
	
	Deck deck; 
	Dealer dealer; 
	Human human; 
	
	public Game() {
		deck = new Deck(); 
		deck.shuffle();
		
		dealer = new Dealer(); 
		human = new Human("PLAYER", 100); 
		
		System.out.println("Hello " + human.getPlayerName() + "!"); 
	}
	
	
	public void doRound() {
		
		System.out.println(); 
		System.out.println(); 
		
		if (deck.size() < DECK_THOLD) {
			deck.reinitialize();
			deck.shuffle(); 
		}
		
		human.startBet();
		dealer.startRound(deck);
		human.startRound(deck);
		human.executeRound(deck);
		dealer.executeRound(deck);
		
		evaluateWinner(dealer, human); 
		
	}
	
	
	private void evaluateWinner(Dealer dealer, Human player) {
		
		Hand dealerHand = dealer.getHand(); 

		System.out.println(">> FOR PRIMARY HAND"); 
		evlauateSingleHand(dealerHand, player.getPrimaryHand(), dealer, player); 
		
		if (player.isSplitted()) {
			System.out.println(">> FOR SECONDARY HAND"); 
			evlauateSingleHand(dealerHand, player.getSecondaryHand(), dealer, player); 
		}
		
	}
	
	private void evlauateSingleHand(Hand dealerHand, Hand humanHand, Dealer dealer, Human player) {

		int money = humanHand.getBet(); 

		if (humanHand.isSurrendered()) { // surrender case
			
			System.out.println("you lose 1/2 money becuase you surrender."); 
			player.lose(money / 2);
			dealer.win(money / 2); 
			
		} else {

			if (humanHand.compareTo(dealerHand) == 1) { // player winning case
				if (humanHand.is21()) {
					System.out.println("your money doubled because you hit 21."); 
					money *= 2; 
				}
				player.win(money);
				dealer.lose(money); 
			} else if (humanHand.compareTo(dealerHand) == -1) { // player losing case
				player.lose(money);
				dealer.win(money); 
			} else { // no winner case
				System.out.println("Dealer and your hand have same value. "); 
			}
			
		}
	}
	
	public Deck getDeck() {
		return deck; 
	}
	
}

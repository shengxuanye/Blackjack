package com.shengxuanye.blackjack;

import com.shengxuanye.blackjack.deck.*;
import com.shengxuanye.blackjack.players.*;

public class Game {
	
	/*
	 * This class implements a blackjack game, with one player and one dealer. There *might* be small 
	 * variations to specific rules, but I follows the page (http://www.blackjackinfo.com/blackjack-rules.php)
	 * 
	 * @ author Shengxuan Ye
	 */
	
	private final int DECK_THOLD = 30;  	// The deck decreases the size as the game goes on. This parameter
											// sets the minimum threshold for the deck to reinitialize. 
	
	private int initialChips = 100; 		// The initial chips. 
	private String playerName = "PLAYER"; 	// The name of the player. 
	
	private Deck deck; 	
	private Dealer dealer; 	
	private Human human; 
	
	
	public Game() {
		deck = new Deck(); 
		deck.shuffle();
		
		dealer = new Dealer(); 
		human = new Human(playerName, initialChips); 
		
		System.out.println("hello " + human.getPlayerName() + "!"); 
	}
	
	/*
	 * This method implements a "round" in the game. Basically, it checks whether the deck needs reinitialize, 
	 * then do the following: player bet, dealer get 2 cards and show one card, player get 2 cards, player
	 * decide the actions, and finally dealer get cards until hitting 17. Finally, the winners are revealed
	 * by calling evaluteWinner(). 
	 */
	
	public void doRound() {
		
		System.out.println("\n"); 
		
		if (deck.size() < DECK_THOLD) {
			deck.reinitialize();
			deck.shuffle(); 
		}
		
		human.startBet();
		dealer.startRound(deck);
		human.startRound(deck);
		human.executeRound(deck);
		
		System.out.println(); 
		dealer.executeRound(deck);
		
		System.out.println(); 
		evaluateWinner(dealer, human); 
		
	}
	
	/*
	 * The following two methods evaluates the winner. Specifically, evaluateWinner checks all hands, and
	 * evaluateSinleHand checks for one hand (so call 2 times when splitted). 
	 */
	
	private void evaluateWinner(Dealer dealer, Human player) {
		
		Hand dealerHand = dealer.getHand(); 

		System.out.println(">> RESULTS:\t FOR PRIMARY HAND: player: " + player.getPrimaryHand().getTotalVal() 
				+ " vs. dealer: " + dealerHand.getTotalVal()); 
		evaluateSingleHand(dealerHand, player.getPrimaryHand(), dealer, player); 
		
		if (player.isSplitted()) {
			System.out.println(">> RESULTS:\t FOR SECONDARY HAND: player: " + player.getSecondaryHand().getTotalVal() 
					+ " vs. dealer: " + dealerHand.getTotalVal()); 
			evaluateSingleHand(dealerHand, player.getSecondaryHand(), dealer, player); 
		}
		
	}
	
	
	private void evaluateSingleHand(Hand dealerHand, Hand humanHand, Dealer dealer, Human player) {

		int money = humanHand.getBet(); 

		if (humanHand.isSurrendered()) { // surrender case
			
			System.out.println("you lose 1/2 money becuase you surrender."); 
			player.lose(Math.round((float)money / 2));
			dealer.win(Math.round((float)money / 2)); 
			
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
	
}

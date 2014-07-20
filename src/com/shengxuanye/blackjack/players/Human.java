package com.shengxuanye.blackjack.players;

import com.shengxuanye.blackjack.deck.*;
import com.shengxuanye.blackjack.utility.InputUtil;

public class Human extends Player{
	
	/*
	 * This class implements a human player. It extends the Player class. The following actions are 
	 * implemented: hit, stand, double down, and surrender. The "insurance" is not implemented since it 
	 * is not commonly understood. 
	 * 
	 * @author Shengxuan Ye
	 */
	
	private final String OPTIONS_STR = "1 = hit, 2 = stand, 3 = double down, 4 = surrender"; 
	
	private String playerName; 		// name of the player
	private Hand[] hands; 			// two hands hands[0] is the primary hand, hands[1] is the splitted hand
	
	private boolean isSplitted; 	// set true if a hand is splitted in the round.
	private boolean isHandEnded; 	// set true if a hand is ended. 
	
	private InputUtil iu; 
	
	public Human(String playerName, int balance) {
		super();
		this.playerName = playerName;
		this.balance = balance;
		
		isSplitted = false; 
		isHandEnded = false; 
		
		iu = new InputUtil(); 
	}
	
	/*
	 * This method implements the betting procedures. Basically, it checks for the amount of money and obtain
	 * how much the player want to bet on the first hand. 
	 */
	
	public void startBet() {
		if (balance <= 0) {
			int blood = iu.getIntInputs("you don't have any more money. do you want to sell some blood to vampire the shadowy? (1 = yes, other # = no)");
			if (blood == 1) {
				System.out.println("you sold 1 liter of blood for 100 chips."); 
				balance = 100; 
			} else {
				System.out.println("good bye."); 
				System.exit(0);
			}
		}
		
		int bet;
		do {
			bet = iu.getIntInputs("how much do you want to bet on (you have total " + balance + ")? enter 0 to exit");
			if (bet == 0) {
				System.out.println("your " + balance + " chips are all eaten by a cute giraffe when you leave the table. Shengxuan will not pay you anything. "); 
				System.exit(0); 
			}
			if (bet > balance) {
				System.out.println("you have " + balance + " chips but you bet on " + bet); 
				bet = -1; 
			}
		} while (bet == -1); 
		
		hands = new Hand[2]; 
		hands[0] = new Hand(bet);
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.shengxuanye.blackjack.players.Player#startRound(com.shengxuanye.blackjack.deck.Deck)
	 */
	
	public void startRound(Deck d) {
		
		hands[0].addCard(d.pop());
		hands[0].addCard(d.pop());
		
		printCards(hands[0]); 
		
		isSplitted = false; 
		isHandEnded = false; 
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.shengxuanye.blackjack.players.Player#executeRound(com.shengxuanye.blackjack.deck.Deck)
	 */
	
	/*
	 * The method implements how a round is executed. First it see if the hand can split (for 2 cards have
	 * same value). It asks the user whether to split the card. After that, it asks users to take actions
	 * individually. 
	 */
	
	public void executeRound(Deck d) {
		
		/* split the card, if possible */
		if (hands[0].canSplit()) {
			
			int wantSplit = iu.getIntInputs("do you want to split? 1 = yes, other # = no");
			
			if (wantSplit == 1) {
				
				if (hands[0].getBet() > balance) {
					System.out.println("meee... you can't do it because you don't have enough chips."); 
				} else {
					hands[1] = new Hand(hands[0].getBet());
					
					hands[1].addCard(hands[0].getAllCards().get(1));
					hands[0].removeCard(1);
					
					isSplitted = true; 
				}
			}
			
		}
		
		
		boolean succ = false; 
		isHandEnded = false; 
		
		/* action on the primary hand. See next section for details. */
		if (isSplitted)	 // to show the user again the current cards in hand after splitting. 
			printCards(hands[0]); 
		
		do {
			int option = iu.getIntInputs("what is your action for your primary hand? "  + OPTIONS_STR);
			if (option != -1)
				succ = doOption(option, d, 0); 
		} while (!succ && !isHandEnded); 
		
		
		/* action on the secondary hand. See next section for details. */
		if (isSplitted) {
			System.out.println(); 
			printCards(hands[1]); 
			isHandEnded = false; 
			do {
				int option = iu.getIntInputs("what is your action for your secondary hand? " + OPTIONS_STR);
				if (option != -1)
					succ = doOption(option, d, 1); 
			} while (!succ && !isHandEnded); 
		}
		
	}
	
	/*
	 * The following section deals with different actions. doOption is a switcher for different options. 
	 * Then hit(), stand(), doubleDown() and surrender() implements different actions. 
	 */
	
	private boolean doOption(int option, Deck d, int handID) {
		switch (option) {
			case 1:
				hit(d, handID); 
				break; 
			case 2: 
				stand(handID); 
				break; 
			case 3:
				doubleDown(d, handID); 
				break; 
			case 4: 
				surrender(handID);
				break; 
			default: 
				System.out.println("incorrect option. try again."); 
				return false; 
		}
		return false; 
	}
	

	public void hit(Deck d, int handID) {
		System.out.println(String.format(">> %s:\t HIT", playerName));
		
		hands[handID].addCard(d.pop());
		printCards(hands[handID]); 
		
		if (hands[handID].isBusted()) {
			System.out.println(String.format(">> %s:\t BUSTED :-(", playerName));
			isHandEnded = true; 
		}
	}
	
	
	public void stand(int handID) {
		isHandEnded = true; 
		System.out.println(String.format(">> %s:\t STAND", playerName));
	}

	
	public void doubleDown(Deck d, int handID) {
		
		if (hands[handID].getBet() * 2 <= balance) {
			hands[handID].setBet(hands[handID].getBet() * 2);
			System.out.println(String.format(">> %s:\t DOUBLE DOWN, now bet at %d (hit then stand)", playerName, 
					hands[handID].getBet()));
			hit(d, handID);
			stand(handID); 
		} else  {
			System.out.println("you don't have enough money to bet for double down.");
		}
		
	}
	
	
	public void surrender(int handID) {
		hands[handID].setSurrendered(true); 
		isHandEnded = true; 
		System.out.println(String.format(">> %s:\t SURRENDER", playerName));
	}
	
	
	/*
	 * Getters and setters
	 */
	
	public boolean isSplitted() {
		return isSplitted; 
	}
	
	public Hand getPrimaryHand() {
		return hands[0]; 
	}
	
	public Hand getSecondaryHand() {
		return hands[1]; 
	}
	
	public String getPlayerName() {
		return playerName; 
	}
	
	/*
	 * Functions to set balance for winning and losing. 
	 */
	
	public void win(int money) {
		balance += money; 
		System.out.println(String.format(">> %s:\t **WIN** = %d, total = %d", playerName, money, balance));	
	}
	
	
	public void lose(int money) {
		balance -= money; 
		System.out.println(String.format(">> %s:\t **LOSE** = %d, total = %d", playerName, money, balance));
		
	}
	
	/*
	 * A help function to print cards
	 */

	private void printCards(Hand h) {
		System.out.print(">> " + playerName + ":\t ");	
		h.printCards();
	}

}

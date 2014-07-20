
package com.shengxuanye.blackjack;

public class Blackjack {

	/*
	 * Blackjack main function. This function create a blackjack game and run the rounds forever, unless 
	 * an exit() called internally. 
	 * 
	 *  @author Shengxuan Ye
	 */
	
	public static void main(String[] args) {
		
		Game g = new Game(); 
		
		while (true)
			g.doRound();
		
	}
	
}

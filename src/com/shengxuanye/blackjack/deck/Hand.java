package com.shengxuanye.blackjack.deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Stack;

public class Hand implements Comparable<Hand> {
	
	private ArrayList<Card> hand;
	private int bet; 	// each hand has a bet
	private boolean isEnded; 	// mark end of the hand
	private boolean isSurrendered; 		// mark if this hand is surrendered or not

	public Hand(int bet) {
		super();
		hand = new ArrayList<Card>(); 
		this.bet = bet; 
	}
	
	public void addCard(Card c) {
		hand.add(c); 
	}
	
	public void removeCard(int i) {
		hand.remove(i); 
	}
	
	public void printCards() {
		for (Card c : hand)
			System.out.print(c.getName() + "\t");
		System.out.print(getTotalVal());
		System.out.println(); 
	}
	
	public ArrayList<Card> getAllCards() {
		return hand; 
	}
	
	public String getFirstCardName() {
		if (hand.size() > 0)
			return hand.get(0).getName();
		else
			return "no_card"; 
	}
	
	public ArrayList<Integer> getTotalVal() { // this will return a SORTED array of all possible unique values, given ace on hand
		
		Stack<Integer> s = new Stack<Integer>(); 
		s.push(0); 
		
		for (int i = 0; i < hand.size(); i++) {
			
			Stack<Integer> tempStack = new Stack<Integer>(); 
			
			while (!s.isEmpty()) {
				
				Card thisCard = hand.get(i); 
				
				if (!thisCard.isAce())
					tempStack.push(s.pop() + thisCard.getValue()); 
				else {
					int curVal = s.pop(); 
					thisCard.useAceAs1();
					tempStack.push(curVal + thisCard.getValue()); 
					thisCard.useAceAs11(); 
					tempStack.push(curVal + thisCard.getValue()); 
				}
			}
			
			s = tempStack; 
			
		}
		
		ArrayList<Integer> al = new ArrayList<Integer>(new HashSet<Integer>(s)); 
		Collections.sort(al);
		
		return al; 
		
	}
	
	public boolean isBusted() {
		return getTotalVal().get(0) > 21; 
	}
	
	public boolean is21() {
		boolean has21 = false; 
		ArrayList<Integer> al = getTotalVal(); 
		
		for (int i = 0; i < al.size(); i++) {
			has21 = has21 || al.get(i) == 21; 
		}
		
		return has21; 
	}
	
	public int getLargestNumLessThan21() {
		if (!isBusted()) {
			
			ArrayList<Integer> al = getTotalVal(); 
			int maxNum = 0; 
			
			for (int i = 0; i < al.size(); i++) {
				if (al.get(i) <= 21)
				maxNum = Math.max(maxNum, al.get(i)); 
			}
			
			return maxNum; 
		}
		return -1; 
	}
	
	public boolean canSplit() {
		if (hand.get(0).getValue() == hand.get(1).getValue()) {
			return true;
		}
		return false; 
	}
	
	public int getBet() {
		return bet;
	}
	
	public void setBet(int b) {
		bet = b; 
	}

	public boolean isEnded() {
		return isEnded;
	}

	public void setEnded(boolean isEnded) {
		this.isEnded = isEnded;
	}

	public boolean isSurrendered() {
		return isSurrendered;
	}

	public void setSurrendered(boolean isSurrendered) {
		this.isSurrendered = isSurrendered;
	}


	@Override
	public int compareTo(Hand dealer) {
		if (this.isBusted())
			return -1; 
		if (dealer.isBusted())
			return 1; 
		if (dealer.getLargestNumLessThan21() < this.getLargestNumLessThan21())
			return 1;
		else if (dealer.getLargestNumLessThan21() > this.getLargestNumLessThan21())
			return -1; 
		else
			return 0; 
	}
	

}

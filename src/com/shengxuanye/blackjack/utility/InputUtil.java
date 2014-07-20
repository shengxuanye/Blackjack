package com.shengxuanye.blackjack.utility;

import java.util.Scanner;

public class InputUtil {
	
	/*
	 * The class implements the functions to get inputs from users (both int and string). 
	 * 
	 * @author Shengxuan Ye
	 */
	
	Scanner in; 
	
	public InputUtil() {
		in = new Scanner(System.in);
	}
	
	/*
	 * Return non-negative integer from user. If incorrect input, return -1. 
	 */
	
	public int getIntInputs(String prompt) {
		
		System.out.println(prompt + ":"); 
		
		try {
			int option = Integer.parseInt(in.nextLine());
			if (option >= 0) 
				return option;
			else
				System.out.println("number must >= 0"); 
		} catch (Exception e) {
			System.out.println("incorrect input"); 
		}
		
		return -1; 
	}
	
	/*
	 * Return string from user. If incorrect, return empty string. 
	 */
	
	public String getStringInputs(String prompt) {
		
		System.out.println(prompt + ":");
		
		try {
			String option = in.nextLine();
			return option; 
		} catch (Exception e) {
			System.out.println("incorrect input"); 
		}
		
		return ""; 
	}
	
	protected void finalize() {
		in.close(); 
	}
	
}

package com.shengxuanye.blackjack.utility;

import java.util.Scanner;

public class InputUtil {
	
	Scanner in; 
	
	public InputUtil() {
		in = new Scanner(System.in);
	}
	
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

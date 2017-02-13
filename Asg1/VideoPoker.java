/* 
Chelsea Coullette and Siliang Luo
CPSC 215 Software Development Foundations
Instructor: Sami
Program 1: Video Poker

This file is the main function
*/
package cu.cs.cpsc215.project1;

import java.util.Scanner;
//import java.util.ArrayList;
//import java.io.InputStream;

public class VideoPoker {

	public static void main(String[] args) {
		//create deck of cards
		Deck cardDeck = new Deck();

		//create players
		Card[] playerOne = new Card[5];
		Card[] playerTwo = new Card[5];
		Card[] playerThree = new Card[5];
		Card[] playerMe = new Card[5];

		//index for where the top of the deck is
		int top = 0;
		System.out.println("Hello, Please input your name:");
		Scanner in = new Scanner(System.in);
		String name = in.next();
		System.out.println("-------------------------");
		System.out.println(" Welcome to Video Poker! ");
		System.out.println("-------------------------");	

		//arrays for Magic Dollars earned each round
		int[] magicOne = new int[10];
		int[] magicTwo = new int[10];
		int[] magicThree = new int[10];
		int[] magicMe = new int[10];

		for (int i=0; i<10; i++) {
			magicOne[i] = 0;
			magicTwo[i] = 0;
			magicThree[i] = 0;
			magicMe[i] = 0;	
		}

		//initialize all totals to 0
		int totalOne = 0;
		int totalTwo = 0;
		int totalThree = 0;
		int totalMe = 0;

		//Round start
		for (int round=0; round<10; round++) {
			top = 0;
			System.out.println("------------------------------");
			System.out.println("          Round "+ (round+1) + ":");
			System.out.println("------------------------------");
			
			//shuffle the deck and distribute 5 cards to each player
			cardDeck.Shuffle();

			for (int i=0; i<5; i++) {
				playerOne[i] = cardDeck.distribute(top);
				top++;
				playerTwo[i] = cardDeck.distribute(top);
				top++;
				playerThree[i] = cardDeck.distribute(top);
				top++;
				playerMe[i] = cardDeck.distribute(top);
				top++;
			}

			//Get user input for discarding cards
			System.out.println(name + "'s" + " hand: "+playerMe[0]+", "
					+playerMe[1]+", "+playerMe[2]+", "+playerMe[3]+", and "+playerMe[4]+ "." + "\n");
			System.out.println("Please enter a string of 0's &/or 1's to represent which \n" +
					"cards you would like to reject (0 - discard the card or 1 - keep the card).\n" +
					"For ex. A string of '00011' means discard the first 3 cards and keep the last two");

			
			//get user input and call Discard when char = 0
			//check for errors
			//if there are errors, ask for user input again		
			Scanner input = new Scanner(System.in);
			String i = input.next();
			
			int j=0;
			while (j<i.length()) {
				//check for if they didn't enter 0 or 1
				if (i.length() != 5) {
					System.out.println("Error: Please enter a 5 digits total");
					input = new Scanner(System.in);
					i = input.next();
					j = -1;
				}
				else if (i.charAt(j) != '0' && i.charAt(j) != '1') {
					System.out.println("Error: Please enter a 0s and 1s only");
					input = new Scanner(System.in);
					i = input.next();
					j = -1;
				}
				//no error detected, so replace a card where user entered 0
				else if (i.charAt(j) == '0') {
					playerMe[j] = cardDeck.distribute(top);
					top++;		
				}
				j++;
			}

			//finalize all the players' poker hands
			Hand aiOne = new Hand(playerOne[0], playerOne[1], playerOne[2], 
					playerOne[3], playerOne[4]);			
			Hand aiTwo = new Hand(playerTwo[0], playerTwo[1], playerTwo[2], 
					playerTwo[3], playerTwo[4]);
			Hand aiThree = new Hand(playerThree[0], playerThree[1], 
					playerThree[2], playerThree[3], playerThree[4]);
			Hand me = new Hand(playerMe[0], playerMe[1], playerMe[2], 
					playerMe[3], playerMe[4]);

			//put in Magic Dollars
			magicOne[round] = aiOne.setMagicDollars(round);
			magicTwo[round] = aiTwo.setMagicDollars(round);
			magicThree[round] = aiThree.setMagicDollars(round);
			magicMe[round] = me.setMagicDollars(round);

			//print out results of the round
			System.out.print("\nAI Player 1 has ");
			aiOne.printHand();
			System.out.print("This is a "+aiOne.payToString()+", so player 1 receives "
					+magicOne[round]+ " MagicDollars. \n");

			System.out.print("\nAI Player 2 has ");
			aiTwo.printHand();
			System.out.print("This is a "+aiTwo.payToString()+", so player 2 receives "
					+magicTwo[round]+ " MagicDollars. \n");

			System.out.print("\nAI Player 3 has ");
			aiThree.printHand();
			System.out.print("This is a "+aiThree.payToString()+", so player 3 receives "
					+magicThree[round]+ " MagicDollars. \n");

			System.out.print("\n"+ name +" has ");
			me.printHand();
			System.out.print("This is a "+me.payToString()+", so "+ name +" receives "
					+magicMe[round]+ " MagicDollars. \n");

		} //end of round loops

		//Calculate and print out total MagicDollars for each player			
		for (int x=0; x<10; x++) {
			totalOne += magicOne[x];
			totalTwo += magicTwo[x];
			totalThree += magicThree[x];
			totalMe += magicMe[x];
		}
		System.out.println("\nAI Player 1 earned "+ totalOne +" MagicDollars.");
		System.out.println("AI Player 2 earned "+ totalTwo +" MagicDollars.");
		System.out.println("AI Player 3 earned "+ totalThree +" MagicDollars.");
		System.out.println(name +" earned "+ totalMe +" MagicDollars.");

		//Calculate winner and print out winner
		int[] total = new int[4];
		total[0] = totalOne;
		total[1] = totalTwo;
		total[2] = totalThree;
		total[3] = totalMe;

		String[] player = new String[4];
		player[0] = "AI Player 1";
		player[1] = "AI Player 2";
		player[2] = "AI Player 3";
		player[3] = name;

		int min;
		for (int x=0; x<4; x++) {
			min = x;
			for (int y=x+1; y<4; y++) {
				if (total[y] < total[min])
					min = y;
			}

			int temp = total[x];
			String temp2 = player[x];
			total[x] = total[min];
			player[x] = player[min];
			total[min] = temp;	
			player[min] = temp2;
		}
		System.out.println("\n"+ player[3] + " wins the game!\n");

	}//end of main
}
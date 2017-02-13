/* 
Chelsea Coullette and Siliang Luo
CPSC 215 Software Development Foundations
Instructor: Sami
Program 1: Video Poker

This file is the Deck class
*/
package cu.cs.cpsc215.project1;

import cu.cs.cpsc215.project1.Card.Suit;
import java.util.Random;

public class Deck {

	// Member fields
	private Card[] myCards;
	private boolean[] myDrawnCards;

	// Constructor
	public Deck() {
		// Card array
		myCards = new Card[52];

		// Boolean to indicate if I have drawn cards
		myDrawnCards = new boolean[52];

		// Initially all cards are not drawn
		for (int i = 0; i < 52; i++) {
			myDrawnCards[i] = false;
		}

		// Make all 52 cards
		int count = 0;		
		for (int j = 2; j < 15; j++) {
			myCards[count] = new Card(j, Suit.Spades);
			count++;
			myCards[count] = new Card(j, Suit.Hearts);
			count++;
			myCards[count] = new Card(j, Suit.Diamonds);
			count++;
			myCards[count] = new Card(j, Suit.Clubs);
			count++;
		}
	}

	
	//shuffle method
	public void Shuffle() {
		for (int i=0; i<52; i++) {
			Random rnd = new Random();
			int next = rnd.nextInt(52);
			int next2 = rnd.nextInt(52);
			//System.out.println("next: "+ next);
			//System.out.println("next2: "+ next2);

			Card temp = myCards[next];
			myCards[next] = myCards[next2];
			myCards[next2] = temp;

		}
	/*CODE TO TEST DECK SHUFFLE:
	 for (int x=0; x<52; x++)
		System.out.println("x: " + x + " card: " + myCards[x]); */
	}

	// draw method
	public Card distribute(int top) {
		return myCards[top];		
	}

}
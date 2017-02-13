/* 
Chelsea Coullette and Siliang Luo
CPSC 215 Software Development Foundations
Instructor: Sami
Program 1: Video Poker

This file is the Card class
*/
package cu.cs.cpsc215.project1;

public class Card {
	
	// Suit Enum
	enum Suit {
		Spades, Hearts, Diamonds, Clubs
	};

	// Member fields
	private final int myValue;
	private final Suit mySuit;
	
	// Constructor
	public Card(int value, Suit suit) {
		this.myValue = value;
		this.mySuit = suit;
	}
	
	public int getMyValue() {
		return myValue;
	}
	
	public Suit getMySuit() {
		return mySuit;
	}
	
	// toString method
	public String toString() {
		String retStr = "";
		
		// Append the value to the string
		switch (myValue) {
		case 11:
			retStr += "Jack";
			break;
		case 12:
			retStr += "Queen";
			break;
		case 13:
			retStr += "King";
			break;
		case 14:
			retStr += "Ace";
			break;
		default:
			retStr += myValue;
			break;
		}
		
		retStr += " of ";
		
		// Append the suit to string
		if (mySuit == Suit.Spades) {
			retStr += "Spades";
		}
		else if (mySuit == Suit.Hearts) {
			retStr += "Hearts";
		}
		else if (mySuit == Suit.Diamonds) {
			retStr += "Diamonds";
		}
		else {
			retStr += "Clubs";
		}
		
		return retStr;
	}

}
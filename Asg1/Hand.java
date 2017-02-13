/* 
Chelsea Coullette and Siliang Luo
CPSC 215 Software Development Foundations
Instructor: Sami
Program 1: Video Poker

This file is the Hand class
*/
package cu.cs.cpsc215.project1;

public class Hand {
	
	// Member fields
	private int payValue;
	private Card[] myHand;
	
	
	// Constructor
	public Hand(Card one, Card two, Card three, Card four, Card five) {
		//initialize the payValue index to 0 (No pair)
		payValue = 0;
		
		// Card array
		myHand = new Card[5];
		myHand[0] = one;
		myHand[1] = two;
		myHand[2] = three;
		myHand[3] = four;
		myHand[4] = five;
		sort();
		setStraightFlushType();
		if (payValue == 0){
			//System.out.println("looking for straight");
			sameValues();
		}
	}


	//method to sort Hand in ascending order
	public void sort() {
		int minCard;

		for (int i=0; i<5; i++) {
			minCard = i;

			for (int j=i+1; j<5; j++) {
				if (myHand[j].getMyValue() < myHand[minCard].getMyValue())
					minCard = j;
			}

			Card temp = myHand[i];
			myHand[i] = myHand[minCard];
			myHand[minCard] = temp;	
		}	
	}


	//method to see if there is a flush
	//to be called by setStraightFlushType method
	public boolean flush() {
		//look for flushes
		for (int i=1; i<5; i++) {
			if (myHand[0].getMySuit() != myHand[i].getMySuit())
				return false;
		}	
		return true;
	}


	//method to see if there is a straight
	//type = -1 => not straight; 0 => reg straight; 1 => royal straight
	//to be called by setStraightFlushType method
	public int straight() {
		//Royal
		//case for if there is an Ace in the hand
		if (myHand[4].getMyValue() == 14) {
			if ((myHand[0].getMyValue() == 2 && myHand[1].getMyValue() == 3 &&
					myHand[2].getMyValue() == 4 && myHand[3].getMyValue() == 5) ||
					(myHand[0].getMyValue() == 10 && myHand[1].getMyValue() == 11
					&& myHand[2].getMyValue() == 12 && myHand[3].getMyValue() == 13))
				return 1;
			payValue = 0;
			return -1;
		}
		//when there isn't an Ace
		else {
			int next = 1;
			next += myHand[0].getMyValue();

			for (int i=1; i<5; i++){
				//not straight
				if (myHand[i].getMyValue() != next) {
					payValue=0;
					//System.out.println("I'm not a straight");
					return -1;
				}
				next++;
			}
		}
		return 0;
	}


	//method to determine what type of straight and/or flush type it is
	//sets the final payValue accordingly
	public void setStraightFlushType() {
		//if it's Straight
		if (flush() != true && (straight() == 0 || straight() == 1))
			payValue = 4;
		//if it's Flush
		else if (flush() == true && straight() == -1)
			payValue = 5;
		//if it's Straight flush
		else if (flush() == true && straight() == 0)
			payValue = 8;
		//if it's Royal flush
		else if (flush() == true && straight() == 1)
			payValue = 9;
		else
			payValue = 0;
	}


	//method to find if there are pairs
	//to be called by sameValues method
	//returns index of Hand where the pair begins, or -1 if there is no pair
	public int findPair() {
		for(int i=0; i<5; i++) {
			for(int j=i+1; j<5; j++) {
				//One pair
				if(myHand[i].getMyValue() == myHand[j].getMyValue()) {
					return j;
				}
			}
		}
		return -1;
	}


	//method to determine number of same values
	public void sameValues() {
		int i = findPair();
		
		if (i == -1)
			return;
		//three of a kind
		else if (i < 4 && myHand[i].getMyValue() == myHand[i+1].getMyValue()) {
			payValue = 3;
			//four of a kind
			if (i < 3 && (myHand[i].getMyValue() == myHand[i+2].getMyValue()))
			payValue = 7;
		}
		else if (i==1 && (myHand[i+1].getMyValue()== myHand[i+2].getMyValue()
				|| myHand[i+2].getMyValue() == myHand[i+3].getMyValue())){
			//two pairs
			if (myHand[i+1].getMyValue() != myHand[i+3].getMyValue())
				payValue = 2;
			//full house
			else
				payValue = 6;
		}
		//two pairs
		else if (i==2 && myHand[i+1].getMyValue() == myHand[i+2].getMyValue())
			payValue = 2;
		//One pair
		else
			payValue = 1;	
	}


	//method to set the payValue to its Magic Dollars amount
	public int setMagicDollars(int round) {
		int retInt;
		
		switch(payValue) {
		case 1:
			retInt = 1;
			break;
		case 2:
			retInt = 2;
			break;
		case 3:
			retInt = 3;
			break;
		case 4:
			retInt = 4;
			break;
		case 5:
			retInt = 5;
			break;
		case 6:
			retInt = 6;
			break;
		case 7:
			retInt = 25;
			break;
		case 8:
			retInt = 50;
			break;
		case 9:
			retInt = 250;
			break;
		default:
			retInt = 0;
			break;
		}
		return retInt;
	}
	
	
	//method to set the category of the payOut
	public String payToString() {
		String result = "";
		switch(payValue) {
		case 1:
			result = "One pair";
			break;
		case 2:
			result = "Two pairs";
			break;
		case 3:
			result = "Three of a kind";
			break;
		case 4:
			result = "Straight";
			break;
		case 5:
			result = "Flush";
			break;
		case 6:
			result = "Full house";
			break;
		case 7:
			result = "Four of a kind";
			break;
		case 8:
			result = "Straight flush";
			break;
		case 9:
			result = "Royal flush";
			break;
		default:
			result = "No pair";
			break;
		}
		return result;
	}


	//method to print the hand out
	public void printHand() {
		System.out.println(myHand[0]+", "+myHand[1]+", "+myHand[2]+", "
							+myHand[3]+" and "+myHand[4]+ ".");
	}
}
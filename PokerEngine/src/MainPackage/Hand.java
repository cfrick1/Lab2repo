package MainPackage;

import java.util.*;

public class Hand {
	
	private ArrayList<Card> cards = new ArrayList<Card>();
	//setting 5 as default hand size just as an example
	private int handSize = 5;
	
	public Hand(Deck currentDeck, int handSize){
		this.handSize = handSize;
		for (int i=0; i<this.handSize; i++){
			this.cards.add(currentDeck.draw());
		}
	}
	
	public ArrayList<Card> getCards(){
		return this.cards;
	}
	
	public static String display(Hand thisHand){
		ArrayList<Card> theseCards = thisHand.getCards();
		String hand_display= "";
		for (int i=0; i<thisHand.handSize; i++) {
			if (theseCards.get(i).getValue() ==1) {
				hand_display += "A";
			}
			else if (theseCards.get(i).getValue() == 11) {
				hand_display += "J";
			}
			else if (theseCards.get(i).getValue() == 12) {
				hand_display += "Q";
			}
			else if (theseCards.get(i).getValue() == 13) {
				hand_display += "K";
			}
			else {
				hand_display += Integer.toString(theseCards.get(i).getValue());
			}
			hand_display += theseCards.get(i).getSuit() + " ";
		}
		return hand_display;
	}
	
	public void setCards(ArrayList<Card> cards){
		this.cards = cards;
	}
	public int getHandSize(){
		return this.handSize;
	}
	public void setHandSize(int handSize){
		this.handSize = handSize;
	}
	
	
	public static Hand compare(ArrayList<Hand> theseHands){
		long highVal = Hand.judge(theseHands.get(0));
		Hand highHand = theseHands.get(0);
		
		for (int i = 1; i < theseHands.size(); i++){
			long tempVal = Hand.judge(theseHands.get(i));
			if (tempVal > highVal){
				highVal = tempVal;
				highHand = theseHands.get(i);
			}
		}
		
		return highHand;
	}

	
	public static long judge(Hand thisHand){
		/* instead of doing booleans for all of these, I'm storing the "high card value" of these combos.
		 * 
		 * This will allow relative scoring (like, if both players have a pair...) while still allowing
		 * for the "yes vs no" functionality of booleans
		 * 
		 */
		
		ArrayList<Card> theseCards = thisHand.getCards();
		int handSize = thisHand.getHandSize();
		
		int royalFlushValue = 0;
		int straightFlushValue = 0;
		int fourOfAKindValue = 0;
		int fullHouseValue = 0;
		int flushValue = 0;
		int straightValue = 0;
		int threeOfAKindValue = 0;
		int twoPairHighValue = 0;
		int twoPairLowValue = 0;
		int pairValue = 0;
		int highCard = 0;
		
		//stores suits and values, and gets highValue of Hand
		String[] suits = new String[handSize];
		int[] values = new int[handSize];
		for (int i = 0; i<handSize; i++){
			Card temp = theseCards.get(i);
			suits[i] = temp.getSuit();
			values[i] = temp.getValue();
			//this isn't pretty, but I'm giving aces a value of 14 
			if (values[i] == 1){
				highCard = 14;
				values[i] = 14;
			}
			else if (values[i] > highCard){
				highCard = values[i];
			}
		}
		
		//sorted value set; comes in handy later
		int[] sortedValues = values;
		Arrays.sort(sortedValues);
		
		//pair check. i goes to handSize - 1 because otherwise would get reference error
		for (int i = 0; i < (handSize - 1); i++){
			if (sortedValues[i] == sortedValues[i+1]){
				pairValue = sortedValues[i];
			}
		}
		
		//two pair check.
		if (pairValue > 0){
			int possibleTwoPairHighValue = pairValue;
			for (int i = 0; i < (handSize - 1); i++){
				if (sortedValues[i] == sortedValues[i+1]){
					if (sortedValues[i] != possibleTwoPairHighValue) {
						twoPairHighValue = possibleTwoPairHighValue;
						twoPairLowValue = sortedValues[i];
					}
				}
			}
		}
		
		//three of a kind check
		if (pairValue > 0){
			for (int i = 0; i < (handSize - 2); i++){
				if (sortedValues[i] == sortedValues[i + 2]){
					threeOfAKindValue = sortedValues[i];
				}
			}
		}
		
		//full house check
		if (threeOfAKindValue > 0){
			if (twoPairHighValue > 0){
				fullHouseValue = twoPairHighValue;
			}
		}
		
		//straight check. this is ugly. I am sorry.
		int[] cheatValues;
		if (sortedValues[handSize - 1] == 14){
			cheatValues = new int[handSize + 1];
			cheatValues[0] = 1;
			for (int i = 1; i < handSize + 1; i++){
				cheatValues[i] = sortedValues[i-1];
			}
		}
		else {
			cheatValues = sortedValues;
		}
		int streakLength = 0;
		for (int i = 0; i < cheatValues.length - 1; i++){
			if (cheatValues[i] + 1 == cheatValues[i+1]){
				streakLength += 1;
				if (streakLength > 4){
					straightValue = cheatValues[i+1];
				}
			}
			else {
				streakLength = 0;
			}
		}
		
		//flush check
		boolean flushHasNotBeenDisproved = true;
		for (int i = 0; i < handSize - 1; i++){
			if (suits[i] != suits[i+1]){
				flushHasNotBeenDisproved = false;
			}
		}
		if (flushHasNotBeenDisproved){
			flushValue = highCard;
		}
		
		//four of a kind check
		if (threeOfAKindValue > 0){
			for (int i = 0; i < (handSize - 3); i++){
				if (sortedValues[i] == sortedValues[i + 3]){
					fourOfAKindValue = sortedValues[i];
				}
			}
		}
		
		//straight flush check
		if (straightValue > 0){
			if (flushValue > 0) {
				straightFlushValue = flushValue;
			}
		}
		
		//royal flush check
		if (straightFlushValue > 0){
			boolean hasAce = false;
			boolean hasKing = false;
			boolean hasQueen = false;
			boolean hasJack = false;
			boolean hasTen = false;
			for (int i = 0; i<5; i++){
				if (values[i] == 14) {
					hasAce = true;
				}
				else if (values[i]==13){
					hasKing = true;
				}
				else if (values[i]==12){
					hasQueen = true;
				}
				else if (values[i]==11){
					hasJack = true;
				}
				else if (values[i]==10){
					hasTen = true;
				}
			}
			if (hasAce && hasKing && hasQueen && hasJack && hasTen){
				royalFlushValue = 14;
			}
		}
		
		
		//final score tabulation
		long final_score = 
				(long)(royalFlushValue) * (long)Math.pow(10, 30) +
				(long)(straightFlushValue) * (long)Math.pow(10, 27) +
				(long)(fourOfAKindValue) * (long)Math.pow(10, 24) +
				(long)(fullHouseValue) * (long)Math.pow(10, 21) +
				(long)(flushValue) * (long)Math.pow(10, 18) +
				(long)(straightValue) * (long)Math.pow(10, 15) +
				(long)(threeOfAKindValue) * (long)Math.pow(10, 12) +
				(long)(twoPairHighValue) * (long)Math.pow(10, 9) +
				(long)(twoPairLowValue) * (long)Math.pow(10, 6) +
				(long)(pairValue) * (long)Math.pow(10, 3) +
				(long)(highCard);
		
		return final_score;
	}
}

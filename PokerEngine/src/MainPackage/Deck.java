package MainPackage;

import java.util.*;

public class Deck {
	
	private ArrayList<Card> cards = new ArrayList<Card>();
	
	private String[] suits = new String[4];
	
	public Deck(){
		this.suits[0]="H";
		this.suits[1]="D";
		this.suits[2]="C";
		this.suits[3]="S";
		
		for (int i = 0; i < this.suits.length; i++){
			//j will be the value. goes from 1 to 13
			for (int j = 1; j < 14; j++){
				Card newCard = new Card( j , this.suits[i]);
				this.cards.add(newCard);
			}
		}
		
		Collections.shuffle(this.cards);
	}
	
	
	
	public Card draw() {
		Card temp = this.cards.get(0);
		this.cards.remove(0);
		return temp;
	}
	
	public int cardsRemaining() {
		return this.cards.size();
	}
}
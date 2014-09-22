package MainPackage;

import java.util.*;

public class Deck {
	
	private ArrayList<Card> cards = new ArrayList<Card>();
	
	private String[] suits = new String[4];
	
	public Deck(){
		this.suits[0]="heart";
		this.suits[1]="diamond";
		this.suits[2]="club";
		this.suits[3]="spade";
		
		for (int i = 0; i < this.suits.length; i++){
			//j will be the value. goes from 1 to 13
			for (int j = 1; j < 14; j++){
				Card newCard = new Card( j , this.suits[i]);
				this.cards.add(newCard);
			}
		}
		
		this.shuffle();
	}
	
	private void shuffle(){
		for (int i = 0; i < 52; i++){
			int rando = (int)Math.floor((Math.random()*52));
			
			//temporarily stores card at index i
			Card holder = this.cards.get(i);
			
			//puts the random card in slot i
			this.cards.add(i, this.cards.get(rando));
			
			//puts the card from slot i in the random position
			this.cards.add(rando, holder);
		}
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
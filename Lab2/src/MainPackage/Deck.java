package MainPackage;

import java.util.*;

public class Deck {
	
	private ArrayList<Card> cards = new ArrayList<Card>();{
	
	for (Value x: Value.values()){
		Card thiscard = new Card();
		thiscard.setValue(x);
		for (Suit y: Suit.values()){
			thiscard.setSuit(y);
		}

	}

}
}
package MainPackage;

import java.util.ArrayList;

public class Play {
	public static void main(String[] args){
		
		Deck thisDeck = new Deck();
		
		Hand Hand1 = new Hand(thisDeck, 5);
		Hand Hand2 = new Hand(thisDeck, 5);
		Hand Hand3 = new Hand(thisDeck, 5);
		ArrayList<Hand> theseHands = new ArrayList<Hand>();
		theseHands.add(Hand1);
		theseHands.add(Hand2);
		theseHands.add(Hand3);
		System.out.println("These are the three Hands: ");
		System.out.println("Hand 1: " + Hand.display(Hand1));
		System.out.println("Hand 2: " + Hand.display(Hand2));
		System.out.println("Hand 3: " + Hand.display(Hand3));
		Hand winner = Hand.compare(theseHands);
		System.out.println("The winner is " + Hand.display(winner));
	}
}

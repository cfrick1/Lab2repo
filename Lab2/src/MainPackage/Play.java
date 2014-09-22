package MainPackage;

public class Play {
	public static void main(String[] args){
		Deck thisDeck = new Deck();
		
		Hand thisHand = new Hand(thisDeck, 5);
		
		System.out.println("The score for this hand is " + Hand.judge(thisHand) + ".");
	}
}

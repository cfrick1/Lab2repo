package MainPackage;

public class Card {
	
	private Value value;
	private Suit suit;
	
	public Card(){
		
	}
	
	public Card(Value value, Suit suit){
		this.value = value;
		this.suit = suit;
	}

	public void setValue(Value value){
		this.value = value;
	}

	public void setSuit(Suit suit){
		this.suit = suit;
	}
}


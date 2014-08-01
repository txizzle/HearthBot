package player;

public class Hand 
{
	protected Card[] currentCards;
	protected Player owner;

	private Hand(Player handOwner) {
		owner = handOwner;
	}

	protected void AddCard(Card toAdd) {
		//if 10 cards, discard card
		//if less than 10 cards, add to hand
	}
}
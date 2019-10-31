
public class Card {

	protected int rank;
	protected String suit;
	protected static String[] suits = { "hearts", "spades", "diamonds", "clubs" };
	protected static int[] ranks  = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

	Card(int rank, String suit) {
		this.rank = rank;
		this.suit = suit;
	}
	
	public String toString(int rank, int suit) {
		return ranks[rank] + " of " + suits[suit];
	}

	public int getRank() {
		return rank;
	}

	public String getSuit() {
		return suit;
	}
}


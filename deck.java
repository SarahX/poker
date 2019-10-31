import java.util.Random;

public class Deck {
	public Card[] cardDeck = new Card[52];
	public Random rand = new Random();
	Card temp;
	
	public Deck() {
		int x = 0;
		for (int i = 0; i < 13 ; i++) {
			for (int j = 0; j < 4; j++) {
				
				Card card = new Card(i,j);
				cardDeck[x] = card;
				x++;
			}
		}
	}
	public Card[] Shuffle(Card[] cardDeck) {
		for (int i=0; i<100; i++) {
			int a = rand.nextInt(cardDeck.length - 1);
			int b = rand.nextInt(cardDeck.length - 1);

			temp = cardDeck[a];
			
			cardDeck[a] = cardDeck[b];
			cardDeck[b] = temp;
		}
		return cardDeck;
	}
	
	public Card drawFromDeck(int i)
	{	   
		return cardDeck[i];
	}

}




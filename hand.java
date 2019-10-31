
public class Hand {
	protected Card[] hand1 = new Card[5];
	protected Card[] hand2 = new Card[5];
	protected int[] val1 = new int[6];
	protected int[] val2 = new int[6];

	public Hand(Deck d) {
		for (int i = 0; i < 5; i++) {
			hand1[i] = d.drawFromDeck(i); //fill up 1st hand array.
		}
		for (int i = 5; i < 10; i++) {
			hand2[i - 5] = d.drawFromDeck(i); //fill up 2nd hand array.
		}
		analyze(hand1, val1);
		display(val1);
		analyze(hand2, val2);
		display(val2);

	}

	public void analyze(Card[] hand, int[] val) {	
		int[] ranks = new int[14];
		int[] orderedRanks = new int[5];	 //miscellaneous cards that are not otherwise significant
		boolean flush = true;
		boolean straight = false;
		int sameCards = 1;
		int sameCards2 = 1;
		int largeGroupRank = 0;
		int smallGroupRank = 0;
		int index = 0;
		int topStraightValue = 0;

		for (int i = 0; i <= 13; i++) {
			ranks[i]=0;
		}
		for (int i = 0; i <= 4; i++) {
			ranks[hand[i].getRank()]++;
		}
		for (int i = 0; i < 4; i++) {
			if (hand[i].getSuit() != hand[i + 1].getSuit()) {
				flush = false;
			}
		}

		for (int i = 13; i >= 1; i--) {
			if (ranks[i] > sameCards) {
				if (sameCards != 1) { //if sameCards was not the default value
					sameCards2 = sameCards;
					smallGroupRank = largeGroupRank;
				}

				sameCards = ranks[i];
				largeGroupRank = i;

			} else if (ranks[i] > sameCards2) {
				sameCards2 = ranks[i];
				smallGroupRank = i;
			}
		}

		if (ranks[1] == 1) { //if ace, run this before because ace is highest card
			orderedRanks[index] = 14;
			index++;
		}

		for (int i = 13; i >= 2; i--)
		{
			if (ranks[i] == 1)
			{
				orderedRanks[index] = i; //if ace
				index++;
			}
		}


		for (int i = 1; i <= 9; i++) { //can't have straight with lowest value of more than 10
			if ((ranks[i] == 1) && (ranks[i + 1] == 1) && (ranks[i + 2] == 1) && (ranks[i + 3] == 1) && (ranks[i + 4] == 1)) {
				straight = true;
				topStraightValue = i + 4; //4 above bottom value
				break;
			}
		}

		if ((ranks[10] == 1) && (ranks[11] == 1) && (ranks[12] == 1) && (ranks[13] == 1) && (ranks[1] == 1)) { //ace high
			straight = true;
			topStraightValue = 14; //higher than king
		}

		for (int i = 0; i <= 5; i++){	//clearing val after using it to get info
			val[i] = 0;
		}


		//start hand evaluation
		if (sameCards == 1) {
			val[0] = 1;
			val[1] = orderedRanks[0];
			val[2] = orderedRanks[1];
			val[3] = orderedRanks[2];
			val[4] = orderedRanks[3];
			val[5] = orderedRanks[4];
		}

		if (sameCards == 2 && sameCards2 == 1) {
			val[0] = 2;
			val[1] = largeGroupRank; //rank of pair
			val[2] = orderedRanks[0];
			val[3] = orderedRanks[1];
			val[4] = orderedRanks[2];
		}

		if (sameCards == 2 && sameCards2 == 2) { //two pair
			val[0] = 3;
			val[1] = largeGroupRank > smallGroupRank ? largeGroupRank : smallGroupRank; //rank of greater pair
			val[2] = largeGroupRank < smallGroupRank ? largeGroupRank : smallGroupRank;
			val[3] = orderedRanks[0];  //extra card
		}

		if (sameCards == 3 && sameCards2 != 2) {
			val[0] = 4;
			val[1] = largeGroupRank;
			val[2] = orderedRanks[0];
			val[3] = orderedRanks[1];
		}

		if (straight && !flush) {
			val[0] = 5;
			val[1] = topStraightValue;
		}

		if (flush && !straight) {
			val[0] = 6;
			val[1] = orderedRanks[0]; //tie determined by ranks of cards
			val[2] = orderedRanks[1];
			val[3] = orderedRanks[2];
			val[4] = orderedRanks[3];
			val[5] = orderedRanks[4];
		}

		if (sameCards == 3 && sameCards2 == 2) {
			val[0] = 7;
			val[1] = largeGroupRank;
			val[2] = smallGroupRank;
		}

		if (sameCards == 4) {
			val[0] = 8;
			val[1] = largeGroupRank;
			val[2] = orderedRanks[0];
		}

		if (straight && flush){
			val[0] = 9;
			val[1] = topStraightValue;
		}
	}


	public void display(int[] val) {
		String str;
		switch(val[0]) {

		case 1:
			str = "high card";
			break;
		case 2:
			str = "pair of " + Card.rankAsString(val[1]) + "\'s";
			break;
		case 3:
			str = "two pair " + Card.rankAsString(val[1]) + " " + Card.rankAsString(val[2]);
			break;
		case 4:
			str = "three of a kind " + Card.rankAsString(val[1]) + "\'s";
			break;
		case 5:
			str = Card.rankAsString(val[1]) + " high straight";
			break;
		case 6:
			str = "flush";
			break;
		case 7:
			str = "full house " + Card.rankAsString(val[1]) + " over " + Card.rankAsString(val[2]);
			break;
		case 8:
			str = "four of a kind " + Card.rankAsString(val[1]);
			break;
		case 9:
			str = "straight flush " + Card.rankAsString(val[1]) + " high";
			break;
		default:
			str = "error in Hand.display: value[0] contains invalid value";
		}
		//s = "				" + str;
		System.out.println(str);
	}


	public void displayAll(Card[] hand) {
		for (int i = 0; i < 5; i++) {
			System.out.println(hand[i]);
		}
	}
	public int compareTo(Card[] hand1, Card[] hand2) {
		for (int i = 0; i < 6; i++) { //loops through values to compare
			if (val1[i] > val2[i]) {
				return 1;
			} else if (val1[i] < val2[i]) {
				return -1;
			}
		}
		return 0; //if hands are equal
	}
}

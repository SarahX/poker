
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
			ranks[cards[i].getRank()]++;
		}
		for (int i = 0; i < 4; i++) {
			if (cards[i].getSuit() != cards[i + 1].getSuit()) {
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

		for (int i = 0; i <= 5; i++){
			value[i]=0;
		}


		//start hand evaluation
		if ( sameCards==1 ) {
			value[0]=1;
			value[1]=orderedRanks[0];
			value[2]=orderedRanks[1];
			value[3]=orderedRanks[2];
			value[4]=orderedRanks[3];
			value[5]=orderedRanks[4];
		}

		if (sameCards==2 && sameCards2==1)
		{
			value[0]=2;
			value[1]=largeGroupRank; //rank of pair
			value[2]=orderedRanks[0];
			value[3]=orderedRanks[1];
			value[4]=orderedRanks[2];
		}

		if (sameCards==2 && sameCards2==2) //two pair
		{
			value[0]=3;
			value[1]= largeGroupRank>smallGroupRank ? largeGroupRank : smallGroupRank; //rank of greater pair
			value[2]= largeGroupRank<smallGroupRank ? largeGroupRank : smallGroupRank;
			value[3]=orderedRanks[0];  //extra card
		}

		if (sameCards==3 && sameCards2!=2)
		{
			value[0]=4;
			value[1]= largeGroupRank;
			value[2]=orderedRanks[0];
			value[3]=orderedRanks[1];
		}

		if (straight && !flush)
		{
			value[0]=5;
			value[1]=topStraightValue;
		}

		if (flush && !straight)
		{
			value[0]=6;
			value[1]=orderedRanks[0]; //tie determined by ranks of cards
			value[2]=orderedRanks[1];
			value[3]=orderedRanks[2];
			value[4]=orderedRanks[3];
			value[5]=orderedRanks[4];
		}

		if (sameCards==3 && sameCards2==2)
		{
			value[0]=7;
			value[1]=largeGroupRank;
			value[2]=smallGroupRank;
		}

		if (sameCards==4)
		{
			value[0]=8;
			value[1]=largeGroupRank;
			value[2]=orderedRanks[0];
		}

		if (straight && flush)
		{
			value[0]=9;
			value[1]=topStraightValue;
		}


	}


	void display()
	{
		String s;
		switch( value[0] )
		{

		case 1:
			s="high card";
			break;
		case 2:
			s="pair of " + Card.rankAsString(value[1]) + "\'s";
			break;
		case 3:
			s="two pair " + Card.rankAsString(value[1]) + " " + Card.rankAsString(value[2]);
			break;
		case 4:
			s="three of a kind " + Card.rankAsString(value[1]) + "\'s";
			break;
		case 5:
			s=Card.rankAsString(value[1]) + " high straight";
			break;
		case 6:
			s="flush";
			break;
		case 7:
			s="full house " + Card.rankAsString(value[1]) + " over " + Card.rankAsString(value[2]);
			break;
		case 8:
			s="four of a kind " + Card.rankAsString(value[1]);
			break;
		case 9:
			s="straight flush " + Card.rankAsString(value[1]) + " high";
			break;
		default:
			s="error in Hand.display: value[0] contains invalid value";
		}
		s = "				" + s;
		System.out.println(s);
	}


	public void displayAll(Card[] hand) {
		for (int i = 0; i < 5; i++) {
			System.out.println(hand[i]);
		}
	}
	public int compareTo(Card[] hand1, Card[] hand2) {
		for (int i = 0; i < 6; i++) { //loops through values to compare
			if (this.val[i] > that.val[i]) {
				return 1;
			} else if (this.val[i] < that.val[i]) {
				return -1;
			}
		}
		return 0; //if hands are equal
	}
}

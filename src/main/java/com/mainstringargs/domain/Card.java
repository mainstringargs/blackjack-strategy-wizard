package com.mainstringargs.domain;

public class Card implements Comparable<Card> {

	private Rank rank;
	private Suit suit;

	public Card(Rank rank, Suit suit) {
		super();
		this.rank = rank;
		this.suit = suit;
	}

	public Rank getRank() {
		return rank;
	}

	public Suit getSuit() {
		return suit;
	}

	public static Card fromString(String cardString) {

		if (cardString.length() == 2) {
			char rankChar = cardString.charAt(0);
			char suitChar = cardString.charAt(1);
			Rank rank = Rank.fromShortName(rankChar);
			Suit suit = Suit.fromShortName(suitChar);

			return new Card(rank, suit);
		}

		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rank == null) ? 0 : rank.hashCode());
		result = prime * result + ((suit == null) ? 0 : suit.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (rank != other.rank)
			return false;
		if (suit != other.suit)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return rank + " of " + suit;
	}

	@Override
	public int compareTo(Card o) {
		int rankCompared = rank.compareTo(o.rank);

		if (rankCompared == 0) {
			return suit.compareTo(o.suit);
		}

		return rankCompared;
	}

}

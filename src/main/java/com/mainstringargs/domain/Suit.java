package com.mainstringargs.domain;

public enum Suit {
	HEARTS('H'), DIAMONDS('D'), CLUBS('C'), SPADES('S');

	private char shortName;

	Suit(char shortName) {
		this.shortName = shortName;
	}

	public char getShortName() {
		return shortName;
	}

	public static Suit fromShortName(char shortName) {
		for(Suit suit: Suit.values()) {
			if(suit.getShortName() == Character.toUpperCase(shortName)) {
				return suit;
			}
		}
		return Suit.HEARTS;
	}
}

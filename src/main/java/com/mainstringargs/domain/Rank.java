package com.mainstringargs.domain;

public enum Rank {
	ACE(1, 'A'), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10, 'T'), JACK(10,
			'J'), QUEEN(10, 'Q'), KING(10, 'K');

	private int defaultValue;
	private char shortName;

	Rank(int defaultValue) {
		this.defaultValue = defaultValue;
		this.shortName = Character.forDigit(defaultValue, 10);
	}

	Rank(int defaultValue, char shortName) {
		this.defaultValue = defaultValue;
		this.shortName = shortName;
	}

	public int getDefaultValue() {
		return defaultValue;
	}

	public char getShortName() {
		return shortName;
	}

	public static Rank fromShortName(char shortName) {
		for(Rank rank: Rank.values()) {
			if(rank.getShortName() == Character.toUpperCase(shortName)) {
				return rank;
			}
		}
		return Rank.ACE;
	}
}

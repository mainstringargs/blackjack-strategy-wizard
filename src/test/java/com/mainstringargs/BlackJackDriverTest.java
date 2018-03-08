package com.mainstringargs;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

import org.junit.BeforeClass;
import org.junit.Test;

import com.mainstringargs.BlackJackDriver;
import com.mainstringargs.domain.Card;
import com.mainstringargs.domain.Rank;
import com.mainstringargs.domain.Suit;

public class BlackJackDriverTest {

	private static TreeSet<Card> allCards = new TreeSet<Card>();

	@BeforeClass
	public static void beforeTest() {
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				allCards.add(new Card(rank, suit));
			}
		}
	}

	@Test
	public void testGetHandValuesNoAce() {
		for (Card card : allCards) {
			if (card.getRank() != Rank.ACE) {
				assertEquals(card.getRank().getDefaultValue(), BlackJackDriver.getHandValues(card).get(0).intValue());
			}
		}
	}

	@Test
	public void testGetHandValuesAce() {

		Card ace = new Card(Rank.ACE, Suit.CLUBS);
		assertEquals(Arrays.asList(1, 11), BlackJackDriver.getHandValues(ace));

	}

	@Test
	public void testGetHandValuesTwoCardsNoAce() {
		for (Card cardA : allCards) {
			for (Card cardB : allCards) {

				if (cardA.getRank() != Rank.ACE && cardB.getRank() != Rank.ACE) {

					int calculatedTotal = cardA.getRank().getDefaultValue() + cardB.getRank().getDefaultValue();

					assertEquals(calculatedTotal, (int) BlackJackDriver.getHandValues(cardA, cardB).get(0));
				}

			}
		}
	}

	@Test
	public void testGetHandValuesThreeCardsNoAce() {
		for (Card cardA : allCards) {
			for (Card cardB : allCards) {
				for (Card cardC : allCards) {
					if (cardA.getRank() != Rank.ACE && cardB.getRank() != Rank.ACE && cardC.getRank() != Rank.ACE) {

						int calculatedTotal = cardA.getRank().getDefaultValue() + cardB.getRank().getDefaultValue()
								+ cardC.getRank().getDefaultValue();

						assertEquals(calculatedTotal, (int) BlackJackDriver.getHandValues(cardA, cardB, cardC).get(0));
					}
				}
			}
		}
	}

	@Test
	public void testGetHandValuesTwoCardsTwoAces() {
		Card cardA = new Card(Rank.ACE, Suit.CLUBS);
		Card cardB = new Card(Rank.ACE, Suit.HEARTS);
		assertEquals(Arrays.asList(2, 12, 12, 22), BlackJackDriver.getHandValues(cardA, cardB));

	}

	@Test
	public void testGetHandValuesTwoCardsThreeAces() {
		Card cardA = new Card(Rank.ACE, Suit.CLUBS);
		Card cardB = new Card(Rank.ACE, Suit.HEARTS);
		Card cardC = new Card(Rank.ACE, Suit.SPADES);
		List<Integer> expected = Arrays.asList(3, 13, 13, 13, 23, 23, 23, 33);
		List<Integer> actual = BlackJackDriver.getHandValues(cardA, cardB, cardC);
		assertTrue(expected.size() == actual.size() && expected.containsAll(actual) && actual.containsAll(expected));
	}

}

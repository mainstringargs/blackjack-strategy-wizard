package com.mainstringargs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

import com.mainstringargs.domain.Card;
import com.mainstringargs.domain.Rank;
import com.mainstringargs.domain.Suit;

public class BlackJackDriver {

	private static TreeMap<Card, Integer> dealtCards = new TreeMap<Card, Integer>();
	private static TreeMap<Card, Integer> undealtCards = new TreeMap<Card, Integer>();

	public static void main(String[] args) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Number of Hands: ");
		String line = null;
		try {
			line = reader.readLine();
		} catch (IOException e) {
			line = "1";
		}

		int numHands = Integer.parseInt(line);

		System.out.print("Number of Decks: ");
		try {
			line = reader.readLine();
		} catch (IOException e) {
			line = "1";
		}

		int numDecks = Integer.parseInt(line);

		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				undealtCards.put(new Card(rank, suit), numDecks);
			}
		}

		System.out.println("Initializing with " + numHands + " hand(s) & " + numDecks + " deck(s)");

		while (true) {
			System.out.print("Enter Dealer Card: ");

			try {
				line = reader.readLine();
			} catch (IOException e) {
				line = "AH";
			}

			Card dealerCard = Card.fromString(line);
			System.out.println("Dealer Card: " + dealerCard);
			dealtCards.put(dealerCard, null);
			System.out.print("Enter First Card in Hand: ");

			try {
				line = reader.readLine();
			} catch (IOException e) {
				line = "AH";
			}

			Card firstCardHand = Card.fromString(line);
			System.out.println("First Card in Hand: " + firstCardHand);
			dealtCards.put(firstCardHand, null);
			System.out.print("Enter Second Card in Hand: ");

			try {
				line = reader.readLine();
			} catch (IOException e) {
				line = "AH";
			}

			Card secondCardHand = Card.fromString(line);
			dealtCards.put(secondCardHand, null);
			System.out.println("Second Card in Hand: " + secondCardHand);

			findMove(dealerCard, firstCardHand, secondCardHand);
		}

	}

	private static void findMove(Card dealerCard, Card... handCards) {

		int numCardInstances = undealtCards.get(dealerCard);
		undealtCards.put(dealerCard, --numCardInstances);

		for (Card handCard : handCards) {
			numCardInstances = undealtCards.get(handCard);
			undealtCards.put(handCard, --numCardInstances);
		}

		List<Integer> handValues = getHandValues(handCards);

	}

	public static List<Integer> getHandValues(Card... handCards) {

		// int defaultValue = Arrays.stream(handCards).map(x ->
		// x.getRank().getDefaultValue())
		// .reduce((x, y) -> x+y).get();

		List<Integer> runningTotals = new ArrayList<Integer>();

		for (Card card : handCards) {

			if (runningTotals.size() == 0) {
				runningTotals.add(card.getRank().getDefaultValue());

				if (card.getRank() == Rank.ACE) {
					runningTotals.add(11);
				}
			} else {
				// snapshot for ACE
				List<Integer> runningTotalsSnapShot = new ArrayList<Integer>(runningTotals);

				for (int i = 0; i < runningTotals.size(); i++) {
					int val = runningTotals.get(i);
					val += card.getRank().getDefaultValue();
					runningTotals.set(i, val);
				}

				if (card.getRank() == Rank.ACE) {
					for (int i = 0; i < runningTotalsSnapShot.size(); i++) {
						int val = runningTotalsSnapShot.get(i);
						val += 11;
						runningTotals.add(val);
					}
				}
			}

		}
		Collections.sort(runningTotals);

		return runningTotals;
	}
}

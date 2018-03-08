package com.mainstringargs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.TreeMap;

import com.mainstringargs.domain.Card;

public class CardTracker {

	public static void main(String[] args) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String line = null;

		TreeMap<Card, Integer> dealtCards = new TreeMap<Card, Integer>();
		LinkedList<Card> orderedCards = new LinkedList<>();
		int totalCardsDealt = 0;

		while (true) {
			System.out.println(totalCardsDealt+" Dealt Cards: " + dealtCards);
			System.out.print("Enter Card (u to undo):");

			try {
				line = reader.readLine();
			} catch (IOException e) {
				line = "";
			}

			if (line.trim().equalsIgnoreCase("u")) {
				if (dealtCards.size() > 0) {
					Card lastCard = orderedCards.removeLast();

					System.out.println("Removing Last Dealt Card: " + lastCard);

					if (lastCard != null) {
						Integer currentNumForCard = dealtCards.get(lastCard);

						if (currentNumForCard > 1) {
							dealtCards.put(lastCard, --currentNumForCard);
						} else if (currentNumForCard == 1) {
							dealtCards.remove(lastCard);
						}
					}

					totalCardsDealt--;
				}
			} else {
				Card card = Card.fromString(line);

				if (card != null) {
					Integer currentNumForCard = dealtCards.get(card);

					if (currentNumForCard == null) {
						currentNumForCard = 0;
					}

					dealtCards.put(card, ++currentNumForCard);
					orderedCards.addLast(card);

					System.out.println(card + " has appeared " + currentNumForCard + " times.");
					totalCardsDealt++;
				} else {
					System.out.println("Invalid Entry.  Try Again.");
				}
			}

		}

	}
}

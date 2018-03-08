package com.mainstringargs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.mainstringargs.domain.Card;

public class BlackJackDriver {

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

			System.out.print("Enter First Card in Hand: ");

			try {
				line = reader.readLine();
			} catch (IOException e) {
				line = "AH";
			}

			Card firstCardHand = Card.fromString(line);
			System.out.println("First Card in Hand: " + firstCardHand);

			System.out.print("Enter Second Card in Hand: ");

			try {
				line = reader.readLine();
			} catch (IOException e) {
				line = "AH";
			}

			Card secondCardHand = Card.fromString(line);
			System.out.println("Second Card in Hand: " + secondCardHand);
		}

	}
}

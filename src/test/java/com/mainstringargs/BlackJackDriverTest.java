import static org.junit.Assert.*;

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
	public void testGetHandValuesNoneAce() {
		for (Card card : allCards) {
			if (card.getRank() != Rank.ACE) {
				assertEquals(card.getRank().getDefaultValue(), BlackJackDriver.getHandValues(card));
			}
		}
	}

}

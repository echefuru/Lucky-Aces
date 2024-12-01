package use_case;

import java.util.List;

import entity.Card;
import entity.Deck;

/**
 * An interface that defines the methods that the ApiDataAccess class must implement.
 */
public interface ApiDataAccessInterface {

    /**
     * A method that returns a new Deck.
     * @return a Deck composed of the standard 52 cards.
     */
    Deck createDeck();

    /**
     * A method that returns a new Deck.
     * @param numDecks is the number of decks that the new Deck is composed of.
     * @return a Deck composed of the specified number of decks.
     */
    Deck createDeck(int numDecks);

    /**
     * A method that returns several Cards from the Deck.
     * @param deck is the Deck from where the Cards will be drawn.
     * @param numCards is the number of Cards to be drawn.
     * @return a List of Cards from deck.
     */
    List<Card> draw(Deck deck, int numCards);

    /**
     * A method that adds back all discarded cards to the given Deck and shuffles it.
     * @param deck is the Deck to be reset and shuffled.
     */
    void shuffle(Deck deck);
}

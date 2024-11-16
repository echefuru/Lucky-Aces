package use_case;

import entity.Card;
import entity.Deck;

import java.io.IOException;
import java.util.List;

/**
 * An interface that defines the methods that the ApiDataAccess class must implement.
 */
public interface ApiDataAccessInterface {

    /**
     * A method that returns a new Deck.
     * @return a Deck composed of the standard 52 cards.
     * @throws IOException if there is an error in attempting to call API.
     */
    Deck createDeck() throws IOException;

    /**
     * A method that returns a new Deck.
     * @param numDecks is the number of decks that the new Deck is composed of.
     * @return a Deck composed of the specified number of decks.
     * @throws IOException if there is an error in attempting to call API.
     */
    Deck createDeck(int numDecks) throws IOException;

    /**
     * A method that returns a Card from the Deck.
     * @param deck is the Deck from where the Card will be drawn.
     * @return a Card from deck.
     * @throws IOException if there is an error in attempting to call API.
     */
    Card draw(Deck deck) throws IOException;

    /**
     * A method that returns several Cards from the Deck.
     * @param deck is the Deck from where the Cards will be drawn.
     * @param numCards is the number of Cards to be drawn.
     * @return a List of Cards from deck.
     * @throws IOException if there is an error in attempting to call API.
     */
    List<Card> draw(Deck deck, int numCards) throws IOException;

    /**
     * A method that adds back all discarded cards to the given Deck, shuffles, and returns the full Deck.
     * @param deck is the Deck to be reset, shuffled and returned.
     * @return the Deck newly shuffled.
     * @throws IOException if there is an error in attempting to call API.
     */
    Deck shuffle(Deck deck) throws IOException;
}

package use_case;

import entity.Card;
import entity.Deck;
import org.json.JSONException;

import java.util.List;

/**
 * An interface that defines the methods that the ApiDataAccess class must implement.
 */
public interface ApiDataAccessInterface {

    /**
     * A method that returns a new Deck.
     * @param numDecks is the number of decks that the new Deck is composed of.
     * @return a Deck composed of the specified number of decks.
     * @throws JSONException if there is an error attempting to parse response payload.
     */
    Deck createDeck(int numDecks) throws JSONException;

    /**
     * A method that returns a Card from the Deck.
     * @param deck is the Deck from where the Card will be drawn.
     * @return a Card from deck.
     * @throws JSONException if there is an error attempting to parse response payload.
     */
    Card draw(Deck deck) throws JSONException;

    /**
     * A method that returns several Cards from the Deck.
     * @param deck is the Deck from where the Cards will be drawn.
     * @param numCards is the number of Cards to be drawn.
     * @return a List of Cards from deck.
     * @throws JSONException if there is an error attempting to parse response payload.
     */
    List<Card> draw(Deck deck, int numCards) throws JSONException;
}

package entity.player;

import java.util.ArrayList;
import java.util.List;

import entity.Card;

/**
 * The abstract representation of a Player in the game.
 */
abstract class AbstractPlayer {
    private List<Card> cards;

    AbstractPlayer() {
        cards = new ArrayList<Card>();
    }

    public void setCards(List<Card> newCards) {
        this.cards = newCards;
    }

    public List<Card> getCards() {
        return cards;
    }

    /**
     * Return the string representation of all cards the player holds.
     * @return A list of strings representing all the cards the player holds
     */
    public List<String> getCardStrings() {
        final List<String> cardStrings = new ArrayList<String>(cards.size());
        for (Card card : cards) {
            cardStrings.add(card.toString());
        }
        return cardStrings;
    }

    /**
     * Clears the cards held by the player.
     */
    public void clearCards() {
        cards.clear();
    }
}

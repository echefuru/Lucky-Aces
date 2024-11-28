package entity.room;

import entity.Card;
import entity.Deck;

import java.util.ArrayList;
import java.util.List;

/**
 * The abstract representation of a game room in our program.
 */
abstract class AbstractRoom {

    private Deck deck;
    private List<Card> playerCards;
    private List<Card> dealerCards;

    public void playerDraw(List<Card> cards) {
        playerCards.addAll(cards);
    }

    public void dealerDraw(List<Card> cards) {
        dealerCards.addAll(cards);
    }

    public List<String> playerStrings() {
        final List<String> playerStrings = new ArrayList<String>(playerCards.size());
        for (Card card : playerCards) {
            playerStrings.add(card.toString());
        }
        return playerStrings;
    }

    public List<String> dealerStrings() {
        final List<String> dealerStrings = new ArrayList<String>(dealerCards.size());
        for (Card card : dealerCards) {
            dealerStrings.add(card.toString());
        }
        return dealerStrings;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public List<Card> getPlayerCards() {
        return playerCards;
    }

    public void setPlayerCards(List<Card> playerCards) {
        this.playerCards = playerCards;
    }

    public List<Card> getDealerCards() {
        return dealerCards;
    }

    public void setDealerCards(List<Card> dealerCards) {
        this.dealerCards = dealerCards;
    }
}

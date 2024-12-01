package entity.room;

import java.util.ArrayList;
import java.util.List;

import entity.Card;
import entity.Deck;

/**
 * The abstract representation of a game room in our program.
 */
abstract class AbstractRoom {

    private Deck deck;
    private List<Card> playerCards = new ArrayList<Card>();
    private List<Card> dealerCards = new ArrayList<Card>();

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

    /**
     * The total value of the player's cards.
     * @return total value of player's cards.
     */
    abstract int getPlayerTotal();

    /**
     * The total value of the dealer's cards.
     * @return total value of dealer's cards.
     */
    abstract int getDealerTotal();
}

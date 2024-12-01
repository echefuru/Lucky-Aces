package entity.room;

import java.util.List;

import entity.Card;
import entity.Deck;

/**
 * The abstract representation of a game room in our program.
 */
abstract class AbstractRoom {
    private Deck deck;
    private int wins;
    private int losses;
    private int draws;

    AbstractRoom(Deck deck) {
        this.deck = deck;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public abstract void playerDrawCards(int player, List<Card> cards);

    public void incrementWins() {
        wins++;
    }

    public void incrementLosses() {
        losses++;
    }

    public void incrementDraws() {
        draws++;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getDraws() {
        return draws;
    }

    public abstract List<String> getPlayerCardStrings(int player);

    public abstract void newRound();
}

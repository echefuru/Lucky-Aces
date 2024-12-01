package entity.room;

import java.util.List;

import data_type.Card;
import data_type.Deck;
import entity.player.BlackjackPlayer;

/**
 * A Blackjack extension of the AbstractRoom.
 */
public class BlackjackRoom extends AbstractRoom {

    public static final int HUMAN_PLAYER = 0;
    public static final int DEALER = 1;

    private final int minimumBet;
    private final BlackjackPlayer[] players;

    public BlackjackRoom(Deck deck, int bankroll, int minimumBet) {
        super(deck);
        this.setDeck(deck);
        this.minimumBet = minimumBet;

        this.players = new BlackjackPlayer[2];
        for (int i = 0; i < this.players.length; i++) {
            this.players[i] = new BlackjackPlayer(bankroll);
        }

        this.players[DEALER].setBankroll(BlackjackPlayer.INFINITY);
    }

    /**
     * Return the hand value of the given player according to Blackjack rules.
     * @param player the player who holds the hand
     * @return the hand value
     */
    public int getPlayerHandValue(int player) {
        return players[player].getHandValue();
    }

    @Override
    public void playerDrawCards(int player, List<Card> cards) {
        players[player].getCards().addAll(cards);
    }

    @Override
    public List<String> getPlayerCardStrings(int player) {
        return players[player].getCardStrings();
    }

    @Override
    public void newRound() {
        for (BlackjackPlayer player : players) {
            player.clearCards();
        }
    }
}

package entity.room;

import java.util.List;

import entity.Card;
import entity.Deck;
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

    public BlackjackPlayer getHumanPlayer() {
        return this.players[HUMAN_PLAYER];
    }

    public BlackjackPlayer getDealer() {
        return this.players[DEALER];
    }

    /**
     * Return the hand value of the given player according to Blackjack rules.
     * @param player the player who holds the hand
     * @return the hand value
     */
    public int getPlayerHandValue(int player) {
        return players[player].getHandValue();
    }

    /**
     * Retuen the bankroll of the given player.
     * @param player the player being checked
     * @return the bankroll of the player
     */
    public int getPlayerBankroll(int player) {
        return players[player].getBankroll();
    }

    public int getMinimumBet() {
        return minimumBet;
    }

    /**
     * Update the bankroll of the player after placing a bet.
     * @param player the player to place the bet
     * @param bet the bet to be placed by the player
     */
    public void playerPlaceBet(int player, int bet) {
        players[player].placeBet(bet);
    }

    /**
     * Update the bankroll of the player after winning.
     * @param player the player to gain the winnings
     * @param multiplier the multiplier applied to the player's bet
     */
    public void playerWin(int player, int multiplier) {
        players[player].win(multiplier);
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

package use_case.blackjack;

import entity.Card;
import entity.Deck;
import entity.Rank;
import use_case.ApiDataAccessInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * The Blackjack Interactor.
 */
public class BlackjackInteractor implements BlackjackInputBoundary {

    private static final int NUM_DECKS = 6;

    private final ApiDataAccessInterface apiDataAccessObject;
    private final BlackjackOutputBoundary blackjackPresenter;
    private Deck deck;
    private List<Card> playerCards;
    private List<Card> dealerCards;
    private int playerTotal;
    private int dealerTotal;

    public BlackjackInteractor(ApiDataAccessInterface apiDataAccessObject,
                               BlackjackOutputBoundary blackjackOutputBoundary) {
        this.apiDataAccessObject = apiDataAccessObject;
        this.blackjackPresenter = blackjackOutputBoundary;
    }

    @Override
    public void play() {
        deck = apiDataAccessObject.createDeck(NUM_DECKS);
        playerCards = apiDataAccessObject.draw(deck, 2);
        dealerCards = apiDataAccessObject.draw(deck, 1);

        // TODO: Check for instant 21 and check aces behind that.
        playerTotal = handValue(playerCards);

        // Cards to Strings for output.
        final List<String> playerCardStrings = new ArrayList<String>(playerCards.size());
        for (Card card : playerCards) {
            playerCardStrings.add(card.toString());
        }
        final List<String> dealerCardStrings = new ArrayList<String>(dealerCards.size());
        for (Card card : dealerCards) {
            dealerCardStrings.add(card.toString());
        }
        final BlackjackOutputData blackjackOutputData = new BlackjackOutputData(playerCardStrings, playerTotal,
                dealerCardStrings);

        // Stage set based on immediate 21 or not.
        if (playerTotal == 21) {
            // If player has 21 immediately, they hold by definition.
            hold();
        }
        else {
            // If player has less than 21, they get chance to hit.
            blackjackPresenter.preparePlayView(blackjackOutputData);
        }
    }

    @Override
    public void hit() {
        playerCards.add(apiDataAccessObject.draw(deck, 1).get(0));

        playerTotal = handValue(playerCards);

        final List<String> playerCardStrings = new ArrayList<String>(playerCards.size());
        for (Card card : playerCards) {
            playerCardStrings.add(card.toString());
        }
        final List<String> dealerCardStrings = new ArrayList<String>(dealerCards.size());
        for (Card card : dealerCards) {
            dealerCardStrings.add(card.toString());
        }
        final BlackjackOutputData blackjackOutputData = new BlackjackOutputData(playerCardStrings, playerTotal,
                dealerCardStrings);

        if (playerTotal == 21) {
            hold();
        }
        else if (playerTotal < 21) {
            // If player has less than 21, they get chance to hit.
            blackjackPresenter.preparePlayView(blackjackOutputData);
        }
        else {
            // If player has more than 21, they lose.
            blackjackPresenter.prepareBustView(blackjackOutputData);
        }
    }

    @Override
    public void hold() {
        do {
            dealerCards.add(apiDataAccessObject.draw(deck, 1).get(0));
            dealerTotal = handValue(dealerCards);
        } while (dealerTotal < 17);

        final List<String> playerCardStrings = new ArrayList<String>(playerCards.size());
        for (Card card : playerCards) {
            playerCardStrings.add(card.toString());
        }
        final List<String> dealerCardStrings = new ArrayList<String>(dealerCards.size());
        for (Card card : dealerCards) {
            dealerCardStrings.add(card.toString());
        }
        final BlackjackOutputData blackjackOutputData = new BlackjackOutputData(playerCardStrings, playerTotal,
                dealerCardStrings);

        if (dealerTotal > 21 || dealerTotal < playerTotal) {
            // If dealer busts or has less than player, you win.
            blackjackPresenter.prepareWinView(blackjackOutputData);
        }
        else if (dealerTotal == playerTotal) {
            blackjackPresenter.prepareDrawView(blackjackOutputData);
        }
        else {
            blackjackPresenter.prepareLossView(blackjackOutputData);
        }
    }

    @Override
    public void playAgain() {
        // TODO: nullifying objects will move to entities.
        deck = null;
        playerCards = null;
        dealerCards = null;
        playerTotal = 0;

        blackjackPresenter.preparePlayAgainView();
    }

    @Override
    public void switchToGameLibraryView() {
        blackjackPresenter.switchToGameLibraryView();
    }

    // TODO: Private helpers, move these to some Entity.
    private int handValue(List<Card> cards) {
        int value = 0;
        for (Card card : cards) {
            value += card.getRank().getRankValue();
        }
        if (hasAce(cards) && value < 12) {
            value += 10;
        }
        return value;
    }

    // TODO: Private helpers, move these to some Entity.
    private boolean hasAce(List<Card> cards) {
        boolean hasAce = false;
        for (Card card : cards) {
            if (card.getRank() == Rank.ACE) {
                hasAce = true;
                break;
            }
        }
        return hasAce;
    }
}

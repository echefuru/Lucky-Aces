package use_case.blackjack;

import entity.Card;
import entity.Deck;
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
    private String stage;

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

        for (Card card : playerCards) {
            playerTotal += card.getRank().getRankValue();
        }

        // TODO: Check for instant win, check for double aces.

        final List<String> playerCardStrings = new ArrayList<String>(playerCards.size());
        for (Card card : playerCards) {
            playerCardStrings.add(card.toString());
        }

        final List<String> dealerCardStrings = new ArrayList<String>(dealerCards.size());
        for (Card card : dealerCards) {
            dealerCardStrings.add(card.toString());
        }

        // final BlackjackOutputData response = new BlackjackOutputData();
    }

    @Override
    public void hit() {

    }

    @Override
    public void hold() {

    }

    @Override
    public void switchToGameLibraryView() {
        blackjackPresenter.switchToGameLibraryView();
    }
}


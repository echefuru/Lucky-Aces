package data_access;

import entity.Card;
import entity.Deck;
import entity.Suit;
import entity.Rank;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.ApiDataAccessInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO for Deck management, retrieved from external API.
 */
public class ApiDataAccess implements ApiDataAccessInterface {
    // Defining necessary constants.
    private static final String API_URL = "https://www.deckofcardsapi.com/api/";
    private static final String SUCCESS_FLAG = "success";
    private static final String ERROR_FLAG = "error";
    private static final String DECK_ID_KEY = "deck_id";
    private static final String HTTP_ERROR_STRING = "HTTP %s";

    @Override
    public Deck createDeck() throws IOException {
        // Create the client.
        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        // Note: The API creates a deck of 52 standard cards by default.
        final Request request = new Request.Builder()
                .url(String.format("%s/deck/new/shuffle", API_URL))
                .build();

        // Get the response body; check private method for implementation.
        final JSONObject responseBody = getResponseBody(client, request);

        // Logic of happy path; return the generated Deck with its id.
        return new Deck(responseBody.getString(DECK_ID_KEY));
    }

    @Override
    public Deck createDeck(int numDecks) throws IOException {
        // Create the client.
        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        // Note: The API requires the number of decks as a query parameter.
        final Request request = new Request.Builder()
                .url(String.format("%s/deck/new/shuffle/?deck_count=%s", API_URL, numDecks))
                .build();

        // Get the response body; check private method for implementation.
        final JSONObject responseBody = getResponseBody(client, request);

        // Logic of happy path; return the generated Deck with its id.
        return new Deck(responseBody.getString(DECK_ID_KEY));
    }

    @Override
    public Card draw(Deck deck) throws IOException {
        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        // Note: The API requires the deck_id in the path.
        final Request request = new Request.Builder()
                .url(String.format("%s/deck/%s/draw", API_URL, deck.getDeckId()))
                .build();

        final JSONObject responseBody = getResponseBody(client, request);

        // Logic of happy path; return the generated Card.
        final JSONObject jsonCard = responseBody.getJSONArray("cards").getJSONObject(0);
        return new Card(Rank.fromString(jsonCard.getString("value")),
                Suit.valueOf(jsonCard.getString("suit")));
    }

    @Override
    public List<Card> draw(Deck deck, int numCards) throws IOException {
        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        // Note: The API requires the deck_id and number of cards to draw as query parameters.
        final Request request = new Request.Builder()
                .url(String.format("%s/deck/%s/draw/?count=%s", API_URL, deck.getDeckId(), numCards))
                .build();

        final JSONObject responseBody = getResponseBody(client, request);

        // Logic of happy path; return the generated List of Cards.
        final List<Card> cards = new ArrayList<>();
        final JSONArray jsonCards = responseBody.getJSONArray("cards");
        for (int i = 0; i < jsonCards.length(); i++) {
            cards.add(new Card(Rank.fromString(jsonCards.getJSONObject(i).getString("value")),
                    Suit.valueOf(jsonCards.getJSONObject(i).getString("suit"))));
        }
        return cards;
    }

    @Override
    public Deck shuffle(Deck deck) throws IOException {
        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        // Note: The API requires the deck_id in the path.
        final Request request = new Request.Builder()
                .url(String.format("%s/deck/%s/shuffle", API_URL, deck.getDeckId()))
                .build();

        final JSONObject responseBody = getResponseBody(client, request);

        // Logic of happy path; return the newly shuffled Deck with its id.
        return new Deck(responseBody.getString(DECK_ID_KEY));
    }

    private JSONObject getResponseBody(OkHttpClient client, Request request) throws IOException {
        // Make the call and check for HTTP errors.
        final Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new RuntimeException(String.format(HTTP_ERROR_STRING, response.code()));
        }

        // Get the response body and check for API-defined errors.
        final JSONObject responseBody = new JSONObject(response.body().string());
        if (!responseBody.getBoolean(SUCCESS_FLAG)) {
            throw new RuntimeException(responseBody.getString(ERROR_FLAG));
        }

        return responseBody;
    }

    public static void main(String[] args) throws IOException {
        ApiDataAccessInterface dao = new ApiDataAccess();

        Deck deck = dao.createDeck(1);
        Card card = dao.draw(deck);
        System.out.println("Drawing 1 card, 51 remaining.");
        System.out.println(card.getRank() + ", " + card.getSuit());

        System.out.println("Drawing 51 cards, none remaining.");
        List<Card> cards = dao.draw(deck, 51);
        for (Card c : cards) {
            System.out.println(c.getRank() + ", " + c.getSuit());
        }

        System.out.println("Shuffling deck, back to 52 remaining.");
        dao.shuffle(deck);

        System.out.println("Drawing 1 card again.");
        Card card1 = dao.draw(deck);
        System.out.println(card1.getRank() + ", " + card1.getSuit());
    }
}
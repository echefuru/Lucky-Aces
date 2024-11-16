package data_access;

import entity.Card;
import entity.Deck;
import entity.Suit;
import entity.Rank;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
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
    public Deck createDeck(int numDecks) {
        // Create the client.
        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        // Note: The API requires the number of decks as a query parameter.
        final Request request = new Request.Builder()
                .url(String.format("%s/deck/new/shuffle/?deck_count=%s", API_URL, numDecks))
                .build();
        try {
            // Make the call.
            final Response response = client.newCall(request).execute();
            // Check for HTTP codes that are not in [200,300).
            if (!response.isSuccessful()) {
                throw new RuntimeException(String.format(HTTP_ERROR_STRING, response.code()));
            }
            // Create JSON object out of response body.
            final JSONObject responseBody = new JSONObject(response.body().string());
            // Check for API=specific error.
            if (!responseBody.getBoolean(SUCCESS_FLAG)) {
                throw new RuntimeException(responseBody.getString(ERROR_FLAG));
            }
            // Logic of happy path; return the generated Deck with its id.
            return new Deck(responseBody.getString(DECK_ID_KEY));
        }
        catch (IOException | JSONException event) {
            throw new RuntimeException(event);
        }
    }

    @Override
    public Card draw(Deck deck) {
        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        // Note: The API requires the deck_id as a query parameter.
        final Request request = new Request.Builder()
                .url(String.format("%s/deck/%s/draw", API_URL, deck.getDeckId()))
                .build();
        try {
            final Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new RuntimeException(String.format(HTTP_ERROR_STRING, response.code()));
            }

            final JSONObject responseBody = new JSONObject(response.body().string());
            if (!responseBody.getBoolean(SUCCESS_FLAG)) {
                throw new RuntimeException(responseBody.getString(ERROR_FLAG));
            }

            // Logic of happy path; return the generated Card.
            final JSONObject jsonCard = responseBody.getJSONArray("cards").getJSONObject(0);
            return new Card(Rank.fromString(jsonCard.getString("value")),
                            Suit.valueOf(jsonCard.getString("suit")));
        }
        catch (IOException | JSONException | IllegalArgumentException event) {
            throw new RuntimeException(event);
        }
    }

    @Override
    public List<Card> draw(Deck deck, int numCards) {
        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        // Note: The API requires the deck_id and number of cards to draw as query parameters.
        final Request request = new Request.Builder()
                .url(String.format("%s/deck/%s/draw/?count=%s", API_URL, deck.getDeckId(), numCards))
                .build();
        try {
            final Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new RuntimeException(String.format(HTTP_ERROR_STRING, response.code()));
            }

            final JSONObject responseBody = new JSONObject(response.body().string());
            if (!responseBody.getBoolean(SUCCESS_FLAG)) {
                throw new RuntimeException(responseBody.getString(ERROR_FLAG));
            }

            // Logic of happy path; return the generated List of Cards.
            final List<Card> cards = new ArrayList<>();
            final JSONArray jsonCards = responseBody.getJSONArray("cards");
            for (int i = 0; i < jsonCards.length(); i++) {
                cards.add(new Card(Rank.fromString(jsonCards.getJSONObject(i).getString("value")),
                                    Suit.valueOf(jsonCards.getJSONObject(i).getString("suit"))));
            }
            return cards;
        }
        catch (IOException | JSONException | IllegalArgumentException event) {
            throw new RuntimeException(event);
        }
    }

    public static void main(String[] args) {
        ApiDataAccessInterface dao = new ApiDataAccess();

        Deck deck = dao.createDeck(1);
        Card card = dao.draw(deck);
        System.out.println(card.getRank() + ", " + card.getSuit());

        List<Card> cards = dao.draw(deck, 51);
        for (Card c : cards) {
            System.out.println(c.getRank() + ", " + c.getSuit());
        }

        Card card1 = dao.draw(deck);
        System.out.println(card1.getRank() + ", " + card1.getSuit());
    }
}

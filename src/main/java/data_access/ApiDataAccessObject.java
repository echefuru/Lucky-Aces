package data_access;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import entity.Card;
import entity.Deck;
import entity.Rank;
import entity.Suit;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import use_case.ApiDataAccessInterface;

import entity.*;

/**
 * DAO for Deck management, retrieved from external API.
 */
public class ApiDataAccessObject implements ApiDataAccessInterface {
    // Defining necessary constants.
    private static final String API_URL = "https://www.deckofcardsapi.com/api/";
    private static final String REMAINING_FLAG = "remaining";

    @Override
    public Deck createDeck() {
        // Create the client.
        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        // Note: The API creates a deck of 52 standard cards by default.
        final Request request = new Request.Builder()
                .url(String.format("%s/deck/new/shuffle", API_URL))
                .build();

        // Get the response body; check private method for implementation.
        final JSONObject responseBody = getResponseBody(client, request);

        // Logic of happy path; return the generated Deck with its id.
        return new Deck(responseBody.getString("deck_id"), responseBody.getInt(REMAINING_FLAG));
    }

    @Override
    public Deck createDeck(int numDecks) {
        // Create the client.
        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        // Note: The API requires the number of decks as a query parameter.
        final Request request = new Request.Builder()
                .url(String.format("%s/deck/new/shuffle/?deck_count=%s", API_URL, numDecks))
                .build();

        // Get the response body; check private method for implementation.
        final JSONObject responseBody = getResponseBody(client, request);

        // Logic of happy path; return the generated Deck.
        return new Deck(responseBody.getString("deck_id"), responseBody.getInt(REMAINING_FLAG));
    }

    @Override
    public List<Card> draw(Deck deck, int numCards) {
        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        // Note: The API requires the deck_id and number of cards to draw as query parameters.
        final Request request = new Request.Builder()
                .url(String.format("%s/deck/%s/draw/?count=%s", API_URL, deck.getDeckId(), numCards))
                .build();

        final JSONObject responseBody = getResponseBody(client, request);

        // Logic of happy path; return the generated List of Cards and update Deck remaining cards.
        deck.setRemaining(responseBody.getInt(REMAINING_FLAG));
        final List<Card> cards = new ArrayList<>();
        final JSONArray jsonCards = responseBody.getJSONArray("cards");
        for (int i = 0; i < jsonCards.length(); i++) {
            cards.add(new Card(Rank.fromString(jsonCards.getJSONObject(i).getString("value")),
                    Suit.valueOf(jsonCards.getJSONObject(i).getString("suit")),
                    jsonCards.getJSONObject(i).getString("code")));
        }
        return cards;
    }

    @Override
    public void shuffle(Deck deck) {
        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        // Note: The API requires the deck_id in the path.
        final Request request = new Request.Builder()
                .url(String.format("%s/deck/%s/shuffle", API_URL, deck.getDeckId()))
                .build();

        final JSONObject responseBody = getResponseBody(client, request);

        // Logic of happy path; reset and shuffle the Deck.
        deck.setRemaining(responseBody.getInt(REMAINING_FLAG));
    }

    private JSONObject getResponseBody(OkHttpClient client, Request request) {
        // Make the call and check for HTTP errors.
        final Response response;
        try {
            response = client.newCall(request).execute();
        }
        catch (IOException ex) {
            throw new RuntimeException("IOException during API call.");
        }

        if (!response.isSuccessful()) {
            throw new RuntimeException(String.format("HTTP %s", response.code()));
        }

        // Get the response body and check for API-defined errors.
        final JSONObject responseBody;
        try {
            responseBody = new JSONObject(response.body().string());
        }
        catch (IOException ex) {
            throw new RuntimeException("IOException during JSON parsing.");
        }

        if (!responseBody.getBoolean("success")) {
            throw new RuntimeException(responseBody.getString("API error."));
        }

        return responseBody;
    }

}

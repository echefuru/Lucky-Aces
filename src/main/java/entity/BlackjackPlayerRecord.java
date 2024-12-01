package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * The record of the blackjack player.
 */
public class BlackjackPlayerRecord {
    private static final int INITIAL_CAPACITY = 10;
    private int totalWins;
    private int totalLosses;
    private int totalRounds;
    private List<Integer> handValueRecord;

    public BlackjackPlayerRecord() {
        this.totalWins = 0;
        this.totalLosses = 0;
        this.totalRounds = 0;
        this.handValueRecord = new ArrayList<>(INITIAL_CAPACITY);
    }

    public int getTotalWins() {
        return totalWins;
    }

    public int getTotalLosses() {
        return totalLosses;
    }

    public int getTotalRounds() {
        return totalRounds;
    }

    /**
     * Return the hand value records.
     * @return a list of the hand value records
     */
    public List<Integer> getHandValueRecord() {
        return handValueRecord;
    }

    /**
     * Update the handValueRecord with the new hand value.
     * @param handValue new (current hand value)
     */
    public void updateHandValueRecord(int handValue) {
        this.handValueRecord.add(handValue);
    }

    /**
     * Record the number of rounds and if won this round.
     * @param won if won this round, 0 for lose, 1 for win, 2 for draw
     */
    public void recordRound(int won) {
        this.totalRounds++;
        if (won == 1) {
            this.totalWins++;
        }
        else if (won == 0) {
            this.totalLosses++;
        }
    }
}

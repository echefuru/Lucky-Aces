package use_case.blackjack.player_record;

import java.util.List;

/**
 * Output Data for the Player Record Use Case.
 */
public class PlayerRecordOutputData {
    private int totalWins;
    private int totalRounds;
    private List<Integer> handValueRecord;

    public PlayerRecordOutputData(int totalWins, int totalRounds, List<Integer> handValueRecord) {
        this.totalWins = totalWins;
        this.totalRounds = totalRounds;
        this.handValueRecord = handValueRecord;
    }

    public int getTotalWins() {
        return totalWins;
    }

    public int getTotalRounds() {
        return totalRounds;
    }

    public List<Integer> getHandValueRecord() {
        return handValueRecord;
    }
}

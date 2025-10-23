public class BetCard {
    public int moneyPerBet;
    public int numDraws;

    public BetCard() {
        this.moneyPerBet = 0;
        this.numDraws = 0;
    }

    public boolean isBetsReady() {
        return this.moneyPerBet != 0 && this.numDraws != 0;
    }
}

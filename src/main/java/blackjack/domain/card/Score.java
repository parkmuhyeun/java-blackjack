package blackjack.domain.card;

import blackjack.domain.user.Dealer;

import java.util.Objects;

public class Score {
    private static final int TEN = 10;
    private static final int BLACKJACK = 21;
    private static final int DEALER_HIT = 16;

    private final int score;

    public Score(final int score) {
        this.score = score;
    }

    public Score minusTenIfBust() {
        if (isBust()) {
            return new Score(this.score - TEN);
        }
        return this;
    }

    public boolean isBust() {
        return this.score > BLACKJACK;
    }

    public boolean isBlackjack() {
        return this.score == BLACKJACK;
    }

    public boolean isPlayerHit() {
        return this.score <= BLACKJACK;
    }

    public boolean isDealerHit() {
        return this.score <= DEALER_HIT;
    }

    public boolean isSame(Dealer dealer) {
        return this.score == dealer.score();
    }

    public boolean isHigh(Dealer dealer) {
        return this.score > dealer.score();
    }

    public int getScore() {
        return this.score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return score == score1.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }
}
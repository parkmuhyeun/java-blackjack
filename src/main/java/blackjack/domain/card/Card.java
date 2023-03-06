package blackjack.domain.card;

import blackjack.domain.Pattern;

import java.util.Objects;

abstract public class Card {

    protected final Pattern pattern;
    protected final String symbol;

    public Card(Pattern pattern, String symbol) {
        validateSymbol(symbol);
        this.pattern = pattern;
        this.symbol = symbol;
    }

    protected void validateSymbol(String symbol) {}

    public abstract int getValue();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return pattern == card.pattern && Objects.equals(symbol, card.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pattern, symbol);
    }

    public String getCardInfo() {
        return symbol + pattern.getName();
    }
}

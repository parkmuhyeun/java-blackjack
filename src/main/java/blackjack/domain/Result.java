package blackjack.domain;

public enum Result {
    LOSE("패"),
    DRAW("무"),
    WIN("승");

    private final String result;

    Result(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }
}

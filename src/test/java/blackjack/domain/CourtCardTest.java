package blackjack.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CourtCardTest {

    @ParameterizedTest
    @ValueSource(strings = {"J", "Q", "K"})
    void symbolIsValid(String input) {
        Assertions.assertThatNoException().isThrownBy(() -> new CourtCard(Pattern.SPADE, input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"k", "a", "A", "E"})
    void symbolIsInvalid(String input) {
        Assertions.assertThatThrownBy(() -> new CourtCard(Pattern.SPADE, input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(String.format("심볼은 J, Q, K 중 하나여야 합니다. 입력된 값 : %s", input));
    }
}
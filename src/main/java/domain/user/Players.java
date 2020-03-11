package domain.user;


import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 클래스 이름 : .java
 *
 * @author
 * @version 1.0
 * <p>
 * 날짜 : 2020/03/11
 */
public class Players {
	public static final String OVER_MAX_PLAYERS_COUNT = "블랙잭의 최대 인원은 8명입니다.";
	public static final String UNDER_MIN_PLAYERS_COUNT = "블랙잭의 최소 인원은 1명입니다.";
	private static final String DELIMITER = ",";
	private static final int MAX_PLAYERS_COUNT = 8;
	private static final int MIN_PLAYERS_COUNT = 0;

	private final List<Player> players;

	private Players(List<Player> players) {
		if (players.size() >= MAX_PLAYERS_COUNT) {
			throw new IllegalArgumentException(OVER_MAX_PLAYERS_COUNT);
		}

		if (players.size() == MIN_PLAYERS_COUNT) {
			throw new IllegalArgumentException(UNDER_MIN_PLAYERS_COUNT);
		}
		this.players = players;
	}

	public static Players of(List<Player> values) {
		return new Players(values);
	}

	public static Players of(String names) {
		String trimmedNames = names.trim();
		return of(Arrays.stream(trimmedNames.split(DELIMITER))
				.map(Player::new)
				.collect(Collectors.toList()));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Players players1 = (Players) o;
		return Objects.equals(players, players1.players);
	}

	@Override
	public int hashCode() {
		return Objects.hash(players);
	}
}

package blackjack.controller;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.game.BlackJackGame;
import blackjack.domain.game.Result;
import blackjack.strategy.RandomCardShuffle;
import blackjack.util.Repeater;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackJackController {

    private final BlackJackGame blackJackGame;

    public BlackJackController() {
        blackJackGame = new BlackJackGame(new RandomCardShuffle());
    }

    public void run() {
        final Players players = Repeater.repeatIfError(this::inputPlayerNames, OutputView::printErrorMessage);
        final Dealer dealer = new Dealer();

        initHit(players, dealer);
        askPlayers(players);
        hitCardByDealer(dealer);
        printFinal(players, dealer);
    }

    private void initHit(Players players, Dealer dealer) {
        blackJackGame.initHit(players, dealer);
        OutputView.printInitCardDeck(dealer, players);
    }

    private Players inputPlayerNames() {
        return new Players(InputView.inputPlayerNames());
    }

    private void askPlayers(Players players) {
        for (Player player : players.getPlayers()) {
            askPlayer(player);
        }
    }

    private void askPlayer(Player player) {
        Command command = Command.CONTINUE;
        int score = 0;

        while (isContinueToAsk(command, score)) {
            command = Repeater.repeatIfError(() -> inputCommand(player), OutputView::printErrorMessage);
            hitByCommand(player, command);
            OutputView.printPlayerCardDeck(player);
            score = blackJackGame.calculateScore(player);
            checkBurst(score);
        }
    }

    private boolean isContinueToAsk(Command command, int score) {
        return Command.isContinue(command) && blackJackGame.isValidScore(score);
    }

    private Command inputCommand(Player player) {
        return Command.toCommand(InputView.inputReply(player.getName().getValue()));
    }

    private void hitByCommand(Player player, Command command) {
        if (Command.isContinue(command)) {
            blackJackGame.hit(player);
        }
    }

    private void checkBurst(int score) {
        if (blackJackGame.isBurst(score)) {
            OutputView.printBurstMessage();
        }
    }

    private void hitCardByDealer(Dealer dealer) {
        OutputView.println();
        int dealerScore = blackJackGame.calculateScore(dealer);

        while (blackJackGame.isContinueToHit(dealerScore)) {
            blackJackGame.hit(dealer);
            dealerScore = blackJackGame.calculateScore(dealer);
            OutputView.printDealerPickMessage(dealer);
        }
    }

    private void printFinal(Players players, Dealer dealer) {
        List<Result> results = blackJackGame.getPlayersResult(dealer, players);

        OutputView.printFinalCardDeckAndScore(dealer, players);
        OutputView.printResult(blackJackGame.getDealerResult(results), dealer, players, results);
    }
}

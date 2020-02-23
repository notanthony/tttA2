

public class HumanPlayer implements Player {
	public void play (TicTacToeGame game) {
		if (game == null || game.getGameState() != GameState.PLAYING) {
			System.out.println("Game is not playableyable...");
		}
	}
}
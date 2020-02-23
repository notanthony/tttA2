import java.util.*;

public class ComputerRandomPlayer implements Player {

	@Override
	public void play (TicTacToeGame game) {
		if (game == null || game.getGameState() != GameState.PLAYING) {
			System.out.println("Game is not playable...");
		} 
		int position = Utils.generator.nextInt(game.columns*game.lines-game.getLevel())+1;
		int x = -1;
		while (0 != position) {
			x++;
			if (game.valueAt(x) == CellValue.EMPTY) {
				position--;
			}
		}
		game.play(x);
	}
}
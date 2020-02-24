/**
 * The class <b>ComputerRandomPlayer</b> is the class that controls the computer's plays.
 * 
 * @author Anthony Zhao and Cadence Yeung
 */

import java.util.*;

public class ComputerRandomPlayer implements Player {

	@Override
	public void play (TicTacToeGame game) {
		// checks if game is playable
		if (game == null || game.getGameState() != GameState.PLAYING) {
			System.out.println("Game is not playable...");
		}
		int position = Utils.generator.nextInt(game.getColumns()*game.getLines()-game.getLevel())+1; // generates random position
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
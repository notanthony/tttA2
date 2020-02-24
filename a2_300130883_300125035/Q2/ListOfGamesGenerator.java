/**
 * The class <b>ListOfGamesGenerator</b> is the class that generates all the different games for the 
 * specified parameters.
 * 
 * @author Anthony Zhao and Cadence Yeung
 */

import java.util.LinkedList;

public class ListOfGamesGenerator {
	/**
	 * generates all different games for the specified parameters. Each game is
	 * recorded only once. once a game is finished, it is not extended furthe
	 * 
	 * @param lines   the number of lines in the game
	 * @param columns the number of columns in the game
	 * @param sizeWin the number of cells that must be aligned to win.
	 * @return a list of lists of game instances, ordered by levels
	 */
	public static LinkedList<LinkedList<TicTacToeGame>> generateAllGames(int lines, int columns, int winLength) {
		LinkedList<LinkedList<TicTacToeGame>> main = new LinkedList<>();
		LinkedList<TicTacToeGame> first = new LinkedList<>();
		first.add(new TicTacToeGame(lines, columns, winLength));
		main.add(first);
		for (int x = 0; x < lines * columns; x++) {
			LinkedList<TicTacToeGame> level = main.get(x);
			LinkedList<TicTacToeGame> nextLevel = new LinkedList<>();
			for (int y = 0; y < level.size(); y++) {
				TicTacToeGame preGame = level.get(y);
				for (int z = 0; z < lines * columns; z++) {
					boolean add = preGame.getGameState() == GameState.PLAYING && preGame.valueAt(z) == CellValue.EMPTY;
					if (add) {
						TicTacToeGame game = new TicTacToeGame(preGame, z);
						for (TicTacToeGame g : nextLevel) {
							if (g.equals(game)) {
								add = false;
							}
						}
						if (add) {
							nextLevel.add(game);
						}
					}
				}
			}
			main.add(nextLevel);
		}
		return main;
	}
}

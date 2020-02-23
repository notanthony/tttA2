import java.io.Console;
import java.util.Scanner;

public class HumanPlayer implements Player {

	@Override
	public void play (TicTacToeGame game) {
		Console console = System.console();
		Scanner sc = new Scanner(console.reader());
		if (game == null || game.getGameState() != GameState.PLAYING) {
			System.out.println("Game is not playable...");
		} else {
			// code from the main of TicTacToe
			while (GameState.PLAYING == game.getGameState()) {
				System.out.println(game.toString());
				String player = "";
				if (game.nextCellValue() == CellValue.X) {
					player = "X";
				} else {
					player = "O";
				}
				System.out.print(player + " to play: ");
				game.play(sc.nextInt() -1);
			}
		}
	}
}
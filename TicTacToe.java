import java.io.Console;
import java.util.Scanner;

/**
 * The class <b>TicTacToe</b> is the class that users to play TicTacToe using
 * the logic from TicTacToeGame
 * 
 * @author Anthony Zhao and Bradley Liu
 */
public class TicTacToe {

	/**
	 * <b>main</b> of the application. Creates the instance of TicTacToeGame and
	 * starts the game. If two parameters lines and columns are passed, they are
	 * used. If the paramters lines, columns and win are passed, they are used.
	 * Otherwise, a default value is used. Defaults values (3) are also used if the
	 * paramters are too small (less than 2). Here, we assume that the command lines
	 * arguments are indeed integers
	 *
	 * @param args command lines parameters
	 */
	public static void main(String[] args) {
		StudentInfo.display();
		Console console = System.console();
		Scanner sc = new Scanner(console.reader());
		TicTacToeGame game;
		int lines, columns, win;
		lines = 3;
		columns = 3;
		win = 3;

		if (args.length >= 2) {
			lines = Integer.parseInt(args[0]);
			if (lines < 2) {
				System.out.println("Invalid argument, using default...");
				lines = 3;
			}
			columns = Integer.parseInt(args[1]);
			if (columns < 2) {
				System.out.println("Invalid argument, using default...");
				columns = 3;
			}
		}
		if (args.length >= 3) {
			win = Integer.parseInt(args[2]);
			if (win < 2) {
				System.out.println("Invalid argument, using default...");
				win = 3;
			}
		}
		if (args.length > 3) {
			System.out.println("Too many arguments. Only the first 3 are used.");
		}

		game = new TicTacToeGame(lines, columns, win);
		// will prompt user to play the game until the game is finished
		while (GameState.PLAYING == game.getGameState()) {
			// prints the game board
			System.out.println(game.toString());
			String player = "";
			// determines the current turn
			if (game.nextCellValue() == CellValue.X) {
				player = "X";
			} else {
				player = "O";
			}
			// prompts the player to play
			System.out.print(player + " to play: ");
			// sends the players move to TicTacToeGame but since users usually consider the
			// first index 1 instead of 0 decrements their play by 1 to fit the java
			// standard
			game.play(sc.nextInt() -1);
		}
		// prints out the final game board and the end state of the game
		System.out.println(game.toString());
		if (game.getGameState() == GameState.DRAW) {
			System.out.println("DRAW");
		}
		if (game.getGameState() == GameState.XWIN) {
			System.out.println("X WINS");
		}
		if (game.getGameState() == GameState.OWIN) {
			System.out.println("O WINS");
		}
	}

}
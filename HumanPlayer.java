/**
 * The class <b>HumanPlayer</b> is the class that controls the human's plays.
 * 
 * @author Anthony Zhao and Cadence Yeung
 */

public class HumanPlayer implements Player {

	@Override
	public void play (TicTacToeGame game) {
		int level = game.getLevel();
		// allow human to play while on the same level
		do {
			game.play(Integer.parseInt(Utils.console.readLine())-1); //  plays human's input after receiving it from user
		} while (level == game.getLevel()); 
	}
}
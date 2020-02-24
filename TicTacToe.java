/**
 * The class <b>TicTacToe</b> is the class that implements the actual Tic Tac Toe game, where it 
 * controls the human and computer activity and prints the result of the game at the end. It also
 * asks the player if he/she wants to continue playing once this game is over.
 * 
 * @author Anthony Zhao and Cadence Yeung
 */

public class TicTacToe{

   /**
     * <b>main</b> of the application. Creates the instance of  GameController 
     * and starts the game. If two parameters line  and column
     * are passed, they are used. 
     * Otherwise, a default value is used. Defaults values are also
     * used if the paramters are too small (less than 2).
     * 
     * @param args
     *            command line parameters
     */
     public static void main(String[] args) {

        StudentInfo.display();
        TicTacToeGame game;
        int lines = 3;
        int columns = 3;
        int win = 3;
	
   
        try{
            if (args.length >= 2) {
                lines = Integer.parseInt(args[0]);
                if(lines<2){
                    System.out.println("Invalid argument, using default...");
                    lines = 3;
                }
                columns = Integer.parseInt(args[1]);
                if(columns<2){
                    System.out.println("Invalid argument, using default...");
                    columns = 3;
                }
            }
            if (args.length >= 3){
                win = Integer.parseInt(args[2]);
                if(win<2){
                    System.out.println("Invalid argument, using default...");
                    win = 3;
                }
            } 
            if (args.length > 3){
                System.out.println("Too many arguments. Only the first 3 are used.");
            } 

        } catch(NumberFormatException e){
            System.out.println("Invalid argument, using default...");
            lines   = 3;
            columns  = 3;
            win = 3;
        }
        
        Player[] players = {new HumanPlayer(), new ComputerRandomPlayer()};
        int first = 0+Utils.generator.nextInt(2); // randomly decide which player gets to go first (human or computer)
		do {
            game = new TicTacToeGame(lines, columns, win);
            // for loop that prints who's turn it is, the board, and who is to play, until the game ends
			for (int counter = first; game.getGameState() == GameState.PLAYING; counter++) { 
				if (counter%2 == 0) { // if counter is even, it is player 1's turn
					System.out.println("Player 1's Turn" + Utils.NEW_LINE + game + Utils.NEW_LINE + game.nextCellValue() + " to play: ");
					players[0].play(game); // calls play from HumanPlayer
				
                } else { // else, counter is odd and it is player 2's turn
					System.out.println("Player 2's Turn");
					players[1].play(game); // calls play from ComputerRandomPlayer
				}
			}
            first++;
            // prints result of game and ask if you want to play again
			System.out.print(game + Utils.NEW_LINE + "Result: " + game.getGameState() + Utils.NEW_LINE + "Play again (Y)?:");
		} while (Utils.console.readLine().compareToIgnoreCase("y") == 0);
    }

}

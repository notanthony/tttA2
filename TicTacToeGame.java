/**
 * The class <b>TicTacToeGame</b> is the
 * class that implements the Tic Tac Toe Game.
 * It contains the grid and tracks its progress.
 * It automatically maintain the current state of
 * the game as players are making moves.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */
public class TicTacToeGame {

// FINISH THE VARIABLE DECLARATION
   /**
	* The board of the game, stored as a one dimension array.
	*/
	private CellValue[] board;


   /**
	* level records the number of rounds that have been
	* played so far. 
	*/
	private int level;

   /**
	* gameState records the current state of the game
	*/
	private GameState gameState = GameState.PLAYING;

   /**
	* lines is the number of lines in the grid
	*/
	private int lines;

   /**
	* columns is the number of columns in the grid
	*/
	private int columns;


   /**
	* sizeWin is the number of cell of the same type
	* that must be aligned to win the game
	*/
	private int sizeWin;


   /**
	* default constructor, for a game of 3x3, which must
	* align 3 cells
	*/
	public TicTacToeGame(){
		lines = 3;
		columns = 3;
		sizeWin = 3;
		gameStart();
	}

   /**
	* constructor allowing to specify the number of lines
	* and the number of columns for the game. 3 cells must
	* be aligned.
   	* @param lines
    *  the number of lines in the game
    * @param columns
    *  the number of columns in the game
  	*/
	public TicTacToeGame(int lines, int columns){
		this.lines = lines;
		this.columns = columns;
		sizeWin = 3;
		gameStart();
	}

   /**
	* constructor allowing to specify the number of lines
	* and the number of columns for the game, as well as 
	* the number of cells that must be aligned to win.
   	* @param lines
    *  the number of lines in the game
    * @param columns
    *  the number of columns in the game
    * @param sizeWin
    *  the number of cells that must be aligned to win.
  	*/
	public TicTacToeGame(int lines, int columns, int sizeWin){
		this.lines = lines;
		this.columns = columns;
		this.sizeWin = sizeWin;
		gameStart();	
	}



   /**
	* getter for the variable lines
	* @return
	* 	the value of lines
	*/
	public int getLines(){
		return lines;
	}

   /**
	* getter for the variable columns
	* @return
	* 	the value of columns
	*/
	public int getColumns(){
		return columns;
	}

   /**
	* getter for the variable level
	* @return
	* 	the value of level
	*/
	public int getLevel(){
		return level;
	}

  	/**
	* getter for the variable sizeWin
	* @return
	* 	the value of sizeWin
	*/
	public int getSizeWin(){
		return sizeWin;
	}
   /**
	* getter for the variable gameState
	* @return
	* 	the value of gameState
	*/
	public GameState getGameState(){
		return gameState;
	}

   /**
	* returns the cellValue that is expected next,
	* in other word, which played (X or O) should 
	* play next.
	* This method does not modify the state of the
	* game.
	* @return 
    *  the value of the enum CellValue corresponding
    * to the next expected value.
  	*/
	public CellValue nextCellValue(){
		if (level%2 == 0) 
			return CellValue.X;
		return CellValue.O;
	}

   /**
	* returns the value  of the cell at
	* index i.
	* If the index is invalid, an error message is
	* printed out. The behaviour is then unspecified
   	* @param i
    *  the index of the cell in the array board
    * @return 
    *  the value at index i in the variable board.
  	*/
	public CellValue valueAt(int i) {
		return board[i];
	}

   /**
	* This method is called when the next move has been
	* decided by the next player. It receives the index
	* of the cell to play as parameter.
	* If the index is invalid, an error message is
	* printed out. The behaviour is then unspecified
	* If the chosen cell is not empty, an error message is
	* printed out. The behaviour is then unspecified
	* If the move is valide, the board is updated, as well
	* as the state of the game.
	* To faciliate testing, is is acceptable to keep playing
	* after a game is already won. If that is the case, the
	* a message should be printed out and the move recorded. 
	* the  winner of the game is the player who won first
   	* @param i
    *  the index of the cell in the array board that has been 
    * selected by the next player
  	*/
	public void play(int i) {
		if (i<0 || i>=board.length) {
			System.out.println("Invalid argument, try again");
			return;
		}
		if (board[i] != CellValue.EMPTY) {
			System.out.println("Cell is not empty, try again");
			return;
		}
		board[i] = nextCellValue();
		level++;
		System.out.println(toString());
		if (gameState == GameState.PLAYING) {
			setGameState(i);
			gameHelper();
		}	
		turnHelper();
	}


   /**
	* A helper method which updates the gameState variable
	* correctly after the cell at index i was just set in
	* the method play(int i)
	* The method assumes that prior to setting the cell
	* at index i, the gameState variable was correctly set.
	* it also assumes that it is only called if the game was
	* not already finished when the cell at index i was played
	* (i.e. the game was playing). Therefore, it only needs to 
	* check if playing at index i has concluded the game, and if
	* set the oucome correctly
	* 
   	* @param i
    *  the index of the cell in the array board that has just 
    * been set
  	*/
	
	private void turnHelper() {
		String player;
		if (nextCellValue() == CellValue.X) {
			player = "X";
		} else {
			player = "O";
		}	
		System.out.println(player + " to play: ");
	}

	private void gameHelper() {
		if (gameState == GameState.DRAW) {
			System.out.println("DRAW");
		}
		if (gameState == GameState.XWIN) {
			System.out.println("X WINS");
		}
		if (gameState == GameState.OWIN) {
			System.out.println("O WINS");
		}
	}

	private void setGameState(int i){
		if (winChecker(i)) {
			if (board[i] == CellValue.X) {
				gameState = GameState.XWIN;
				return;
			}
			gameState = GameState.OWIN;
			return;
		}
		if (level == board.length) {
			gameState = GameState.DRAW;
		}
	}
	
	private boolean winChecker(int i) {
		for (int x = columns-1; x< columns+2; x++) {
			if ((1 + setGameStateHelper(i-x, -x) + setGameStateHelper(i+x, x)) >= sizeWin)
				return true;
		}
		int currentLine = i-i%columns; 
		int counter = 0;
		for (int x = 0;  x < columns; x++) {
			if (board[currentLine+x] == board[i]) {
				counter ++;
			}else{ 
				counter = 0;
			}
			if (counter == sizeWin) 
				return true;
		}
		return false;
	}
	
	private int setGameStateHelper(int x, int increment) {
		if (x < 0 || x >= board.length || board[x] == nextCellValue() || board[x] ==  CellValue.EMPTY)
			return 0;
		return 1 + setGameStateHelper(x+increment, increment);
	}
	

	private void gameStart() {
		board = new CellValue[lines*columns];
		for (int x  = 0; x < board.length; x++) 
				board[x] = CellValue.EMPTY;
		System.out.println(toString());
		turnHelper();
	}


   /**
	* Returns a String representation of the game matching
	* the example provided in the assignment's description
	* 
   	* @return
    *  String representation of the game
  	*/

	public String toString(){
		String boardString = "";
		for (int x = 0; x < lines*2-1; x++) {
			boardString += "\n";
			if (x%2 == 1) {
				for (int y =0; y < 4*columns-1; y++) {
					boardString+="-";
				}
			} else {
				for (int y =0; y < columns; y++) {
					String cell = " ";
					if (board[x/2*columns+y] == CellValue.X) {
						cell = "X";
					}
					if (board[x/2*columns+y] == CellValue.O) {
						cell = "O";
					}
					if (y == 0) {
						boardString+= " " + cell + " ";
					}
					else {
						boardString+= "| "+ cell+ " ";
					}
				}
			}
		}
		return boardString;
	}
}
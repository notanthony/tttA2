/**
 * The class <b>TicTacToeGame</b> is the
 * class that implements the Tic Tac Toe Game.
 * It contains the grid and tracks its progress.
 * It automatically maintain the current state of
 * the game as players are making moves.
 *
 * @author Anthony Zhao and Bradley Liu
 */
public class TicTacToeGame 	  
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
	* This method intilizizes the values in board
	*/
	private void gameStart() {
		board = new CellValue[lines*columns];
		for (int x  = 0; x < board.length; x++) 
				board[x] = CellValue.EMPTY;
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
		//only checks if someone won if someone did not already win
		if (gameState == GameState.PLAYING) {
			setGameState(i);
		}	
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
	private void setGameState(int i){
		if (winChecker(i)) {
			//checks which player made the winning move
			if (board[i] == CellValue.X) {
				gameState = GameState.XWIN;
				return;
			}
			gameState = GameState.OWIN;
			return;
		}
		//if the board is full and the game has not been won then its a draw
		if (level == board.length) {
			gameState = GameState.DRAW;
		}
	}
	
	/**
	* A helper method that determines if the game state
   	* @param i
    *  the index of the cell in the array board that has just 
    * been set
	* @return 
	*  wether or not the play won the game
  	*/
	private boolean setGameStateHelper(int i) {
		/*The values of the for loop indicate the incrementer for the other method 
		 *columns-1 checks in a diagonal that looks like /
		 *columns checks vertically from the index played, looks like |
		 *columns+1 checks in a diagonal that looks like \
		 */
		
		for (int x = columns-1; x< columns+2; x++) {
			/*calls another method to count the amount of the players symbol in a row
			 *if it is equal or greater to the amount needed to win then returns true
			 *since the method called is recursive to prevent a stackoverflow it calls the method twice. one call decrementing the values and one incrementing
			 */
			if ((1 + winChecker(i-x, -x) + winChecker(i+x, x)) >= sizeWin)
				return true;
		}
		//finds the start of the line by subtracting the index value by i%coloumn which gives the current coloumn on the line
		int currentLine = i-i%columns; 
		int counter = 0;
		//to simplify finding if a win was made horizontally the for loop runs through the entire line
		for (int x = 0;  x < columns; x++) {
			//if a cell matches the symbol of the current player the counter is incremented but once the streak is broken the counter is reset
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
	
	/**
	* A helper method that counts the amount of matches in a row
   	* @param x
    *  the index of the cell that has to be checked
	* @param increment
	*  the incrementer of the index value to determine the next cell
	* @return 
	*  the amount of matches in a row
  	*/
	private int winChecker(int x, int increment) {
		//base case is reached when the index is out of bounds or the streak is broken
		if (x < 0 || x >= board.length || board[x] == nextCellValue() || board[x] ==  CellValue.EMPTY)
			return 0;
		//pushes the next index into the stack, the 1 + will increment the final return value when the compiler reaches the base case and iterates through the stack
		return 1 + setGameStateHelper(x+increment, increment);
	}

   /**
	* Returns a String representation of the game matching
	* the example provided in the assignment's description
	* 
   	* @return
    *  String representation of the game
  	*/
	public String toString(){
		//intilizizes the return string that holds the board as its constructed
		String boardString = "";
		//for loop that iterates through every line of the game since every other line is a seperator line, made only of -'s, it needs to run lines*2-1 times
		for (int x = 0; x < lines*2-1; x++) {
			boardString += "\n";
			//if its an odd line then it is a seperator line
			if (x%2 == 1) {
				//the amount of - in the examples given is 4*coloumns-1
				for (int y =0; y < 4*columns-1; y++) {
					boardString+="-";
				}
			//non seperator lines
			} else {
				/*Since the for loop goes by coloumns a variable currentLine is intialized aswell for later use
				 *x/2*columns is used to determine the array index of the current line, since the array is really just a 1d array representing a 2d array
				 *x/2 determines the line and *columns finds the index the line starts at since each line is coloums long
				 */
				for (int y =0, int currentLine = x/2*columns; y < columns; y++) {
					/*This section determines which symbol to print, the default print is " " for empty cells
					 *if there is something in the cell it replaces the " ", to do this the index of the array needs to be found
					 *using the index of the current line the +y increments it to the coloumn in that line
					 */
					String cell = " ";
					if (board[currentLine+y] == CellValue.X) {
						cell = "X";
					}
					if (board[currentLine+y] == CellValue.O) {
						cell = "O";
					}
					//this constucts the coloumn using the template "(space)(symbol)(space)"the first line is special since it does not have a seperator 
					if (y == 0) {						
						boardString+= " " + cell + " ";
					}
					//every other line has a | seperating it
					else {
						boardString+= "| "+ cell+ " ";
					}
				}
			}
		}
		return boardString;
	}
}
package assignment;

public class ConectFourGame {
	
	private final String  COLUMN_OVERFLOW = "Column overflow";
	private final String  ROW_OVERFLOW = "Row overflow";
	private final String  VERTICAL_WIN = "Vertical win. "; // Win type 1
	private final String  HORIZONTAL_WIN = "Horizontal win. "; // Win type 2
	private final String  DIAGONAL_WIN = "Horizontal win. "; // Win type 3
	private final String  ALL_INPUT_PROCESSED_NO_ONE_WINS = "All input processed. No one wins.";
	
	
	private final int COL_SIZE = 5;
	private final int ROW_SIZE = 5;
	
	int [][] board = new int[ROW_SIZE][COL_SIZE];
	String player1= "";
	String player2= "";
	boolean isComputerPlaying = false;
	
	public String runGame(String players, Cell [] blocked, PlayerMove [] moves) {
		assignPlayers(players);
		
		String returnValueFromProcessingBlocked = processBlocked(blocked);
		if(returnValueFromProcessingBlocked.length() > 0) {
			return returnValueFromProcessingBlocked;
		}
		
	   return  processInput(moves);
	}

	private String processInput(PlayerMove[] moves) {
		for(int i=0; i< moves.length ; i++) {
			String processingResult;
			
			if("COMPUTER".equals(player2)) {
				processingResult = processInput(moves[i].col -1, 1);
				if(processingResult.length() > 0) {
					return processingResult;
				}
								
				Cell cell = makeComputerMove();
				
				if(cell.row == -1) {
					return ALL_INPUT_PROCESSED_NO_ONE_WINS;
				}
				
				int checkWin = checkForWin(2, cell.row, cell.col);
				if(checkWin != 0) {
					return constructWinMessage(checkWin, 2);
				}
				
			} else {
				if(i % 2 == 0) {
					processingResult = processInput(moves[i].col -1, 1);
				} else {
					processingResult = processInput(moves[i].col -1, 2);
				}
			}
			
			if(processingResult.length() > 0) {
				return processingResult;
			}
		}
		
		return ALL_INPUT_PROCESSED_NO_ONE_WINS;
	}

	private Cell makeComputerMove() {
		 // Check if picking any column leads to computer win
		 // Check if picking any column leads to opposition win
		 for(int j=0; j < COL_SIZE ; j++) {
			 for(int i =0; i < ROW_SIZE ; i++) {
				 
				 if(board[i][j] == -1) {
					 break;
				 } else if (board[i][j] ==0) {
					 int isGameWon = checkForWin(2, i, j);
					 
					 if(isGameWon > 0) {
						 return new Cell(i, j);
					 }
					 
					 isGameWon = checkForWin(1, i, j);
					 
					 if(isGameWon > 0) {
						 return new Cell(i, j);
					 }
				 } 
			 }
		 }
		 
		 // todo ..check if there is possibility of getting 3, 2 then finally place 1 ..
		 
		 // Just check cells if there is a possibility of 
		 for(int j=0; j < COL_SIZE ; j++) {
			 for(int i =0; i < ROW_SIZE ; i++) {
				 // 
			 }
		}

		return new Cell(-1, -1);
	}

	private String processBlocked(Cell[] blocked) {
		for(int i=0; i < blocked.length; i++) {
			if(blocked[i].col -1 < 0 || blocked[i].col -1 >= COL_SIZE) {
				return COLUMN_OVERFLOW;
			} else if(blocked[i].row -1 < 0 || blocked[i].row -1 >= ROW_SIZE) {
				return ROW_OVERFLOW;
			} else {
				board[blocked[i].row -1][blocked[i].col-1] =-1;
			}
		}
		
		return "";
	}

	private void assignPlayers(String players) {
		String [] str=players.split("-");
		
		player1 = "HUMAN".equals(str[0]) ? "A" : "COMPUTER";
		player2 = "COMPUTER".equals(str[1]) ? "COMPUTER" : ("COMPUTER".equals(player1) ? "A" : "B");
		
		if("COMPUTER".equals(player1) ||  "COMPUTER".equals(player2)) {
			isComputerPlaying = true;
		}
	}

	public String processInput (int j, int player) { // player will 1 or 2 ...-1 for blocked cells
		if(j < 0 || j >= COL_SIZE) {
			return COLUMN_OVERFLOW;
		}
		
		int placedRow = -1;
		for(int i=0; i<ROW_SIZE; i++) {
			if(board[i][j] !=0) {
				placedRow = i-1;
				break;
			}
		}
		
		if(placedRow != -1) {
			int isGameWon = checkForWin(player, placedRow, j);
			if(isGameWon >0) {
				return constructWinMessage(isGameWon, player);
			}
		}  else {
			return COLUMN_OVERFLOW;
		}
		
		return "";
	}

	private String constructWinMessage(int isGameWon, int player) {
		StringBuffer sbf = new StringBuffer();
		switch(isGameWon) {
		case 1:
			sbf.append(VERTICAL_WIN);
			break;
			
		case 2:
			sbf.append(HORIZONTAL_WIN);
			break;
			
		case 3:
			sbf.append(DIAGONAL_WIN);
			break;
		}
		
		sbf.append("(");
		sbf.append(player == 1 ? player1 : player2 + " wins");
		sbf.append(")");
		
		return sbf.toString();
	}

	private int checkForWin(int player, int placedRow, int j) {
		if(isVerticalWin(player, placedRow, j)) {
			return 1;
		} else if(isHorizontalWin(player, placedRow, j)) {
			return 2;
		} else if(isDiagonalWin(player, placedRow, j)){
			return 3;
		}
		
		return 0;
	}

	private boolean isDiagonalWin(int player, int row, int col) {
		int leftTopToRightBottom=0;
		
		int i = row-1;
		int j = col-1;
		while(i > 0 && j > 0 && board[i][j] == player) {
			leftTopToRightBottom++;
			i--;
			j--;
		}
		
		i = row+1;
		j = col+1;
		while(i < ROW_SIZE && j < COL_SIZE && board[i][j] == player) {
			leftTopToRightBottom++;
			i++;
			j++;
		}
		
		if(leftTopToRightBottom +1 ==4) {
			return true;
		}
		
		
		int rightTopToleftBottom=0;
		
		i = row-1;
		j = col+1;
		while(i > 0 && j < COL_SIZE && board[i][j] == player) {
			rightTopToleftBottom++;
			i--;
			j++;
		}
		
		i = row+1;
		j = col-1;
		while(i < ROW_SIZE && j > 0 && board[i][j] == player) {
			rightTopToleftBottom++;
			i++;
			j--;
		}
		
		if(rightTopToleftBottom +1 ==4) {
			return true;
		}
		
		return false;
	}

	private boolean isHorizontalWin(int player, int placedRow, int j) {
		int horizontalEntries = 0;
		
		int i=j;
		while(i-1 >=0 && board[placedRow][i-1] == player) {
			horizontalEntries++;
			i--;
		}
		
		i=j;
		while(i+1 < ROW_SIZE && board[placedRow][i+1] == player) {
			horizontalEntries++;
			i++;
		}
		
		if(horizontalEntries +1 == 4) {
			return true;
		}
		
		return false;
	}

	private boolean isVerticalWin(int player, int placedRow, int j) {
		int verticalEntries = 0;
		int i=placedRow;
		while(i-1 >=0 && board[i-1][j] == player) {
			verticalEntries++;
			i--;
		}
		
		i=placedRow;
		while(i+1 < ROW_SIZE && board[i+1][j] == player) {
			verticalEntries++;
			i++;
		}
		
		if(verticalEntries +1 == 4) {
			return true;
		}
		
		return false;
	}
}

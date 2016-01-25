package controllers;

import java.util.Random;

import edu.princeton.cs.introcs.StdDraw;

public class TicTacToe {
	

	// Allocate identifiers to represent game state
	//
	// Using an array of int so that summing along a row, a column or a
	// diagonal is a easy test for a win
	final int EMPTY = 0;
	final int X_SHAPE = 1;
	final int O_SHAPE = -1;
	final boolean DEBUG = true;
	
	private int[][] board = new int[3][3];;
	private int[] totals = new int[8];
	private int intMoveNo, intCol, intRow;	
	
	
	public TicTacToe(){
				
		// Setup graphics and draw empty board
		StdDraw.clear();
		StdDraw.setPenRadius(0.04);							// draw thicker lines
		StdDraw.line(0, 0.33, 1, 0.33);
		StdDraw.line(0, 0.66, 1, 0.66);
		StdDraw.line(0.33, 0, 0.33, 1.0);
		StdDraw.line(0.66, 0, 0.66, 1.0);
			
		intMoveNo = 0;				
		
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[i].length; j++){
				board[i][j] = EMPTY;
			}			
		}
			
		
	}
	
	public void resetGame(){
		
		StdDraw.clear();
		StdDraw.setPenRadius(0.04);	
		StdDraw.line(0, 0.33, 1, 0.33);
		StdDraw.line(0, 0.66, 1, 0.66);
		StdDraw.line(0.33, 0, 0.33, 1.0);
		StdDraw.line(0.66, 0, 0.66, 1.0);
			
		intMoveNo = 0;				
		
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[i].length; j++){
				board[i][j] = EMPTY;
			}			
		}
		
		for(int i = 0; i < totals.length; i++){
			totals[i] = 0;
		}
		
	}
	
	/**
	 * Checks if it is the computers or the humans turn to make a move
	 * @return true if it is the computers turn
	 */
	public boolean isCompTurn(){
		
		if(intMoveNo % 2 == 0){
			if (DEBUG) System.out.println("\tComputer move ..." + (intMoveNo));
			return true;
		}
		else{
			if (DEBUG) System.out.println("\tHuman move ..." + (intMoveNo));
			return false;
		}		
		
	}
	
	/**
	 * Will perform a move based on the following conditions in order:
	 * 	1: A winning move
	 *  2: A blocking move
	 *  3: A move that will set up a win on the next turn
	 *  4: A random move
	 */
	public void compTurn(){
		
		boolean blnCompHasMoved = false;
		getTotals();
		
		
		// Checks if there is a winning move possible and makes it
		for(int i = 0; i < totals.length; i++){
			while(!blnCompHasMoved && (totals[i] == -2)){
				
				if (DEBUG) System.out.println("\tCase " + i + " for win");
				
				switch (i){

					case 0:
						if(board[0][0] == EMPTY){intCol = 0; intRow = 0;}
						else if(board[1][0] == EMPTY){intCol = 1; intRow = 0;}
						else if(board[2][0] == EMPTY){intCol = 2; intRow = 0;}
					
						break;
					case 1:
						if(board[0][1] == EMPTY){intCol = 0; intRow = 1;}
						else if(board[1][1] == EMPTY){intCol = 1; intRow = 1;}
						else if(board[2][1] == EMPTY){intCol = 2; intRow = 1;}
					
						break;
					case 2:
						if(board[0][2] == EMPTY){intCol = 0; intRow = 2;}
						else if(board[1][2] == EMPTY){intCol = 1; intRow = 2;}
						else if(board[2][2] == EMPTY){intCol = 2; intRow = 2;}
						
						break;
					case 3:
						if(board[0][0] == EMPTY){intCol = 0; intRow = 0;}
						else if(board[0][1] == EMPTY){intCol = 0; intRow = 1;}
						else if(board[0][2] == EMPTY){intCol = 0; intRow = 2;}
						
						break;
					case 4:
						if(board[1][0] == EMPTY){intCol = 1; intRow = 0;}
						else if(board[1][1] == EMPTY){intCol = 1; intRow = 1;}
						else if(board[1][2] == EMPTY){intCol = 1; intRow = 2;}
						
						break;
					case 5:
						if(board[2][0] == EMPTY){intCol = 2; intRow = 0;}
						else if(board[2][1] == EMPTY){intCol = 2; intRow = 1;}
						else if(board[2][2] == EMPTY){intCol = 2; intRow = 2;}
						
						break;
					case 6:
						if(board[0][0] == EMPTY){intCol = 0; intRow = 0;}
						else if(board[1][1] == EMPTY){intCol = 1; intRow = 1;}
						else if(board[2][2] == EMPTY){intCol = 2; intRow = 2;}
						
						break;
					case 7:
						if(board[2][0] == EMPTY){intCol = 2; intRow = 0;}
						else if(board[1][1] == EMPTY){intCol = 1; intRow = 1;}
						else if(board[0][2] == EMPTY){intCol = 0; intRow = 2;}
						
						break;
					
					default:
						break;					

				}
				if (DEBUG) System.out.println("\tComputer turn at " + intCol + ", " + intRow);
				drawMove(intCol, intRow, O_SHAPE);
				blnCompHasMoved = true;
			}
		}
		
		//Checks if the opponent has a winning move and blocks it
		for(int i = 0; i < totals.length; i++){
			while(!blnCompHasMoved && (totals[i] == 2)){
				
				if (DEBUG) System.out.println("\tCase " + i + " for block");
				
				switch (i){

					case 0:
						if(board[0][0] == EMPTY){intCol = 0; intRow = 0;}
						else if(board[1][0] == EMPTY){intCol = 1; intRow = 0;}
						else if(board[2][0] == EMPTY){intCol = 2; intRow = 0;}
					
						break;
					case 1:
						if(board[0][1] == EMPTY){intCol = 0; intRow = 1;}
						else if(board[1][1] == EMPTY){intCol = 1; intRow = 1;}
						else if(board[2][1] == EMPTY){intCol = 2; intRow = 1;}
					
						break;
					case 2:
						if(board[0][2] == EMPTY){intCol = 0; intRow = 2;}
						else if(board[1][2] == EMPTY){intCol = 1; intRow = 2;}
						else if(board[2][2] == EMPTY){intCol = 2; intRow = 2;}
						
						break;
					case 3:
						if(board[0][0] == EMPTY){intCol = 0; intRow = 0;}
						else if(board[0][1] == EMPTY){intCol = 0; intRow = 1;}
						else if(board[0][2] == EMPTY){intCol = 0; intRow = 2;}
						
						break;
					case 4:
						if(board[1][0] == EMPTY){intCol = 1; intRow = 0;}
						else if(board[1][1] == EMPTY){intCol = 1; intRow = 1;}
						else if(board[1][2] == EMPTY){intCol = 1; intRow = 2;}
						
						break;
					case 5:
						if(board[2][0] == EMPTY){intCol = 2; intRow = 0;}
						else if(board[2][1] == EMPTY){intCol = 2; intRow = 1;}
						else if(board[2][2] == EMPTY){intCol = 2; intRow = 2;}
						
						break;
					case 6:
						if(board[0][0] == EMPTY){intCol = 0; intRow = 0;}
						else if(board[1][1] == EMPTY){intCol = 1; intRow = 1;}
						else if(board[2][2] == EMPTY){intCol = 2; intRow = 2;}
						
						break;
					case 7:
						if(board[2][0] == EMPTY){intCol = 2; intRow = 0;}
						else if(board[1][1] == EMPTY){intCol = 1; intRow = 1;}
						else if(board[0][2] == EMPTY){intCol = 0; intRow = 2;}
						
						break;
					
					default:
						break;					

				}
				if (DEBUG) System.out.println("\tComputer turn at " + intCol + ", " + intRow);
				drawMove(intCol, intRow, O_SHAPE);
				blnCompHasMoved = true;
			}
		}
		
		// Checks if there is a move that will result in two wining conditions on the move after.
		//  This is done by looking at each empty cell individually and seeing if there are only o's
		//  in the column and row that it is in. Each corner cell will also have to check one of the 
		//  diagonals. The center cell will have to check the row, column and both diagonals.
		//
		if(!blnCompHasMoved){
			
			// Two else if's for a corner cell
			if(board[0][0] == EMPTY && (
					(board[1][1] + board[2][2]) == O_SHAPE) && (
							((board[0][1] + board[0][2]) == O_SHAPE)||
							((board[1][0] + board[2][0]) == O_SHAPE)
							))
			{
				drawMove(0, 0, O_SHAPE);
				blnCompHasMoved = true;
			}
			else if(board[0][0] == EMPTY &&
					((board[0][1] + board[0][2]) == O_SHAPE) &&
					((board[1][0] + board[2][0]) == O_SHAPE)){
				
				drawMove(0, 0, O_SHAPE);
				blnCompHasMoved = true;
				
			}
			// Single else if for center edge cell
			else if(board[0][1] == EMPTY &&
					((board[0][0] + board[0][2]) == O_SHAPE) &&
					((board[1][1] + board[2][1]) == O_SHAPE)){
				
				drawMove(0, 1, O_SHAPE);
				blnCompHasMoved = true;
				
			}
			// Two else if's for a corner cell
			else if(board[0][2] == EMPTY && (
					(board[1][1] + board[2][0]) == O_SHAPE) && (
							((board[0][0] + board[0][1]) == O_SHAPE)||
							((board[1][2] + board[2][2]) == O_SHAPE)
							))
			{
				drawMove(0, 2, O_SHAPE);
				blnCompHasMoved = true;
			}
			else if(board[0][2] == EMPTY &&
					((board[0][0] + board[0][1]) == O_SHAPE) &&
					((board[1][2] + board[2][2]) == O_SHAPE)){
				
				drawMove(0, 2, O_SHAPE);
				blnCompHasMoved = true;
				
			}
			// Single else if for center edge cell
			else if(board[1][0] == EMPTY &&
					((board[1][1] + board[1][2]) == O_SHAPE) &&
					((board[0][0] + board[2][0]) == O_SHAPE)){
				
				drawMove(1, 0, O_SHAPE);
				blnCompHasMoved = true;
				
			}
			// Single else if for center edge cell
			else if(board[1][2] == EMPTY &&
					((board[1][0] + board[1][1]) == O_SHAPE) &&
					((board[0][2] + board[2][2]) == O_SHAPE)){
				
				drawMove(1, 2, O_SHAPE);
				blnCompHasMoved = true;
				
			}
			// Two else if's for a corner cell
			else if(board[2][0] == EMPTY && (
					(board[1][1] + board[0][2]) == O_SHAPE) && (
							((board[2][1] + board[2][2]) == O_SHAPE)||
							((board[0][0] + board[1][0]) == O_SHAPE)
							))
			{
				drawMove(2, 0, O_SHAPE);
				blnCompHasMoved = true;
			}
			else if(board[2][0] == EMPTY &&
					((board[2][1] + board[2][2]) == O_SHAPE) &&
					((board[0][0] + board[1][0]) == O_SHAPE)){
				
				drawMove(2, 0, O_SHAPE);
				blnCompHasMoved = true;
				
			}
			// Single else if for center edge cell
			else if(board[2][1] == EMPTY &&
					((board[2][0] + board[2][2]) == O_SHAPE) &&
					((board[0][1] + board[2][1]) == O_SHAPE)){
				
				drawMove(2, 1, O_SHAPE);
				blnCompHasMoved = true;
				
			}
			// Two else if's for a corner
			else if(board[2][2] == EMPTY && (
					(board[1][1] + board[0][0]) == O_SHAPE) && (
							((board[2][0] + board[2][1]) == O_SHAPE)||
							((board[0][2] + board[1][2]) == O_SHAPE)
							))
			{
				drawMove(2, 2, O_SHAPE);
				blnCompHasMoved = true;
			}
			else if(board[2][2] == EMPTY &&
					((board[2][0] + board[2][1]) == O_SHAPE) &&
					((board[0][2] + board[1][2]) == O_SHAPE)){
				
				drawMove(2, 2, O_SHAPE);
				blnCompHasMoved = true;
				
			}
			// Three else if's for the center cell
			else if(board[1][1] == EMPTY && (
					(board[1][0] + board[1][2]) == O_SHAPE) && (
							((board[0][0] + board[2][2]) == O_SHAPE)||
							((board[0][1] + board[2][1]) == O_SHAPE)||
							((board[2][0] + board[0][2]) == O_SHAPE)
							))
			{
				drawMove(1, 1, O_SHAPE);
				blnCompHasMoved = true;
			}
			else if(board[1][1] == EMPTY && (
					(board[0][1] + board[2][1]) == O_SHAPE) && (
							((board[0][0] + board[2][2]) == O_SHAPE)||
							((board[2][0] + board[0][2]) == O_SHAPE)
							))
			{
				drawMove(1, 1, O_SHAPE);
				blnCompHasMoved = true;
			}
			else if(board[1][1] == EMPTY && (
					(board[0][2] + board[2][0]) == O_SHAPE) && (
							(board[0][0] + board[2][2]) == O_SHAPE)							
							)
			{
				drawMove(1, 1, O_SHAPE);
				blnCompHasMoved = true;
			}
		}
		
		// If there is no winning move, no blocking move and no move for a winning condition a
		// random cell is chosen
		if(!blnCompHasMoved){
			
			Random ranNo =  new Random();
			do{
				intCol = ranNo.nextInt(3);
				intRow = ranNo.nextInt(3);
				
			}while(board[intCol][intRow] != EMPTY);
			drawMove(intCol, intRow, O_SHAPE);
		}
		
			
		
	}
	
	/**
	 * If it is the humans turn it will listen for a mouse press, confirm that that cell is empty
	 * and then call the drawMove method for the chosen cell
	 */
	public void humanTurn(){
		boolean blnMousePressed = false;

        do {        	

            if (StdDraw.mousePressed()) {

                intCol = (int) (StdDraw.mouseX() * 3);	

                intRow = (int) (StdDraw.mouseY() * 3);

                if(board[intCol][intRow] == EMPTY){
                	
                	if (DEBUG) System.out.println("\tHuman turn at " + intCol + ", " + intRow);

                    drawMove(intCol, intRow, X_SHAPE);
                	blnMousePressed = true;
                }
                

            }

        }while(!blnMousePressed);
               
		
	}
	
	/**
	 * Takes in three parameters that will determine the cell location as well as the symbol to be drawn
	 * @param intCol
	 * @param intRow
	 * @param intShape
	 */
	public void drawMove(int intCol, int intRow, int intShape){
			
		if(intShape == 1){
			StdDraw.text((0 + (0.33*intCol) + 0.16), (0 + (0.33*intRow)) + 0.16, "x");
		}
		else if(intShape == -1){
			StdDraw.text((0 + (0.33*intCol) + 0.16), (0 + (0.33*intRow)) + 0.16, "o");
		}
			
		board[intCol][intRow] = intShape;
		intMoveNo++;
					
	}
	
	/**
	 * Will add up all the possible combinations of winning lines
	 */
	public void getTotals(){
		totals[0] = board[0][0] + board[1][0] + board[2][0];
		totals[1] = board[0][1] + board[1][1] + board[2][1];
		totals[2] = board[0][2] + board[1][2] + board[2][2];
		totals[3] = board[0][0] + board[0][1] + board[0][2];
		totals[4] = board[1][0] + board[1][1] + board[1][2];
		totals[5] = board[2][0] + board[2][1] + board[2][2];
		totals[6] = board[0][0] + board[1][1] + board[2][2];
		totals[7] = board[0][2] + board[1][1] + board[2][0];
		
	}
	
	/**
	 * Calls the getTotals method and returns true if either the computer or human player has 
	 * played a winning move
	 * @return true if any total is equivalent to a winning move
	 */
	public boolean checkWinner(){
		getTotals();
		for(int i: totals){
			if(i == -3){
				if (DEBUG) System.out.println("\tComputer Win!");
				return true;
			}
			else if(i == 3){
				if (DEBUG) System.out.println("\tHuman Win!");
				return true;
			}
		}
		
		return false;
		
	}

}

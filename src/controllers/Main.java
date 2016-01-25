package controllers;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main {
	
	
	public static void main(String[] args) {
		
		boolean blnPlayGame = true;
		
		do{
			TicTacToe game = new TicTacToe();
			
			while(!game.checkWinner()){
				if(game.isCompTurn()){
					game.compTurn();
					if(game.checkWinner()){
						JOptionPane.showMessageDialog(new JFrame(), "Game Over");
					}
				}
				else{
					game.humanTurn();
					if(game.checkWinner()){
						JOptionPane.showMessageDialog(new JFrame(), "Congratulations");
					}
				}
			}
			
			int reply = JOptionPane.showConfirmDialog(null, "Would you like to play again?",
					"New Game?", JOptionPane.YES_NO_OPTION);
	        if (reply == JOptionPane.NO_OPTION) {
	          JOptionPane.showMessageDialog(null, "Goodbye");
	          blnPlayGame = false;
	        }
	        else{
	        	//game.resetGame();
	        	JOptionPane.showMessageDialog(null, "I'm sorry this function is currently under "
	        			+ "maintenance. \nGoodbye");
		          blnPlayGame = false;
	        }
	        
			
		}while(blnPlayGame);
			
		
	}

}

package W19Project3GIVETOSTUDENTS;

import chess.IChessPiece;
import chess.Move;
import chess.Player;

public class Knight extends ChessPiece {

	public Knight(Player player) {
		super(player);
	}

	public String type() {

	    return "W19Project3GIVETOSTUDENTS.Knight";
	}
	/***
     * isValidMove: Takes user input and checks if your move is possible in a game of chess, see comment below for algorithim
     * documentation
     *
     * @param move used to compare start and end values for row and column in board object
     * @param board
     * @return
     */
	public boolean isValidMove(Move move, IChessPiece[][] board){

		boolean valid = true;

	/**
         * Knight Move Algorithim: checks changes in the original point being added or subtracted by 2 for the row array and col
	 * array. From each option of that it checks two options that being a change in the original point in row or col by 1.
	 * If all cases are read then return false.
         */
	
	//Case 1: starting move going down 2 units
        if(move.fromRow + 2 == move.toRow) {
            if(move.fromColumn + 1 == move.toColumn) {
                return true;
            } else if(move.fromColumn - 1 == move.toColumn) {
                return true;
            }
	//Case 2: starting move going up 2 units
        }else if(move.fromRow - 2 == move.toRow) {
            if(move.fromColumn - 1 == move.toColumn) {
                return true;
            } else if (move.fromColumn + 1 == move.toColumn) {
                return true;
            }
	//Case 3: starting move going left 2 units
        }else if(move.fromColumn - 2 == move.toColumn) {
            if(move.fromRow - 1 == move.toRow) {
                return true;
            } else if (move.fromRow + 1 == move.toRow) {
                return true;
            }
	//Case 4: starting move going right 2 units
        }else if(move.fromColumn + 2 == move.toColumn) {
            if(move.fromRow - 1 == move.toRow) {
                return true;
            } else if (move.fromRow + 1 == move.toRow) {
                return true;
            }
        }
	//Any other move is wrong
        return false;
		
	}

}

package W19Project3GIVETOSTUDENTS;

import W19Project3GIVETOSTUDENTS.ChessPiece;
import chess.IChessPiece;
import chess.Move;
import chess.Player;

public class Bishop extends ChessPiece {

	public Bishop(Player player) {
		super(player);
	}

	public String type() {
		return "W19Project3GIVETOSTUDENTS.Bishop";
	}

	/***
	 * for loop that returns true if the bishop moves diagonally and returns false to anything else
	 * @param move used to validate moves
	 * @param board
	 * @return true or false
	 */
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		//if branch that checks for valid moves
		//You can go up to 7 tiles diagonally
		for(int i = 1; i < 8; i++) {
			//+i, +i validation
			if(move.fromRow + i == move.toRow && move.fromColumn + i == move.toColumn){
				return true;
			}
			//-i, -i validation
			if(move.fromRow - i == move.toRow && move.fromColumn - i == move.toColumn) {
				return true;
			}
			//+i, -i validation
			if(move.fromRow + i == move.toRow && move.fromColumn - i == move.toColumn) {
				return true;
			}
			//-i, +i validation
			if(move.fromRow - i == move.toRow && move.fromColumn + i == move.toColumn) {
				return true;
			}
		}
		//anything else is not valid
		return false;
	}
}

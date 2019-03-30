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
	 * A If branch that checks for increment/decrement of fromRow and fromCol in ways that are diagonal
	 * and also checks if you are hopping over black/white pieces illegaly (see line 35)
	 * @param move used to validate moves
	 * @param board
	 * @return true or false
	 */
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		//If branch that deals with allowed moves of the bishop
		//Diagonal: -i, -i (Up-Left)
		int diffCol;
		if(move.fromRow > move.toRow && move.fromColumn > move.toColumn) {
			diffCol = move.fromColumn - 1;
			System.out.println("DEBUG: Up-Left Diagonal");
			//Loop
			for(int start = move.fromRow - 1; start >= move.toRow; start--) {
				//if your location isnt null and you havent reached the end point, move is invalid
				if(board[start][diffCol] != null && start != move.toRow && diffCol != move.toColumn) {
					return false;
				}
				if(start == move.toRow && diffCol == move.toColumn) {
					return true;
				}
				diffCol--;
			}
			return false;
		//Diagonal: +i, +i (Down-Right)
		}else if(move.fromRow < move.toRow && move.fromColumn < move.toColumn) {
			diffCol = move.fromColumn + 1;
			System.out.println("DEBUG: Down-Right");
			for(int start = move.fromRow + 1; start <= move.toRow; start++) {
				if(board[start][diffCol] != null && start != move.toRow && diffCol != move.toColumn) {
					return false;
				}
				if(start == move.toRow && diffCol == move.toColumn) {
					return true;
				}
				diffCol++;
			}
			return false;
		//Diagonal: -i, +i (Up-Right)
		}else if(move.fromRow > move.toRow && move.fromColumn < move.toColumn) {
			diffCol = move.fromColumn + 1;
			System.out.println("DEBUG: Up-Right");
			for(int start = move.fromRow - 1; start >= move.toRow; start--) {
				if(board[start][diffCol] != null && start != move.toRow && diffCol != move.toColumn) {
					return false;
				}
				if(start == move.toRow && diffCol == move.toColumn) {
					return true;
				}
				diffCol++;
			}
			return false;
		//Diagonal: +i, -i (Down-Left)
		}else if(move.fromRow < move.toRow && move.fromColumn > move.toColumn) {
			diffCol = move.fromColumn - 1;
			System.out.println("DEBUG: Down-Left");
			for(int start = move.fromRow + 1; start <= move.toRow; start++) {
				if(board[start][diffCol] != null && start != move.toRow && diffCol != move.toColumn) {
					return false;
				}
				if(start == move.toRow && diffCol == move.toColumn) {
					return true;
				}

				diffCol--;
			}
			return false;
		}


		return false;
	}
}

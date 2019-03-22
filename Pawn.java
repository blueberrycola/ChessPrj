package W19Project3GIVETOSTUDENTS;

import chess.IChessPiece;
import chess.Move;
import chess.Player;

public class Pawn extends ChessPiece {

	public Pawn(Player player) {
		super(player);
	}

	public String type() {
		return "W19Project3GIVETOSTUDENTS.Pawn";
	}

	// determines if the move is valid for a pawn piece
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		boolean valid = true;
		//Move up one
		//model.pieceAt(r, c).player() == Player.WHITE
		if(player() == Player.WHITE) {
		    if(move.fromRow - 1 == move.toRow && move.fromColumn == move.toColumn) {
				return true;
			}
			if(move.fromRow - 1 == move.toRow) {
			    if(move.fromColumn + 1 == move.toColumn || move.fromColumn - 1 == move.toColumn) {

                }
            }
			//NOTE: how would I go about checking if the piece in the from destination is different than the to destination
		} else {
			if(move.fromRow + 1 == move.toRow && move.fromColumn == move.toColumn) {
				System.out.println(move.toString());
				return true;
			}
		}
		return false;
	}
}

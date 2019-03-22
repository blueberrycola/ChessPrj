package W19Project3GIVETOSTUDENTS;

import chess.IChessPiece;
import chess.Move;
import chess.Player;

public class Rook extends ChessPiece {

	public Rook(Player player) {
		
		super(player);
		
	}

	public String type() {
		return "W19Project3GIVETOSTUDENTS.Rook";
	}
	
	// determines if the move is valid for a rook piece
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		
		boolean valid = true;
		//Loop that checks
        for(int i = 0; i < 8; i++) {
			// Allowed directions: Right, Up, Left, Down
        	if(move.fromRow + i == move.toRow && move.fromColumn == move.toColumn) {
				System.out.println(move.toString());
        		return valid;
			}

			if(move.fromRow == move.toRow && move.fromColumn + i == move.toColumn) {
				System.out.println(move.toString());
				return valid;
			}
			if(move.fromRow - i == move.toRow && move.fromColumn == move.toColumn) {
				return valid;
			}
			if(move.fromRow == move.toRow && move.fromColumn - i == move.toColumn) {
				System.out.println(move.toString());
				return valid;
			}
		}

        return false;
		
	}
	
}

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

		//Valid move check for vertical movement, plus or minus
        if(move.fromRow == move.toRow && move.fromColumn == move.toColumn) {
            return false;
        }
        if(move.fromRow < move.toRow && move.fromColumn == move.toColumn) {
            for(int startY = move.fromRow; startY < move.toRow; startY++) {

                if(startY == move.toRow - 1) {
                    return true;
                }

                if(board[move.fromRow][move.fromColumn].player() == board[startY][move.fromColumn].player()) {
                    return false;
                }

            }
            return true;
        } else if(move.fromRow > move.toRow && move.fromColumn == move.toColumn) {
            for(int startY = move.fromRow; startY > move.toRow; --startY) {

            }
            return true;
        }
        //Valid move check for horizontal movement, plus or minus
        else if(move.fromRow == move.toRow && move.fromColumn > move.toColumn) {
            return true;
        } else if(move.fromRow == move.toRow && move.fromColumn < move.toColumn) {
            return true;
		}



        return false;
		
	}
	
}

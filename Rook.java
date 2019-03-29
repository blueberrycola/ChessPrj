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

        if((move.fromRow < move.toRow) && (move.fromColumn == move.toColumn)) {
            for(int start = move.fromRow + 1; start <= move.toRow; start++) {
                if(board[start][move.fromColumn] != null) {
                    return false;
                }
            }
            return true;
        } else if(move.fromRow > move.toRow && move.fromColumn == move.toColumn) {
            for(int start = move.fromRow - 1; start >= move.toRow; start--) {
                if(board[start][move.fromColumn] != null) {
                    return false;
                }
            }
            return true;
        }
        //Valid move check for horizontal movement, plus or minus
        else if(move.fromRow == move.toRow && move.fromColumn < move.toColumn) {
            for(int start = move.fromColumn + 1; start <= move.toColumn; start++) {
                if(board[move.fromRow][start] != null) {
                    return false;
                }
            }
            return true;
        } else if(move.fromRow == move.toRow && move.fromColumn > move.toColumn) {
            for(int start = move.fromColumn - 1; start >= move.toColumn; start--) {
                if(board[move.fromRow][start] != null) {
                    return false;
                }
            }
            return true;
		}

        return false;
		
	}
	
}

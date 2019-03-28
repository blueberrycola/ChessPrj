package W19Project3GIVETOSTUDENTS;

import chess.IChessPiece;
import chess.Move;
import chess.Player;

public class Pawn extends ChessPiece {
    private boolean firstTurn = true;
	public Pawn(Player player) {

	    super(player);
	}

	public String type() {
		return "W19Project3GIVETOSTUDENTS.Pawn";
	}

    /***
     * The following method checks what moves are possible for a pawn chess piece. The firstTurn boolean value checks
     * to see if the given pawn object can move up to 2 spaces or just one. Attacking other chess pieces has also been
     * added by making sure you are not 'attacking' the same player type in the given chess piece. If statements have
     * been used to differentiate white moves from black moves since pawns do not have a universal moveset like other
     * chess pieces
     * @param move
     * @param board
     * @return True or False,
     */
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		boolean valid = true;
		//Move up one
		//model.pieceAt(r, c).player() == Player.WHITE
		if(player() == Player.WHITE) {
		    //First turn check goes forward 2 units but only once
		    if(move.fromRow - 2 == move.toRow && move.fromColumn == move.toColumn && firstTurn) {
		        firstTurn = false;
		        return true;
            }

		    //Move up by one unit
		    if(move.fromRow - 1 == move.toRow && move.fromColumn == move.toColumn) {
				if(firstTurn) {
				    firstTurn = false;
                }
		        return true;
			}
			//Move diagonally: ATTACK ONLY
			if(move.fromRow - 1 == move.toRow) {
			    if(move.fromColumn + 1 == move.toColumn || move.fromColumn - 1 == move.toColumn) {
                    if(board[move.fromRow][move.fromColumn].player() == Player.WHITE && board[move.toRow][move.toColumn].player() == Player.BLACK) {
                        firstTurn = false;
                        return true;
                    }

                }
            }
		}
		if(player() == Player.BLACK) {
			//FIXME: Add first turn buffed move
		    //First turn check
            if(move.fromRow + 2 == move.toRow && move.fromColumn == move.toColumn && firstTurn) {
                firstTurn = false;
                return true;
            }
            //Move up by one unit
		    if(move.fromRow + 1 == move.toRow && move.fromColumn == move.toColumn) {
				firstTurn = false;
				return true;
			}
			//Move diagonally: ATTACK ONLY
            if(move.fromRow + 1 == move.toRow) {
                if(move.fromColumn + 1 == move.toColumn || move.fromColumn - 1 == move.toColumn) {
                    if(board[move.fromRow][move.fromColumn].player() == Player.BLACK && board[move.toRow][move.toColumn].player() == Player.WHITE) {
                        firstTurn = false;
                        return true;
                    }
                }
            }
		}
		return false;
	}
}

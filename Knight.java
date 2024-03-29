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

    /********************************************************************************
     * Responsible for showing all moves possible for the knight chess piece
     * @param move  a {@link W18project3.Move} object describing the move to be made.
     * @param board the {@link W18project3.IChessBoard} in which this piece resides.
     * @return True or False, Only true if the move is allowed by the if statements
     *******************************************************************************/
	public boolean isValidMove(Move move, IChessPiece[][] board){

		boolean valid = true;

		//Knight move algorithm:
        //Any case for L moves that move in the y axis: left or right col move

        if(move.fromRow + 2 == move.toRow) {
            if(move.fromColumn + 1 == move.toColumn) {
                return true;
            } else if(move.fromColumn - 1 == move.toColumn) {
                return true;
            }
        }else if(move.fromRow - 2 == move.toRow) {
            if(move.fromColumn - 1 == move.toColumn) {
                return true;
            } else if (move.fromColumn + 1 == move.toColumn) {
                return true;
            }
        }else if(move.fromColumn - 2 == move.toColumn) {
            if(move.fromRow - 1 == move.toRow) {
                return true;
            } else if (move.fromRow + 1 == move.toRow) {
                return true;
            }
        }else if(move.fromColumn + 2 == move.toColumn) {
            if(move.fromRow - 1 == move.toRow) {
                return true;
            } else if (move.fromRow + 1 == move.toRow) {
                return true;
            }
        }
        //anything else is false
        return false;
		
	}

}

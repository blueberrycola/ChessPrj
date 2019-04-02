package W19Project3GIVETOSTUDENTS;

import chess.IChessPiece;
import chess.Move;
import chess.Player;

public class Queen extends ChessPiece {

	public Queen(Player player) {
		super(player);

	}

	public String type() {
		return "W19Project3GIVETOSTUDENTS.Queen";
		
	}
	/********************************************************************************
	 * Responsible for showing all moves possible for the queen chess piece by treating
	 * it as a rook and a bishop
	 * @param move  a {@link W18project3.Move} object describing the move to be made.
	 * @param board the {@link W18project3.IChessBoard} in which this piece resides.
	 * @return True or False, Only true if the move is allowed by the if statements
	 *******************************************************************************/
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		Bishop move1 = new Bishop(board[move.fromRow][move.fromColumn].player());
		Rook move2 = new Rook(board[move.fromRow][move.fromColumn].player());
		return (move1.isValidMove(move, board) || move2.isValidMove(move, board));
	}
}

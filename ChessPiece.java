package W19Project3GIVETOSTUDENTS;

import chess.IChessPiece;
import chess.Move;
import chess.Player;




public abstract class ChessPiece implements IChessPiece {

	private Player owner;


	protected ChessPiece(Player player) {
		this.owner = player;


	}

	public abstract String type();

	public Player player() {
		return owner;
	}

    /***
     * ChessPiece(): Responsible for telling whose turn is it
     * @param move  a {@link W18project3.Move} object describing the move to be made.
     * @param board the {@link W18project3.IChessBoard} in which this piece resides.
     * @return
     */
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		boolean valid = false;


		return false;
	}
}

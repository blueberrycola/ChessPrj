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


	public boolean isValidMove(Move move, IChessPiece[][] board) {
		boolean valid = false;


		return valid;
	}
}

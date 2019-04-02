package W19Project3GIVETOSTUDENTS;

import chess.IChessPiece;
import chess.Move;
import chess.Player;




public abstract class ChessPiece implements IChessPiece {

	private Player owner;

	/***********************************************************************************
	 * Constructor responcible for establishing who owns the piece using a player object
	 * @param player
	 **********************************************************************************/
	protected ChessPiece(Player player) {
		this.owner = player;


	}

	public abstract String type();

	/*********************************************
	 * Returns the player of said piece
	 * @return
	 ********************************************/
	public Player player() {
		return owner;
	}

    /***************************************************************************************
     * Never actually used
     * @param move  a {@link W18project3.Move} object describing the move to be made.
     * @param board the {@link W18project3.IChessBoard} in which this piece resides.
     * @return
	 **************************************************************************************/
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		boolean valid = false;


		return false;
	}
}

package W19Project3GIVETOSTUDENTS;

import chess.IChessPiece;
import chess.Move;
import chess.Player;

public class King extends ChessPiece {

	public King(Player player) {
		super(player);
	}

	public String type() {
		return "W19Project3GIVETOSTUDENTS.King";
	}

    /*******************************************************************************************************************
     * Checks is valid move by checking if you move up or down a row, or dont move row, if any of the if statements are
     * accepted it then gets checked by threeColumnCheck
     * @param move used to compare numbers on where you lie on the board
     * @param board used to show numbers for move
     * @return
     ******************************************************************************************************************/
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		if(move.fromRow + 1 == move.toRow) {
            return threeColumnCheck(move, board);
        }
        else if(move.fromRow == move.toRow) {
            return threeColumnCheck(move, board);
        }
        else if (move.fromRow - 1 == move.toRow) {
            return threeColumnCheck(move, board);
        }

        return false;
	}

    /*************************************************************************************************************
     * Checks if isValidMove can return true or false judged by comparing toColumn added with elements from
     * the following sequence {-1, 0, -1}
     * @return check (will return false unless the other from tile is +1, 0, or -1 when compared to the 'toColumn'
     ************************************************************************************************************/
	public boolean threeColumnCheck(Move move, IChessPiece[][] board) {
	    boolean check = false;
	    if(move.fromColumn + 1 == move.toColumn) {
	        check = true;
        }else if(move.fromColumn == move.toColumn) {
	        check = true;
        }else if(move.fromColumn - 1 == move.toColumn) {
	        check = true;
        }
        return check;
    }
}

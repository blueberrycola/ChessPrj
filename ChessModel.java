package W19Project3GIVETOSTUDENTS;

import chess.*;

import javax.swing.*;
import java.util.ArrayList;

public class ChessModel implements IChessModel {
	private IChessPiece[][] board;
	private Player player;

	//Arrays Responcible for undoButton
	private ArrayList<Integer> fromMoveRow, fromMoveCol, toMoveRow, toMoveCol;
	private ArrayList<String> pieceMemory;

	// declare other instance variables as needed

	public ChessModel() {



		player = Player.WHITE;
		setNextPlayer(player);
		board = new IChessPiece[8][8];
		player = Player.WHITE;

		fromMoveRow = new ArrayList<>();
		fromMoveCol = new ArrayList<>();
		toMoveRow = new ArrayList<>();
		toMoveCol = new ArrayList<>();
		pieceMemory = new ArrayList<>();

		board[7][0] = new Rook(Player.WHITE);
		board[7][1] = new Knight(Player.WHITE);
		board[7][2] = new Bishop(Player.WHITE);
		board[7][3] = new Queen(Player.WHITE);
		board[7][4] = new King(Player.WHITE);
		board[7][5] = new Bishop(Player.WHITE);
		board[7][6] = new Knight(Player.WHITE);
		board[7][7] = new Rook(Player.WHITE);

		board[0][0] = new Rook(Player.BLACK);
		board[0][1] = new Knight(Player.BLACK);
		board[0][2] = new Bishop(Player.BLACK);
		board[0][3] = new Queen(Player.BLACK);
		board[0][4] = new King(Player.BLACK);
		board[0][5] = new Bishop(Player.BLACK);
		board[0][6] = new Knight(Player.BLACK);
		board[0][7] = new Rook(Player.BLACK);

		//Loop to place pawns cuz lazy
		for(int i = 0; i < 16; i++) {
			if(i < 8) {
				//White pawns
				board[6][i] = new Pawn(Player.WHITE);
				//Black pawns
			} else {
				board[1][i - 8] = new Pawn(Player.BLACK);
			}
		}


	}

	public void undoButton() {
		System.out.println(fromMoveCol.size());
		if(fromMoveCol.size() == 0) {
			JOptionPane.showMessageDialog(null,"There are no moves to reverse");
		} else {


			int undoVal = fromMoveRow.size() - 1;

			if(undoVal % 2 == 0) {
				setNextPlayer(Player.WHITE);
			}else {
				setNextPlayer(Player.BLACK);
			}

			System.out.println(fromMoveRow.get(undoVal) + " " + fromMoveCol.get(undoVal));
			System.out.println(toMoveRow.get(undoVal) + " " + toMoveCol.get(undoVal));
			//Reverses the move you just placed and removes the stored coordinates from the ArrayLists
			IChessPiece temp = board[toMoveRow.get(undoVal)][toMoveCol.get(undoVal)];

			if(pieceMemory.get(undoVal).contains("W19Project3GIVETOSTUDENTS.Pawn")) {
				board[fromMoveRow.get(undoVal)][fromMoveCol.get(undoVal)] = new Pawn(currentPlayer());
				board[toMoveRow.get(undoVal)][toMoveCol.get(undoVal)] = null;
			}else {
				setPiece(toMoveRow.get(undoVal), toMoveCol.get(undoVal), null);
				setPiece(fromMoveRow.get(undoVal), fromMoveCol.get(undoVal) , temp);
			}
			toMoveRow.remove(undoVal);
			fromMoveRow.remove(undoVal);
			toMoveCol.remove(undoVal);
			fromMoveCol.remove(undoVal);
			pieceMemory.remove(undoVal);


		}

	}

	public boolean isComplete() {
		//FIXME: isComplete() return false until you are at step 10
		boolean valid = false;
		return valid;
	}



	//isValidMove(): used for basic chessboard rules
	public boolean isValidMove(Move move) {

		currentPlayer();
		System.out.println("Player is " + currentPlayer());



		switch(player) {
			case WHITE:

				if(board[move.fromRow][move.fromColumn].player() == Player.BLACK){
					System.out.println("Can't move Black yet");
					return false;
				}


				if (move.fromRow == move.toRow && move.fromColumn == move.toColumn && currentPlayer() == Player.WHITE) {
					player = Player.WHITE;
					setNextPlayer(player);
					System.out.println("Player is " + currentPlayer() + " You can't move in the same spot");
					return false;
				}


				if(board[move.fromRow][move.fromColumn].player() == Player.WHITE) {
					if(board[move.toRow][move.toColumn] == null) {
						player = Player.WHITE;
						setNextPlayer(player);
						System.out.println("Player is " + currentPlayer() + " Can't jump your own piece");
					}
					else if(board[move.toRow][move.toColumn].player() == Player.WHITE) {
						player = Player.WHITE;
						setNextPlayer(player);
						System.out.println("Player is " + currentPlayer() + " Can't jump your own piece");
						return false;
					}
				}

				if (board[move.fromRow][move.fromColumn] != null && currentPlayer() == Player.WHITE) {
					if (board[move.fromRow][move.fromColumn].isValidMove(move, board) && currentPlayer() == Player.WHITE) {
						//
						player = Player.BLACK;

						/*********************************************************
						 * Since white always goes first the undo move button
						 * will store moves as 0 and all ints even(move % 2 == 0)
						 ********************************************************/
						fromMoveRow.add(move.fromRow);
						fromMoveCol.add(move.fromColumn);
						toMoveRow.add(move.toRow);
						toMoveCol.add(move.toColumn);
						pieceMemory.add(board[move.fromRow][move.fromColumn].type());




						System.out.println("Player is " + currentPlayer() + " Valid move, carry on");
						return true;
					}
				}

				break;

			case BLACK:
				if(board[move.fromRow][move.fromColumn].player() == Player.WHITE){
					System.out.println("Can't move Black yet");
					return false;
				}


				if (move.fromRow == move.toRow && move.fromColumn == move.toColumn && currentPlayer() == Player.BLACK) {
					player = Player.BLACK;
					setNextPlayer(player);
					System.out.println("Player is " + currentPlayer() + " You can't move in the same spot");
					return false;
				}


				if(board[move.fromRow][move.fromColumn].player() == Player.BLACK) {
					if(board[move.toRow][move.toColumn] == null) {
						player = Player.BLACK;
						setNextPlayer(player);
						System.out.println("Player is " + currentPlayer() + " Can't jump your own piece");
					}
					else if(board[move.toRow][move.toColumn].player() == Player.BLACK) {
						//FIXME: implement better checking, ie: wBishop jumping over wPawn
						player = Player.BLACK;
						setNextPlayer(player);
						System.out.println("Player is " + currentPlayer() + " Can't jump your own piece");
						return false;
					}
				}

				if (board[move.fromRow][move.fromColumn] != null && currentPlayer() == Player.BLACK) {
					if (board[move.fromRow][move.fromColumn].isValidMove(move, board) && currentPlayer() == Player.BLACK) {

						player = Player.WHITE;

						System.out.println("Player is " + currentPlayer() + " Valid move, carry on");
						fromMoveRow.add(move.fromRow);
						fromMoveCol.add(move.fromColumn);
						toMoveRow.add(move.toRow);
						toMoveCol.add(move.toColumn);
						pieceMemory.add(board[move.fromRow][move.fromColumn].type());

						return true;
					}
				}

				break;
		}

		return false;
	}


	public void move(Move move) {
		board[move.toRow][move.toColumn] = board[move.fromRow][move.fromColumn];

		board[move.fromRow][move.fromColumn] = null;
		//Used because the players are switched before move is done executing
		if(currentPlayer() == Player.WHITE) {
			if(inCheck(Player.WHITE)){
				JOptionPane.showMessageDialog(null, "You are in check! Undo move!");
			}
		}else {
			if(inCheck(Player.BLACK)){
				JOptionPane.showMessageDialog(null, "You are in check! Undo move!");
			}
		}


	}


	/***
	 * Finds the king black or white player and determines if you are in check using two if statement algorithims
	 * Knight Check is first then the other pieces,
	 *
	 * @param  p {@link W18project3.Move} the Player being checked
	 * @return
	 */
	public boolean inCheck(Player p) {
		//Find king

		int kingRow = 0;
		int kingCol = 0;
		System.out.println(p);
		boolean knightFound = false;
		boolean twoKnight = false;
		int[] knightLocation = new int[2];
		int[] knightTwoLocation = new int[2];



		for(int r = 0; r < numRows(); r++) {
			for(int c = 0; c < numColumns(); c++) {
				if(board[r][c] != null) {
					if (board[r][c].type().contains("W19Project3GIVETOSTUDENTS.King") && board[r][c].player() != currentPlayer()) {
						kingRow = r;
						kingCol = c;

					}
					if(board[r][c].type().contains("W19Project3GIVETOSTUDENTS.Knight") && board[r][c].player() == currentPlayer()) {
					    if(!knightFound) {
					        knightLocation[0] = r;
					        knightLocation[1] = c;
					        knightFound = true;
                        }else {
					        twoKnight = true;
					        knightTwoLocation[0] = r;
					        knightTwoLocation[1] = c;
                        }
                    }

				}
			}
		}


		System.out.println("DEBUG kingRow: " + kingRow);
		System.out.println("DEBUG kingCol: " + kingCol);
		//Checks for knight
        



		//Begins scanning different tiles near the king and expand to check each piece if it can kill the king

		for(int r = 0; r < 8; r++ ){
			// For loop Cycle: NW, N, NE, W, E, SE, S, SW
			int[] rowDirections = {-1, -1, -1, 0, 0, 1, 1, 1};
			int[] colDirections = {-1, 0, 1, -1, 1, -1, 0, 1};

			int row = kingRow;
			int col = kingCol;
			int rowIncrement = rowDirections[r];
			int colIncrement = colDirections[r];
			for(int c = 0; c < 8; c++) {

				row = row + rowIncrement;
				col = col + colIncrement;
				if(row < 0 || row > 7 || col < 0 || col > 7) {
					break;
				}else {
					if(board[row][col] != null) {
						/************************************************************************************
						 * isValidMove() is scanned if it matches a enemy piece and has the ability to attack
						 ***********************************************************************************/
						if(board[row][col].player() != currentPlayer()) {
							System.out.println(board[row][col].type());
							//bishop

							/***********************************************************************
							 * These branches check for bishop, rook, queen, and pawn lethal moves to king
							 **********************************************************************/
							if(board[row][col].type().contains("W19Project3GIVETOSTUDENTS.Bishop")) {
								Bishop bishop = new Bishop(p);
								Move move = new Move(row, col, kingRow, kingCol);
								System.out.println("FOUND A BISHOP");
								if (bishop.isValidMove(move, board)) {
									return true;
								}
							}else if(board[row][col].type().contains("W19Project3GIVETOSTUDENTS.Rook")) {
								Rook rook = new Rook(p);
								Move move = new Move(row, col, kingRow, kingCol);
								System.out.println("FOUND A ROOK");
								if (rook.isValidMove(move, board)) {
									return true;
								}
							}

							}else if(board[row][col].type().contains("W19Project3GIVETOSTUDENTS.Queen")) {
								Queen queen = new Queen(p);
								Move move = new Move(row, col, kingRow, kingCol);
								if(queen.isValidMove(move,board)){
									return true;
								}
							}else {
						    if(r == 0) {
                                if(board[row][col].type().contains("W19Project3GIVETOSTUDENTS.Pawn")){
                                    /***************************************************************************************
                                     * Due to how inCheck and Pawn differ in how player is used we cannot use Pawn.isValid()
                                     **************************************************************************************/
                                    System.out.println("FOUND A PAWN");

                                    if(p == Player.BLACK) {
                                        if(row - 1 == kingRow && (col + 1 == kingCol || col - 1 == kingCol)) {
                                            return true;
                                        }
                                    }else {
                                        if (row + 1 == kingRow && (col + 1 == kingCol || col - 1 == kingCol)) {
                                            return true;
                                        }
                                    }

                                }
                                if(board[row][col].type().contains("W19Project3GIVETOSTUDENTS.King")) {
                                    King king = new King(p);
                                    Move move = new Move(row, col, kingRow, kingCol);
                                    System.out.println("FOUND A KING");

                                }
                            }
                        }





							}
						}
					}
				}



		return false;
	}

	public Player currentPlayer() {
		return player;
	}

	public int numRows() {
		return 8;
	}

	public int numColumns() {
		return 8;
	}

	public IChessPiece pieceAt(int row, int column) {
		return board[row][column];
	}

	public void setNextPlayer(Player turn) {
		player = turn;
	}

	public void setPiece(int row, int column, IChessPiece piece) {
		board[row][column] = piece;
	}

	public void AI() {
		/*
		 * Write a simple AI set of rules in the following order.
		 * a. Check to see if you are in check.
		 * 		i. If so, get out of check by moving the king or placing a piece to block the check
		 *
		 * b. Attempt to put opponent into check (or checkmate).
		 * 		i. Attempt to put opponent into check without losing your piece
		 *		ii. Perhaps you have won the game.
		 *
		 *c. Determine if any of your pieces are in danger,
		 *		i. Move them if you can.
		 *		ii. Attempt to protect that piece.
		 *
		 *d. Move a piece (pawns first) forward toward opponent king
		 *		i. check to see if that piece is in danger of being removed, if so, move a different piece.
		 */

	}
}

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
        boolean foundKing = false;
        boolean doneKnight = false;
        boolean donePieces = false;

        if(!foundKing) {
            for(int r = 0; r < numRows(); r++) {
                for(int c = 0; c < numColumns(); c++) {
                    if(board[r][c] != null) {
                        if (board[r][c].type().contains("W19Project3GIVETOSTUDENTS.King") && board[r][c].player() == p) {
                            kingRow = r;
                            kingCol = c;
                            foundKing = true;
                        }

                    }
                }
            }
        }

        IChessPiece knight = new Knight(p);
        Move move = new Move(kingRow, kingCol, kingRow + 2, kingCol + 1);
        Move move2 = new Move(kingRow, kingCol, kingRow + 2, kingCol - 1);
        Move move3 = new Move(kingRow, kingCol, kingRow - 2, kingCol + 1);
        Move move4 = new Move(kingRow, kingCol, kingRow - 2, kingCol - 1);
        Move move5 = new Move(kingRow, kingCol, kingRow + 1, kingCol + 2);
        Move move6 = new Move(kingRow, kingCol, kingRow - 1, kingCol + 2);
        Move move7 = new Move(kingRow, kingCol, kingRow + 1, kingCol - 2);
        Move move8 = new Move(kingRow, kingCol, kingRow - 1, kingCol - 2);

        if(knight.isValidMove(move, board)) {
            if(kingRow + 2 <= 7 && kingCol + 1 <= 7) {
                if(board[kingRow + 2][kingCol + 1] != null) {
                    if(board[kingRow + 2][kingCol + 1].type().contains("W19Project3GIVETOSTUDENTS.Knight")){
                        if(board[kingRow + 2][kingCol + 1].player() != board[kingRow][kingCol].player()) {
                            return true;
                        }
                    }
                }
            }
        }else if(knight.isValidMove(move2, board)) {
            if (kingRow + 2 <= 7 && kingCol - 1 >= 0) {
                if (board[kingRow + 2][kingCol - 1] != null) {
                    if (board[kingRow + 2][kingCol - 1].type().contains("W19Project3GIVETOSTUDENTS.Knight")) {
                        if (board[kingRow + 2][kingCol - 1].player() != board[kingRow][kingCol].player()) {
                            return true;
                        }
                    }
                }
            }
        }else if(knight.isValidMove(move3, board)) {
            if (kingRow - 2 >= 0 && kingCol + 1 <= 7) {
                if (board[kingRow - 2][kingCol + 1] != null) {
                    if (board[kingRow - 2][kingCol + 1].type().contains("W19Project3GIVETOSTUDENTS.Knight")) {
                        if (board[kingRow - 2][kingCol + 1].player() != board[kingRow][kingCol].player()) {
                            return true;
                        }
                    }
                }
            }
        }else if(knight.isValidMove(move4, board)) {
            if (kingRow - 2 >= 0 && kingCol - 1 >= 0) {
                if (board[kingRow - 2][kingCol - 1] != null) {
                    if (board[kingRow - 2][kingCol - 1].type().contains("W19Project3GIVETOSTUDENTS.Knight")) {
                        if (board[kingRow - 2][kingCol - 1].player() != board[kingRow][kingCol].player()) {
                            return true;
                        }
                    }
                }
            }
        }else if(knight.isValidMove(move5, board)) {
            if (kingRow + 1 <= 7 && kingCol + 2 <= 7) {
                if (board[kingRow + 1][kingCol + 2] != null) {
                    if (board[kingRow + 1][kingCol + 2].type().contains("W19Project3GIVETOSTUDENTS.Knight")) {
                        if (board[kingRow + 1][kingCol + 2].player() != board[kingRow][kingCol].player()) {
                            return true;
                        }
                    }
                }
            }
        }else if(knight.isValidMove(move6, board)) {
            if (kingRow - 1 >= 0 && kingCol + 2 <= 7) {
                if (board[kingRow - 1][kingCol + 2] != null) {
                    if (board[kingRow - 1][kingCol + 2].type().contains("W19Project3GIVETOSTUDENTS.Knight")) {
                        if (board[kingRow - 1][kingCol + 2].player() != board[kingRow][kingCol].player()) {
                            return true;
                        }
                    }
                }
            }
        }else if(knight.isValidMove(move7, board)) {
            if (kingRow + 1 <= 7 && kingCol - 2 >= 0) {
                if (board[kingRow + 1][kingCol - 2] != null) {
                    if (board[kingRow + 1][kingCol - 2].type().contains("W19Project3GIVETOSTUDENTS.Knight")) {
                        if (board[kingRow + 1][kingCol - 2].player() != board[kingRow][kingCol].player()) {
                            return true;
                        }
                    }
                }
            }
        }else if(knight.isValidMove(move8, board)) {
            if (kingRow - 1 >= 0 && kingCol - 2 >= 0) {
                if (board[kingRow - 1][kingCol - 2] != null) {
                    if (board[kingRow - 1][kingCol - 2].type().contains("W19Project3GIVETOSTUDENTS.Knight")) {
                        if (board[kingRow - 1][kingCol - 2].player() != board[kingRow][kingCol].player()) {
                            return true;
                        }
                    }
                }
            }
        }



        System.out.println("Current player is " + p.toString());
        System.out.println("DEBUG: King r: " + kingRow);
        System.out.println("DEBUG: King c: " + kingCol);
        //Knight check




        //Check if you are vulnerable to attack(exposed tiles)











        /**************************************************************************************************************
         * Other Pieces Algorithim: Checks 3x3ish area around king. Any tile containing a friendly is set to be ignored
         * Check for pawns in the diagonals corresponding to the player, check for king(ALL), check for queen(ALL),
         * check for bishop(ALL). Any tile that is null is checked for Rooks and Queen (UP,DOWN,LEFT,RIGHT)
         * Bishop and Queen (Up-Left,Up-Right,Down-Left,DownRight
         **************************************************************************************************************/

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
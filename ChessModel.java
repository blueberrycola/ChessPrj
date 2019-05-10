package W19Project3GIVETOSTUDENTS;

import chess.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class ChessModel implements IChessModel {
    private IChessPiece[][] board;
    private Player player;

    //Arrays Responcible for undoButton
    private ArrayList<Integer> fromMoveRow, fromMoveCol, toMoveRow, toMoveCol;
    private ArrayList<String> pieceMemory;

    // declare other instance variables as needed

    /*******************************************************************************************************
     * This constructor places all pieces where they need to be on the board and initialize variable needed,
     * makes the IChessPiece[][] initialized, and uses a for loop to create pawns.
     ******************************************************************************************************/
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

        //In some instances of testing this is needed due to outOfBoundsException
        fromMoveRow.add(0);
        fromMoveCol.add(0);
        toMoveCol.add(0);
        toMoveRow.add(0);
        pieceMemory.add("Null");

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

    /*****************************************************************************************************************
     * Method responsible for undoing moves. Dependant on what the arraylists that store moves and piece type in order
     * to reverse
     ****************************************************************************************************************/
    public void undoButton() {
        System.out.println(fromMoveCol.size());
        if(fromMoveCol.size() == 0) {
            JOptionPane.showMessageDialog(null,"There are no moves to reverse");
            AI();
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
            }else if(pieceMemory.get(undoVal).contains("W19Project3GIVETOSTUDENTS.Knight")) {
                board[fromMoveRow.get(undoVal)][fromMoveCol.get(undoVal)] = new Knight(currentPlayer());
                board[toMoveRow.get(undoVal)][toMoveCol.get(undoVal)] = null;
            }else if (pieceMemory.get(undoVal).contains("W19Project3GIVETOSTUDENTS.King")) {
                board[fromMoveRow.get(undoVal)][fromMoveCol.get(undoVal)] = new King(currentPlayer());
                board[toMoveRow.get(undoVal)][toMoveCol.get(undoVal)] = null;
            }else if (pieceMemory.get(undoVal).contains("W19Project3GIVETOSTUDENTS.Queen")) {
                board[fromMoveRow.get(undoVal)][fromMoveCol.get(undoVal)] = new Queen(currentPlayer());
                board[toMoveRow.get(undoVal)][toMoveCol.get(undoVal)] = null;
            }else if(pieceMemory.get(undoVal).contains("W19Project3GIVETOSTUDENTS.Bishop")) {
                board[fromMoveRow.get(undoVal)][fromMoveCol.get(undoVal)] = new Bishop(currentPlayer());
                board[toMoveRow.get(undoVal)][toMoveCol.get(undoVal)] = null;
            }else if (pieceMemory.get(undoVal).contains("W19Project3GIVETOSTUDENTS.Rook")) {
                board[fromMoveRow.get(undoVal)][fromMoveCol.get(undoVal)] = new Rook(currentPlayer());
                board[toMoveRow.get(undoVal)][toMoveCol.get(undoVal)] = null;
            } else {
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

    /**************************************************************************************
     * isComplete is in charge of telling us when the game almost all through if statements
     * @return valid, a boolean value, either true or false
     *************************************************************************************/
    public boolean isComplete() {
        //fixme: add isComplete condition
        boolean valid = false;
        return valid;
    }


    /******************************************************************************************************************
     * This method is responsible for enforcing game rules such as you cannot jump over pieces if you are not a knight,
     * you cant attack your own piece, you cant move your piece on the same tile, you moved twice before the other
     * player went, etc
     * @param move a {@link W18project3.Move} object describing the move to be made.
     * @return T or F, depending on if the move is valid or not
     *****************************************************************************************************************/
    public boolean isValidMove(Move move) {
        /*
           Conditionals to check: dont move in the same spot, dont move when it isnt your turn, dont move when hopping over
           pieces-regardless of player type-unless knight
         */
        if(board[move.fromRow][move.fromColumn].type().contains("KNIGHT")) {

        } else {
            String pieceName = board[move.fromRow][move.fromColumn].type();
            //Checks if the move is valid for the piece
            boolean validPieceMove = false;
            if(pieceName.contains("KING")) {
                King king = new King(player);
                if(king.isValidMove(move, board)) {
                    validPieceMove = true;
                }
            }else if(pieceName.contains("QUEEN")) {
                Queen queen = new Queen(player);
                if(queen.isValidMove(move, board)) {
                    validPieceMove = true;
                }
            }else if(pieceName.contains("BISHOP")) {
                Bishop bishop = new Bishop(player);
                if(bishop.isValidMove(move, board)) {
                    validPieceMove = true;
                }
            }else if(pieceName.contains("PAWN")) {
                Pawn pawn = new Pawn(player);
                if(pawn.isValidMove(move, board)) {
                    validPieceMove = true;
                }
            }else if(pieceName.contains("ROOK")) {
                Rook rook = new Rook(player);
                if(rook.isValidMove(move, board)) {
                    validPieceMove = true;
                }
            }
            //if the piece move is valid for the given piece start checking for illegal moves, ie: rook over pawn
            //At this given line the method has checked if the move is legal in relation to piece and movement but now
            //we must check for jumping, double clicking, and friendly fire
            if(validPieceMove) {
                if(move.fromRow == move.toRow && move.fromColumn == move.toColumn) {
                    System.out.println("You cant move in the same space");
                    return false;
                } else if(pieceAt(move.toRow, move.toColumn).player() == pieceAt(move.fromRow, move.toColumn).player()) {
                    System.out.println("You cannot attack a friendly");
                } else {
                    //Horizontal Movement Scan Loop
                    if(move.fromRow != move.toRow && move.fromColumn == move.toColumn) {

                    }
                    //Vertical Movement Scan Loop
                    if(move.fromRow == move.toRow && move.fromColumn != move.toColumn) {

                    }
                    //Diagonal Movement Scan Loop
                    if(move.fromRow != move.toRow && move.fromColumn != move.toColumn) {

                    }
                }

            }else {
                return false;
            }
        }

        //Statements needed for undoButton and inCheck methods
        fromMoveRow.add(move.fromRow);
        fromMoveCol.add(move.fromColumn);
        toMoveRow.add(move.toRow);
        toMoveCol.add(move.toColumn);
        pieceMemory.add(board[move.fromRow][move.fromColumn].type() + " " + board[move.fromRow][move.fromColumn].player());

        return false;
    }

    /*******************************************************************************
     * Method responcible for actually moving the chess piece object
     * @param move a {@link W18project3.Move} object describing the move to be made.
     ******************************************************************************/
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
    //Fixme: undoButton changes player to black pawn if first move


    /************************************************************************************************************
     * Finds the king black or white player and determines if you are in check using two if statement algorithims
     * Knight Check is first then the other pieces,
     *
     * @param  p {@link W18project3.Move} the Player being checked
     * @return
     ***********************************************************************************************************/
    public boolean inCheck(Player p) {
        //Fixme: All pieces work BUT Knights need some work
        //Fixme: store type and location of checking piece
        //Fixme: rewrite code:
                //Scan King for exposed pieces, if exposed piece is a validMove for the chess type store the data and return boolean statement
                //Check if a knight has "reach" by using isValidMove() with moves a knight can do if it were at a kings location
                    //isValidMove needs algorithim for array bounds to not cause IndexOutOfBoundsException. Possibly recursive
        int kingRow = 0;
        int kingCol = 0;

        for(int r = 0; r < numRows(); r++) {
            for(int c = 0; c < numColumns(); c++) {
                if(board[r][c] != null) {
                    if (board[r][c].type().contains("W19Project3GIVETOSTUDENTS.King") && board[r][c].player() == p) {
                        kingRow = r;
                        kingCol = c;

                    }


                }
            }
        }


        System.out.println("DEBUG kingRow: " + kingRow);
        System.out.println("DEBUG kingCol: " + kingCol);
        int[] moveTwo = {2, -2};
        int[] moveOne = {1, -1};
        boolean lastMoveFlag = false;
        //if the last piece moved was a knight or king scan for a check if not ignore knight check
        if(pieceMemory.get(pieceMemory.size() - 1).contains("KNIGHT")) {
            lastMoveFlag = true;
        }

        if(lastMoveFlag) {
            Move knightmove = null;
            Move inverseMove = null;
            //Conditional loop that checks for possible knight moves and validates whether there is an enemy knight present
            for(int y = 0; y < 2; y++) {
                for(int x = 0; x < 2; x++) {

                    if(kingRow + moveTwo[x] >= 0 && kingRow + moveTwo[x] <= 7) {
                        knightmove = new Move(kingRow, kingCol,
                                moveTwo[x] + kingRow, moveOne[x] + kingCol);
                        inverseMove = new Move(kingRow, kingCol,
                                moveOne[x] + kingRow, moveTwo[x] + kingCol);

                    }
                    if(kingRow + moveOne[x] >= 0 && kingRow + moveOne[x] <= 7) {
                        knightmove = new Move(kingRow, kingCol,
                                moveTwo[x] + kingRow, moveOne[x] + kingCol);
                        inverseMove = new Move(kingRow, kingCol,
                                moveOne[x] + kingRow, moveTwo[x] + kingCol);
                    }
                    if(kingCol + moveTwo[x] >= 0 && kingCol + moveTwo[x] <= 7) {
                        knightmove = new Move(kingRow, kingCol, moveTwo[x], moveOne[x]);
                        inverseMove = new Move(kingRow, kingCol,
                                moveOne[x] + kingRow, moveTwo[x] + kingCol);
                    }
                    if(kingCol + moveOne[x] >= 0 && kingCol + moveOne[x] <= 7) {
                        knightmove = new Move(kingRow, kingCol,
                                moveTwo[x] + kingRow, moveOne[x] + kingCol);
                        inverseMove = new Move(kingRow, kingCol,
                                moveOne[x] + kingRow, moveTwo[x] + kingCol);
                    }
                    if(knightmove != null) {
                        if(isValidMove(knightmove) || isValidMove(inverseMove)) {
                            if(pieceAt(moveTwo[x] + kingRow, moveOne[x] + kingCol).type().contains("KNIGHT")
                                    && pieceAt(moveTwo[x] + kingRow, moveOne[x] + kingCol).player() != p)
                            {
                                return true;
                            }
                            if(pieceAt(moveOne[x] + kingRow, moveTwo[x] + kingCol).player() != p
                                    && pieceAt(moveOne[x] + kingRow, moveTwo[x] + kingCol).type().contains("KNIGHT"))
                            {
                                return true;

                            }
                        }
                    } else {
                        return false;
                    }
                }
            }
        }


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
                    if(board[row][col] != null && board[row][col].player() != p) {
                        /************************************************************************************
                         * isValidMove() is scanned if it matches a enemy piece and has the ability to attack
                         ***********************************************************************************/
                        if(board[row][col].player() == currentPlayer()) {
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
                            }

                            else if(board[row][col].type().contains("W19Project3GIVETOSTUDENTS.Rook")) {
                                Rook rook = new Rook(p);
                                Move move = new Move(row, col, kingRow, kingCol);
                                System.out.println("FOUND A ROOK");
                                if (rook.isValidMove(move, board)) {
                                    return true;
                                }
                            }

                            else if(board[row][col].type().contains("W19Project3GIVETOSTUDENTS.Queen")) {
                                Queen queen = new Queen(p);
                                Move move = new Move(row, col, kingRow, kingCol);
                                if(queen.isValidMove(move,board)){
                                    return true;
                                }
                            }


                            if(board[row][col].type().contains("W19Project3GIVETOSTUDENTS.Pawn")){
                                /***************************************************************************************
                                 * Due to how inCheck and Pawn differ in how player is used we cannot use Pawn.isValid()
                                 **************************************************************************************/
                                System.out.println("FOUND A PAWN");

                                if(p == Player.BLACK) {
                                    if(row + 1 == kingRow && (col + 1 == kingCol || col - 1 == kingCol)) {
                                        return true;
                                    }
                                }else if(p == Player.WHITE){
                                    if (row - 1 == kingRow && (col + 1 == kingCol || col - 1 == kingCol)) {
                                        return true;
                                    }
                                }

                            }

                            if(board[row][col].type().contains("W19Project3GIVETOSTUDENTS.King")) {
                                King king = new King(p);
                                Move move = new Move(row, col, kingRow, kingCol);
                                System.out.println("FOUND A KING");
                                if(king.isValidMove(move,board)){
                                    return true;
                                }

                            }

                        }

                    }
                }
            }
        }



        return false;
    }

    /********************************************
     * Returns the player that is allowed to move
     * @return Player.BLACK or Player.WHITE
     *******************************************/
    public Player currentPlayer() {
        return player;
    }

    /***
     * returns the number of rows
     * @return ALWAYS 8
     */
    public int numRows() {
        return 8;
    }

    /***
     * returns the number of columns
     * @return ALWAYS 8
     */
    public int numColumns() {
        return 8;
    }

    /****************************************************************************************
     * method used to tell what piece is at a given location, very useful for debugging/JUnit
     * @param row
     * @param column
     * @return String: IChessPiece.type()
     ****************************************************************************************/
    public IChessPiece pieceAt(int row, int column) {
        return board[row][column];
    }

    /****************************************************************************************************************
     * Sets the next player to whatever player invoked it. ie: setNextPlayer(Player.black) --> player = Player.BLACK;
     * @param turn
     ***************************************************************************************************************/
    public void setNextPlayer(Player turn) {
        player = turn;
    }

    /*****************************************************************************************
     * Takes the coordinate that you enter and piece you are requesting to place and places it
     * @param row
     * @param column
     * @param piece
     ****************************************************************************************/

    public void setPiece(int row, int column, IChessPiece piece) {
        board[row][column] = piece;
    }

    /**
     * Used when the vs Computer button is selected in order to properly use the ai method which requires a clean slate
     * in order for the conditionals to be properly executed
     */
    public void aiBoard() {
        player = Player.WHITE;
        setNextPlayer(player);
        board = new IChessPiece[8][8];

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
    /***
     * A simple AI that moves for black chess pieces clicked on is recursive in nature until game over
     * @author Chase Johnston
     */
    public void AI() {
        //Fixme: write a simple AI to play for black chess pieces
        System.out.println("DEBUG: Black players are now operated by a computer ");

        Random rand = new Random();
        //Obj responsible for intitiating first pawn move, 50/50 first move is double spaced

        Random pawnChoose = new Random();
        int pawnNum = pawnChoose.nextInt(8) + 1;

        //Fixme: inCheck bugs
        //Fixme: post early strat
        //Fixme: defensive moves
        //Fixme: Implement reactionary triggers ie: protect king away from check if you can protecc or attack a offending piece
        //NOTE: ai chooses attack or defense based on a 50/50 chance
        //Conditionals looped each move the black
        if(player == player.BLACK && inCheck(player.BLACK)) {
            //FIXME: Implement something to store data about what pieces were attacking in
        } else {
            //Attacking
            if(rand.nextInt(2) == 1) {

            }
            //Defending
            else {

            }
        }


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

    /**
     * The moves that the ai will take if something obvious happens such as you being in check or an ability to
     * sacrifice a piece of yours to protect king.
     */
    public void reflexAIMoves(String caseName) {

    }

    /**
     * A constructor specifically for testing scenarios by giving a blank board for piece placement
     * ie: checking all moves for the inCheck method properly finds potential knight pieces
     */
    public void blankBoard() {
        for(int y = 0; y < 8; y++) {
            for(int x = 0; x < 8; x++) {
                board[y][x] = null;
            }
        }
    }


}

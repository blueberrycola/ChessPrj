package W19Project3GIVETOSTUDENTS;

import chess.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;





public class ChessPanel extends JPanel {

    private JButton[][] board;
    private JButton undoButton;

    private ChessModel model;

    private ImageIcon wRook, wBishop, wQueen, wKing, wPawn, wKnight;
    private ImageIcon bRook, bBishop, bQueen, bKing, bPawn, bKnight;

    private boolean firstTurnFlag;
    private int fromRow;
    private int toRow;
    private int fromCol;
    private int toCol;
    // declare other instance variables as needed

    private listener listener;

    /**************************************************************************************************************
     * Constructor responsible for making all the things needed for the GUI such as the panel itself or undo button
     *************************************************************************************************************/
    public ChessPanel() {
        model = new ChessModel();
        board = new JButton[model.numRows()][model.numColumns()];
        undoButton = new JButton("Undo Previous Move");
        listener = new listener();
        createIcons();

        JPanel boardpanel = new JPanel();
        JPanel buttonpanel = new JPanel();
        buttonpanel.add(undoButton);
        undoButton.addActionListener(listener);
        buttonpanel.setLayout(new GridLayout(3, 9,1,1));
        boardpanel.setLayout(new GridLayout(model.numRows(), model.numColumns(), 1, 1));

        for (int r = 0; r < model.numRows(); r++) {
            for (int c = 0; c < model.numColumns(); c++) {
                if (model.pieceAt(r, c) == null) {
                    board[r][c] = new JButton("", null);
                    board[r][c].addActionListener(listener);
                } else if (model.pieceAt(r, c).player() == Player.WHITE) {
                    placeWhitePieces(r, c);
                } else { placeBlackPieces(r, c); }

                setBackGroundColor(r, c);
                boardpanel.add(board[r][c]);
            }
        }
        add(boardpanel, BorderLayout.WEST);
        boardpanel.setPreferredSize(new Dimension(600, 600));
        add(buttonpanel);
        firstTurnFlag = true;
    }

    /********************************************
     * Sets the color of the checker styled board
     * @param r
     * @param c
     *******************************************/
    private void setBackGroundColor(int r, int c) {
        if ((c % 2 == 1 && r % 2 == 0) || (c % 2 == 0 && r % 2 == 1)) {
            board[r][c].setBackground(Color.LIGHT_GRAY);
        } else if ((c % 2 == 0 && r % 2 == 0) || (c % 2 == 1 && r % 2 == 1)) {
            board[r][c].setBackground(Color.WHITE);
        }
    }

    /***********************************************************************
     * Responsible for assigning where the chess pieces go on the white side
     * @param r
     * @param c
     ***********************************************************************/
    private void placeWhitePieces(int r, int c) {
        //if the type equals knight then initialize wKnight
        if (model.pieceAt(r, c).type().equals("W19Project3GIVETOSTUDENTS.Pawn")) {
            board[r][c] = new JButton(null, wPawn);
            board[r][c].addActionListener(listener);
        }
        //if the type equals knight then initialize wKnight
        if (model.pieceAt(r, c).type().equals("W19Project3GIVETOSTUDENTS.Rook")) {
            board[r][c] = new JButton(null, wRook);
            board[r][c].addActionListener(listener);
        }
        //if the type equals knight then initialize wKnight
        if (model.pieceAt(r, c).type().equals("W19Project3GIVETOSTUDENTS.Knight")) {
            board[r][c] = new JButton(null, wKnight);
            board[r][c].addActionListener(listener);
        }
        //if the type equals knight then initialize wKnight
        if (model.pieceAt(r, c).type().equals("W19Project3GIVETOSTUDENTS.Bishop")) {
            board[r][c] = new JButton(null, wBishop);
            board[r][c].addActionListener(listener);
        }
        //if the type equals knight then initialize wKnight
        if (model.pieceAt(r, c).type().equals("W19Project3GIVETOSTUDENTS.Queen")) {
            board[r][c] = new JButton(null, wQueen);
            board[r][c].addActionListener(listener);
        }
        //if the type equals knight then initialize wKnight
        if (model.pieceAt(r, c).type().equals("W19Project3GIVETOSTUDENTS.King")) {
            board[r][c] = new JButton(null, wKing);
            board[r][c].addActionListener(listener);
        }
    }
    /***********************************************************************
     * Responsible for assigning where the chess pieces go on the black side
     * @param r
     * @param c
     ***********************************************************************/
    private void placeBlackPieces(int r, int c) {
        //if the type equals knight then initialize wKnight
        if (model.pieceAt(r, c).type().equals("W19Project3GIVETOSTUDENTS.Pawn")) {
            board[r][c] = new JButton(null, bPawn);
            board[r][c].addActionListener(listener);
        }
        //if the type equals knight then initialize wKnight
        if (model.pieceAt(r, c).type().equals("W19Project3GIVETOSTUDENTS.Rook")) {
            board[r][c] = new JButton(null, bRook);
            board[r][c].addActionListener(listener);
        }
        //if the type equals knight then initialize wKnight
        if (model.pieceAt(r, c).type().equals("W19Project3GIVETOSTUDENTS.Knight")) {
            board[r][c] = new JButton(null, bKnight);
            board[r][c].addActionListener(listener);
        }
        //if the type equals knight then initialize wKnight
        if (model.pieceAt(r, c).type().equals("W19Project3GIVETOSTUDENTS.Bishop")) {
            board[r][c] = new JButton(null, bBishop);
            board[r][c].addActionListener(listener);
        }
        //if the type equals knight then initialize bKnight
        if (model.pieceAt(r, c).type().equals("W19Project3GIVETOSTUDENTS.Queen")) {
            board[r][c] = new JButton(null, bQueen);
            board[r][c].addActionListener(listener);
        }
        //if the type equals knight then initialize bKnight
        if (model.pieceAt(r, c).type().equals("W19Project3GIVETOSTUDENTS.King")) {
            board[r][c] = new JButton(null, bKing);
            board[r][c].addActionListener(listener);
        }
    }

    /******************************************************
     * Instantiates needed image icons for the chess pieces
     *****************************************************/
    private void createIcons() {
        // Sets the Image for white player pieces
        wRook = new ImageIcon("./src/W19Project3/wRook.png");
        wBishop = new ImageIcon("./src/W19Project3/wBishop.png");
        wQueen = new ImageIcon("./src/W19Project3/wQueen.png");
        wKing = new ImageIcon("./src/W19Project3/wKing.png");
        wPawn = new ImageIcon("./src/W19Project3/wPawn.png");
        wKnight = new ImageIcon("./src/W19Project3/wKnight.png");
        // Sets the image for black player pieces
        bRook = new ImageIcon("./src/W19Project3/bRook.png");
        bBishop = new ImageIcon("./src/W19Project3/bBishop.png");
        bQueen = new ImageIcon("./src/W19Project3/bQueen.png");
        bKing = new ImageIcon("./src/W19Project3/bKing.png");
        bPawn = new ImageIcon("./src/W19Project3/bPawn.png");
        bKnight = new ImageIcon("./src/W19Project3/bKnight.png");

    }

    /*****************************************************************
     * Method responsible for updating the current state of ChessModel
     ****************************************************************/
    private void displayBoard() {

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++)
                if (model.pieceAt(r, c) == null)
                    board[r][c].setIcon(null);
                else
                if (model.pieceAt(r, c).player() == Player.WHITE) {
                    if (model.pieceAt(r, c).type().equals("W19Project3GIVETOSTUDENTS.Pawn"))
                        board[r][c].setIcon(wPawn);

                    if (model.pieceAt(r, c).type().equals("W19Project3GIVETOSTUDENTS.Rook"))
                        board[r][c].setIcon(wRook);

                    if (model.pieceAt(r, c).type().equals("W19Project3GIVETOSTUDENTS.Knight"))
                        board[r][c].setIcon(wKnight);

                    if (model.pieceAt(r, c).type().equals("W19Project3GIVETOSTUDENTS.Bishop"))
                        board[r][c].setIcon(wBishop);

                    if (model.pieceAt(r, c).type().equals("W19Project3GIVETOSTUDENTS.Queen"))
                        board[r][c].setIcon(wQueen);

                    if (model.pieceAt(r, c).type().equals("W19Project3GIVETOSTUDENTS.King"))
                        board[r][c].setIcon(wKing);

                } else {
                    if (model.pieceAt(r, c).type().equals("W19Project3GIVETOSTUDENTS.Pawn"))
                        board[r][c].setIcon(bPawn);

                    if (model.pieceAt(r, c).type().equals("W19Project3GIVETOSTUDENTS.Rook"))
                        board[r][c].setIcon(bRook);

                    if (model.pieceAt(r, c).type().equals("W19Project3GIVETOSTUDENTS.Knight"))
                        board[r][c].setIcon(bKnight);

                    if (model.pieceAt(r, c).type().equals("W19Project3GIVETOSTUDENTS.Bishop"))
                        board[r][c].setIcon(bBishop);

                    if (model.pieceAt(r, c).type().equals("W19Project3GIVETOSTUDENTS.Queen"))
                        board[r][c].setIcon(bQueen);

                    if (model.pieceAt(r, c).type().equals("W19Project3GIVETOSTUDENTS.King"))
                        board[r][c].setIcon(bKing);
                }
        }
        repaint();
    }


    /************************************************************************************
     * Inner class responsible for any JButton that has an action listener assigned to it
     * board[r][c] event is any button that is also a chess piece
     * undoButton event is when you click the undo button
     ***********************************************************************************/
    private class listener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            for (int r = 0; r < model.numRows(); r++) {
                for (int c = 0; c < model.numColumns(); c++) {
                    if (board[r][c] == event.getSource()) {

                        if (firstTurnFlag == true) {
                            //If the piece at row, column is null then ignore
                            if (model.pieceAt(r, c) == null) return;
                            fromRow = r;
                            fromCol = c;
                            firstTurnFlag = false;
                        } else {
                            toRow = r;
                            toCol = c;
                            firstTurnFlag = true;
                            Move m = new Move(fromRow, fromCol, toRow, toCol);
                            if ((model.isValidMove(m)) == true) {
                                model.move(m);
                                displayBoard();
                            }
                        }
                    }
                }
            }
            if(undoButton == event.getSource()) {
                model.undoButton();
                displayBoard();
            }
        }
    }
}

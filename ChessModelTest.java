package W19Project3GIVETOSTUDENTS;

import chess.*;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ChessModelTest {

    @Test
    public void inCheckBishop() {
        ChessModel model = new ChessModel();
        King king = new King(Player.BLACK);
        Bishop bishop = new Bishop(Player.WHITE);
        model.setPiece(1,1,bishop);
        model.setPiece(4,4,king);
        Move m = new Move(1,1,2,2);
        model.move(m);
        Assert.assertEquals(true, model.inCheck(Player.WHITE));
    }

    @Test
    public void inCheckRook() {
        ChessModel model = new ChessModel();
        King king = new King(Player.BLACK);
        Rook rook = new Rook(Player.WHITE);
        model.setPiece(2,1, king);
        model.setPiece(3, 5,rook);
        Move m = new Move(3,5,2,5);
        model.move(m);
        Assert.assertEquals(true, model.inCheck(Player.WHITE));
    }

    @Test
    public void inCheckKnight() {
        ChessModel model = new ChessModel();
        King king = new King(Player.BLACK);
        Knight knight = new Knight(Player.WHITE);
        model.setPiece(2,1,king);
        model.setPiece(6,4,knight);
        Move m = new Move(6,4,4,2);
        model.move(m);
        Assert.assertEquals(true, model.inCheck(Player.WHITE));
    }
    @Test
    public void undoNoMoves() {

    }

    @Test
    public void inCheckQueen() {
        ChessModel model = new ChessModel();
        King king = new King(Player.BLACK);
        Queen queen = new Queen(Player.WHITE);
        model.setPiece(2,2, king);
        model.setPiece(3,6, queen);
        Move m = new Move(3,6,2,6);
        model.move(m);
        Assert.assertEquals(true, model.inCheck(Player.WHITE));
    }

    @Test
    public void inCheckKing() {
        ChessModel model = new ChessModel();
        King king = new King(Player.BLACK);
        King kingTwo = new King(Player.WHITE);
        model.setPiece(1,1,king);
        model.setPiece(1,3,kingTwo);
        Move m = new Move(1,3,1,2);
        model.move(m);
        Assert.assertEquals(true, model.inCheck(Player.WHITE));
    }



    @Test
    public void undoButton() {
        ChessModel model = new ChessModel();
        King king = new King(Player.BLACK);
        Queen queen = new Queen(Player.WHITE);
        model.setPiece(2,1,king);
        model.setPiece(3,5,queen);
        Move m = new Move(3,5,5,5);
        model.move(m);
        model.undoButton();
        Assert.assertEquals(m.fromRow, 3);
        Assert.assertEquals(m.fromColumn, 5);

    }

    @Test
    public void moveOverEnemyPiece() {
        ChessModel model = new ChessModel();
        Queen queen = new Queen(Player.BLACK);
        Rook rook = new Rook(Player.WHITE);
        model.setPiece(1,2,queen);
        model.setPiece(1,4,rook);
        Move m = new Move(1,1,1,6);
        Assert.assertEquals(false,model.isValidMove(m));


    }

    @Test
    public void moveOverFriendPiece() {
        ChessModel model = new ChessModel();
        Queen queen = new Queen(Player.BLACK);
        Rook rook = new Rook(Player.BLACK);
        model.setPiece(1,2,queen);
        model.setPiece(1,4,rook);
        Move m = new Move(1,1,1,6);
        Assert.assertEquals(false, model.isValidMove(m));
    }

    @Test
    public void doubleDipping() {
        ChessModel model = new ChessModel();
        Queen queen = new Queen(Player.BLACK);
        model.setPiece(1,1,queen);
        Move mOne = new Move(1,1,2,2);
        Move mTwo = new Move(2,2,3,3);
        model.move(mOne);
        Assert.assertEquals(false, model.isValidMove(mTwo));
    }

    @Test
    public void friendlyFire() {
        ChessModel model = new ChessModel();
        Move m = new Move();
    }

    @Test
    public void validMove() {
        ChessModel model = new ChessModel();
        Knight knight = new Knight(Player.BLACK);
        model.setPiece(0,0, knight);
        Move m = new Move(0,0,2,1);
        model.move(m);
        Assert.assertEquals(true, model.isValidMove(m));
    }

    //FIXME: add isComplete tests
    //FIXME: add AI tests




}
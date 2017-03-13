package model;

import model.Piece.color;
import view.ConsoleView;

/**
 * Created by ChristofferTronje on 2016-11-03.
 */
public class ChessGame {

    Board board;
    Piece[] whitePieces;
    Piece[] blackPieces;
    Player white;
    Player black;
    Player currentPlayer;
    Boolean gameOver = false;

    public ChessGame(){
        board = new Board();
        white = new Player(color.WHITE);
        black = new Player(color.BLACK);
        currentPlayer = new Player((color.WHITE));
        whitePieces = new Piece[16];
        blackPieces = new Piece[16];
    }

    public void initBoard(){


        for(int i = 0; i<board.getBricks().length;i++){
            Pawn p = new Pawn(color.WHITE);
            p.setX(i);
            p.setY(6);
            board.setBrick(p);
        }


        for(int i = 0; i<board.getBricks().length;i++){
            Pawn p = new Pawn(color.BLACK);
            p.setX(i);
            p.setY(1);
            board.setBrick(p);
        }

        Rook r = new Rook(color.WHITE);
        r.setX(0);
        r.setY(7);
        board.setBrick(r);

        Rook r2 = new Rook(color.WHITE);
        r2.setX(7);
        r2.setY(7);
        board.setBrick(r2);

        Rook r3 = new Rook(color.BLACK);
        r3.setX(0);
        r3.setY(0);
        board.setBrick(r3);

        Rook r4 = new Rook(color.BLACK);
        r4.setX(7);
        r4.setY(0);
        board.setBrick(r4);

        Knight k = new Knight(color.WHITE);
        k.setX(1);
        k.setY(7);
        board.setBrick(k);

        Knight k2 = new Knight(color.WHITE);
        k2.setX(6);
        k2.setY(7);
        board.setBrick(k2);

        Knight k3 = new Knight(color.BLACK);
        k3.setX(1);
        k3.setY(0);
        board.setBrick(k3);

        Knight k4 = new Knight(color.BLACK);
        k4.setX(6);
        k4.setY(0);
        board.setBrick(k4);

        Bishop b = new Bishop(color.WHITE);
        b.setX(2);
        b.setY(7);
        board.setBrick(b);

        Bishop b2 = new Bishop(color.WHITE);
        b2.setX(5);
        b2.setY(7);
        board.setBrick(b2);

        Bishop b3 = new Bishop(color.BLACK);
        b3.setX(2);
        b3.setY(0);
        board.setBrick(b3);

        Bishop b4 = new Bishop(color.BLACK);
        b4.setX(5);
        b4.setY(0);
        board.setBrick(b4);

        Queen q = new Queen(color.WHITE);
        q.setX(3);
        q.setY(7);
        board.setBrick(q);

        Queen q2 = new Queen(color.BLACK);
        q2.setX(3);
        q2.setY(0);
        board.setBrick(q2);

        King ki = new King(color.WHITE);
        ki.setX(4);
        ki.setY(7);
        board.setBrick(ki);

        King ki2 = new King(color.BLACK);
        ki2.setX(4);
        ki2.setY(0);
        board.setBrick(ki2);

        updatePlayers();
        currentPlayer = white;
    }

    public void setPlayers(Player white, Player black){
        this.white = white;
        this.black = black;
    }

    public Player getWhitePlayer(){
        return white;
    }

    public Player getBlackPlayer(){
        return black;
    }

    public Player getCurrentPlayer(){
        return currentPlayer;
    }

    public Board getBoard(){
        return board;
    }

    public boolean movePiece(Player player, int x, int y, int newX, int newY){

        Piece p = player.containsPiece(x,y);

        if(p == null)
            return false;

        ValidMoves moves = new ValidMoves();

        if(!moves.validMove(p,newX,newY,board)) {       // kollar om det är ett giltligt drag för pjäsen
            return false;
        }

        if (board.getBrick(x,y).isOccupied()){      // kollar om någon redan är på platsen
            board.getBrick(p.getX(),p.getY()).removePiece();
        }

        p.setX(newX);
        p.setY(newY);
        board.setBrick(p);


        updatePlayers();


        if (currentPlayer.getColor() == Piece.color.WHITE) {
            currentPlayer = black;
        }
        else if (currentPlayer.getColor() == Piece.color.BLACK) {
            currentPlayer = white;
        }

        ConsoleView con = new ConsoleView();
        con.board(board);

        return true;
    }

    private void updatePlayers(){

        int t = 0;
        int o = 0;
        for(int i = 0;i<board.getBricks().length;i++){
            for(int u = 0;u<board.getBricks().length;u++){
                if(board.getBrick(i,u).isOccupied()){
                    Piece p = board.getBrick(i,u).getPiece();
                    if (p.getColor() == color.WHITE){
                        System.out.println("HEJ");
                        whitePieces[t] = p;
                        t++;
                    }
                    else if (p.getColor() == color.BLACK){
                        System.out.println("DÅ");
                        blackPieces[o] = p;
                        o++;
                    }
                }
            }
        }

        white.setPieces(whitePieces);
        black.setPieces(blackPieces);

        if(white.getNumberOfPieces() == 0 || black.getNumberOfPieces() == 0)
            gameOver = true;


    }

    public boolean isPlayersPiece(int x, int y){
/*
        if(currentPlayer.getColor() == Piece.color.WHITE){
            currentPlayer = white;
        }
        else{
            currentPlayer = black;
        }
*/
        if(currentPlayer.containsPiece(x,y) != null)
            return true;

        return false;





    }

    public boolean isCheck(){
        return false;
    }

    public boolean isGameOver(){
        return gameOver;
    }

}

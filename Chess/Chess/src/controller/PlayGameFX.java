package controller;

import model.*;

/**
 * Created by ChristofferTronje on 2016-11-26.
 */
public class PlayGameFX {

    model.ChessGame game;
    Player currentPlayer;
    Boolean onFirstClick = true;
    Piece pieceToBeMoved;
    String yx;
    ValidMoves moves = new ValidMoves();


    public PlayGameFX(){
        game = new model.ChessGame();
    }


    public void play2(){
        game.initBoard();
    }

    public boolean pieceClicked(int x, int y){

        if(game.isPlayersPiece(x,y)) {

            if (onFirstClick) {      // piece markerad
                onFirstClick = false;
                pieceToBeMoved = game.getCurrentPlayer().containsPiece(x, y);
                yx = pieceToBeMoved.getY() + " " + pieceToBeMoved.getX();
                return true;
            }
        }
        else {       // piece tagen
            return movePiece(x, y);
        }
        return false;

    }

    public boolean tileClicked(int x, int y){
        if(onFirstClick)
            return false;

        return movePiece(x,y);

    }

    public boolean getOnFirstClick(){
        return onFirstClick;
    }

    public boolean movePiece(int newX,int newY){

        game.getCurrentPlayer();

        if(game.movePiece(game.getCurrentPlayer(),pieceToBeMoved.getX(),pieceToBeMoved.getY(),newX,newY)){
            onFirstClick = true;
            return true;
        }
        else{
            return false;
        }
    }

    public String getPieceToBeMovedXY(){
        return yx;
    }

    public ChessGame getGame(){
        return game;
    }

    public void destinationClicked(){

    }

    public boolean isGameOver(){
        return game.isGameOver();
    }

    public Board getBoard(){
        return game.getBoard();
    }

    public Piece getPieceToBeMoved(){
        return pieceToBeMoved;
    }

}

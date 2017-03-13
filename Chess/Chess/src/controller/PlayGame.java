package controller;


import javafx.scene.layout.Pane;
import model.Piece;
import model.Player;
import view.ConsoleView;

public class PlayGame {

    model.ChessGame game;
    ConsoleView view;
    Player currentPlayer;

    public PlayGame(){
        game = new model.ChessGame();
        view = new ConsoleView();

    }


    public void play(Pane pane){

        game.initBoard();

        Player currentPlayer = game.getBlackPlayer();

        boolean moved = true;


        while (true) {

            if (currentPlayer.getColor() == Piece.color.WHITE && moved) {
                currentPlayer = game.getBlackPlayer();
            }
            else if (currentPlayer.getColor() == Piece.color.BLACK && moved) {
                currentPlayer = game.getWhitePlayer();
            }


            model.Board board = game.getBoard();
            view.board(board);
            view.selectPiece(currentPlayer);


            String chosenPiece = view.setCordinates();
            view.whereTo();
            String chosenLocation = view.newPieceLocation();



            int x = ((int)chosenPiece.charAt(0)-65);
            int y = Character.getNumericValue(chosenPiece.charAt(1));
            int y2 = ((int)chosenLocation.charAt(0)-65);
            int x2 = Character.getNumericValue(chosenLocation.charAt(1));

            if(board.getBrick(x,y).isOccupied()){
                moved = game.movePiece(currentPlayer,x,y, y2, x2);

                if(moved) {
                    view.pieceMoved();
                }
                else {
                    view.pieceNotMoved();

                }
            }
            else{
                view.noPieceAtLocation();
            }
        }

    }



}

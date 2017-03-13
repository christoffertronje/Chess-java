package view;/**
 * Created by ChristofferTronje on 2016-11-20.
 */

import controller.PlayGame;
import controller.PlayGameFX;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import model.Board;
import model.Brick;
import model.ChessGame;
import model.Piece;

import java.io.File;
import java.util.DoubleSummaryStatistics;
import java.util.Scanner;
import java.util.StringJoiner;

public class GUIView {


    String s;
    String blackPath;
    String whitePath;
    String backgroundPath;
    String piecesString;
    PlayGameFX controller;
    AnchorPane anch;
    Pane pane;
    ImageView[][] imageBricks;
    ImageView[][] imagePieces;
    String[] pieces;
    Brick[][] bricks;
    int xValue = 0;
    int yValue = 0;
    double chessSize = 450;
    double tileSize;

    public GUIView(){
        pane = new Pane();
        controller = new PlayGameFX();
        imagePieces = new ImageView[8][8];
    }

    public void play(AnchorPane anch){
        this.anch = anch;
        this.anch.getChildren().add(pane);
        controller.play2();
        board(controller.getBoard());

    }

    public String setCordinates(){
        Scanner scan = new Scanner(System.in);
        String cords = scan.nextLine();
        return cords;
    }

    public void board(Board board){
        initBricks(board);
        initPieces();
    }


    public void notPlayerPiece(){
        System.out.println("Not this players turn!");
    }

    private void putPieceOnBoard(){

    }

    private void updateBoard(int x, int y){
        int x2 = Character.getNumericValue(controller.getPieceToBeMovedXY().charAt(2));
        int y2 = Character.getNumericValue(controller.getPieceToBeMovedXY().charAt(0));

        if (imagePieces[x][y] != null) {
            pane.getChildren().remove(imagePieces[x][y]);
        }

        imagePieces[x][y] = imagePieces[x2][y2];
        imagePieces[x2][y2] = null;
        positionBrick(imagePieces[x][y],x,y);
        if(controller.isGameOver()){
            System.out.println("ASDASDSDS");
            anch.getChildren().remove(pane);
        }

    }

    public void noPieceAtLocation(){
        System.out.println("No piece at given location!");

    }

    public void pieceMoved(){
        System.out.println("Piece moved!");
    }

    public void pieceNotMoved(){
        System.out.println("Not a valid move!");
    }

    public void selectPiece(model.Player p){
        System.out.println("Player: " + p.getColor().toString());
        System.out.println("Selecet piece!");
    }

    private boolean pieceClicked(int x, int y){
        if(controller.pieceClicked(x,y)) {
            if(!controller.getOnFirstClick()) {
            }
            else {
                updateBoard(x, y);
            }
            return true;
        }
        else
            return false;
    }

    private boolean brickClicked(int x, int y){
        if (controller.tileClicked(x,y)){
            if(controller.getOnFirstClick()) {
                updateBoard(x, y);
                return true;
            }
        }
            return false;
    }

    private void positionBrick(ImageView img,int x, int y){

        img.setUserData(String.valueOf(y) + String.valueOf(x));
        double Xx = (xValue+10)+tileSize*x;
        double Yy = (yValue+10)+tileSize*y;
        x = (int)Xx;
        y = (int)Yy;

        img.setLayoutX(x);
        img.setLayoutY(y);
      //  return img;
    }

    private void initPieces(){



        for(int i = 0; i < bricks.length; i++){
            for(int q = 0; q < bricks.length; q++){

                if (bricks[i][q].isOccupied()){
                    int index;
                    Piece p = bricks[i][q].getPiece();
                    if(p.getColor() == Piece.color.WHITE){
                        index = 0;
                    }
                    else {
                        index = 6;
                    }

                    if (bricks[i][q].getPiece().getKind() == Piece.pieceFigure.KING)
                        index+=0;
                    else if (bricks[i][q].getPiece().getKind() == Piece.pieceFigure.QUEEN)
                        index+=1;
                    else if (bricks[i][q].getPiece().getKind() == Piece.pieceFigure.ROOK)
                        index+=2;
                    else if (bricks[i][q].getPiece().getKind() == Piece.pieceFigure.BISHOP)
                        index+=3;
                    else if (bricks[i][q].getPiece().getKind() == Piece.pieceFigure.KNIGHT)
                        index+=4;
                    else
                        index+=5;


                    Image piece = new Image(pieces[index],tileSize,tileSize,true,true);
                    ImageView iv2 = new ImageView();
                    iv2.idProperty().set(String.valueOf(i)+String.valueOf(q));
                    iv2.setImage(piece);
                    positionBrick(iv2,q,i);
                    iv2.setUserData(String.valueOf(i) + String.valueOf(q));
                    imagePieces[q][i] = iv2;
                    iv2.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                        Object obj = event.getSource();
                        Character y = ((ImageView)obj).getUserData().toString().charAt(0);
                        Character x = ((ImageView)obj).getUserData().toString().charAt(1);
                        if(pieceClicked(Character.getNumericValue(x),Character.getNumericValue(y)))
                            return;
                        notPlayerPiece();
                    });

                    pane.getChildren().add(iv2);
                }

            }
        }



    }

    private void initBricks(Board board){


        bricks = board.getBricks();



        s = "file:/Users/ChristofferTronje/Documents/intWorkspace/Chess/Chess/src/view/images";
        blackPath = s+"/black.png";
        whitePath = s+"/white.png";
        backgroundPath = s+"/chessBackground.jpg";
        piecesString = s+"/kingWhite.png " +
                s+"/queenWhite.png " +
                s+"/rookWhite.png " +
                s+"/bishopWhite.png " +
                s+"/knightWhite.png " +
                s+"/pawnWhite.png " +
                s+"/kingBlack.png " +
                s+"/queenBlack.png " +
                s+"/rookBlack.png " +
                s+"/bishopBlack.png " +
                s+"/knightBlack.png " +
                s+"/pawnBlack.png";

        pieces = piecesString.split(" ");


        String currentBrickPath = whitePath;

        Image bg1 = new Image(backgroundPath,chessSize,chessSize,true,true);
        tileSize = ((chessSize/8)-2.5);
        ImageView bg = new ImageView();
        bg.setImage(bg1);
        bg.setLayoutX(xValue);
        bg.setLayoutY(yValue);
        pane.getChildren().add(bg);

        imageBricks = new ImageView[8][8];

        for(int i = 0; i < bricks.length; i++){
            for(int q = 0; q < bricks.length; q++){

                Image image = new Image(currentBrickPath,tileSize,tileSize,true,true);
                ImageView iv1 = new ImageView();
                iv1.setImage(image);
                positionBrick(iv1,q,i);


                iv1.setUserData(String.valueOf(i) + String.valueOf(q));
                pane.getChildren().add(iv1);
                imageBricks[q][i] = iv1;
                iv1.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                    Object obj = event.getSource();
                    Character y = ((ImageView)obj).getUserData().toString().charAt(0);
                    Character x = ((ImageView)obj).getUserData().toString().charAt(1);
                    brickClicked(Character.getNumericValue(x),Character.getNumericValue(y));
                });

                if (currentBrickPath.contentEquals(whitePath) && q!=7)
                    currentBrickPath = blackPath;
                else if(currentBrickPath.contentEquals(blackPath) && q!=7)
                    currentBrickPath = whitePath;
            }
        }


    }

}

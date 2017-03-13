package view;

import model.Board;
import model.Piece;
import model.Brick;

import java.util.Scanner;

/**
 * Created by ChristofferTronje on 2016-11-08.
 */
public class ConsoleView {

    String unicodeMessage =
            "\u2654 " + // white king
                    "\u2655 " + // white queen
                    "\u2656 " + // white rook
                    "\u2657 " + // white bishop
                    "\u2658 " + // white knight
                    "\u2659 " + // white pawn
                    "\u265A " + // black king?
                    "\u265B " + // black queen
                    "\u265C " + // black rook
                    "\u265D " + // black bishop
                    "\u265E " + // black knight
                    "\u265F"; // black pawn

    String[] chesspieces;

    public ConsoleView(){
        chesspieces = new String[12];
        chesspieces = unicodeMessage.split(" ");
    }

    public String setCordinates(){
        Scanner scan = new Scanner(System.in);
        String cords = scan.nextLine();
        return cords;
    }

    public void board(Board board){

        Brick[][] bricks = board.getBricks();

        int boardCordsX = 65;
        int boardCordsY = 0;

        for(int q = 0; q<bricks.length;q++){
            System.out.print("    " + Character.toString((char)boardCordsX));
            boardCordsX++;
        }
        System.out.println();
        for(int i = 0; i<bricks.length*2;i++){
            for(int q = 0; q<bricks.length;q++){
                if(i%2 == 0) {
                    if(q==0)
                        System.out.print(" _____");
                    else
                        System.out.print("_____");

                }
                else{
                    if(q==0)
                        System.out.print(boardCordsY++);
                   // System.out.println(bricks[i/2][q].isOccupied());
                    if(bricks[i/2][q].isOccupied()){
                        int index;
                        Piece p = bricks[i/2][q].getPiece();
                        if(p.getColor() == Piece.color.WHITE){
                            index = 6;
                        }
                        else {
                            index = 0;
                        }

                        System.out.print("|  ");
                        if(p.getKind() == Piece.pieceFigure.KING)
                            System.out.print(chesspieces[0+index]);
                        else if(p.getKind() == Piece.pieceFigure.QUEEN)
                            System.out.print(chesspieces[1+index]);
                        else if(p.getKind() == Piece.pieceFigure.ROOK)
                            System.out.print(chesspieces[2+index]);
                        else if(p.getKind() == Piece.pieceFigure.BISHOP)
                            System.out.print(chesspieces[3+index]);
                        else if(p.getKind() == Piece.pieceFigure.KNIGHT)
                            System.out.print(chesspieces[4+index]);
                        else if(p.getKind() == Piece.pieceFigure.PAWN)
                            System.out.print(chesspieces[5+index]);

                        System.out.print(" ");

                    }
                    else {
                        System.out.print("|    ");
                    }

                }
            }
                System.out.println("|");
        }
        System.out.print(" ");
        for(int q = 0; q<bricks.length;q++){
                System.out.print("_____");
        }
        System.out.println();
        System.out.println();
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

    public void whereTo(){
        System.out.println("Selecet a new place for the piece!");
    }

    public String newPieceLocation(){
        Scanner scan = new Scanner(System.in);
        String cords = scan.nextLine();
        return cords;
    }


}

package model;

/**
 * Created by ChristofferTronje on 2016-11-16.
 */
public class Player {

    Piece.color color;
    Piece[] pieces;
    int numberOfPieces;

    public Player(Piece.color col){
        color = col;
    }

    public Piece containsPiece(int x, int y){
        return getSpecificPiece(x,y);
    }

    public Piece.color getColor(){
        return color;
    }

    public void setPieces(Piece[] pieces){
        this.pieces = pieces;
        numberOfPieces = 0;
        for(int i = 0; i < pieces.length;i++) {
            if(pieces[i] != null) {
                System.out.println(pieces[i].getKind());
                numberOfPieces++;
            }
        }
    }

    private Piece getSpecificPiece(int x, int y){
        for(int i = 0; i <pieces.length;i++){
            if(pieces[i]!=null){
                if(pieces[i].getX() == x && pieces[i].getY() == y){
                    return pieces[i];
                }
            }
        }
       // System.out.println("X: " + x + " Y: " + y);
        return null;
    }

    public int getNumberOfPieces(){
        return numberOfPieces;
    }

    public Piece[] getPieces(){
        return pieces;
    }
}

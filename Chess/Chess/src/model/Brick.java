package model;

/**
 * Created by ChristofferTronje on 2016-11-17.
 */
public class Brick {

    Piece p;
    boolean isOccupied = false;
    int x;
    int y;

    public Brick(int x, int y){
        this.x = x;
        this.y = y;
    }


    public void occupieBrick(Piece p){
        this.p = p;
        isOccupied = true;
    }

    public boolean isOccupied(){
        return isOccupied;
    }

    public Piece getPiece() {
        return p;
    }

    public void removePiece(){
        p = null;
        isOccupied = false;
    }
}

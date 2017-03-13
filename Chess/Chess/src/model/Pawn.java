package model;

/**
 * Created by ChristofferTronje on 2016-11-03.
 */
public class Pawn extends Piece {

    private pieceFigure figure = pieceFigure.PAWN;
    private int x;
    private int y;
    private color col;


    public Pawn(color col) {
        this.col = col;
    }


    @Override
    public pieceFigure getKind() {
        return figure;
    }

    @Override
    public color getColor() {
        return col;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

}

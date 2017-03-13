package model;

/**
 * Created by ChristofferTronje on 2016-11-03.
 */
public class Bishop extends Piece {

    private pieceFigure figure = pieceFigure.BISHOP;
    private int x;
    private int y;
    private color col;

    public Bishop(color col){
        this.col = col;
    }


    public pieceFigure getKind(){
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

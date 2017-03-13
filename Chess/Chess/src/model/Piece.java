package model;

/**
 * Created by ChristofferTronje on 2016-11-03.
 */
abstract public class Piece {

    public enum pieceFigure{
        KING,QUEEN,KNIGHT,BISHOP,ROOK,PAWN
    }

    public enum color{
        BLACK,WHITE
    }

    public abstract pieceFigure getKind();
    public abstract color getColor();
    public abstract void setX(int x);
    public abstract void setY(int y);
    public abstract int getX();
    public abstract int getY();







}

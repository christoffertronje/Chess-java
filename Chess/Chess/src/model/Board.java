package model;

/**
 * Created by ChristofferTronje on 2016-11-18.
 */
public class Board {

    Brick[][] bricks;
    Piece[] pieces;

    public Board(){
        bricks = new Brick[8][8];
        pieces = new Piece[16];
        for(int y = 0; y<bricks.length;y++){
            for(int x = 0; x<bricks.length;x++){
                Brick b = new Brick(x,y);
                bricks[x][y] = b;
            }
        }


    }

    public Brick[][] getBricks(){
        return bricks;
    }

    public Brick getBrick(int x, int y){
        return bricks[y][x];
    }

    public void setBrick(Piece p){
        bricks[p.getY()][p.getX()].occupieBrick(p);
    }


}

package model;

/**
 * Created by ChristofferTronje on 2016-11-09.
 */
public class ValidMoves {

    public boolean validMove(Piece p, int newX, int newY, Board board) {

        Brick[][] bricks = board.getBricks();
        Piece[][] pieces = new Piece[8][8];

        for (int i = 0; i < pieces.length; i++) {
            for (int q = 0; q < pieces.length; q++) {
                if(bricks[i][q].isOccupied){
                    pieces[i][q] = bricks[i][q].getPiece();
                }
            }
        }


        if (p.getKind() == Piece.pieceFigure.PAWN){
            return pawnValidMove(p,newX,newY,pieces);

        }

        else if (p.getKind() == Piece.pieceFigure.BISHOP){
            return bishopValidMove(p,newX,newY,pieces);

        }

        else if (p.getKind() == Piece.pieceFigure.KNIGHT){
            return knightValidMove(p,newX,newY,pieces);

        }

        else if (p.getKind() == Piece.pieceFigure.KING){
            return kingValidMove(p,newX,newY,pieces);

        }

        else if (p.getKind() == Piece.pieceFigure.QUEEN){
            return queenValidMove(p,newX,newY,pieces);

        }

        else if (p.getKind() == Piece.pieceFigure.ROOK){
            return rookValidMove(p,newX,newY,pieces);

        }

        return false;

    }

    private boolean pawnValidMove(Piece p, int newX, int newY, Piece[][] pieces){
        int validMoveCounter;
        if (p.getColor()==Piece.color.WHITE)
            validMoveCounter = 1;
        else
            validMoveCounter = -1;
        /**
         * kollar att det är samma värde i x-led
         * kollar så att det bara är ett steg framåt i y-led
         * kollar så att nästa plats är tom
         */
        if(p.getX() == newX && (newY+validMoveCounter==p.getY()) && pieces[newY][newX] == null){    // ett steg framåt
            return true;
        }
        else if(newY+validMoveCounter==p.getY() && p.getX() == newX+1 && pieces[newY][newX] != null){
            if(pieces[newY][newX].getColor() != p.getColor())   // kolla så att dom inte har samma färg
                return true;
        }
        else if(newY+validMoveCounter==p.getY() && p.getX() == newX-1 && pieces[newY][newX] != null){
            if(pieces[newY][newX].getColor() != p.getColor())   // kolla så att dom inte har samma färg
                return true;
            }


        return false;
    }

    private boolean bishopValidMove(Piece p, int newX, int newY, Piece[][] pieces){

        if(sameColor(pieces[newY][newX],p))
            return false;

        int distanceX;
        int distanceY;

            distanceX = Math.abs(p.getX()-newX);
            distanceY = Math.abs(p.getY()-newY);

            if(distanceX == distanceY){
                int distance;
                int axisX;
                int axisY;

                if(p.getX()<newX) {
                    distance = newX-p.getX();
                    axisX = 1;
                }
                else {
                    distance = p.getX() - newX;
                    axisX = -1;
                }
                if (p.getY()<newY){
                    axisY = 1;
                }
                else
                    axisY = -1;

                for(int i = 1; i<distance;i++) {
                    if (pieceExist(pieces[p.getY()+(axisY*i)][p.getX()+(axisX*i)]))
                        return false;
                }
                return true;

            }

      return false;
    }

    private boolean kingValidMove(Piece p, int newX, int newY, Piece[][] pieces) {

        if (sameColor(pieces[newY][newX], p))
            return false;

        if ((p.getX() == newX - 1) || (p.getX() == newX + 1) || (p.getY() == newY - 1) || (p.getY() == newY + 1))
            return true;
        return false;
    }

    private boolean queenValidMove(Piece p, int newX, int newY, Piece[][] pieces){


        if(rookValidMove(p,newX,newY,pieces)){
            return true;
        }

        if(bishopValidMove(p,newX,newY,pieces)){
            return true;
        }

        return false;
    }

    private boolean rookValidMove(Piece p, int newX, int newY, Piece[][] pieces){
        if(sameColor(pieces[newY][newX],p))
            return false;

        int distance;
        int axis;

        if(p.getY() == newY && p.getX() != newX){   // rör sig i x-led

            if(p.getX()<newX) {
                distance = newX-p.getX();
                axis = 1;
            }
            else {
                distance = p.getX() - newX;
                axis = -1;
            }
            for(int i = 1; i<distance;i++) {
                if ((pieces[p.getY()][p.getX()+(axis*i)]) != null)
                    return false;
            }
            return true;
        }

        else if(p.getX() == newX && p.getY() != newY){      // rör sig i y-led
            if(p.getY()<newY) {
                distance = newY-p.getY();
                axis = 1;
            }
            else {
                distance = p.getY() - newY;
                axis = -1;
            }

            for(int i = 1; i<distance;i++) {
                if (pieces[p.getY()+(axis*i)][p.getX()] != null)
                    return false;
            }

            return true;
        }



        return false;
    }

    private boolean knightValidMove(Piece p, int newX, int newY, Piece[][] pieces){

        if(sameColor(pieces[newY][newX],p))
            return false;

        int distanceX = Math.abs(p.getX()-newX);
        int distanceY = Math.abs(p.getY()-newY);

        if((distanceX == distanceY*2) || (distanceX*2 == distanceY) && ((distanceX == 1) || (distanceY == 1)) )
            return true;

        return false;

    }

    private boolean pieceExist(Piece p){
        if (p == null)
            return false;
        else
            return true;
    }

    private boolean sameColor(Piece p, Piece p2){
        if(p != null) {
            if (p.getColor() == p2.getColor())
                return true;
        }
        return false;
    }

}

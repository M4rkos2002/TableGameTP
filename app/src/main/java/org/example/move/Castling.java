package org.example.move;


import org.example.commons.Board;
import org.example.commons.Coordinate;
import org.example.commons.Piece;
import org.example.commons.PieceName;
import org.example.move.prediction.Prediction;
import org.example.move.prediction.WillBeChecked;

public class Castling implements GameMovement {

    private final Prediction willBeCheck = new WillBeChecked();
    @Override
    public Boolean canUpdate(Coordinate from, Coordinate to, Board board) {
        if(this.verifyMove(to, board)){
            if(this.verifyPieces(from, this.findRook(to, board), board)) {
                if (!this.isBlocked(from, to, board)) {
                    return !this.isChecked(from, to, board);
                }
            }
        }
        return false;
    }

    @Override
    public Board update(Coordinate from, Coordinate to, Board board) {
        Piece king = board.getPieceFromCoord(from); Coordinate newKingPosition;
        Piece rook = board.getPieceFromCoord(to); Coordinate newRookPosition;
        if(from.getColumn() < to.getColumn()){ //right castling
            newRookPosition = new Coordinate(from.getRow(), from.getColumn() + 2);
            newKingPosition = new Coordinate(from.getRow(), from.getColumn() + 1);
        }
        else{
            newRookPosition = new Coordinate(from.getRow(), from.getColumn()-1);
            newKingPosition = new Coordinate(from.getRow(), from.getColumn() - 2);
        }
        return board.deleteByCoord(from).deleteByCoord(to).addByCoord(king, newKingPosition).addByCoord(rook, newRookPosition);
    }

    private Coordinate findRook(Coordinate to, Board board){
        if(to.getRow().equals(board.getRowsSize())){
            if(to.getColumn().equals(board.getColumnsSize()-1)){ return new Coordinate(to.getRow(), board.getColumnsSize());}
            else{ return new Coordinate(to.getRow(), 1);}
        }
        else{
            if(to.getColumn().equals(board.getColumnsSize()-1)){ return new Coordinate(1, board.getColumnsSize());}
            else{ return new Coordinate(1, 1); }
        }
    }

    private Boolean verifyMove(Coordinate to, Board board){
        return to.compareTo(new Coordinate(board.getRowsSize(), board.getColumnsSize() - 1)) > 0 ||
                to.compareTo(new Coordinate(board.getRowsSize(), 2)) > 0 ||
                to.compareTo(new Coordinate(1, board.getColumnsSize()-1)) > 0 ||
                to.compareTo(new Coordinate(1,2)) > 0;
    }
    private Boolean verifyPieces(Coordinate from, Coordinate to, Board board){
        Piece king = board.getPieceFromCoord(from);
        Piece rook = board.getPieceFromCoord(to);
        return king != null && rook != null &&
                king.getInitialPosition() && rook.getInitialPosition() &&
                king.getName().equals(PieceName.KING) && rook.getName().equals(PieceName.ROOK);
    }

    private Boolean isBlocked(Coordinate from, Coordinate to, Board board){
        if(from.getColumn() < to.getColumn()){
            for(int y = from.getColumn(); y <= to.getColumn(); y++){
                Piece current = board.getPieceFromCoord(new Coordinate(from.getRow(), y));
                if(current != null){
                    return true;
                }
            }
            return false;
        }
        else{
            for(int y = from.getColumn(); y < 0; y--){
                Piece current = board.getPieceFromCoord(new Coordinate(from.getRow(), y));
                if(current != null){
                    return true;
                }
            }
            return false;
        }
    }

    private Boolean isChecked(Coordinate from, Coordinate to, Board board){
        Piece king = board.getPieceFromCoord(from);
        if(willBeCheck.predict(from, to, board)){ return true;}
        else if(from.compareTo(to) > 0){ return false;}

        Coordinate newFrom;
        if(from.getColumn() < to.getColumn()){ newFrom = new Coordinate(from.getRow(), from.getColumn() + 1);}
        else{ newFrom = new Coordinate(from.getRow(), from.getColumn() - 1);}
        Board newBoard = board.deleteByCoord(from).addByCoord(king, newFrom); //update board
        return isChecked(newFrom, to, newBoard);
    }
}

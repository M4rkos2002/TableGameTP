package org.example.adapter;

import edu.austral.dissis.chess.gui.ChessPiece;
import edu.austral.dissis.chess.gui.PlayerColor;
import edu.austral.dissis.chess.gui.Position;
import org.example.commons.Color;
import org.example.commons.Coordinate;
import org.example.commons.Piece;
import org.example.commons.PieceName;

public class UIAdapter {


    public ChessPiece from_Piece_to_ChessPiece(Piece piece){
        PlayerColor color = this.from_Color_to_PlayerColor(piece.getColor());
        Position position = this.from_Coordinate_to_Position(piece.getPosition());
        String name = this.from_Name_to_String(piece.getName());
        return new ChessPiece(piece.getId(), color, position, name);
    }

    public PlayerColor from_Color_to_PlayerColor(Color color){
        return (color.equals(Color.WHITE)) ? PlayerColor.WHITE : PlayerColor.BLACK;
    }

    public Position from_Coordinate_to_Position(Coordinate coord){
        return new Position(coord.getRow(), coord.getColumn());
    }

    public String from_Name_to_String(PieceName name){
        if(name.equals(PieceName.CHECKER)){
            return PieceName.PAWN.toString().toLowerCase();
        }
        return name.toString().toLowerCase();
    }

    public Coordinate from_Position_to_Coordinate(Position position){
        return new Coordinate(position.getRow(), position.getColumn());
    }
}

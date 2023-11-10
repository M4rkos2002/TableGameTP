package org.example.commons;

import java.util.*;
import java.util.stream.Collectors;

public class Board {

    private final HashMap<Coordinate, Piece> board;
    private final Integer rowsQty;
    private final Integer columnsQty;

    public Board(Integer rows, Integer columns, List<Piece> pieces) {
        HashMap<Coordinate, Piece> map = new HashMap<>();
        for (int x = 1; x <= rows; x++) {
            for (int y = 1; y <= columns; y++) {
                Coordinate c = new Coordinate(x,y);
                Optional<Piece> piece = pieces.stream().filter(p -> p.getPosition().compareTo(c) > 0).findFirst();
                if(piece.isPresent()){map.put(c, piece.get());}
                else{map.put(new Coordinate(x, y), null);}
            }
        }
        this.board = map;
        this.rowsQty = rows;
        this.columnsQty = columns;
    }

    public Board(Integer rows, Integer columns, HashMap<Coordinate, Piece> map){
        this.rowsQty = rows;
        this.columnsQty = columns;
        this.board = map;
    }

    public Integer getColumnsSize(){
        return columnsQty;
    }
    public Integer getRowsSize(){
        return rowsQty;
    }

    public List<Piece> getInGamePieces(){
        return board.values().stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    public List<Piece> getInGameByColor(Color player){
        return board.values().stream().filter(p ->{if(p != null) {
                                                       return p.getColor().equals(player);
                                                    }
                                                    else{return false;}
                                                    })
                .collect(Collectors.toList());
    }

    public Piece getPieceFromCoord(Coordinate c){
        Piece piece = null;
        for(Coordinate coord: board.keySet()){
            if(c.compareTo(coord) > 0){
                piece = board.get(coord);
            }
        }
        return piece;
    }

    public Piece getPieceByNameAndColor(PieceName name, Color color){
        Optional<Piece> piece = board.values().stream().filter(p -> {if( p!=null){
                    return p.getName().equals(name) && p.getColor().equals(color);
                    }
                    else{return false;}})
                .findFirst();
        return piece.orElse(null);
    }

    public List<Coordinate> getCoordinates(){
        return new ArrayList<>(board.keySet());
    }

    public Board deleteByCoord(Coordinate coord){
        HashMap<Coordinate, Piece> newMap = new HashMap<>();
        for(Coordinate c:board.keySet()){
            if(c.compareTo(coord) > 0){ //equal coords
                newMap.put(c, null);
            }
            else{
                newMap.put(c, board.get(c));
            }
        }
        return new Board(rowsQty, columnsQty, newMap);
    }

    public Board addByCoord(Piece piece, Coordinate coord){
        HashMap<Coordinate, Piece> newMap = new HashMap<>();
        for(Coordinate c:board.keySet()){
            if(c.compareTo(coord) > 0){ //equal coords
                newMap.put(c, new Piece(piece.getId(), coord, false, piece.getColor(), piece.getName(), piece.getRules()));
            }
            else{
                newMap.put(c, board.get(c));
            }
        }
        return new Board(rowsQty, columnsQty, newMap);
    }

}

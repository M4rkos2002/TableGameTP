package org.example.commons;

public class Coordinate implements Comparable<Coordinate>{

    private final Integer row;
    private final Integer column;

    public Coordinate(Integer row, Integer column){
        this.row = row;
        this.column = column;
    }

    public Integer getRow(){
        return row;
    }
    public Integer getColumn(){
        return column;
    }

    @Override
    public int compareTo(Coordinate o) {
        if(row == o.getRow() && column == o.getColumn()){
            return 1;
        }
        return -1;
    }

    @Override
    public String toString(){
        return  "(" + row + " , " + column + ")";
    }
}

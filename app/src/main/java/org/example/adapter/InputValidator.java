package org.example.adapter;

import edu.austral.dissis.chess.gui.*;

import java.util.List;
import java.util.Optional;

public class InputValidator {

    public MoveResult validateInput(Move move, List<ChessPiece> pieces, PlayerColor currentPlayer){
        Optional<ChessPiece> fromPiece = pieces.stream().filter(p -> p.getPosition().equals(move.getFrom())).findFirst();
        Optional<ChessPiece> toPiece = pieces.stream().filter(p -> p.getPosition().equals(move.getTo())).findFirst();

        if (fromPiece.isEmpty()) {
            return new InvalidMove("There is no piece at position " + move.getFrom().toString());
        }
        else{
            if(!fromPiece.get().getColor().equals(currentPlayer)){
                return new InvalidMove("This piece does not belong to curren player " + currentPlayer.toString());
            }
            else if(toPiece.isPresent() && toPiece.get().getColor().equals(currentPlayer)){
                return new InvalidMove("Friendly fire!!");
            }
            else{
                return null;
            }
        }
    }
}

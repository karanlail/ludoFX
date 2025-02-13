package com.karan.ludofx.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class for Player object, every player must have color, name and assigned tokens
 */
public class Player {
    private final Color color;
    private final String name;
    private final List<Token> tokens;

    public Player(Color color, String name) {
        this.color = color;
        this.name = name;
        tokens= new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            tokens.add(new Token(getColor(), i));
        }
    }

    public Color getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    /**
     * returns a List of tokens that can be moved with the current diceValue
     * @param diceValue
     * @return
     */
    public List<Token> getMovableTokens(int diceValue){
        return tokens.stream()
                .filter(token -> token.movable(diceValue))
                .collect(Collectors.toList());
    }

    /**
     * checks if all the player's tokens have FINISHED
     * @return
     */
    public boolean hasWon(){
        return tokens.stream()
                .allMatch(Token::isFinished);
    }

    /**
     * This method allows to move a token with token's id
     */
    public void moveTokenWithId(int id, int steps){
        getMovableTokens(steps).stream()
                .filter(token -> token.getId() == id)
                .forEach(token -> token.move(steps));
    }
    @Override
    public String toString() {
        return "Player \n {" +
                "color=" + color +
                ", name='" + name + '\'' +
                "\ntokens=" + tokens +
                "}";
    }
}

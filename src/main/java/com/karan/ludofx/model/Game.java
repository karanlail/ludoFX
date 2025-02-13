package com.karan.ludofx.model;

import java.util.*;

/**
 * class for game object containing game state.
 * Sets a group of SAFE_CELLS on the board where tokens cannot be captured.
 * Keeps record of players in game and current player taking turn.
 * Uses LinkedList for players for faster removal and insertion
 */
class Game {
    private final Set<Integer> SAFE_CELLS = new HashSet<>(Set.of(0,8,13,21,26,34,39,47));
    private final List<Player> players;
    private Player currentPlayer;

    public Game(int numberOfPlayers, List<String> playerNames) {
        this.players = new LinkedList<>();
        for (int i = 0; i < numberOfPlayers; i++) {
            players.add(new Player(Color.colorToBoardPositioning(i+1, numberOfPlayers),playerNames.get(i)));
        }
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * next player in line is saved in currentPLayer, and also moved from front of line to end of line.
     */
    public void nextPlayer(){
        currentPlayer = players.removeFirst();
        players.addLast(currentPlayer);
    }

    public List<Player> getPlayers() {
        return players;
    }

    /**
     * checks if any player has won the game
     * @return Winner, Null
     */
    public Player winnerCheck(){
        for (Player player:players){
            if(player.hasWon()) return player;
        }
        return null;
    }

    /**
     * Given the token of a player, captures tokens of other players if on same cell, unless cell is a SAFE_ZONE
     * @param playerToken
     * @return captured tokens
     */
    public List<Token> capture(Token playerToken){
        List<Token> captured = new ArrayList<>();
        players.stream()
                .filter(player -> player!=currentPlayer)
                .forEach(player -> player.getTokens().stream()
                        .filter(token -> token.isActive() && token.getPosition()== playerToken.getPosition())
                        .filter(token -> !SAFE_CELLS.contains(token.getPosition()))
                        .forEach(token -> {
                            token.captured();
                            captured.add(token);
                        }));
        return captured;
    }

}

package com.karan.ludofx.model;


import com.karan.ludofx.controllers.GameController;
import com.karan.ludofx.controllers.TokenView;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class for creation of a separation layer between game and GUI, also manages the general turn mechanics.
 * Many methods call gameController methods for changing graphics
 */
public class GameManager {
    private final Game game;
    private final Dice dice;
    private GameController gameController;
    private int currentDiceValue=0;
    private boolean dice_rolled;
    private final Map<Token, TokenView> tokenTokenViewMap;

    public GameManager(int numberOfPlayers, List<String> names) {
        this.game = new Game(numberOfPlayers,names);
        this.dice = new Dice();
        tokenTokenViewMap = new HashMap<>();
    }

    /**
     * initializes gameController, calls tokenBuilder() and nextPlayer()
     * @param gameController
     */
    public void begin(GameController gameController) {
        this.gameController=gameController;
        tokenBuilder(game.getPlayers());
        nextPlayer();
    }

    /**
     * Places tokens on the GUI, by creating TokenView for each token and placing them in Map and calling updateTokenView,
     * Creates handlers to handle mouseClicks on tokens, by calling handlersBuild for each token
     * @param players
     */
    private void tokenBuilder(List<Player> players){
        players.forEach(player -> player.getTokens().forEach(token -> tokenTokenViewMap.put(token, new TokenView(token))));
        tokenTokenViewMap.forEach(this::updateTokenView);
        tokenTokenViewMap.forEach(this::handlersBuild);
    }

    private Map<Token, TokenView> getTokenTokenViewMap() {
        return tokenTokenViewMap;
    }

    /**method overriding
     * @param token
     */
    private void updateTokenView(Token token){
        TokenView tokenView = getTokenTokenViewMap().get(token);
        this.updateTokenView(token, tokenView);
    }

    /**
     * calls placeTokenView in gameController for placing token
     * Separates behaviour according to tokeState, as BASE tokens require placement according to id, other tokens require placement according to token position
     * @param token
     * @param tokenView
     */
    private void updateTokenView(Token token, TokenView tokenView){
        if (token.isBase()) gameController.placeTokenView(token.getColor().toString(), token.getId(), token.getTokenState().toString(), tokenView);
        else gameController.placeTokenView(token.getColor().toString(), token.getPosition(), token.getTokenState().toString(), tokenView);
    }

    /**
     * Checks if player already rolled, otherwise rolls dice.
     * Checks if player has movable tokens with dice rolled, if not calls nextPlayer()
     * If player has one and only movable token, moves automatically.
     */
    public void rollDice(){
        if(isDice_rolled()) {
            gameController.setPlayerAction("Dice already rolled, choose a token");
            return;
        }
        currentDiceValue=dice.roll();
        gameController.setDiceImage(currentDiceValue);
        List<Token> movable = game.getCurrentPlayer().getMovableTokens(getCurrentDiceValue());
        if(movable.isEmpty()){
            gameController.setPlayerAction("No tokens to move with this dice");
            PauseTransition pause = new PauseTransition(Duration.millis(500));
            pause.setOnFinished(_ -> nextPlayer());
            pause.play();
        } else if (movable.size()==1) {
            moveToken(movable.getFirst());
        } else {
            setDice_rolled(true);
            gameController.setPlayerAction("choose a token");
        }
    }

    private int getCurrentDiceValue() {
        return currentDiceValue;
    }

    private void setDice_rolled(boolean dice_rolled) {
        this.dice_rolled = dice_rolled;
    }

    private boolean isDice_rolled() {
        return dice_rolled;
    }

    /**
     * calls nextPlayer method of game and changes dice_rolled to false for next player.
     */
    private void nextPlayer(){
        game.nextPlayer();
        gameController.setCurrentPlayer(game.getCurrentPlayer().getName(), game.getCurrentPlayer().getColor().toString());
        setDice_rolled(false);
        gameController.setPlayerAction("roll your dice");
    }

    /**
     * Moves token chosen by player.
     * Checks if player has previously rolled the dice.
     * Checks if the chosen token is movable and one of player's own tokens
     * @param token
     */

    private void tokenChosen(Token token){
        if(!isDice_rolled()){
            gameController.setPlayerAction("Must roll dice first");
            return;
        }
        if(!game.getCurrentPlayer().getTokens().contains(token)) {
            gameController.setPlayerAction("not your token");
        } else if (!game.getCurrentPlayer().getMovableTokens(getCurrentDiceValue()).contains(token)) {
            gameController.setPlayerAction("cannot move this token");
        }
        else moveToken(token);
    }

    /**
     * moves a token, capturing other tokens if possible and ensuring GUI update.
     * grants another turn if player rolled a 6 or captured least one token.
     * Checks if there's a winner
     * @param token
     */
     private void moveToken(Token token){
         setDice_rolled(false);
         token.move(getCurrentDiceValue());
         updateTokenView(token);
         List<Token> captured = game.capture(token);
         captured.forEach(this::updateTokenView);
         if (getCurrentDiceValue() != 6 && captured.isEmpty()) {
             nextPlayer();
         } else {
             setDice_rolled(false);
             gameController.setPlayerAction("Take another turn, roll dice");
         }
         Player possibleWinner = game.winnerCheck();
         if(possibleWinner != null){
             gameController.showWinner(possibleWinner.getName());
         }
     }

    /**
     * creates handlers for mouseclick event for tokens. Contains debug prints.
     * @param token
     * @param tokenView
     */
    private void handlersBuild(Token token, TokenView tokenView){
        tokenView.getNode().setOnMouseClicked(e -> {
            System.out.println("Token clicked in GUI: " + token);
            tokenChosen(token);
            e.consume();
        });
        System.out.println("Handler attached to: " + token);
    }
}

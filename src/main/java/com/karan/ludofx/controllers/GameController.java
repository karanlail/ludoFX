package com.karan.ludofx.controllers;

import com.karan.ludofx.App;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class for controlling the GUI of the game.
 * Each cell of the board is represented by a StackPane, named accordingly with formats such as:
 * "trackX" for main outer track
 * "trackCOLORX" for home track of each color leading to finish.
 * "trackBaseX" for StackPanes in base
 * the respective HashMaps built in the constructor follow this format and are named accordingly, with "Cells" name ending
 */
public class GameController {

    @FXML private StackPane track0;
    @FXML private StackPane track1;
    @FXML private StackPane track2;
    @FXML private StackPane track3;
    @FXML private StackPane track4;
    @FXML private StackPane track5;
    @FXML private StackPane track6;
    @FXML private StackPane track7;
    @FXML private StackPane track8;
    @FXML private StackPane track9;
    @FXML private StackPane track10;
    @FXML private StackPane track11;
    @FXML private StackPane track12;
    @FXML private StackPane track13;
    @FXML private StackPane track14;
    @FXML private StackPane track15;
    @FXML private StackPane track16;
    @FXML private StackPane track17;
    @FXML private StackPane track18;
    @FXML private StackPane track19;
    @FXML private StackPane track20;
    @FXML private StackPane track21;
    @FXML private StackPane track22;
    @FXML private StackPane track23;
    @FXML private StackPane track24;
    @FXML private StackPane track25;
    @FXML private StackPane track26;
    @FXML private StackPane track27;
    @FXML private StackPane track28;
    @FXML private StackPane track29;
    @FXML private StackPane track30;
    @FXML private StackPane track31;
    @FXML private StackPane track32;
    @FXML private StackPane track33;
    @FXML private StackPane track34;
    @FXML private StackPane track35;
    @FXML private StackPane track36;
    @FXML private StackPane track37;
    @FXML private StackPane track38;
    @FXML private StackPane track39;
    @FXML private StackPane track40;
    @FXML private StackPane track41;
    @FXML private StackPane track42;
    @FXML private StackPane track43;
    @FXML private StackPane track44;
    @FXML private StackPane track45;
    @FXML private StackPane track46;
    @FXML private StackPane track47;
    @FXML private StackPane track48;
    @FXML private StackPane track49;
    @FXML private StackPane track50;
    @FXML private StackPane track51;

    @FXML private StackPane trackBaseGreen0;
    @FXML private StackPane trackBaseGreen1;
    @FXML private StackPane trackBaseGreen2;
    @FXML private StackPane trackBaseGreen3;

    @FXML private StackPane trackBaseBlue0;
    @FXML private StackPane trackBaseBlue1;
    @FXML private StackPane trackBaseBlue2;
    @FXML private StackPane trackBaseBlue3;

    @FXML private StackPane trackBaseYellow0;
    @FXML private StackPane trackBaseYellow1;
    @FXML private StackPane trackBaseYellow2;
    @FXML private StackPane trackBaseYellow3;

    @FXML private StackPane trackBaseRed0;
    @FXML private StackPane trackBaseRed1;
    @FXML private StackPane trackBaseRed2;
    @FXML private StackPane trackBaseRed3;

    @FXML private StackPane trackGreen0;
    @FXML private StackPane trackGreen1;
    @FXML private StackPane trackGreen2;
    @FXML private StackPane trackGreen3;
    @FXML private StackPane trackGreen4;

    @FXML private StackPane trackBlue0;
    @FXML private StackPane trackBlue1;
    @FXML private StackPane trackBlue2;
    @FXML private StackPane trackBlue3;
    @FXML private StackPane trackBlue4;

    @FXML private StackPane trackYellow0;
    @FXML private StackPane trackYellow1;
    @FXML private StackPane trackYellow2;
    @FXML private StackPane trackYellow3;
    @FXML private StackPane trackYellow4;

    @FXML private StackPane trackRed0;
    @FXML private StackPane trackRed1;
    @FXML private StackPane trackRed2;
    @FXML private StackPane trackRed3;
    @FXML private StackPane trackRed4;

    @FXML private Label playerAction;
    @FXML private Label currentPlayer;
    @FXML private Pane diceImage;
    @FXML private Button buttonRollDice;

    private Map<Integer, StackPane> trackCells;
    private Map<Integer, StackPane> trackGreenCells;
    private Map<Integer, StackPane> trackBlueCells;
    private Map<Integer, StackPane> trackYellowCells;
    private Map<Integer, StackPane> trackRedCells;
    private Map<Integer, StackPane> trackBaseGreenCells;
    private Map<Integer, StackPane> trackBaseBlueCells;
    private Map<Integer, StackPane> trackBaseYellowCells;
    private Map<Integer, StackPane> trackBaseRedCells;

    private Map<Integer, ImageView> diceImages;

    /**
     * Class constructor initializes a series of HashMaps to have organized access to every trackCell on the board
     */
    public GameController() {
        this.trackCells=new HashMap<>();
        this.trackGreenCells=new HashMap<>();
        this.trackBlueCells=new HashMap<>();
        this.trackYellowCells=new HashMap<>();
        this.trackRedCells=new HashMap<>();
        this.trackBaseBlueCells=new HashMap<>();
        this.trackBaseGreenCells=new HashMap<>();
        this.trackBaseYellowCells=new HashMap<>();
        this.trackBaseRedCells=new HashMap<>();
        this.diceImages=new HashMap<>();
    }

    /**
     * Calls boardBuilder().
     * Creates ImageViews for every dice value possible.
     * Calls begin() in gameManager
     */
    @FXML
    public void initialize(){
        System.out.println("GameController initialized");
        boardBuilder();
        for (int i = 1; i <= 6; i++) {
            diceImages.put(i, new ImageView(App.imageLoader("assets/diceImages/dice"+i+".png")));
            diceImages.get(i).setFitWidth(150);
            diceImages.get(i).setFitHeight(150);
        }
        setDiceImage(1);
        App.getGameManager().begin(this);
    }

    /**
     * changes current player label, with a text color corresponding to the color of the Player.
     * @param name name of the current player
     * @param color color of the current player
     */
    public void setCurrentPlayer(String name,String color){
        currentPlayer.setText(name.toUpperCase());
        currentPlayer.setTextFill(Color.valueOf(color));
    }

    /**
     * Changes the player action label, which tells the current player what the player should do at current point in game.
     * @param action String representing the action for player
     */
    public void setPlayerAction(String action){
        playerAction.setText(action);
    }

    /**
     * method for Roll Dice button in GUI
     */
    public void rollDice(){
        App.getGameManager().rollDice();
    }

    /**
     * set's the Dice image in GUI corresponding to the value of the dice, removes previous ImageView node and places new one
     * @param value
     */
    public void setDiceImage(int value){
        diceImage.getChildren().clear();
        diceImage.getChildren().add(diceImages.get(value));
    }

    /**
     * If the token View was already assigned to a StackPane, removes from that StackPane before new placement.
     * Places a TokenView node on the corresponding StackPane using either the id, if token is on Base, or token position, if on track.
     * Uses previously initialized HashMaps to find the correct StackPane, considering also the color of the token when necessary.
     * Also ensures that StackPane is not interfering with the tokenView and that tokenView is visible, on front, and not mouse transparent.
     * @param color token color
     * @param id token id for BASE
     * @param location token position
     * @param tokenView TokenView node to be placed
     */
    public void placeTokenView(String color, int id, String location, TokenView tokenView){
        if (tokenView.getNode().getParent() != null && tokenView.getNode().getParent() instanceof StackPane) {
            StackPane oldCell = (StackPane) tokenView.getNode().getParent();
            oldCell.getChildren().remove(tokenView.getNode());
        }
        StackPane targetCell = null;
        switch (location) {
            case "BASE" -> {
                switch (color){
                    case "GREEN" -> targetCell = trackBaseGreenCells.get(id);
                    case "BLUE" -> targetCell = trackBaseBlueCells.get(id);
                    case "YELLOW" -> targetCell = trackBaseYellowCells.get(id);
                    case "RED" -> targetCell = trackBaseRedCells.get(id);
                }
            }
            case "HOME" -> {
                switch (color){
                    case "GREEN" -> targetCell = trackGreenCells.get(id);
                    case "BLUE" -> targetCell = trackBlueCells.get(id);
                    case "YELLOW" -> targetCell = trackYellowCells.get(id);
                    case "RED" -> targetCell = trackRedCells.get(id);
                }
            }
            case null, default -> targetCell = trackCells.get(id);
        }
        if (targetCell != null) {
            targetCell.getChildren().add(tokenView.getNode());
        } else {
            System.out.println("ERROR: Could not place token in GUI!");
        }
        targetCell.setPickOnBounds(false);
        targetCell.setMouseTransparent(false);
        targetCell.setDisable(false);

        // Force token to be interactive
        tokenView.getNode().toFront();
        tokenView.getNode().setPickOnBounds(true);
        tokenView.getNode().setMouseTransparent(false);
        tokenView.getNode().setDisable(false);
        tokenView.getNode().setOpacity(1.0);
    }

    /**
     * Calls trackBuilder method for each StackPane name format, for each color, effectively creating HasMaps for entire board
     */
    private void boardBuilder(){
        try{
            trackBuilder("track");
            List<String> colors = new ArrayList<>();
            colors.add("Blue");
            colors.add("Yellow");
            colors.add("Green");
            colors.add("Red");
            for (String color:colors){
                trackBuilder("trackBase"+color);
                trackBuilder("track"+color);
            }
        }catch (NoSuchFieldException e){
            System.out.println("Error while building tracks");
        }
    }

    /**
     * trackBuilder takes the HashMap previously initialized and inserts the FXML StackPanes injected in controller, for later use.
     * uses Java.Reflection
     * Requires the variable names to respect very precise format described in Class documentation.
     * @param category String containing the initial part of the track name format
     * @throws NoSuchFieldException handles in boardBuilder
     */
    @SuppressWarnings("unchecked")
    private void trackBuilder(String category) throws NoSuchFieldException {
        // Hard coding every StackPane into the HashMap would have been a tedious process
        Field trackType = this.getClass().getDeclaredField(category + "Cells");
        if (trackType.getType().equals(Map.class)) {
            try {
                trackType.setAccessible(true);
                HashMap<Integer, StackPane> currentMap = (HashMap<Integer, StackPane>) trackType.get(this);
                Field[] fields = this.getClass().getDeclaredFields();
                for (Field field : fields) {
                    if (field.getName().matches(category + "\\d+") && field.getType().equals(StackPane.class)) {
                        field.setAccessible(true);
                        try {
                            StackPane pane = (StackPane) field.get(this);
                            // Extract the numeric part of the field name, e.g. "track2" -> 2
                            String numPart = field.getName().substring(category.length()); // "track" has 4 characters
                            int index = Integer.parseInt(numPart);
                            currentMap.put(index,(StackPane) field.get(this));
                        } catch (IllegalAccessException | NumberFormatException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Error accessing main Field in track Builder");
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * On winner found by gameManager, this method is called to show the winner with an alert and close the stage
     * @param winnerName
     */
    public void showWinner(String winnerName) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText("We have a Winner!");
        alert.setContentText(winnerName + " has won the game!");
        alert.showAndWait();
        App.stage.close();
    }
}

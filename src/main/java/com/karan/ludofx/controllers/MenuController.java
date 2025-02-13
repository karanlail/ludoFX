package com.karan.ludofx.controllers;

import com.karan.ludofx.App;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

/** Controller class for initial game Menu.
 * MenuController allows to choose number of player 2-4 and personalize names for each player.
 * Also provides default names in "playerX" format.
 *
 */
public class MenuController {

    @FXML TextField player1;
    @FXML TextField player2;
    @FXML TextField player3;
    @FXML TextField player4;

    private void initiateGame(int numberOfPlayers, List<String> names){
        App.setGameManager(numberOfPlayers,names);
        App.changeSceneToGame();
    }
    @FXML
    private void initiateTwoPlayers(){
        int n=2;
        List<String> names = new ArrayList<>();
        names.add(player1.getText());
        names.add(player2.getText());
        initiateGame(n,names);
    }
    @FXML
    private void initiateThreePlayers(){
        int n=3;
        List<String> names = new ArrayList<>();
        names.add(player1.getText());
        names.add(player2.getText());
        names.add(player3.getText());
        initiateGame(n,names);
    }
    @FXML
    private void initiateFourPlayers(){
        int n=4;
        List<String> names = new ArrayList<>();
        names.add(player1.getText());
        names.add(player2.getText());
        names.add(player3.getText());
        names.add(player4.getText());
        initiateGame(n,names);
    }
}

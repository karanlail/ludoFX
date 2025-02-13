package com.karan.ludofx.controllers;

import com.karan.ludofx.App;
import com.karan.ludofx.model.Color;
import com.karan.ludofx.model.Token;
import javafx.scene.image.ImageView;

/**
 * Class for managing an ImageView node for a token.
 */
public class TokenView {
    private final ImageView imageView;

    /**
     * Constructor for TokenView. Takes a token as parameter and creates and ImageView.
     * Also sets height and width
     * @param token
     */
    public TokenView(Token token) {
        this.imageView = new ImageView(App.imageLoader(getImagePathByColor(token.getColor())));
        imageView.setPreserveRatio(false);
        imageView.setFitHeight(48);
        imageView.setFitWidth(48);
    }
    public ImageView getNode(){
        return imageView;
    }

    /**
     * Gets the RELATIVE (assuring portability) path of the image in resources for the token, respective to the color of the token.
     * @param color
     * @return String representing the relative path
     */
    private String getImagePathByColor(Color color) {
        return switch (color) {
            case GREEN -> getDirectoryForImagePath() + "green.png";
            case RED -> getDirectoryForImagePath() + "red.png";
            case BLUE -> getDirectoryForImagePath() + "blue.png";
            case YELLOW -> getDirectoryForImagePath() + "yellow.png";
        };
    }

    /**
     * Gets the relative path in resources to the directory where all the images for tokens are stored.
     * @return String representing the path of the directory
     */
    private String getDirectoryForImagePath(){
        return "assets/tokenImages/";
    }
}

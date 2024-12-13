package com.example.demo.ui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The GameOverImage class is responsible for displaying the "Game Over" image on the screen
 * when the game ends.
 */
public class GameOverImage extends ImageView {

	private static final String IMAGE_NAME = "/com/example/demo/images/gameover.png";

	/**
	 * Constructor for initializing the GameOverImage.
	 *
	 * @param xPosition the X position to place the "Game Over" image on the screen
	 * @param yPosition the Y position to place the "Game Over" image on the screen
	 */
	public GameOverImage(double xPosition, double yPosition) {
		setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));
		setLayoutX(xPosition);
		setLayoutY(yPosition);
	}
}

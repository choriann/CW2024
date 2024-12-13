package com.example.demo.ui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The WinImage class represents the visual element that is displayed when the player wins the game.
 * It shows an image indicating the win state and can be made visible when the game is won.
 */
public class WinImage extends ImageView {

	private static final String IMAGE_NAME = "/com/example/demo/images/youwin.png";  // The path to the win image
	private static final int HEIGHT = 500;  // The height of the win image
	private static final int WIDTH = 600;   // The width of the win image

	/**
	 * Constructor for initializing the win image.
	 * Sets the image, position, and visibility of the win image.
	 * Initially, the win image is not visible.
	 *
	 * @param xPosition the X-coordinate for the win image's position
	 * @param yPosition the Y-coordinate for the win image's position
	 */
	public WinImage(double xPosition, double yPosition) {
		this.setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));
		this.setVisible(false);  // Initially hidden
		this.setFitHeight(HEIGHT);  // Set the height of the image
		this.setFitWidth(WIDTH);    // Set the width of the image
		this.setLayoutX(xPosition);  // Set the X position
		this.setLayoutY(yPosition);  // Set the Y position
	}

	/**
	 * Makes the win image visible, indicating the player has won the game.
	 */
	public void showWinImage() {
		this.setVisible(true);
	}
}

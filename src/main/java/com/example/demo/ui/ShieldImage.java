package com.example.demo.ui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The ShieldImage class represents a visual shield in the game.
 * It is an image that can be shown or hidden to represent the shield being activated or deactivated.
 */
public class ShieldImage extends ImageView {

	private static final String IMAGE_NAME = "/images/shield.png";  // The path to the shield image
	private static final int SHIELD_SIZE = 200;                      // The size of the shield

	/**
	 * Constructor for initializing the shield image.
	 * Sets the initial position of the shield image and its visibility to false.
	 *
	 * @param xPosition the X-coordinate for the shield's position
	 * @param yPosition the Y-coordinate for the shield's position
	 */
	public ShieldImage(double xPosition, double yPosition) {
		this.setLayoutX(xPosition);
		this.setLayoutY(yPosition);
		this.setImage(new Image(getClass().getResource("/com/example/demo/images/shield.png").toExternalForm()));
		this.setVisible(false);  // Shield is initially hidden
		this.setFitHeight(SHIELD_SIZE);  // Set the shield's height
		this.setFitWidth(SHIELD_SIZE);   // Set the shield's width
	}

	/**
	 * Makes the shield visible, indicating that the shield is activated.
	 */
	public void showShield() {
		this.setVisible(true);
	}

	/**
	 * Hides the shield, indicating that the shield is deactivated.
	 */
	public void hideShield() {
		this.setVisible(false);
	}
}

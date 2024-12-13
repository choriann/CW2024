package com.example.demo.actors.actorlogic;

import java.util.Objects;
import javafx.scene.image.*;

/**
 * Abstract class representing an active actor in the game.
 */
public abstract class ActiveActor extends ImageView {

	private static final String IMAGE_LOCATION = "/com/example/demo/images/";

	public ActiveActor(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		// Ensure the image path is valid and non-null
		String imagePath = Objects.requireNonNull(
				getClass().getResource(IMAGE_LOCATION + imageName),
				"Image resource not found: " + IMAGE_LOCATION + imageName
		).toExternalForm();

		this.setImage(new Image(imagePath));
		this.setLayoutX(initialXPos);
		this.setLayoutY(initialYPos);
		this.setFitHeight(imageHeight);
		this.setPreserveRatio(true);
	}

	/**
	 * Updates the position of the actor.
	 */
	public abstract void updatePosition();

	/**
	 * Moves the actor horizontally by a specified amount.
	 *
	 * @param horizontalMove the amount to move horizontally
	 */
	protected void moveHorizontally(double horizontalMove) {
		this.setTranslateX(getTranslateX() + horizontalMove);
	}

	/**
	 * Moves the actor vertically by a specified amount.
	 *
	 * @param verticalMove the amount to move vertically
	 */
	protected void moveVertically(double verticalMove) {
		this.setTranslateY(getTranslateY() + verticalMove);
	}
}

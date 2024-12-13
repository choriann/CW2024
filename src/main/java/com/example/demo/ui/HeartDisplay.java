package com.example.demo.ui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * The HeartDisplay class is responsible for displaying the health hearts of the player.
 * It allows the visual representation of the player's health using heart images.
 */
public class HeartDisplay {

	private static final String HEART_IMAGE_NAME = "/com/example/demo/images/heart.png";
	private static final int HEART_HEIGHT = 50;
	private static final int INDEX_OF_FIRST_ITEM = 0;

	private HBox container;  // The container for the heart images
	private double containerXPosition;  // X position of the container
	private double containerYPosition;  // Y position of the container
	private int numberOfHeartsToDisplay;  // Number of hearts to display

	/**
	 * Constructor for initializing the HeartDisplay.
	 *
	 * @param xPosition the X position to place the container of hearts on the screen
	 * @param yPosition the Y position to place the container of hearts on the screen
	 * @param heartsToDisplay the number of hearts to display
	 */
	public HeartDisplay(double xPosition, double yPosition, int heartsToDisplay) {
		this.containerXPosition = xPosition;
		this.containerYPosition = yPosition;
		this.numberOfHeartsToDisplay = heartsToDisplay;
		initializeContainer();
		initializeHearts();
	}

	/**
	 * Initializes the container (HBox) that holds the heart images.
	 */
	private void initializeContainer() {
		container = new HBox();
		container.setLayoutX(containerXPosition);
		container.setLayoutY(containerYPosition);
	}

	/**
	 * Initializes the hearts inside the container based on the number of hearts to display.
	 * Each heart is represented as an ImageView object.
	 */
	private void initializeHearts() {
		for (int i = 0; i < numberOfHeartsToDisplay; i++) {
			ImageView heart = new ImageView(new Image(getClass().getResource(HEART_IMAGE_NAME).toExternalForm()));
			heart.setFitHeight(HEART_HEIGHT);
			heart.setPreserveRatio(true);
			container.getChildren().add(heart);
		}
	}

	/**
	 * Removes one heart from the container.
	 * This method is called when the player loses health.
	 */
	public void removeHeart() {
		if (!container.getChildren().isEmpty())
			container.getChildren().remove(INDEX_OF_FIRST_ITEM);
	}

	/**
	 * Returns the container holding the heart images.
	 *
	 * @return the container (HBox) of heart images
	 */
	public HBox getContainer() {
		return container;
	}
}

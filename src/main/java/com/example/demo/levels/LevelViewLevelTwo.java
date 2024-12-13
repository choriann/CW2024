package com.example.demo.levels;

import com.example.demo.ui.ShieldImage;
import javafx.scene.Group;

/**
 * LevelViewLevelTwo is a subclass of LevelView that adds support for displaying and hiding the shield image.
 */
public class LevelViewLevelTwo extends LevelView {

	private final Group root;
	private final ShieldImage shieldImage;

	/**
	 * Constructor to initialize the level view for Level Two.
	 * Sets up the shield image and calls the superclass constructor.
	 *
	 * @param root the root group where the UI elements will be added
	 * @param heartsToDisplay the number of hearts to display initially
	 */
	public LevelViewLevelTwo(Group root, int heartsToDisplay) {
		super(root, heartsToDisplay, 0);
		this.root = root;
		this.shieldImage = new ShieldImage(50, 50);  // Initialize shield image with position
	}

	/**
	 * Displays the shield image on the screen.
	 * This method checks if the shield is already displayed before adding it to the root group.
	 */
	public void showShield() {
		if (!root.getChildren().contains(shieldImage)) {
			root.getChildren().add(shieldImage);
		}
		shieldImage.showShield();
	}

	/**
	 * Hides the shield image from the screen.
	 * This method removes the shield from the root group and hides it.
	 */
	public void hideShield() {
		shieldImage.hideShield();
	}
}

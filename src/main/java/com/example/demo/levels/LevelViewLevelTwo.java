package com.example.demo.levels;

import com.example.demo.ui.ShieldImage;
import javafx.scene.Group;

public class LevelViewLevelTwo extends LevelView {

	private final Group root;
	private final ShieldImage shieldImage;

	public LevelViewLevelTwo(Group root, int heartsToDisplay) {
		super(root, heartsToDisplay, 0);
		this.root = root;
		this.shieldImage = new ShieldImage(50, 50);
	}

	public void showShield() {
		if (!root.getChildren().contains(shieldImage)) {
			root.getChildren().add(shieldImage);
		}
		shieldImage.showShield();
	}

	public void hideShield() {
		shieldImage.hideShield();
	}
}

package com.example.demo.levels;

import com.example.demo.actors.Boss;

public class LevelTwo extends LevelParent {

	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background2.jpg";
	private static final int PLAYER_INITIAL_HEALTH = 5;
	private Boss boss;
	private LevelViewLevelTwo levelView;

	public LevelTwo(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
	}

	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}

	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
		} else if (boss.isDestroyed()) {
			goToNextLevel("com.example.demo.levels.LevelThree"); // Transition to Level 3
		}
	}


	@Override
	protected void spawnEnemyUnits() {
		if (getCurrentNumberOfEnemies() == 0) {
			boss = new Boss(this.levelView);
			addEnemyUnit(boss);
			getRoot().getChildren().addAll(boss.getHealthBar());
		}
	}

	@Override
	protected LevelView instantiateLevelView() {
		this.levelView = new LevelViewLevelTwo(getRoot(), PLAYER_INITIAL_HEALTH);
		return this.levelView;
	}

	@Override
	protected int getKillsToAdvance() {
		return 0;
	}
}

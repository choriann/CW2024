package com.example.demo.levels;

import com.example.demo.actors.Boss;

public class LevelTwo extends LevelParent {

	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background2.jpg";
	private static final int PLAYER_INITIAL_HEALTH = 5;
	private Boss boss;
	private LevelViewLevelTwo levelView;

	/**
	 * Constructor to initialize the second level of the game.
	 *
	 * @param screenHeight the height of the game screen
	 * @param screenWidth the width of the game screen
	 */
	public LevelTwo(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
	}

	/**
	 * Initializes the friendly units, which is the user's plane.
	 */
	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}

	/**
	 * Checks if the game is over. The game ends when either the user is destroyed or the boss is defeated.
	 * If the boss is destroyed, the game will transition to Level 3.
	 */
	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
		} else if (boss.isDestroyed()) {
			goToNextLevel("com.example.demo.levels.LevelThree"); // Transition to Level 3
		}
	}

	/**
	 * Spawns enemy units for this level. The level only spawns the boss once all the enemies are defeated.
	 */
	@Override
	protected void spawnEnemyUnits() {
		if (getCurrentNumberOfEnemies() == 0) {
			boss = new Boss(this.levelView);
			addEnemyUnit(boss);
			getRoot().getChildren().addAll(boss.getHealthBar());
		}
	}

	/**
	 * Instantiates the level view for this level, which includes the health display and other UI elements.
	 *
	 * @return the level view for this level
	 */
	@Override
	protected LevelView instantiateLevelView() {
		this.levelView = new LevelViewLevelTwo(getRoot(), PLAYER_INITIAL_HEALTH);
		return this.levelView;
	}

	/**
	 * Returns the number of kills required to advance to the next level.
	 * This level does not require kills to advance, as the boss must be defeated to win.
	 *
	 * @return the number of kills required to advance (always 0 for this level)
	 */
	@Override
	protected int getKillsToAdvance() {
		return 0;
	}
}

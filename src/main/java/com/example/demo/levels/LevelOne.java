package com.example.demo.levels;

import com.example.demo.actors.actorlogic.ActiveActorDestructible;
import com.example.demo.actors.EnemyPlane;

/**
 * Represents the first level of the game.
 * Handles spawning enemies, checking game over conditions, and transitioning to the next level.
 */
public class LevelOne extends LevelParent {

	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background1.jpg"; // Background image for level one
	private static final String NEXT_LEVEL = "com.example.demo.levels.LevelTwo"; // Next level class name
	private static final int TOTAL_ENEMIES = 5; // Maximum number of enemies in the level
	private static final int KILLS_TO_ADVANCE = 10; // Number of kills required to advance to the next level
	private static final double ENEMY_SPAWN_PROBABILITY = .20; // Probability of spawning an enemy each frame
	private static final int PLAYER_INITIAL_HEALTH = 5; // Initial health of the player's plane

	/**
	 * Constructs the first level.
	 *
	 * @param screenHeight the height of the game screen
	 * @param screenWidth the width of the game screen
	 */
	public LevelOne(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
		finished = false; // Reset the flag for a new level
	}

	/**
	 * Checks if the game is over by determining if the user is destroyed
	 * or has reached the kill target to advance to the next level.
	 */
	@Override
	protected void checkIfGameOver() {
		if (finished) return; // Prevent redundant calls
		if (userIsDestroyed()) {
			loseGame();
			finished = true;
		} else if (userHasReachedKillTarget()) {
			goToNextLevel(NEXT_LEVEL);
			finished = true;
		}
	}

	/**
	 * Initializes the friendly units in the level, including the user's plane.
	 */
	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}

	/**
	 * Spawns enemy units based on the spawn probability and the total number of enemies allowed.
	 */
	@Override
	protected void spawnEnemyUnits() {
		int currentNumberOfEnemies = getCurrentNumberOfEnemies();
		for (int i = 0; i < TOTAL_ENEMIES - currentNumberOfEnemies; i++) {
			if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
				double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
				ActiveActorDestructible newEnemy = new EnemyPlane(getScreenWidth(), newEnemyInitialYPosition);
				addEnemyUnit(newEnemy);
			}
		}
	}

	/**
	 * Instantiates the LevelView for LevelOne, providing the root, player health, and kills to advance.
	 *
	 * @return the LevelView instance for this level
	 */
	@Override
	protected LevelView instantiateLevelView() {
		return new LevelView(getRoot(), PLAYER_INITIAL_HEALTH, KILLS_TO_ADVANCE);
	}

	/**
	 * Returns the number of kills required to advance to the next level.
	 *
	 * @return the number of kills to advance
	 */
	@Override
	protected int getKillsToAdvance() {
		return KILLS_TO_ADVANCE;
	}

	/**
	 * Checks if the user has reached the kill target to advance to the next level.
	 *
	 * @return true if the user has reached the kill target, false otherwise
	 */
	private boolean userHasReachedKillTarget() {
		return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
	}
}

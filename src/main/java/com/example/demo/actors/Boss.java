package com.example.demo.actors;

import com.example.demo.actors.actorlogic.ActiveActorDestructible;
import com.example.demo.audio.AudioManager;
import com.example.demo.levels.LevelViewLevelTwo;
import com.example.demo.actors.projectiles.Projectile_Boss;
import javafx.scene.control.ProgressBar;
import java.util.*;

/**
 * Represents the Boss character in the game, a powerful enemy plane with unique behaviors such as firing projectiles,
 * activating shields, and moving in a predefined pattern.
 */
public class Boss extends FighterPlane {

	private static final String IMAGE_NAME = "bossplane.png"; // Image for the boss.
	private static final double INITIAL_X_POSITION = 1000.0; // Initial x-coordinate of the boss.
	private static final double INITIAL_Y_POSITION = 400; // Initial y-coordinate of the boss.
	private static final double PROJECTILE_Y_POSITION_OFFSET = 50; // Offset for projectile y-coordinate.
	private static final double BOSS_FIRE_RATE = 0.04; // Probability of firing a projectile each frame.
	private static final double BOSS_SHIELD_PROBABILITY = 0.002; // Probability of activating the shield each frame.
	private static final int IMAGE_HEIGHT = 70; // Height of the boss's image.
	private static final int VERTICAL_VELOCITY = 8; // Vertical velocity for movement.
	private static final int HEALTH = 100; // Initial health of the boss.
	private static final int MOVE_FREQUENCY_PER_CYCLE = 5; // Frequency of movements in the move pattern.
	private static final int ZERO = 0; // Zero movement for stationary frames.
	private static final int MAX_FRAMES_WITH_SAME_MOVE = 10; // Maximum consecutive frames for the same movement.
	private static final int Y_POSITION_UPPER_BOUND = 50; // Upper y-bound for movement.
	private static final int Y_POSITION_LOWER_BOUND = 600; // Lower y-bound for movement.
	private static final int MAX_FRAMES_WITH_SHIELD = 500; // Maximum frames the shield can remain active.

	private final List<Integer> movePattern; // Predefined movement pattern.
	private boolean isShielded; // Indicates whether the shield is active.
	private int consecutiveMovesInSameDirection; // Tracks consecutive moves in the same direction.
	private int indexOfCurrentMove; // Current index in the move pattern.
	private int framesWithShieldActivated; // Tracks frames the shield has been active.
	private final LevelViewLevelTwo levelView; // Reference to the level view for shield visualization.
	private final ProgressBar healthBar; // Displays the boss's health.

	/**
	 * Constructs a Boss instance.
	 *
	 * @param levelView the level view for managing visuals such as the shield and health bar.
	 */
	public Boss(LevelViewLevelTwo levelView) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, HEALTH);
		movePattern = new ArrayList<>();
		consecutiveMovesInSameDirection = 0;
		indexOfCurrentMove = 0;
		framesWithShieldActivated = 0;
		isShielded = false;
		this.levelView = levelView;
		initializeMovePattern();

		healthBar = new ProgressBar();
		healthBar.setPrefWidth(200);
		healthBar.setStyle("-fx-accent: red; -fx-background-color: lightgray;");
		updateHealthBarPosition();
	}

	@Override
	public void updatePosition() {
		double initialTranslateY = getTranslateY();
		moveVertically(getNextMove());
		double currentPosition = getLayoutY() + getTranslateY();
		if (currentPosition < Y_POSITION_UPPER_BOUND || currentPosition > Y_POSITION_LOWER_BOUND) {
			setTranslateY(initialTranslateY);
		}
	}

	@Override
	public void updateActor() {
		updatePosition();
		updateShield();
		updateHealthBarPosition();
	}

	@Override
	public ActiveActorDestructible fireProjectile() {
		if (bossFiresInCurrentFrame()) {
			AudioManager.playSoundEffect("/sounds/fireball.wav");
			return new Projectile_Boss(getProjectileInitialPosition());
		}
		return null;
	}

	@Override
	public void takeDamage() {
		if (!isShielded) {
			super.takeDamage();
			updateHealthBar();
		}
	}

	/**
	 * Initializes the movement pattern for the boss.
	 */
	private void initializeMovePattern() {
		for (int i = 0; i < MOVE_FREQUENCY_PER_CYCLE; i++) {
			movePattern.add(VERTICAL_VELOCITY);
			movePattern.add(-VERTICAL_VELOCITY);
			movePattern.add(ZERO);
		}
		Collections.shuffle(movePattern);
	}

	/**
	 * Updates the shield's state, activating or deactivating it as needed.
	 */
	private void updateShield() {
		if (isShielded) {
			framesWithShieldActivated++;
		} else if (shieldShouldBeActivated()) {
			activateShield();
		}
		if (shieldExhausted()) {
			deactivateShield();
		}
	}

	/**
	 * Gets the next movement direction for the boss based on its movement pattern.
	 *
	 * @return the next movement direction.
	 */
	private int getNextMove() {
		int currentMove = movePattern.get(indexOfCurrentMove);
		consecutiveMovesInSameDirection++;
		if (consecutiveMovesInSameDirection == MAX_FRAMES_WITH_SAME_MOVE) {
			Collections.shuffle(movePattern);
			consecutiveMovesInSameDirection = 0;
			indexOfCurrentMove++;
		}
		if (indexOfCurrentMove == movePattern.size()) {
			indexOfCurrentMove = 0;
		}
		return currentMove;
	}

	/**
	 * Determines if the boss should fire a projectile in the current frame.
	 *
	 * @return true if the boss fires, false otherwise.
	 */
	private boolean bossFiresInCurrentFrame() {
		return Math.random() < BOSS_FIRE_RATE;
	}

	/**
	 * Gets the initial y-position for the projectile fired by the boss.
	 *
	 * @return the y-coordinate for the projectile.
	 */
	private double getProjectileInitialPosition() {
		return getLayoutY() + getTranslateY() + PROJECTILE_Y_POSITION_OFFSET;
	}

	/**
	 * Determines if the shield should be activated based on probability.
	 *
	 * @return true if the shield should be activated, false otherwise.
	 */
	private boolean shieldShouldBeActivated() {
		return Math.random() < BOSS_SHIELD_PROBABILITY;
	}

	/**
	 * Checks if the shield has been active for the maximum allowed frames.
	 *
	 * @return true if the shield is exhausted, false otherwise.
	 */
	private boolean shieldExhausted() {
		return framesWithShieldActivated == MAX_FRAMES_WITH_SHIELD;
	}

	/**
	 * Activates the shield and notifies the level view.
	 */
	private void activateShield() {
		isShielded = true;
		levelView.showShield();
	}

	/**
	 * Deactivates the shield and notifies the level view.
	 */
	private void deactivateShield() {
		isShielded = false;
		framesWithShieldActivated = 0;
		levelView.hideShield();
	}

	/**
	 * Updates the health bar based on the boss's current health.
	 */
	private void updateHealthBar() {
		healthBar.setProgress((double) getHealth() / HEALTH);
		updateHealthBarPosition();
	}

	/**
	 * Updates the position of the health bar to remain above the boss.
	 */
	private void updateHealthBarPosition() {
		healthBar.setLayoutX(getLayoutX() + getTranslateX());
		healthBar.setLayoutY(getLayoutY() + getTranslateY() - 20); // Position health bar above boss.
	}

	/**
	 * Gets the health bar object for the boss.
	 *
	 * @return the health bar.
	 */
	public ProgressBar getHealthBar() {
		return healthBar;
	}
}

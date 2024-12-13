package com.example.demo.actors;

import com.example.demo.actors.actorlogic.ActiveActorDestructible;
import com.example.demo.actors.projectiles.Projectile_User;
import com.example.demo.audio.AudioManager;

/**
 * Represents the player's plane in the game.
 * The UserPlane can move in all directions within defined bounds, fire projectiles, and track the number of kills.
 */
public class UserPlane extends FighterPlane {

	private static final String IMAGE_NAME = "userplane.png";
	private static final double Y_UPPER_BOUND = 0;
	private static final double Y_LOWER_BOUND = 660.0;
	private static final double X_LEFT_BOUND = 0.0;
	private static final double X_RIGHT_BOUND = 1100;
	private static final double INITIAL_X_POSITION = 5.0;
	private static final double INITIAL_Y_POSITION = 300.0;
	private static final int IMAGE_HEIGHT = 50;
	private static final int VELOCITY = 8;
	private static final int PROJECTILE_X_POSITION = 140;
	private static final int PROJECTILE_Y_POSITION_OFFSET = -15;

	private int verticalVelocityMultiplier;
	private int horizontalVelocityMultiplier;
	private int numberOfKills;

	/**
	 * Constructs a UserPlane instance with the specified initial health.
	 *
	 * @param initialHealth the initial health of the user's plane.
	 */
	public UserPlane(int initialHealth) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth);
		verticalVelocityMultiplier = 0;
		horizontalVelocityMultiplier = 0;
	}

	/**
	 * Updates the position of the user's plane based on movement multipliers.
	 * Ensures the plane does not move out of bounds.
	 */
	@Override
	public void updatePosition() {
		// Handle vertical movement
		if (verticalVelocityMultiplier != 0) {
			double initialTranslateY = getTranslateY();
			this.moveVertically(VELOCITY * verticalVelocityMultiplier);
			double newPositionY = getLayoutY() + getTranslateY();
			if (newPositionY < Y_UPPER_BOUND || newPositionY > Y_LOWER_BOUND) {
				this.setTranslateY(initialTranslateY); // Prevent going out of bounds
			}
		}

		// Handle horizontal movement
		if (horizontalVelocityMultiplier != 0) {
			double initialTranslateX = getTranslateX();
			this.moveHorizontally(VELOCITY * horizontalVelocityMultiplier);
			double newPositionX = getLayoutX() + getTranslateX();
			if (newPositionX < X_LEFT_BOUND || newPositionX > X_RIGHT_BOUND) {
				this.setTranslateX(initialTranslateX); // Prevent going out of bounds
			}
		}
	}

	/**
	 * Moves the user's plane horizontally by a specified distance.
	 *
	 * @param distance the distance to move horizontally.
	 */
	protected void moveHorizontally(double distance) {
		setTranslateX(getTranslateX() + distance);
	}

	/**
	 * Updates the actor by updating its position.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}

	/**
	 * Fires a projectile from the user's plane.
	 * Plays a sound effect when firing.
	 *
	 * @return a {@link Projectile_User} instance representing the fired projectile.
	 */
	@Override
	public ActiveActorDestructible fireProjectile() {
		AudioManager.playSoundEffect("/sounds/shootingeffect.wav");
		double currentXPosition = getLayoutX() + getTranslateX();
		double currentYPosition = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
		return new Projectile_User(currentXPosition + PROJECTILE_X_POSITION, currentYPosition);
	}

	/**
	 * Moves the user's plane upwards.
	 */
	public void moveUp() {
		verticalVelocityMultiplier = -1;
	}

	/**
	 * Moves the user's plane downwards.
	 */
	public void moveDown() {
		verticalVelocityMultiplier = 1;
	}

	/**
	 * Moves the user's plane to the left.
	 */
	public void moveLeft() {
		horizontalVelocityMultiplier = -1;
	}

	/**
	 * Moves the user's plane to the right.
	 */
	public void moveRight() {
		horizontalVelocityMultiplier = 1;
	}

	/**
	 * Stops vertical movement of the user's plane.
	 */
	public void stopVerticalMovement() {
		verticalVelocityMultiplier = 0;
	}

	/**
	 * Stops horizontal movement of the user's plane.
	 */
	public void stopHorizontalMovement() {
		horizontalVelocityMultiplier = 0;
	}

	/**
	 * Gets the number of kills made by the user's plane.
	 *
	 * @return the number of kills.
	 */
	public int getNumberOfKills() {
		return numberOfKills;
	}

	/**
	 * Increments the kill count of the user's plane by 1.
	 */
	public void incrementKillCount() {
		numberOfKills++;
	}
}

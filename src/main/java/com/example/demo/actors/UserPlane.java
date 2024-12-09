package com.example.demo.actors;

import com.example.demo.actors.projectiles.UserProjectile;
import com.example.demo.audio.AudioManager;

public class UserPlane extends FighterPlane {

	private static final String IMAGE_NAME = "userplane.png";
	private static final double Y_UPPER_BOUND = 0;
	private static final double Y_LOWER_BOUND = 660.0;

	private static final double X_LEFT_BOUND = 0.0; // New horizontal bounds
	private static final double X_RIGHT_BOUND = 1100;
	private static final double INITIAL_X_POSITION = 5.0;
	private static final double INITIAL_Y_POSITION = 300.0;
	private static final int IMAGE_HEIGHT = 50;
	private static final int VELOCITY = 8; // Unified velocity for vertical and horizontal
	private static final int PROJECTILE_X_POSITION = 140;
	private static final int PROJECTILE_Y_POSITION_OFFSET = -15;
	private int verticalVelocityMultiplier;
	private int horizontalVelocityMultiplier; // New field for horizontal movement
	private int numberOfKills;

	public UserPlane(int initialHealth) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth);
		verticalVelocityMultiplier = 0;
		horizontalVelocityMultiplier = 0; // Initialize horizontal movement multiplier
	}

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

	protected void moveHorizontally(double distance) {
		setTranslateX(getTranslateX() + distance);
	}

	@Override
	public void updateActor() {
		updatePosition();
	}

	@Override
	public ActiveActorDestructible fireProjectile() {
		AudioManager.playSoundEffect("/sounds/shootingeffect.wav");
		double currentXPosition = getLayoutX() + getTranslateX(); // Updated x position
		double currentYPosition = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET); // Existing y position logic
		return new UserProjectile(currentXPosition + PROJECTILE_X_POSITION, currentYPosition);
	}


	public void moveUp() {
		verticalVelocityMultiplier = -1;
	}

	public void moveDown() {
		verticalVelocityMultiplier = 1;
	}

	public void moveLeft() {
		horizontalVelocityMultiplier = -1; // Move left
	}

	public void moveRight() {
		horizontalVelocityMultiplier = 1; // Move right
	}

	public void stopVerticalMovement() {
		verticalVelocityMultiplier = 0;
	}

	public void stopHorizontalMovement() {
		horizontalVelocityMultiplier = 0;
	}

	public int getNumberOfKills() {
		return numberOfKills;
	}

	public void incrementKillCount() {
		numberOfKills++;
	}
}
package com.example.demo.actors;

import com.example.demo.actors.actorlogic.ActiveActorDestructible;
import com.example.demo.actors.projectiles.Projectile_Enemy;

/**
 * Represents an enemy plane in the game.
 * This class defines the behavior and attributes of enemy planes, including movement, firing projectiles, and interaction with other game entities.
 */
public class EnemyPlane extends FighterPlane {

	private static final String IMAGE_NAME = "enemyplane.png"; // Image for the enemy plane.
	private static final int IMAGE_HEIGHT = 50; // Height of the enemy plane image.
	private static final int HORIZONTAL_VELOCITY = -6; // Horizontal velocity for movement.
	private static final double PROJECTILE_X_POSITION_OFFSET = -50; // X-offset for projectile position.
	private static final double PROJECTILE_Y_POSITION_OFFSET = 20; // Y-offset for projectile position.
	private static final int INITIAL_HEALTH = 1; // Initial health of the enemy plane.
	private static final double FIRE_RATE = 0.01; // Probability of firing a projectile each frame.

	/**
	 * Constructs an EnemyPlane instance with its initial position.
	 *
	 * @param initialXPos the initial X-coordinate of the enemy plane.
	 * @param initialYPos the initial Y-coordinate of the enemy plane.
	 */
	public EnemyPlane(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
	}

	/**
	 * Updates the position of the enemy plane by moving it horizontally.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	/**
	 * Fires a projectile from the enemy plane.
	 * The probability of firing is determined by the {@code FIRE_RATE}.
	 *
	 * @return a new instance of {@link Projectile_Enemy} if the plane fires; otherwise, {@code null}.
	 */
	@Override
	public ActiveActorDestructible fireProjectile() {
		if (Math.random() < FIRE_RATE) {
			double projectileXPosition = getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET);
			double projectileYPostion = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
			return new Projectile_Enemy(projectileXPosition, projectileYPostion);
		}
		return null;
	}

	/**
	 * Updates the state of the enemy plane.
	 * This includes updating its position.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}
}

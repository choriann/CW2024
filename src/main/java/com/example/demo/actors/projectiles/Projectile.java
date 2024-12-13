package com.example.demo.actors.projectiles;

import com.example.demo.actors.actorlogic.ActiveActorDestructible;

/**
 * Represents a projectile in the game, capable of moving horizontally and taking damage.
 * The projectile can be destroyed when its health reaches zero or if it moves out of bounds.
 */
public class Projectile extends ActiveActorDestructible {

	private final double horizontalVelocity; // The horizontal speed of the projectile.
	private int health; // The health of the projectile, determines when it gets destroyed.

	/**
	 * Constructs a new projectile with the specified properties.
	 *
	 * @param imageName         the name of the image representing the projectile.
	 * @param imageHeight       the height of the projectile's image.
	 * @param initialXPos       the initial x-coordinate of the projectile.
	 * @param initialYPos       the initial y-coordinate of the projectile.
	 * @param health            the health of the projectile.
	 * @param horizontalVelocity the speed at which the projectile moves horizontally.
	 */
	public Projectile(String imageName, int imageHeight, double initialXPos, double initialYPos, int health, double horizontalVelocity) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		this.horizontalVelocity = horizontalVelocity;
		this.health = health;
	}

	/**
	 * Updates the position of the projectile by moving it horizontally.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(horizontalVelocity);
	}

	/**
	 * Updates the state of the projectile in each game frame.
	 * If the projectile moves out of bounds, it is destroyed.
	 */
	@Override
	public void updateActor() {
		updatePosition();
		if (isOutOfBounds()) {
			destroy();
		}
	}

	/**
	 * Reduces the health of the projectile when it takes damage.
	 * If the health reaches zero, the projectile is destroyed.
	 */
	@Override
	public void takeDamage() {
		health--;
		if (health <= 0) {
			destroy();
		}
	}

	/**
	 * Checks whether the projectile has moved out of the predefined bounds.
	 *
	 * @return true if the projectile is out of bounds, false otherwise.
	 */
	private boolean isOutOfBounds() {
		// Implement logic for checking if projectile is out of bounds.
		return getLayoutX() < 0 || getLayoutX() > 1200; // Example bounds.
	}
}

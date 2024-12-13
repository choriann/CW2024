package com.example.demo.actors;

import com.example.demo.actors.actorlogic.ActiveActorDestructible;

/**
 * Abstract class representing a fighter plane in the game.
 * A fighter plane has health, can take damage, and can fire projectiles.
 */
public abstract class FighterPlane extends ActiveActorDestructible {

	private int health; // Health of the fighter plane.

	/**
	 * Constructs a FighterPlane instance with its image, dimensions, initial position, and health.
	 *
	 * @param imageName      the name of the image representing the fighter plane.
	 * @param imageHeight    the height of the image.
	 * @param initialXPos    the initial X-coordinate of the fighter plane.
	 * @param initialYPos    the initial Y-coordinate of the fighter plane.
	 * @param health         the initial health of the fighter plane.
	 */
	public FighterPlane(String imageName, int imageHeight, double initialXPos, double initialYPos, int health) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		this.health = health;
	}

	/**
	 * Abstract method for firing a projectile.
	 * Subclasses must implement this method to define the firing behavior.
	 *
	 * @return a new instance of {@link ActiveActorDestructible} representing the projectile.
	 */
	public abstract ActiveActorDestructible fireProjectile();

	/**
	 * Reduces the health of the fighter plane by 1.
	 * If the health reaches zero, the plane is destroyed.
	 */
	@Override
	public void takeDamage() {
		health--;
		if (healthAtZero()) {
			this.destroy();
		}
	}

	/**
	 * Calculates the X-coordinate for spawning a projectile relative to the plane's position.
	 *
	 * @param xPositionOffset the offset to apply to the X-coordinate.
	 * @return the X-coordinate for the projectile.
	 */
	protected double getProjectileXPosition(double xPositionOffset) {
		return getLayoutX() + getTranslateX() + xPositionOffset;
	}

	/**
	 * Calculates the Y-coordinate for spawning a projectile relative to the plane's position.
	 *
	 * @param yPositionOffset the offset to apply to the Y-coordinate.
	 * @return the Y-coordinate for the projectile.
	 */
	protected double getProjectileYPosition(double yPositionOffset) {
		return getLayoutY() + getTranslateY() + yPositionOffset;
	}

	/**
	 * Checks if the fighter plane's health has reached zero.
	 *
	 * @return {@code true} if health is zero, {@code false} otherwise.
	 */
	private boolean healthAtZero() {
		return health == 0;
	}

	/**
	 * Retrieves the current health of the fighter plane.
	 *
	 * @return the current health value.
	 */
	public int getHealth() {
		return health;
	}
}

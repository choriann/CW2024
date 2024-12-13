package com.example.demo.actors.projectiles;

/**
 * Represents a projectile fired by the boss in the game.
 * The boss projectile has specific properties such as an image, health, and velocity.
 */
public class Projectile_Boss extends Projectile {
	private static final int HEALTH = 1; // The health of the boss projectile.
	private static final double VELOCITY = -15; // The horizontal velocity of the boss projectile.

	/**
	 * Constructs a new boss projectile with specified initial vertical position.
	 *
	 * @param initialYPos the initial y-coordinate of the projectile.
	 */
	public Projectile_Boss(double initialYPos) {
		super("fireball.png", 75, 950, initialYPos, HEALTH, VELOCITY);
	}
}

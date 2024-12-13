package com.example.demo.actors.projectiles;

/**
 * Represents a projectile fired by enemy planes.
 */
public class Projectile_Enemy extends Projectile {
	private static final int HEALTH = 1; // Durability of the enemy projectile.
	private static final double VELOCITY = -10; // Speed of the projectile in the horizontal direction.

	/**
	 * Constructs a new enemy projectile with specified initial positions.
	 *
	 * @param initialXPos the initial x-coordinate of the projectile.
	 * @param initialYPos the initial y-coordinate of the projectile.
	 */
	public Projectile_Enemy(double initialXPos, double initialYPos) {
		super("enemyFire.png", 25, initialXPos, initialYPos, HEALTH, VELOCITY);
	}
}

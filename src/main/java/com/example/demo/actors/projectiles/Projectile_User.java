package com.example.demo.actors.projectiles;

/**
 * Represents a projectile fired by the user's plane.
 */
public class Projectile_User extends Projectile {
	private static final int HEALTH = 1; // Durability of the user's projectile.
	private static final double VELOCITY = 15; // Speed of the projectile in the horizontal direction.

	/**
	 * Constructs a new user projectile with specified initial positions.
	 *
	 * @param initialXPos the initial x-coordinate of the projectile.
	 * @param initialYPos the initial y-coordinate of the projectile.
	 */
	public Projectile_User(double initialXPos, double initialYPos) {
		super("userfire.png", 100, initialXPos, initialYPos, HEALTH, VELOCITY);
	}
}

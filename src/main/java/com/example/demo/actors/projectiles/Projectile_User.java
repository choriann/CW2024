package com.example.demo.actors.projectiles;

public class Projectile_User extends Projectile {
	private static final int HEALTH = 1;
	private static final double VELOCITY = 15;

	public Projectile_User(double initialXPos, double initialYPos) {
		super("userfire.png", 100, initialXPos, initialYPos, HEALTH, VELOCITY);
	}
}

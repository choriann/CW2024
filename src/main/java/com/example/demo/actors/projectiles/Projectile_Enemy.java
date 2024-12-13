package com.example.demo.actors.projectiles;

public class Projectile_Enemy extends Projectile {
	private static final int HEALTH = 1;
	private static final double VELOCITY = -10;

	public Projectile_Enemy(double initialXPos, double initialYPos) {
		super("enemyFire.png", 25, initialXPos, initialYPos, HEALTH, VELOCITY);
	}
}

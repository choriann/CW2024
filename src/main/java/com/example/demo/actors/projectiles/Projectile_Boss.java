package com.example.demo.actors.projectiles;

public class Projectile_Boss extends Projectile {
	private static final int HEALTH = 1;
	private static final double VELOCITY = -15;

	public Projectile_Boss(double initialYPos) {
		super("fireball.png", 75, 950, initialYPos, HEALTH, VELOCITY);
	}
}

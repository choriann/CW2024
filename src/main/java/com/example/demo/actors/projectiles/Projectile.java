package com.example.demo.actors.projectiles;

import com.example.demo.actors.ActiveActorDestructible;

public class Projectile extends ActiveActorDestructible {

	private final double horizontalVelocity;
	private int health;

	public Projectile(String imageName, int imageHeight, double initialXPos, double initialYPos, int health, double horizontalVelocity) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		this.horizontalVelocity = horizontalVelocity;
		this.health = health;
	}

	@Override
	public void updatePosition() {
		moveHorizontally(horizontalVelocity);
	}

	@Override
	public void updateActor() {
		updatePosition();
		if (isOutOfBounds()) {
			destroy();
		}
	}

	@Override
	public void takeDamage() {
		health--;
		if (health <= 0) {
			destroy();
		}
	}

	private boolean isOutOfBounds() {
		// Implement logic for checking if projectile is out of bounds.
		return getLayoutX() < 0 || getLayoutX() > 1200; // Example bounds.
	}
}

package com.example.demo.actors.projectiles;

public class BossProjectileLevelThree extends Projectile {

    private static final String IMAGE_NAME = "missile3.png"; // Level 3 projectile image
    private static final int IMAGE_HEIGHT = 70;
    private static final int HORIZONTAL_VELOCITY = -15;
    private static final int INITIAL_X_POSITION = 950;
    private int hitsRemaining = 3; // Requires 3 hits to destroy

    public BossProjectileLevelThree(double initialYPos) {
        super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, initialYPos);
    }

    @Override
    public void updatePosition() {
        moveHorizontally(HORIZONTAL_VELOCITY);
    }

    @Override
    public void takeDamage() {
        hitsRemaining--;
        if (hitsRemaining <= 0) {
            destroy();
        }
    }

    @Override
    public void updateActor() {
        updatePosition();
    }
}

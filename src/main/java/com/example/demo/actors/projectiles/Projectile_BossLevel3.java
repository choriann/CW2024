package com.example.demo.actors.projectiles;

/**
 * Represents a more durable projectile fired by the boss in Level 3.
 * This projectile requires multiple hits to be destroyed.
 */
public class Projectile_BossLevel3 extends Projectile {
    private int hitsRemaining; // Number of hits required to destroy this projectile.

    /**
     * Constructs a new Level 3 boss projectile with a specified initial vertical position.
     *
     * @param initialYPos the initial y-coordinate of the projectile.
     */
    public Projectile_BossLevel3(double initialYPos) {
        super("missile3.png", 70, 950, initialYPos, 3, -15);
        this.hitsRemaining = 3;
    }

    /**
     * Reduces the projectile's durability upon taking damage.
     * Destroys the projectile if its durability reaches zero.
     */
    @Override
    public void takeDamage() {
        hitsRemaining--;
        if (hitsRemaining <= 0) {
            destroy();
        }
    }
}

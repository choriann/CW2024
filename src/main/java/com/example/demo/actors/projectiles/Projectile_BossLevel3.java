package com.example.demo.actors.projectiles;

public class Projectile_BossLevel3 extends Projectile {
    private int hitsRemaining;

    public Projectile_BossLevel3(double initialYPos) {
        super("missile3.png", 70, 950, initialYPos, 3, -15);
        this.hitsRemaining = 3;
    }

    @Override
    public void takeDamage() {
        hitsRemaining--;
        if (hitsRemaining <= 0) {
            destroy();
        }
    }
}

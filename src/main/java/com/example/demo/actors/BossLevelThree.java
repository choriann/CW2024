package com.example.demo.actors;

import com.example.demo.actors.actorlogic.ActiveActorDestructible;
import com.example.demo.audio.AudioManager;
import com.example.demo.levels.LevelViewLevelTwo;
import com.example.demo.actors.projectiles.Projectile_BossLevel3;
import javafx.scene.control.ProgressBar;


import java.util.*;

public class BossLevelThree extends FighterPlane {

    private static final String IMAGE_NAME = "bossplane3.png"; // Level 3 boss image
    private static final double INITIAL_X_POSITION = 1000.0;
    private static final double INITIAL_Y_POSITION = 400.0;
    private static final double PROJECTILE_Y_POSITION_OFFSET = 50.0;
    private static final double BOSS_FIRE_RATE = 0.06; // Adjusted fire rate
    private static final double BOSS_SHIELD_PROBABILITY = 0.002;
    private static final int IMAGE_HEIGHT = 70;
    private static final int VERTICAL_VELOCITY = 8;
    private static final int HEALTH = 170; // Higher health for Level 3 boss
    private static final int MOVE_FREQUENCY_PER_CYCLE = 5;
    private static final int MAX_FRAMES_WITH_SAME_MOVE = 10;
    private static final int Y_POSITION_UPPER_BOUND = 50;
    private static final int Y_POSITION_LOWER_BOUND = 600;
    private static final int MAX_FRAMES_WITH_SHIELD = 500;

    private final List<Integer> movePattern;
    private boolean isShielded;
    private int consecutiveMovesInSameDirection;
    private int indexOfCurrentMove;
    private int framesWithShieldActivated;
    private final LevelViewLevelTwo levelView;
    private final ProgressBar healthBar;

    public BossLevelThree(LevelViewLevelTwo levelView) {
        super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, HEALTH);
        this.levelView = levelView;
        this.movePattern = new ArrayList<>();
        this.consecutiveMovesInSameDirection = 0;
        this.indexOfCurrentMove = 0;
        this.framesWithShieldActivated = 0;
        this.isShielded = false;

        initializeMovePattern();

        // Health bar setup
        healthBar = new ProgressBar();
        healthBar.setPrefWidth(200);
        healthBar.setStyle("-fx-accent: red; -fx-background-color: lightgray;");
        updateHealthBarPosition();
    }

    @Override
    public void updatePosition() {
        double initialTranslateY = getTranslateY();
        moveVertically(getNextMove());
        double currentPosition = getLayoutY() + getTranslateY();
        if (currentPosition < Y_POSITION_UPPER_BOUND || currentPosition > Y_POSITION_LOWER_BOUND) {
            setTranslateY(initialTranslateY);
        }
    }

    @Override
    public void updateActor() {
        updatePosition();
        updateShield();
        updateHealthBarPosition();
    }

    @Override
    public ActiveActorDestructible fireProjectile() {
        if (bossFiresInCurrentFrame()) {
            AudioManager.playSoundEffect("/sounds/missile3.wav"); // Using the same sound effect for now
            return new Projectile_BossLevel3(getProjectileInitialPosition());
        }
        return null;
    }

    @Override
    public void takeDamage() {
        if (!isShielded) {
            super.takeDamage();
            updateHealthBar();
        }
    }

    private void initializeMovePattern() {
        for (int i = 0; i < MOVE_FREQUENCY_PER_CYCLE; i++) {
            movePattern.add(VERTICAL_VELOCITY);
            movePattern.add(-VERTICAL_VELOCITY);
            movePattern.add(0);
        }
        Collections.shuffle(movePattern);
    }

    private void updateShield() {
        if (isShielded) {
            framesWithShieldActivated++;
        } else if (shieldShouldBeActivated()) {
            activateShield();
        }
        if (shieldExhausted()) {
            deactivateShield();
        }
    }

    private int getNextMove() {
        int currentMove = movePattern.get(indexOfCurrentMove);
        consecutiveMovesInSameDirection++;
        if (consecutiveMovesInSameDirection == MAX_FRAMES_WITH_SAME_MOVE) {
            Collections.shuffle(movePattern);
            consecutiveMovesInSameDirection = 0;
            indexOfCurrentMove++;
        }
        if (indexOfCurrentMove == movePattern.size()) {
            indexOfCurrentMove = 0;
        }
        return currentMove;
    }

    private boolean bossFiresInCurrentFrame() {
        return Math.random() < BOSS_FIRE_RATE;
    }

    private double getProjectileInitialPosition() {
        return getLayoutY() + getTranslateY() + PROJECTILE_Y_POSITION_OFFSET;
    }

    private boolean shieldShouldBeActivated() {
        return Math.random() < BOSS_SHIELD_PROBABILITY;
    }

    private boolean shieldExhausted() {
        return framesWithShieldActivated == MAX_FRAMES_WITH_SHIELD;
    }

    private void activateShield() {
        isShielded = true;
        levelView.showShield();
    }

    private void deactivateShield() {
        isShielded = false;
        framesWithShieldActivated = 0;
        levelView.hideShield();
    }

    private void updateHealthBar() {
        healthBar.setProgress((double) getHealth() / HEALTH);
        updateHealthBarPosition();
    }

    private void updateHealthBarPosition() {
        healthBar.setLayoutX(getLayoutX() + getTranslateX());
        healthBar.setLayoutY(getLayoutY() + getTranslateY() - 20);
    }

    public ProgressBar getHealthBar() {
        return healthBar;
    }
}

package com.example.demo.levels;

import java.util.*;
import java.util.stream.Collectors;

import com.example.demo.ui.KillCounterLabel;
import com.example.demo.actors.actorlogic.ActiveActorDestructible;
import com.example.demo.actors.FighterPlane;
import com.example.demo.actors.UserPlane;
import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.util.Duration;

/**
 * Represents a parent class for all levels in the game.
 * Provides common functionality such as managing actors, handling collisions,
 * spawning enemies, and transitioning between levels.
 */

public abstract class LevelParent extends Observable {

    private static final double SCREEN_HEIGHT_ADJUSTMENT = 150;
    private static final int MILLISECOND_DELAY = 50;
    private final double screenHeight;
    private final double screenWidth;
    private final double enemyMaximumYPosition;

    private final Group root;
    private final Timeline timeline;
    private final UserPlane user;
    private final Scene scene;
    private final ImageView background;
    protected boolean finished = false;


    private final List<ActiveActorDestructible> friendlyUnits;
    private final List<ActiveActorDestructible> enemyUnits;
    private final List<ActiveActorDestructible> userProjectiles;
    private final List<ActiveActorDestructible> enemyProjectiles;

    private int currentNumberOfEnemies;
    private LevelView levelView;

    protected KillCounterLabel killCounterLabel; // New field for kill counter


    /**
     * Constructs a new LevelParent with the specified parameters.
     *
     * @param backgroundImageName the file path of the background image
     * @param screenHeight the height of the game screen
     * @param screenWidth the width of the game screen
     * @param playerInitialHealth the initial health of the player's plane
     */

    public LevelParent(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth) {
        this.root = new Group();
        this.scene = new Scene(root, screenWidth, screenHeight);
        this.timeline = new Timeline();
        this.user = new UserPlane(playerInitialHealth);
        this.friendlyUnits = new ArrayList<>();
        this.enemyUnits = new ArrayList<>();
        this.userProjectiles = new ArrayList<>();
        this.enemyProjectiles = new ArrayList<>();

        this.background = new ImageView(new Image(getClass().getResource(backgroundImageName).toExternalForm()));
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.enemyMaximumYPosition = screenHeight - SCREEN_HEIGHT_ADJUSTMENT;
        this.levelView = instantiateLevelView();
        this.currentNumberOfEnemies = 0;
        initializeTimeline();
        friendlyUnits.add(user);
    }

    /**
     * Initializes friendly units in the level.
     * Must be implemented by subclasses.
     */
    protected abstract void initializeFriendlyUnits();

    /**
     * Checks if the game is over.
     * Must be implemented by subclasses.
     */
    protected abstract void checkIfGameOver();

    /**
     * Spawns enemy units in the level.
     * Must be implemented by subclasses.
     */
    protected abstract void spawnEnemyUnits();


    /**
     * Instantiates the LevelView for the level.
     *
     * @return the LevelView for this level
     */

    protected abstract LevelView instantiateLevelView();


    /**
     * Gets the number of kills required to advance to the next level.
     *
     * @return the number of kills required
     */

    protected abstract int getKillsToAdvance();

    public Scene initializeScene() {
        initializeBackground();
        initializeFriendlyUnits();
        // Add the kill counter label
        killCounterLabel = new KillCounterLabel(getKillsToAdvance());
        getRoot().getChildren().add(killCounterLabel); // Add to the root group
        levelView.showHeartDisplay();
        return scene;
    }

    /**
     * Starts the game by focusing on the background and playing the timeline.
     */
    public void startGame() {
        background.requestFocus();
        timeline.play();
    }


    /**
     * Transitions to the specified next level.
     *
     * @param levelName the name of the next level's class
     */
    public void goToNextLevel(String levelName) {
        if (timeline !=null) {
            timeline.stop();  //stop the timeline
        }
        root.getChildren().clear();  //clear all entities
        setChanged();
        notifyObservers(levelName);
    }

    /**
     * Updates the scene each frame by performing game logic such as spawning enemies,
     * updating actors, handling collisions, and checking game-over conditions.
     */
    private void updateScene() {
        spawnEnemyUnits();
        updateActors();
        generateEnemyFire();
        updateNumberOfEnemies();
        handleEnemyPenetration();
        handleUserProjectileCollisions();
        handleEnemyProjectileCollisions();
        handleUserAndEnemyProjectileCollisions();
        handlePlaneCollisions();
        removeAllDestroyedActors();
        updateKillCount();
        updateKillCounter(getUser().getNumberOfKills());
        updateLevelView();
        checkIfGameOver();
    }

    /**
     * Initializes the timeline for the game loop.
     */
    private void initializeTimeline() {
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame gameLoop = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> updateScene());
        timeline.getKeyFrames().add(gameLoop);
    }


    /**
     * Initializes the background image and sets up user controls for movement and actions.
     */
    private void initializeBackground() {
        background.setFocusTraversable(true);
        background.setFitHeight(screenHeight);
        background.setFitWidth(screenWidth);
        background.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                KeyCode kc = e.getCode();
                if (kc == KeyCode.UP) user.moveUp();
                if (kc == KeyCode.DOWN) user.moveDown();
                if (kc == KeyCode.LEFT) user.moveLeft(); // Added to handle horizontal movement
                if (kc == KeyCode.RIGHT) user.moveRight(); //
                if (kc == KeyCode.SPACE) fireProjectile();
            }
        });
        background.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                KeyCode kc = e.getCode();
                if (kc == KeyCode.UP || kc == KeyCode.DOWN) user.stopVerticalMovement();
                if (kc == KeyCode.LEFT || kc == KeyCode.RIGHT) user.stopHorizontalMovement(); // Add this
            }
        });

        root.getChildren().add(background);
    }

    /**
     * Fires a projectile from the user plane and adds it to the scene and projectiles list.
     */
    private void fireProjectile() {
        ActiveActorDestructible projectile = user.fireProjectile();
        root.getChildren().add(projectile);
        userProjectiles.add(projectile);
    }

    /**
     * Generates enemy fire by iterating over all enemy units and spawning projectiles.
     */
    private void generateEnemyFire() {
        enemyUnits.forEach(enemy -> spawnEnemyProjectile(((FighterPlane) enemy).fireProjectile()));
    }

    /**
     * Adds an enemy projectile to the scene and projectiles list if it's not null.
     *
     * @param projectile the enemy projectile to be added
     */
    private void spawnEnemyProjectile(ActiveActorDestructible projectile) {
        if (projectile != null) {
            root.getChildren().add(projectile);
            enemyProjectiles.add(projectile);
        }
    }

    /**
     * Updates the position and state of all actors, including friendly units, enemies, and projectiles.
     */
    private void updateActors() {
        friendlyUnits.forEach(plane -> plane.updateActor());
        enemyUnits.forEach(enemy -> enemy.updateActor());
        userProjectiles.forEach(projectile -> projectile.updateActor());
        enemyProjectiles.forEach(projectile -> projectile.updateActor());
    }

    /**
     * Removes all actors marked as destroyed from the scene and their respective lists.
     */
    private void removeAllDestroyedActors() {
        removeDestroyedActors(friendlyUnits);
        removeDestroyedActors(enemyUnits);
        removeDestroyedActors(userProjectiles);
        removeDestroyedActors(enemyProjectiles);
    }

    /**
     * Removes actors marked as destroyed from the given list and the scene.
     *
     * @param actors the list of actors to check for destruction
     */
    private void removeDestroyedActors(List<ActiveActorDestructible> actors) {
        List<ActiveActorDestructible> destroyedActors = actors.stream().filter(actor -> actor.isDestroyed())
                .collect(Collectors.toList());
        root.getChildren().removeAll(destroyedActors);
        actors.removeAll(destroyedActors);
    }

    /**
     * Handles collisions between friendly units and enemy units (e.g., planes).
     */
    private void handlePlaneCollisions() {
        handleCollisions(friendlyUnits, enemyUnits);
    }

    /**
     * Handles collisions between user projectiles and enemy units.
     */
    private void handleUserProjectileCollisions() {
        handleCollisions(userProjectiles, enemyUnits);
    }

    /**
     * Handles collisions between enemy projectiles and friendly units.
     */
    private void handleEnemyProjectileCollisions() {
        handleCollisions(enemyProjectiles, friendlyUnits);
    }

    /**
     * Handles collisions between two lists of actors, applying damage to colliding actors.
     *
     * @param actors1 the first list of actors
     * @param actors2 the second list of actors
     */
    private void handleCollisions(List<ActiveActorDestructible> actors1,
                                  List<ActiveActorDestructible> actors2) {
        for (ActiveActorDestructible actor : actors2) {
            for (ActiveActorDestructible otherActor : actors1) {
                if (actor.getBoundsInParent().intersects(otherActor.getBoundsInParent())) {
                    actor.takeDamage();
                    otherActor.takeDamage();
                }
            }
        }
    }

    /**
     * Handles enemy planes that penetrate the player's defenses by damaging the user plane.
     */
    private void handleEnemyPenetration() {
        for (ActiveActorDestructible enemy : enemyUnits) {
            if (enemyHasPenetratedDefenses(enemy)) {
                user.takeDamage();
                enemy.destroy();
            }
        }
    }

    /**
     * Updates the level view with the player's current health.
     */
    private void updateLevelView() {
        levelView.removeHearts(user.getHealth());
    }

    /**
     * Updates the kill count by comparing the current and previous number of enemies.
     */
    private void updateKillCount() {
        for (int i = 0; i < currentNumberOfEnemies - enemyUnits.size(); i++) {
            user.incrementKillCount();
        }
    }

    /**
     * Checks if an enemy has penetrated the player's defenses by moving off-screen.
     *
     * @param enemy the enemy to check
     * @return true if the enemy has moved off-screen, false otherwise
     */
    private boolean enemyHasPenetratedDefenses(ActiveActorDestructible enemy) {
        return Math.abs(enemy.getTranslateX()) > screenWidth;
    }


    /**
     * Ends the level with a victory, stopping the game loop and showing the win image.
     */
    protected void winGame() {
        timeline.stop();
        levelView.showWinImage();
    }

    /**
     * Ends the level with a loss, stopping the game loop and showing the game over image.
     */
    protected void loseGame() {
        timeline.stop();
        levelView.showGameOverImage();
    }

    /**
     * Gets the player's user plane.
     *
     * @return the user's plane
     */
    protected UserPlane getUser() {
        return user;
    }

    /**
     * Gets the root group of the scene, which contains all visual elements.
     *
     * @return the root group
     */
    protected Group getRoot() {
        return root;
    }

    /**
     * Gets the current number of enemy units.
     *
     * @return the number of enemies
     */
    protected int getCurrentNumberOfEnemies() {
        return enemyUnits.size();
    }

    /**
     * Adds a new enemy unit to the level and updates the scene.
     *
     * @param enemy the enemy unit to add
     */
    protected void addEnemyUnit(ActiveActorDestructible enemy) {
        enemyUnits.add(enemy);
        root.getChildren().add(enemy);
    }

    /**
     * Gets the maximum Y position allowed for spawning enemy planes.
     *
     * @return the maximum Y position
     */
    protected double getEnemyMaximumYPosition() {
        return enemyMaximumYPosition;
    }

    /**
     * Gets the screen width of the level.
     *
     * @return the screen width
     */
    protected double getScreenWidth() {
        return screenWidth;
    }

    /**
     * Checks if the user's plane has been destroyed.
     *
     * @return true if the user's plane is destroyed, false otherwise
     */
    protected boolean userIsDestroyed() {
        return user.isDestroyed();
    }

    /**
     * Updates the number of enemy units currently in the level.
     */
    private void updateNumberOfEnemies() {
        currentNumberOfEnemies = enemyUnits.size();
    }

    /**
     * Handles collisions between user projectiles and enemy projectiles.
     */
    private void handleUserAndEnemyProjectileCollisions() {
        handleCollisions(userProjectiles, enemyProjectiles);
    }

    /**
     * Updates the kill counter display in the HUD.
     *
     * @param currentKills the current number of kills
     */
    protected void updateKillCounter(int currentKills) {
        if (killCounterLabel != null) {
            killCounterLabel.updateKills(currentKills);
        }
    }



}
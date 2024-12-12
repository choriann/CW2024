package com.example.demo.levels;

import com.example.demo.actors.BossLevelThree;

public class LevelThree extends LevelParent {

    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background3.jpg";
    private static final int PLAYER_INITIAL_HEALTH = 5;
    private BossLevelThree boss;
    private LevelViewLevelTwo levelView;

    public LevelThree(double screenHeight, double screenWidth) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
    }

    @Override
    protected void initializeFriendlyUnits() {
        getRoot().getChildren().add(getUser());
    }

    @Override
    protected void checkIfGameOver() {
        if (userIsDestroyed()) {
            loseGame();
        } else if (boss.isDestroyed()) {
            winGame();
        }
    }

    @Override
    protected void spawnEnemyUnits() {
        if (getCurrentNumberOfEnemies() == 0) {
            boss = new BossLevelThree(this.levelView);
            addEnemyUnit(boss);
            getRoot().getChildren().addAll(boss.getHealthBar());
        }
    }

    @Override
    protected LevelView instantiateLevelView() {
        this.levelView = new LevelViewLevelTwo(getRoot(), PLAYER_INITIAL_HEALTH);
        return this.levelView;
    }

    @Override
    protected int getKillsToAdvance() {
        return 0;
    }
}

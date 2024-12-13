package com.example.demo.ui;

import javafx.scene.control.Label;

/**
 * The KillCounterLabel class is responsible for displaying the player's current kill count
 * and the required kills to advance to the next level.
 */
public class KillCounterLabel extends Label {

    private static final int X_POSITION = 700;  // X position for the label on the screen
    private static final int Y_POSITION = 20;   // Y position for the label on the screen
    private final int killsToAdvance;           // The required kills to advance to the next level

    /**
     * Constructor for initializing the kill counter label.
     * Sets the initial text for kills and positions the label on the screen.
     *
     * @param killsToAdvance the number of kills required to advance to the next level
     */
    public KillCounterLabel(int killsToAdvance) {
        this.killsToAdvance = killsToAdvance;
        this.setText("Kills: 0 / " + killsToAdvance);  // Set initial kill count to 0
        this.setLayoutX(X_POSITION);
        this.setLayoutY(Y_POSITION);
        this.setStyle("-fx-font-size: 20; -fx-text-fill: white;");  // Set label style
    }

    /**
     * Updates the kill counter label with the current kill count.
     *
     * @param currentKills the current number of kills the player has
     */
    public void updateKills(int currentKills) {
        this.setText("Kills: " + currentKills + " / " + killsToAdvance);  // Update the kill count display
    }
}

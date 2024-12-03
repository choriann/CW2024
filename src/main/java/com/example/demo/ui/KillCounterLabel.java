package com.example.demo.ui;

import javafx.scene.control.Label;

public class KillCounterLabel extends Label {
    private static final int X_POSITION = 700; // Adjust to your layout
    private static final int Y_POSITION = 20;
    private final int killsToAdvance;

    public KillCounterLabel(int killsToAdvance) {
        this.killsToAdvance = killsToAdvance;
        this.setText("Kills: 0 / " + killsToAdvance);
        this.setLayoutX(X_POSITION);
        this.setLayoutY(Y_POSITION);
        this.setStyle("-fx-font-size: 20; -fx-text-fill: white;"); // Unique style
    }

    public void updateKills(int currentKills) {
        this.setText("Kills: " + currentKills + " / " + killsToAdvance);
    }
}

package com.example.demo.ui.Menus;

import com.example.demo.controller.Controller;
import com.example.demo.audio.AudioManager;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The MainMenu class handles the user interface and functionality of the main menu screen,
 * including options to play the game, open settings, or quit the application.
 */
public class MainMenu {

    private final Controller controller;
    private final Stage primaryStage;

    /**
     * Constructor to initialize the MainMenu.
     *
     * @param primaryStage the main stage of the application
     * @param controller the controller used to handle game navigation
     */
    public MainMenu(Stage primaryStage, Controller controller) {
        this.primaryStage = primaryStage;
        this.controller = controller;
    }

    /**
     * Displays the main menu with options to play the game, open settings, or quit the application.
     * Sets up the layout, buttons, and action handlers, and starts background music.
     */
    public void show() {
        // Create buttons for the main menu
        Button playButton = new Button("Play Game");
        Button settingsButton = new Button("Settings");
        Button quitButton = new Button("Quit");

        // Add CSS styles to buttons
        playButton.getStyleClass().add("button");
        settingsButton.getStyleClass().add("button");
        quitButton.getStyleClass().add("button");

        // Add button click sound effect and actions
        playButton.setOnAction(e -> {
            AudioManager.playSoundEffect("/sounds/buttonclick.mp3");
            startGame();
        });
        settingsButton.setOnAction(e -> {
            AudioManager.playSoundEffect("/sounds/buttonclick.mp3");
            openSettings();
        });
        quitButton.setOnAction(e -> {
            AudioManager.playSoundEffect("/sounds/buttonclick.mp3");
            primaryStage.close();
        });

        // Set up the layout for the buttons
        VBox menuLayout = new VBox(20, playButton, settingsButton, quitButton);
        menuLayout.getStyleClass().add("settings-layout");

        // Create the scene and set it to the stage
        Scene menuScene = new Scene(menuLayout, primaryStage.getWidth(), primaryStage.getHeight());
        menuScene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        primaryStage.setScene(menuScene);

        // Play background music
        AudioManager.playBackgroundMusic("/sounds/bgm.mp3");
    }

    /**
     * Starts the game by stopping the background music and using the controller to launch the game.
     */
    private void startGame() {
        try {
            AudioManager.stopBackgroundMusic(); // Stop BGM before starting the game
            controller.launchGame(); // Launch the game
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens the settings menu by creating and displaying the SettingsMenu.
     */
    private void openSettings() {
        SettingsMenu settingsMenu = new SettingsMenu(primaryStage, this);
        settingsMenu.show();
    }
}

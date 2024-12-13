package com.example.demo.controller;

import com.example.demo.ui.Menus.MainMenu;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The Main class serves as the entry point for the application and initializes the game.
 */
public class Main extends Application {

	private static final String TITLE = "Sky Battle"; // Title of the application

	/**
	 * Initializes and starts the application.
	 *
	 * @param stage the primary stage for the application
	 */
	@Override
	public void start(Stage stage) {
		stage.setTitle(TITLE); // Set the window title
		stage.setResizable(true); // Allow resizing
		stage.setMaximized(true); // Start maximized

		// Create the main controller and main menu
		Controller controller = new Controller(stage);
		MainMenu mainMenu = new MainMenu(stage, controller);

		mainMenu.show(); // Display the main menu
		stage.show(); // Show the application window
	}

	/**
	 * The main method to launch the application.
	 *
	 * @param args the command-line arguments
	 */
	public static void main(String[] args) {
		launch();
	}
}

package com.example.demo.controller;

import com.example.demo.ui.MainMenu;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {


	private static final String TITLE = "Sky Battle";

	@Override
	public void start(Stage stage) {
		stage.setTitle(TITLE);
		stage.setResizable(true);

		stage.setMaximized(true);

		Controller controller = new Controller(stage);
		MainMenu mainMenu = new MainMenu(stage, controller);
		mainMenu.show(); // Show the main menu
		stage.show();
	}

	public static void main(String[] args) {
		launch();
	}
}
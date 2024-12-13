package com.example.demo.controller;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Observable;
import java.util.Observer;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import com.example.demo.levels.LevelParent;

/**
 * The Controller class manages the transition between game levels and serves as an observer for level updates.
 */
public class Controller implements Observer {

    private static final String LEVEL_ONE_CLASS_NAME = "com.example.demo.levels.LevelOne"; // Initial level
    private final Stage stage;

    /**
     * Constructs a new Controller with the provided stage.
     *
     * @param stage the primary stage of the application
     */
    public Controller(Stage stage) {
        this.stage = stage;
    }

    /**
     * Launches the game, starting at the first level.
     *
     * @throws ClassNotFoundException        if the specified class for the level cannot be found
     * @throws NoSuchMethodException         if the constructor for the level class is missing
     * @throws InvocationTargetException     if an error occurs while invoking the level's constructor
     * @throws InstantiationException        if the level class cannot be instantiated
     * @throws IllegalAccessException        if access to the constructor is denied
     * @throws IllegalArgumentException      if the constructor arguments are invalid
     */
    public void launchGame() throws ClassNotFoundException, NoSuchMethodException, SecurityException,
            InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        stage.show();
        goToLevel(LEVEL_ONE_CLASS_NAME);
    }

    /**
     * Transitions to a specified game level by dynamically instantiating the level class.
     *
     * @param className the fully qualified name of the level class
     * @throws ClassNotFoundException        if the specified class for the level cannot be found
     * @throws NoSuchMethodException         if the constructor for the level class is missing
     * @throws InvocationTargetException     if an error occurs while invoking the level's constructor
     * @throws InstantiationException        if the level class cannot be instantiated
     * @throws IllegalAccessException        if access to the constructor is denied
     * @throws IllegalArgumentException      if the constructor arguments are invalid
     */
    private void goToLevel(String className) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
            InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class<?> myClass = Class.forName(className);
        Constructor<?> constructor = myClass.getConstructor(double.class, double.class);
        LevelParent myLevel = (LevelParent) constructor.newInstance(stage.getHeight(), stage.getWidth());
        myLevel.addObserver(this);
        Scene scene = myLevel.initializeScene();
        stage.setScene(scene);
        myLevel.startGame();
    }

    /**
     * Handles updates from observed levels. Transitions to the next level if notified.
     *
     * @param observable the observable object that triggered the update
     * @param arg        the argument passed by the observable, typically the next level's class name
     */
    @Override
    public void update(Observable observable, Object arg) {
        try {
            goToLevel((String) arg);
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
                 | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText(e.getClass().toString());
            alert.show();
        }
    }
}

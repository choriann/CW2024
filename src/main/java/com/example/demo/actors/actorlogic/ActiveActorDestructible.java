package com.example.demo.actors.actorlogic;


/**
 * Represents an active actor in the game that can be destroyed.
 */

public abstract class ActiveActorDestructible extends ActiveActor implements Destructible {

	private boolean isDestroyed;
	/**
	 * Constructs an ActiveActorDestructible with specified image details and initial position.
	 *
	 * @param imageName    the name of the image file representing the actor
	 * @param imageHeight  the height of the image
	 * @param initialXPos  the initial x-coordinate of the actor
	 * @param initialYPos  the initial y-coordinate of the actor
	 */

	public ActiveActorDestructible(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		isDestroyed = false;
	}

	/**
	 * Updates the position of the actor. Implementation to be provided by subclasses.
	 */

	@Override
	public abstract void updatePosition();


	/**
	 * Updates the state of the actor in the game. Implementation to be provided by subclasses.
	 */
	public abstract void updateActor();

	/**
	 * Handles the logic when the actor takes damage. Implementation to be provided by subclasses.
	 */

	@Override
	public abstract void takeDamage();

	/**
	 * Marks the actor as destroyed.
	 */

	@Override
	public void destroy() {
		this.isDestroyed = true;
	}

	/**
	 * Checks if the actor is destroyed.
	 *
	 * @return true if the actor is destroyed, false otherwise
	 */


	public boolean isDestroyed() {
		return isDestroyed;
	}
	
}

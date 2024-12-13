package com.example.demo.actors.actorlogic;

/**
 * Interface defining the behavior of destructible entities in the game.
 */
public interface Destructible {

	/**
	 * Applies damage to the entity.
	 * The implementation should define the behavior when the entity takes damage.
	 */
	void takeDamage();

	/**
	 * Destroys the entity.
	 * The implementation should define what happens when the entity is destroyed.
	 */
	void destroy();
}

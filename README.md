# 1942 Retro Game (COMP2042 Coursework)
Cheah Chor Ian

20502010
## GitHub Repository
https://github.com/choriann/CW2024.git

Make sure to use branch refactor-2 as that is the latest version
There were issues merging to main, so i could not merge it to main

## Compilation Instructions



Download jdk 21 from oracle

Download intellij IDEA

clone the repository

git clone https://github.com/choriann/CW2024.git


Setup jdk 21 in project structure -> SDk 

Move to the refactor-2 branch for the game latest version

Navigate to pom.xml and click reload maven project to download all dependencies

Run the game by navigating to controller folder and run main.java



## Implemented and Working Properly

### Enemy projectiles destructible
- Made Enemy projectiles destroyable by user's projectiles

### Kill Counter
- Added a kill counter to the game to track the number of enemies destroyed by the player in Level one

### Health Bar 
- Added a health bar to track health of bosses in level 2 and 3
- It follows the position of the boss

### Horizontal Movement
- Allowed users to move horizontally by using left and right arrow keys

### Fixed Hitbox issue
- Removed Whitespaces from hitboxes of actors to fix annoying hitbox issues

### Sound
- Added sound effects and background music, improving user experience
- Sound levels can be controlled in main menu using a slider under settings

### Main Menu
- Added a main menu that is displayed upon launching the game
- Options in main menu are Play Game, Settings and Quit

### Fullscreen
- Made the game launch in full screen mode to improve user experience
- Has resizable function if user wants to resize the window

### Levels
- Added a level 3 to the game
- It contains logic of current level 2 boss. This is done to not reinvent the wheel with another boss. Level 3 boss has higher health, higher fire rate, and the projectiles are harder to destroy

### 

## Implemented and Not Working Properly

### Kill Counter displays even on level 2 and 3 when it should not

## Features Not Implemented

### Pause menu due to time constraints
### Loading animations and explosions due to time constraints
### Level 4 
### Powerups due to time constraints
## New Java Classes

### Projectile_BossLevel3
- Handles the projectile logic for boss of level 3

### BossLevelThree
- Handles the boss logic for level 3

### AudioManager
- Handles the audio logic from bgm to sfx of the game

### LevelThree
- New level 3 class

### MainMenu
- Handles the main menu logic of the game

### SettingsMenu
-Handles the settings of the game

## Modified Java Classes 

Refactor: Clean and centralise projectile logic
- Moved shared logic for updateActor() and updatePosition() to the Projectile superclass.
- Added parameters (health, horizontalVelocity) to Projectile for flexibility.
- Simplified Projectile_Boss, Projectile_BossLevel3, Projectile_Enemy, and Projectile_User by removing redundant methods.
- Retained unique behavior in subclasses (e.g., hitsRemaining for Projectile_BossLevel3).
- Improved readability and maintainability by reducing code duplication and adopting consistent naming conventions.

More modified classes but cannot write due to time constraints..
## Unexpected Problems

### implementing a kill counter feature
-Added a label to display the kill counter in levelview class
-Updated the label dynamically in the game  loop to reflect the player's kills versus the number required to progress.   
-Despite adding the label and updating its text, the kill counter did not display on the game screen.
-Debugging showed that the label was added to the root node successfully, but it was not visible during gameplay.

### When trying to make full screen by setting stage.setFullscreen(true), it only fullscreens main menu but not the rest of the game
- Decided to not use stage.setFullscreen but instead use stage.setMaximised(true)

### When run the game first time, produces error
- Fixed it by renaming file path to the correct path at shield image




## Bugs fixed
### jpg to png at shieldimage

### leveltransition hanging issue by adding timeline stop

### shield not displaying

### hitbox by removing white spaces


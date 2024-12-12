# 1942 Retro Game (COMP2042 Coursework)
Cheah Chor Ian

20502010
## GitHub Repository
https://github.com/choriann/CW2024.git


## Compilation Instructions


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


## Features Not Implemented

## New Java Classes

## Modified Java Classes 

## Unexpected Problems







## Bugs fixed
jpg to png at shieldimage

leveltransition hanging issue by adding timeline stop

shield not displaying

hitbox by removing white spaces


## New Classes

## Challenges faced
implementing a kill counter feature
    -Added a label to display the kill counter in levelview class
    -Updated the label dynamically in the game  loop to reflect the player's kills versus the number required to progress.   
    -Despite adding the label and updating its text, the kill counter did not display on the game screen.
    -Debugging showed that the label was added to the root node successfully, but it was not visible during gameplay.
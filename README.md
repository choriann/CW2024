# 1942 Retro Game (COMP2042 Coursework)
Cheah Chor Ian

20502010
## GitHub Repository
https://github.com/choriann/CW2024.git

## Modified classes

## Features
projectiles destroyable

players can move horizontally now

kill counter






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
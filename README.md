<h1>COMP 2042 COURSEWORK</h1>

<h1>Github Repository</h1>
https://github.com/Class1c-Gust/CW2024.git

## Compilaition Instructions
## Pre-requisites
SDK: 21 - (Amazon Corretto 21.0.2)
IDEL: IntelliJ IDEA
JavaFX: 19.0.2

1. Setup javaFX
2. Clone the project repository to your machine
3. Import the project into your preferred IDE
4. Compile and run 'Main"

<h1>Implemented and working properly</h1>

## Main Menu
Added a main menu which allows the user to start the game without it starting directly. They can either start the game or exit the entire application using two buttons

## Game over screen and Game win screen
Game over screen shows when the user dies allowing them to either retry from level one or go back to the main menu. Game win screen shows when the User beats the last boss, allowing them to return to the main menu

## Transition screen
Transition text shows after the user beats a level to show a countdown from 5 to begin the next level

## Boss health bar and text
Health bar of each boss shows on the top right corner of the screen along with text showing how many shots need to hit before it is destroyed. This is displayed durig each boss level at levels 3, 6, and 9

## New and improved Background images, background music, and Sound effects
New backgrounds are present, along with different background music for each level. Sound effects have been added for various different scenarios in the game; for example, taking damage, shooting, levelup, collisions, powerups etc

## Powerups
New powerups have been added to give the user buffs:
1. Health powerup give the user an extra heart
2. Freeze powerup freezes enemies within a radius and applies a glow freeze effect
3. Multishot powerup gives the user 3 bullets per shot

Powerups probability of spawning changes depending on the level

## New enemies and bosses and weapons
A new enemy type has been introduced which travels faster and has a faster fire rate than default enemies, these have a lower probability of spawning mainly at the higher levels. Additionally 2 more bosses have been introduced which have stats better than the previous boss. They now have the ability to fire missiles.

## Missiles
Bosses can now fire missiles which travel faster than their regular fireball.

## Dynamic levelling
Levelling is now dynamic which means the aspects of the level changes each level. For example, more enemies spawn next level, more needed to kill to advance, higher probability of advanced enemies spawning etc

## 60 FPS 
Improved frame rate of 60 frames per second is now standard to significantly improve the user experience

## Player movement overhaul
Players can move horizontally and vertically


<h1>Implemented and not working properly</h1>

## Background music mute 
Functions needed work fine but did not implement the pause menu needed to mute the music. Explanation why provided below.

<h1>Features not implemented</h1>

## Pause menu
Did not implement due to added complexity it would have added to implement into the game

## Moving background 
Did not implement due to added complexity and peformance issues i encounted during a trial run of this feature

## Timer for boss levels
Did not implement due to time constraints 

## Change aircraft model
Did not implement due to time constraint and overall complexity in implementing new model aircrafts (eg needed to make a whole new menu)

## Physics for user aircraft staying in the air
Did not implement due to the added complexity of the mathematical elements needed for this to function

<h1>Additional Java classes</h1>
1. Config.java - To hold all static contants and configurations in one place
2. GameObjectFactory, BossFactory, EnemyPlaneFactory, LevelFactory, PlaneFactory, ProjectileFactory, UserPlaneFactory - To provide concrete game objecr products to create these game objects whilst applying the factory method design pattern
3, LevelConfiguration - Provides parameters for enemy spawning player health, powerup probabilities
4. CollisonManager - Manage collisions in the game
5. SoundManager - Manage Sounds in the game
6. BossConfiguration - Provides parameters for the boss including shield and missile probability, health, fire rate etc
7. Freeze - Define freeze powerup
8. Heart - Define freeze Heart
9. Multishot - Define freeze Multishot
10. Powerup - Define Powerup superclass
11. BossMissile - Create a boss missile providing specific behaviour
12. GameObject - Provides basic functionality for handling movement, destruction, and bounding box retrieval.
13. GameLoseScreen - screen provides options to retry the game or return to the main menu after losing
14. GameWinScreen - screen provides options to retry the game or return to the main menu after winning
15. MainMenu - Represents the main menu screen of the game. This screen provides options to start the game or exit.
16. MenuScreen - Base class for screens, provides common functionality for managing screen layour, button styles etc

<h1>Modified Java classes</h1>
1. Heartbar - Changed from heartdisplay to display hearts and update
2. BossLevel - to manage the boss, user interactions, and level transitions
3. DynamicLevel - changed from Levelone, Leveltwo etc to represent dynamic levels and provide functionality to handle enemy spawning, interactions etc
4. LevelParent - Chnaged to incorporate level transitions, fixed game win and loss, now update powerups
5. LevelViewBoss - Changed from LevelViewLevelTwo to provide new boss ui features 
6. Controller - Now handles win and lose screen
7. Main - improved code style
8. ActiveActor and ActivaActorDestructible have been deleted and replaced by gameObject for more effecient object creation and less complexity
9. ShieldImage, GameOverImage, WinImage have all been deleted and implemented differenly
10. Destructible interface deleted as made redundant from changes
11. module-info.java altered to incorporate necessary dependencies for javaFX and Junit tests


































Bugs Fixed
1. Fixed shield image name path (from jpg to png), then changed redundant imageName variable to the correct file path
2. Added transitive javafx graphics line to module-info file to fix crashing
3. Fixed crashing by modifying update() method in controller.java to check if the level has changed already
4. Fixed Shield image not appearing on the boss


Refactor
1. Added Factory classes for each main game object to implement "Factory method" design pattern
2. Removed deprecated Observable pattern completely from levelparent and controller classes

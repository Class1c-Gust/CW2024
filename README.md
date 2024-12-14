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
4. Ensure the SDK is set to Java 21 (Amazon Corretto 21.0.2)
5. Ensure all required libraries are included within your classpath
6. Compile and run 'Main"

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
1. Config.java (com/example/demo/config/Config.java) - To hold all static contants and configurations in one place <br>
2. GameObjectFactory, BossFactory, EnemyPlaneFactory, LevelFactory, PlaneFactory, ProjectileFactory, UserPlaneFactory (com/example/demo/Factories) - To provide concrete game objecr products to create these game objects whilst applying the factory method design pattern <br> 
3, LevelConfiguration (com/example/demo/Factories/LevelFactory.java) - Provides parameters for enemy spawning player health, powerup probabilities <br>
4. CollisonManager (com/example/demo/Managers/CollisionManager.java) - Manage collisions in the game <br>
5. SoundManager (com/example/demo/Managers/SoundManager.java) - Manage Sounds in the game <br>
6. BossConfiguration (com/example/demo/Objects/Planes/BossConfiguration.java) - Provides parameters for the boss including shield and missile probability, health, fire rate etc <br>
7. Freeze (com/example/demo/Objects/Powerups/Freeze.java) - Define freeze powerup <br>
8. Heart (com/example/demo/Objects/Powerups/Heart.java) - Define freeze Heart <br>
9. Multishot (com/example/demo/Objects/Powerups/Multishot.java) - Define freeze Multishot <br>
10. Powerup (com/example/demo/Objects/Powerups/Powerup.java) - Define Powerup superclass <br>
11. BossMissile (com/example/demo/Objects/Projectiles/BossMissile.java) - Create a boss missile providing specific behaviour <br>
12. GameObject (com/example/demo/Objects/GameObject.java) - Provides basic functionality for handling movement, destruction, and bounding box retrieval. <br>
13. GameLoseScreen (com/example/demo/screens/GameLoseScreen.java) - screen provides options to retry the game or return to the main menu after losing <br>
14. GameWinScreen (com/example/demo/screens/GameWinScreen.java) - screen provides options to retry the game or return to the main menu after winning <br>
15. MainMenu (com/example/demo/screens/MainMenu.java) - Represents the main menu screen of the game. This screen provides options to start the game or exit. <br>
16. MenuScreen (com/example/demo/screens/MenuScreen.java) - Base class for screens, provides common functionality for managing screen layour, button styles etc <br>

<h1>Modified Java classes</h1>
1. Heartbar - Changed from heartdisplay to display hearts and update <br>
2. BossLevel - to manage the boss, user interactions, and level transitions <br>
3. DynamicLevel - changed from Levelone, Leveltwo etc to represent dynamic levels and provide functionality to handle enemy spawning, interactions etc <br>
4. LevelParent - Chnaged to incorporate level transitions, fixed game win and loss, now update powerups <br>
5. LevelViewBoss - Changed from LevelViewLevelTwo to provide new boss ui features  <br>
6. Controller - Now handles win and lose screen <br>
7. Main - improved code style <br>
8. ActiveActor and ActivaActorDestructible have been deleted and replaced by gameObject for more effecient object creation and less complexity <br>
9. ShieldImage, GameOverImage, WinImage have all been deleted and implemented differenly <br>
10. Destructible interface deleted as made redundant from changes <br>
11. module-info.java altered to incorporate necessary dependencies for javaFX and Junit tests <br>

<h1>Unexpected problems and resolutions</h1>
- Fixed shield image name path (from jpg to png), then changed redundant imageName to the correct file path <br>
- Added transitive javafx graphics line to fix crashing <br>
- Fixed transition to levels <br>
- Fixed hitboxes by reducing their size by 80% <br>
- Fixed Shield not appearing on the screen by modifying bits of code <br>
- Combining actordestructible and activeactor to fix performance (in the end removed) <br>
- MVC pattern (changed to factory method pattern for better result) <br>
- Using factory patten resulted in some create functions being obselete due to needed additional parameters <br>

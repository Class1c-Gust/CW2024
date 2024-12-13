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

## Game over screen
Game over screen shows when the user dies allowing them to either retry from level one or go back to the main menu

## Game win screen
Shows when the User beats the last boss, allowing them to return to the main menu

## Transition screen
Transition text shows after the user beats a level to show a countdown from 5 to begin the next level

## Boss health bar and text
Health bar of each boss shows on the top right corner of the screen along with text showing how many shots need to hit before it is destroyed. This is displayed durig each boss level at levels 3, 6, and 9

## New and improved Background images
New backgrounds are present, each different for each level

## Sound effects
Sound effects have been added for various different scenarios in the game; for example, taking damage, shooting, levelup, collisions, powerups etc














Bugs Fixed
1. Fixed shield image name path (from jpg to png), then changed redundant imageName variable to the correct file path
2. Added transitive javafx graphics line to module-info file to fix crashing
3. Fixed crashing by modifying update() method in controller.java to check if the level has changed already
4. Fixed Shield image not appearing on the boss


Refactor
1. Added Factory classes for each main game object to implement "Factory method" design pattern
2. Removed deprecated Observable pattern completely from levelparent and controller classes

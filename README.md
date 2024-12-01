<h1>Implemented and working properly</h1>

Bugs Fixed
1. Fixed shield image name path (from jpg to png), then changed redundant imageName variable to the correct file path
2. Added transitive javafx graphics line to module-info file to fix crashing
3. Fixed crashing by modifying update() method in controller.java to check if the level has changed already
4. Fixed Shield image not appearing on the boss


Refactor
1. Added Factory classes for each main game object to implement "Factory method" design pattern
2. Removed deprecated Observable pattern completely from levelparent and controller classes

game
====

This project implements the game of Breakout.

Name: Alicia Steiman and Suomo Ammah

### Timeline

Start Date: 2/1/20

Finish Date: 2/16/20

Hours Spent: 30+

### Resources Used
* office hours
* help room

### Running the Program

Main class: Main

Data files needed: 
* line_config_level_one.txt
* line_config_level_two.txt
* line_config_level_three.txt


Key/Mouse inputs:
* Key inputs
    * SPACE (space bar) = starts animation/drops ball
    * RIGHT (right arrow) = moves paddle right
    * LEFT (left arrow) = moves paddle left
  
* Mouse inputs
    * Click "Play game" button in setUpInstructionsScene = sets up/displays Level One
    * Click "Yes" button in setUpLoseScreen scene = sets up/displays whichever level the player was currently on
    * Click "No" button in setUpLoseScreen = quit game/closes game window 
    * Click "Yes" button in setUpWinScreen = sets up/displays next level 
    * Click "No" button in setUpWinScreen = quit game/closes game window


Cheat keys:
* R = resets ball and paddle to their respective initial positions
* L = adds additional lives to ball
* C = clears all bricks present on screen
* P = drops a power-up that is already part of the scene
* D = destroys "first" remaining block on the screen (i.e. last block needed to win)
* I = adds "infinite lives" to ball (assigns myLives = (int) Double.POSITIVE_INFINITY)
* S = increases ball speed by a factor of 2
* DIGIT0 = makes paddle length width of screen (hands-free mode)
* DIGIT1 = jumps to level 1
* DIGIT2 = jumps to level 2
* DIGIT3 = jumps to level 3


Known Bugs:
Although not a bug that affects the run of the Game, the Shape.intersect() method often causes the ball to act in weird ways.
For example, if a ball hits a specific spot of a brick or top of the screen, the ball will glide across the top of the screen
rather than bounce off. A similar problem occurs when the ball hits the corner of the paddle; the ball glides from one side
of the paddle to the other but will eventually launch off. Another bug (that again doesn't affect the Game) is the cheat key
to clear all bricks is not visibly clearing the bricks. Although the ball no longer collides with the "cleared bricks," they
still show up on the screen. Similarly, the cheat code to remove the final brick does not work as intended. 


Extra credit:
In our plan, we decided that our "something extra" would be to include bricks that had to be avoided, and to give "power-downs"
if the brick to be avoided was hit. The power-down in the current implementation is simply decreasing one from score, but
a new PowerUp class easily can be created, such as smallerBall, which could then be released upon hit of an AvoidBrick as the
"power-down." 


### Impressions
#Possible Improvements
We were generally satisfied with the requirements of this project and felt like the specifications were all within reason,
however, to improve this project more time in class should be spent modeling how to do JUnit tests for JavaFX because this was
something we particularly struggled with. 


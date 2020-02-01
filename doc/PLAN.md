# Game Plan
## Alicia Steiman and Suomo Ammah

### Breakout Variant

We thought that Super Breakout and Bricks n Balls were the most 
interesting variants because they still felt like a typical Breakout
game, but included fun alternatives that made the game more engaging.
Super Breakout had challenging levels and creative power ups while
Bricks n Balls gave the user more control of the ball and incorporated
a puzzle-solving aspect by assigning each brick a number of hits that
need to be reached before it breaks. 

### General Level Descriptions

For the first implementation of our game, we aim to have three levels
done by the Test deadline. The first level will be a typical Breakout
configuration with lines of bricks across the top. There will be a 
predetermined number of rows, each with bricks to fill the entire width
of the screen. The second level will incorporate bricks that need to be
hit twice before breaking, but will still have the typical design of
rows of bricks. The third level will continue to have bricks that need
to be hit twice, but will introduce a new configuration. The third
level could have bricks that are missing or could put the bricks in
a diamond shape versus a giant rectangle. 

### Bricks Ideas

As mentioned in the previous question, we want to create bricks that
need to be hit multiple times. For example, level 2 can have some bricks
that need to be hit once and some twice and level 3 would have bricks
that need to be hit once, twice, and three times. Other bricks we want
to include are different colors each worth varying point values and 
potentially different sizes of bricks as the game progresses.  

### Power Up Ideas

Some power up ideas that we were playing with include extra lives, 
increasing the size of the paddle, having multiple balls on the screen, 
speeding up the ball, and slowing down the ball. We also want to play
around with the idea of having certain bricks grant power ups such as
if you hit a certain brick, it will cause an explosion and break the
other bricks around it. 

### Cheat Key Ideas

One cheat key idea we were thinking about was one that gives the user
an unlimited number of balls. Another cheat idea we are thinking about
is making a paddle that fits the entire width of the screen, so the 
user would essentially watch the game be played hands-free. A third cheat
key we want to implement is like a hidden bomb that will explode all
remaining bricks on the screen. A final, simple cheat code we thought
about was being able to change the shape or color of the ball to make
the game more personal to the user. 

### Something Extra

Something beyond the game of Breakout that we intend to add are bricks
that need to be avoided. Each level may have one or more bricks that
should not be hit, and if they are hit the user will experience a 
"power-down." Some power-downs we want to include are a smaller paddle,
an invisible ball, and rearranging the configuration of remaining
bricks. We also might add a customization feature in which players
can unlock new balls, paddles, and backgrounds. 

### Possible Classes

1. A class to initialize everything (i.e. size of the window, number
of bricks, placing on the ball on the screen, etc)
    * setWindow() - creates width and height
    * setPaddle() - positions the paddle on the screen

2. A class to control the movement of the ball
    * checkBall() - determines if the ball has fallen of the screen
    * updateLives() - calls checkBall() to track a user's lives

3. A class keeping track of the bricks to determine their state (i.e.
broken vs. not broken)
    * checkBrick() - determines if a single brick has been broken
    * brickConfig() - returns the brick configuration 

4. A class for the paddle that controls its movement and state in the
case of power ups
    * touchPaddle() - checks if the ball hit the paddle
    * paddleState() - changes state of paddle (size, color, etc.)

5. A class that is the actual game; calls the other classes and puts
it all together 
    * setLevel() - sets player's current level in the game
    * levelUp() - increases player's level by 1

6. A class that handles power-ups
    * setPower() - randomly chooses power from a list of powers
    * powerUp() - checks if brick containing power-up was hit

7. A class to actually run the game (i.e. the "Main" class)
    * start() - launches game
 

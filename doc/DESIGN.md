# Game Design Final
### Suomo Ammah & Alicia Steiman

## Team Roles and Responsibilities

 * Team Member #1 (Alicia)
    * I felt that my role was essentially the chief programmer of the project because I was in charge of handling
    the overall design and implementing the initial versions of superclasses and an example of a subclass that could extend
    a given superclass. I also took on the role of debugging and refactoring, as I made sure that new code was compatible with 
    old code and effectively contributed to the design. I also maintained many of the project documents and often assigned tasks. 

 * Team Member #2 (Suomo)
   * I handled mainly the user interactive portion of the code such as creating subclasses that interacted with the superclasses.


## Design Goals

#### What Features are Easy to Add
A major focus of this project was designing the Game in such a way that new features would be easy to add in all places.
Specifically, new types of Bricks, PowerUps, and Levels can be created by implemented new subclasses of their respective 
superclasses. However, it is much easier to add new Brick or PowerUp features as these classes were more fleshed out than
Level. New power-ups that specifically affect either the ball or paddle are also significantly easier to implement than
a different type of power-up just by the nature of how the abstract methods for PowerUp were written. We wanted to ensure
there was as little duplication as possible throughout the code, and this was achieved by implementing inheritance hierarchies
in which an abstract class held multiple subclasses that dictated how a given PowerUp, for example, should be integrated
into the game. We wanted the Game class to only be responsible for elements that would dictate a game, such as visual 
components and user inputs. Parts of the Game that concerned the ball, for example, were made into helper methods in the Ball
class that were later called upon in Game. If we wanted to add a new feature to the ball, such as operating it through launches
rather than have it bounce, new methods would only need to be added to the Ball class.  


## High-level Design

#### Core Classes
The high-level design can be split into two major components: Game and Sprite. Game handles the majority of JavaFX code in
that it sets the stage of scenes that the user will see when the game starts. It manages all visible components and handles
user input for the paddle movements and a number of buttons that appear between game transitions. Game creates new instances
of a Ball, Paddle, and Level in order to set up the Breakout game and handles the animation of these objects at 60 frames 
per second. The Sprite class essentially holds all visual components of the game; Ball, Paddle, PowerUp, Brick, and Level all extend
Sprite. Although Sprite only contains one useful method, and probably is not necessary for the current implementation of the
game, it provides opportunity to create methods that all these objects can share. For example, the only method in Sprite is
one that checks for a collision between two shapes, which is used in nearly every class. Within the classes that extend Sprite
are inheritance hierarchies. Brick is an abstract class that holds methods such as `checkBreak()` and `removeBrick()`, and has
subclasses (AvoidBrick, MultipleHitsBrick, SingleHitBrick, and PowerUpBrick) that each represent different types of "bricks".
Each type of brick has its own implementation of the abstract methods; they specify how that specific brick is broken (or isn't), determines
whether it releases a power-up, and updates the user's score depending on which brick was hit. PowerUp is also an abstract class
whose subclasses (AddLife, BiggerBall, and LongerPaddle) represent different kinds of PowerUp. Each PowerUp knows how its
power-up is applied, knows how its power-up is reverted, and the abstract class is responsible for setting the animation of the 
power-up falling from a brick, and handles what should happen when the PowerUp object collides with a paddle. Level is also an
abstract class that can read in a file and create a configuration of bricks based on the information in the file. It manages
the user's score and keeps track of the number of bricks left on the screen in order to determine when to end the level. As far
as interaction between classes, no class is dependent on another, i.e. no class creates a new instance of another. However,
the classes interact in that, for example, Brick can interact with Ball to determine if a ball has hit a brick, Paddle and Ball 
interact in a similar fashion to determine how a ball should bounce off a paddle, and Level constructs all the Brick objects in 
a given configuration. Lastly, the Main class has one method that simply launches the game by passing in the Game class. 

## Assumptions that Affect the Design

#### Features Affected by Assumptions
Two major features affected by assumptions are the levels and power-ups. Because we were unable to figure out how to store all
the subclasses of a given superclass in some sort of collection, we had to "hard-code" these elements into the game. For
example, when a PowerUpBrick gets a power-up, it assumes that there are only three types of power-ups. A random number between
0-2 is generated, and depending on the number will determine the PowerUp returned. A similar method is seen in determining what
type of Brick object to create within a Level based on the number in the file passed. The bricks are created using a series
of "if-statements," which means the code only supports up to 4 types of bricks (plus a no brick option). In creating a brick
configuration, the code within that method checks (if num == "1"), for example, and returns a new MultipleHitsBrick. Additionally,
to simplify the project's design, the game will end once a user has successfully completed Level 3. Although new levels might
be added, the code within the Game class would need to be changed to reflect that. Lastly, a final assumption was that there
would be instance variables holding the file for each level rather than having a file indicate what type of level configuration
to build. Files are passed into the Level constructor, but a future implementation of this code might read the first line
of a file as an indicator of what level to create. 

## New Features HowTo

#### Easy to Add Features
* To add a new type of PowerUp, a new subclass that extends the PowerUp class would need to be created. This subclass would have
to implement methods that specify how the power-up is applied (does is increase ball speed? does it create new balls? does it
create an explosion that breaks bricks?) and would also have to implement a method that reverts the object back to its 
original state if it was affected in a significant way, i.e. a ball might go back to its original size, but an explosion that
breaks bricks probably wouldn't make those bricks reappear after a given number of seconds. However, the only part that makes
implementing this feature tricky is that it would need to be added as an option to the `getPowerUp()` method in the PowerUpBrick
class, which determines what power-up gets dropped when a PowerUp brick is removed. 
* To add a new type of Brick, a new subclass that extends the Brick class would need to be created. This subclass would be 
responsible for determining what constitutes a "brick collision" and how a brick collision affects the user's score. The new 
brick type would need to be assigned a number that represents it in the file that determines a brick configuration, and the
method `createConfiguration()` would need to know to create that new type of Brick depending on the number in the file. A 
future implementation of this code could create a Collection of Brick objects and access the subclass at the i-th index of 
that Collection for each "i" in the configuration file. 

#### Other Features not yet Done
* A feature that was not implemented by the deadline was the concept of a high-score, or a leader board. The code currently
saves/updates a user's score between levels, but does not save scores between runs of the game. One way to implement this 
feature would be to save a user's score (and maybe a user inputted name) to a file upon losing or exiting a run of the game. 
The game could then access this file to essentially create a leader board that would be displayed either at the beginning or 
end of the game. However, due to time and lack of research in how to have code save things to an outside file, this was not
yet completed. 
* The idea of "power-downs" were also not completed, but their implementation would be similar to that of power-ups. Power-downs
could be subclasses of PowerUp (despite the confusing name) and would do things such as make the ball invisible, decrease
the paddle size, or rearranging the brick configuration. These power-downs could potentially be released upon hitting an AvoidBrick, 
or maybe they're released at certain time intervals of the game and the user would need to move their paddle so that it doesn't
collide with a power-down. 
* Lastly, the PLAN document suggested potential for adding customized features to the game, such as picking the color of the ball.
We think this feature could be implemented by creating a user input for ball color, that would then be formatted correctly and 
passed into the Ball constructor. Or, different ball options are shown as images on the start-up scene and the user could click
on whichever one they preferred. The click would then signal to the code what color to pass into the Ball constructor. 

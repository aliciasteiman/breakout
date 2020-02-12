### Refactoring Discussion 2/12/20
#### Alicia Steiman and Suomo Ammah

The first thing we did to refactor was shorten our largest method, which was `drawBricks()` in the Bricks class. 
However, this code was already shortened in a local branch but not in master -- rather than assign each brick a 
predetermined color through a series of if statements, the code now assigns each brick a random color. We were able
to replace 6 if statements with just a few lines that selected a random color. 

The next thing we did was revisit our declaration statements to make sure they just declared List<> rather than
specify what kind of list. Most of our code that implements lists declares the method as an ArrayList<>, but they 
now reflect Java collection interfaces rather than the specific implementation class. 

We also started working on fixing instance variables; we went through them to make sure each one had the correct
label whether it be private, public, or protected. 
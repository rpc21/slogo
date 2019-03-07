# Refactoring Documentation
## MEP76, ARD51, LBJ17, RBC21

## Longest Methods
**Parser.makeNodeTree():**  
 * Removed setting startIndex to its own method - this allows for more extensibility because it doesn’t have to set starting index in the makeNodeTree method. Instead, it makes it in a different method and if it needs to be changed later on it is easier to find. 
 * Instead of remaking a CommandNode in makeNodeTree() if certain conditions are met, I am moving this check to be done in the CommandFactory. This makes more sense, because multiple commands need to have two arguments, so the check can be done in one place instead of many. This also helps to remove redundant/duplicated code in Parser. 

**GUIDisplay runCommand():**
 * Eliminated long list of catches 

**StackedCanvasPane.setTurtleShape():**
* Extracted the reflection step into its own method to remove six lines from the method

**PenContextMenu.initializeMenuItems():**
* This method is now in SubContextMenu.  Made lambdas just one line to decrease the length of the message

**Tell:**
* Refactored longest method that need information from Bale (Turtle collection class) in order to determine how many turtles to create and which turtles to activate
* Refactored into three methods between Bale and Tell, one that determines which turtles are active (This is Tell’s job since its children are the ids of the now active turtles), one that determines how many turtles to make, and one that actually creates the turtles if needed

## Checklist Refactoring
**Exception Throwing:**
* This is an issue that I needed to fix in general. Now, instead of throwing multiple exceptions, all methods throw at most one. This is possible due to an Exception hierarchy, so all methods throw InvalidInput exception - which can be InvalidCommand, InvalidList, InvalidVariable, TooFewParameters, etc. This allows for front end to easily display error messages without having to differentiate between many types of exceptions. Then, they can simply print e.getReason, which differs based on type of Exception. 

**Parser:**
* “Wildcard imports should not be used” I changed this to be specifically importing List and ArrayList, because it does not need to have java.util.*, it only needs List and ArrayList.

**CommandController:**
* “Remove this unused variable” this is an old piece of code, so I removed it. 

**CommandFactory:**
* “Remove this unused import statement” this is an old piece of code, so I removed it. 
* “Return this value instead of assigning it to a variable” I do this in the method with two inputs because  it does not need any additional logic. However, in the method with only one input, I will be adding in additional logic before it returns, so I cannot do this in one statement only. 

**UserCreated:**
* “Remove empty methods” I did this, because they were empty methods that were never being used.
* “A method should only throw at most one exception” I am fixing this, mostly so that front end can easily display error messages. 

**PaletteTabExplorer:**
* Removed magic numbers and wildcard imports

**TurtleViewTabExplorer:**
* Remove magic numbers
* Remove unused imports
* Remove parentheses from lambda

**TabExplorer:**
* Remove magic numbers
* Added static to final variables initialized at declaration

**PenContextMenu and TurtleContextMenu:**
* Removed duplicated code by creating an abstract superclass from which both classes extend.
* The two classes now need to only implement one method but all the other methods are inherited from the super class
* Extracted a method from the longest method handle the creation of menu items

**StackedCanvasPane:**
* Refactored the reflection step into a separate method to decrease the length of the longest method.

**Turtle:**
* Added short circuit boolean operators

**Nodes:**
* Organized into packages

**HelpButton:**
* Remove unused statements

**DisplayView:**
* Change return of list to immutable list

## General Refactoring
All discussed above. 
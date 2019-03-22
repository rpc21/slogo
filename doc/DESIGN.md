# Design.md

### High-level design goals of the project
* Encapsulation  
    * We prioritized separating the front-end display of the IDE and back-end from one another so that any changes that occurred on either end would be nearly independent of one another. To accomplish this, we limited the ways in which they could communicate to one another by separating the two parts of our project into modules and creating a single contract by which the back end could communicate changes in state to the front end
    * The back-end used reflection and lambdas in order to limit access to information and methods of other classes. For example, the parser used reflection in order to generate the Command Nodes from the commands, and the Command Nodes used reflection in order to communicate what information it needed to receive or update to a more general collection of Turtle objects rather than directly accessing the turtles. Likewise, the front-end used lambdas to make it so different GUI components could communicate with each other only via the methods they need to access instead of having a reference to an entire other GUI component.  
    * The back-end’s parser uses the given properties files to create CommandNodes for the controllers to execute. However, rather than making nodes based on the user’s input text, the nodes correspond to the keys in the properties file to provide uniformity across languages and which command is input (i.e. + vs sum). Because of this, the parser does not need to be changed when a new language is added, it is just necessary to add in a new properties file with the necessary commands. Additionally, exception catching and throwing is encapsulated by the back-end and wraps up all errors relating to the user’s input into one type of exception: InvalidInputException. These exceptions have a .getReason() method, which front-end can print regardless of the type of exception. This way, front-end does not have to change anything if back-end needs to throw a new type of exception. 
    * The front-end also used reflection to create and change the different kinds of turtles that could be displayed on the screen.  Another area in which the front-end prioritized encapsulation was by using interfaces to group together similar GUI components such as the components that are able to run commands (implement the CommandExecutable interface) or components that have language dependencies and need to be updated when the language changes (implement the LanguageChangeable interface).  Our use of interfaces on the front-end helped our front-end organization and allowed us to only expose the relevant methods when looping over GUI components
* Delegation
  * In the front-end, there were often at least two ways any element of the display could change. This made delegation important as having shorter methods reduced code duplication and allowed different parts of the project to accomplish the same job under different circumstances.  The front-end also used a tree-like delegation structure where the Delegator class can be seen as the root of the tree and delegates our visual commands down to classes with more specific functionality to execute the commands.
  * In the back-end, it became especially important to delegate jobs associated with the turtles’ state to the same area their information was stored, rather than requiring the Command Nodes to access the information, make calculations from it, update it, and store it again. 
  * For the parser, it was also important to delegate jobs, for example with the Validator. The purpose of the validator was to allow the parser to filter out invalid commands or commented out lines. The validator also threw exceptions as necessary. This greatly cleaned up the parser code and allowed the parser to focus more on parsing than code validation. 
* Concise Code
  * We generally favored having more classes in order to limit the amount of work and information one class had to manage, and we also tried to limit the length of our methods so that our code would be more flexible and readable. This also helped us reduce repetition as future methods were able to use the shorter, more general, methods if they were a more separate entity 
  * We also used generics to handle situations in which classes were very similar only differing in types to allow us to write more concise, extensible code
  * We prioritized using reflection over factories to avoid the large if-trees
  * We used abstract superclasses to handle situations in which many front-end components are similar in their implementation but not similar enough to use generics to allow most of the functionality to be handled in the superclass, leaving only the specifics to be handled by the subclasses
  * The parser uses properties files to do all of the checking to avoid having a lot of regex scattered throughout the code. 
  * Printing errors in the front end is very concise because all exceptions are under one subclass of exception, so it is not necessary to have multiple if statements. 

### How to add new features to the project
* Non-Turtle Command
  * Add a subclass of CommandNode and implement its evaluate method to return the appropriate value
  * Add the command key that corresponds to the name of the subclass into each command properties file for each language. For the value of this key, add in the possible ways to call this command. 
  * Add the command key to the PackageLocation properties file with the appropriate package location as the value. 
  * Add the command key to the Parameters properties file with the number of expected parameters the command has 
  * Add information to “help” portion of properties’ files
* Turtle Command
  * Add a subclass of CommandNode and implement its evaluate method to return the appropriate value
  * Add the command key that corresponds to the name of the subclass into each command properties file for each language. For the value of this key, add in the possible ways to call this command. 
  * Add the command key to the Parameters properties file with the number of expected parameters the command has 
  * Add the command key to the PackageLocation properties file with the appropriate package location as the value. 
  * Add a variable (potentially) and a method to Turtle to keep track of and update the state of this particular command’s action
  * Add a subclass of ImmutableVisualCommand that invokes the front-end’s implementation of the  VisualUpdateAPI’s to respond appropriately
  * Implement the method(s) needed to display the changes in the canvas
  * Add information to “help” portion of properties’ files
* New Language
  * Add language as an option in drop-down menu of GUI
  * Add properties file to languages folder in resources 
  * Add the new language to the front-end Languages properties file
* New Argument Syntax
  * In order to add in a new type of syntax, add the regular expression with a defining key into the Synax properties file. 
  * In order to add an additional way to call a method, add “|” and the new way to call each method. 
* New Exception
  * Add in the exception and have it extend InvalidInputException
  * Add the desired error message to the Exceptions properties file
  * Set the reason according to the exceptions file
* New Type of Palette
  * Simply decide what kind of node you want to display in the palette and you can just use the Palette and Palette element classes already created in the project because they use generics
* New Type of Turtle
  * Find the image you want to use and create a subclass of DisplayView that points to this image
  * Add this new turtle’s class name to the Shapes properties file
  * Because of our use of properties files and reflection, no hard coding or factories have to be modified to add a new type of turtle with a new image

### Major design choices, including trade-offs
* Design Choice: Visual Command Abstraction
  * We were originally planning on using property binding so changes that occurred in the backend (such as a command that changes the current background color to be displayed) would be displayed in the front end. However, because we were really sure from the beginning that we wanted to use modules to separate our front end and back end, it was quite difficult to initialize these variables in a manner that permitted any relevant action to have access to these variables. 
  * Pro: The back-end is free from any JavaFX libraries and does not need to change in accordance with changes in the front-end. For example, the front-end manages displaying a turtle’s state (active vs in-active) by enlarging the active turtles. The back-end has no knowledge of this occurring and stores this information in a boolean variable “isActive”
  * Con: Changes in the back-end that are directly linked to the front-end must be communicated through an abstraction we created, Visual Commands, rather than updating automatically. This requires the back-end to manage ordering the visual commands in a proper manner so that they display logically. The front-end is only able to communicate changes to the back-end by building the actual command associated with the change, sending it to the parser, and then receiving back the information from the back-end, in order to ensure both ends are updated properly
* Design Choice: Creating a Separate Class for each GUI Component
  * We chose to create separate classes for each GUI component, even when they are just wrappers of other JavaFX classes with the preferences set to make the set up of GUI cleaner and to try to limit the amount of code in our GUIDisplayClass
  * Pros: The GUIDisplay class essentially only has to call constructors and add the components to the GridPane and organize the components into collections that are easier to manage such as the language changeable components and command executable components.  This design also allowed us to make JavaFX elements implement our own interfaces that helped our organization and added important functionality to the elements allowing big changes such as a language change to be handled with a simple for each loop rather than having to specifically handle each GUI component individually.
  * Cons: There are a couple classes such as CommandLine and HelpButton that are essentially just wrapper classes of JavaFX built-in classes.
* Design Choice: Separating the project into two modules, one for front-end and one for back-end
  * We chose to separate the project into two modules to enforce not having circular dependencies. We completed modules towards the end of sprint one, but in the future it may be a good idea to set up modules earlier on in order to make the process a bit easier. We chose to have back-end export to front-end, because front-end normally needs more information from back-end and back-end rarely needs front-end information. If there was a class that both modules needed to access, such as exceptions, they would be contained in the back-end. For the most part, only interfaces are exported to the front-end with the exception of the controller. This is because front end needs to make an instance of the controller that it can call, so it cannot simply have an interface.
  * Pros: Because we have modules, we know that we do not have any circular dependencies because front end never exports to back end, but back end exports to front end. Additionally, it is very easy to add in a new dependency by simply adding it to the module info. All of the dependencies are clearly stated in this file, which reduces the chance of having unnecessary dependencies.
  * Cons: These were difficult to set up, and sometimes preventing circular dependencies causes a lot of different difficult design problems. For example, because front end could not export to back end, it was necessary to store error messages in back-end OR allow front end to know about the different types of exceptions. Neither of these solutions were perfect, but we decided that having modules was a larger-scale design choice that improved the code in other ways. Additionally, it was difficult to communicate changes from the front-end to the back-end so the back-end would update appropriately when the user changed something graphically.  The front-end code had to build out a command to send through the parser to be able to register this change, requiring that the front-end new about the commands and command syntax.

### Assumptions or decisions made to simplify or resolve ambiguities in the the project's functionality
* Assumption - Toggle One Turtle at a Time: Though a user may toggle the turtles from the front-end, there are some limitations in how they may do that. Due to our decision to use a visual command abstraction to communicate information to the front-end, the user can not visually toggle more than one turtle to be active at any given point. They can, of course, still use the “tell” command instead to do this.
* Assumption - Not running strings of commands in several different languages at once: Our program cannot undo commands that are run in different languages.  We assumed that if a user was to switch language and run a series of commands they would switch languages when the screen was clear and run a fresh set of commands in the new language rather than running several commands in one language, switching languages, running more commands and then wanting to undo what they had done.
* Assumption - the user does not use nested lists or recursion. These two aspects are buggy and do not work properly, so the user can only make top-level lists and more basic methods. Other than this, lists and methods work. 
* Assumption - the user must run the code that defines a method before they use it. This was to improve  our design, so that the parser did not need to store information before the rest of back-end processed the information. Other ways to solve this would involve dynamically processing input, which also would not be a viable solution. If parser did not do error checking, then it would also work, but we figured that there would be more cases where the user inputs a wrong method than a correct one. 


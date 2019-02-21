API Review

Part 1
Dima
1. Our API will allow for multiple turtles to be controlled separately on the screen. Currently, our API is designed specifically for turtles and the proprties position, heading, and color.
2. The turtle sprite on the front end is not passed any information from a command except information that is required graphically (Movement, heading, color). Nothing about how the commands are run or are parsed is known to the front end or the turtle sprite. 
3. Parsing exceptions will print instructions and feedback to the user via the command log. Command executing exceptions that have to do with invalid arguments or missin information will also through exceptions by printing detailed feedback to the user to the command log. This we felt is a good way to communicate exceptions that require user activity, and is similar to what happens in terminal. 
4. We think that our current API is good because it is able to handle all the use cases that we've run through and thought out. The use cases in our design plan cover most of the commands and features we have considered implementing in our project.

Ryan
1. Our flexibility mostly comes from our updateDisplay method that will iterate through a list of unmodifiable displayable objects passed from the backend to update the display of the front end.
2. The details about parsing, commands, syntax errors, etc. are completely unknown by the front end.  Unfortunately, many front end components are known by the back end due to our implementation decision of using property binding to communicate between back end and front end.
3. We will handle exceptions thrown by the parser to communicate to user about the different errors in their command.
4. Our API design is good because the front end external API is very small (ony one method) which indicates that there is not a lot of dependency between the back end and front end.

Part 2

Dima
1. Our current design uses the composite pattern, as each of the turtles is separate by can be treated in the same way. The Memento pattern could be used in order to have an "undo" button is we decide to implement this in our slogo. 
2. I am very excited to work on the variable view because it sounds challenging, but I think is very useful to the user, and a feature that is available in most environments. 
3. While I am excited to work on the variable view, it is also the one I am most worried about. I think that keeping the information displayed clearly and up to date will be challenging. It is definitely a good place to use bindings to keep the variable states up to date. 
4. 



Ryan
1. We think our design would be benefited by using the Observer design pattern to pass informationg from the back end to all the necessary GUI components.
2. I am excited to determine the best way to arrange all our components for resizability
3. I am worried about handling updates from backend in a way that doesn't destroy our design and force us to write bad code. I am also worried about how to use the resources folder and property files
4. 
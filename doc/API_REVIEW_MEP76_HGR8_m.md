# API Review SLogo - mep76 (Megan Phibbons), hgr8 (Harry Ross)

### Part One:
1. Our design is intended to be flexible in the areas of adding in new commands and also in that we can add in 
new numbers of parameters to each command. For example, in order to add in > 2 parameters, we would just need to 
add the new parameters to the child nodes of our Command tree hierarchy. Additionally, in order to add in new types 
of commands, it is only necessary to create a new Command subclass for that command and update the resources file
for the parser. *Note: The idea of a resources file for the command parser came from my discussion with Harry, because
it makes error checking much easier. If the user inputs an incorrect command, it will simply not be in the file. If they
do not have enough parameters, it is easy to tell this from the file. This was probably one of the most helpful
design decisions that came from this conversation. 

2. One of our biggest forms of encapsulation in this project is in our Command class. Each subclass of Command
has a method called evaluate() that will essentially turn the command into an int. This would be a recursive process,
as internal commands would need to evaluate before external commands. However, outside of the Command class, this just
shows up as evaluate() regardless of the type of command. This hides many implementation details from the rest of the code. 

3. My parser will throw two main exceptions: InvalidCommandException() and InvalidArgumentException(). These will be
thrown to the UI to display the error so that the user can resolve the issue properly. InvalidCommandException() will be thrown 
when the command cannot be found in the command properties file, and InvalidArgumentException() will be thrown when
the number of arguments to the command does not match that listed in the properties file. Additionally, the Command classes
will be throwing exceptions for math errors and other issues. 

4. I think our API design is ok, because it is fairly easy to add in new features but hides the implementation of those
features from the rest of the code. Additionally, we have designed our back end such that any issues will not
cause the code to crash, but instead will let the user know that something that they have done is wrong. This is
especially important for SLogo, because it heavily relies on user input. Because of this, we need to make sure our code
is durable and cannot break easily, which will involve extensive testing of each feature to ensure users will not break anything.

### Part Two:
1. We have yet to thoroughly discuss design patterns as a team, but I think that our Command hierarchy is 
generally like a factory class based on subclasses. However, I do think that we as a team need to take a look
at our plan and see how we can implement design patterns more to improve our design. We have discussed their
usefulness, so now we just need to figure out how to implement them. One thing that Harry mentioned was
using a Factory Reflection design pattern, which I want to look into a bit more and see if it would be useful
in our case. 

2. Overall, I think I'm most excited about creating the hierarchy of how the parsed information/commands
are stored. I think that this is a fun and interesting design challenge that will really make me think 
on how I communicate with other parts of the program. While it definitely poses its challenges, I look forward
to implementing it and working with my team on using it!

3. I am very worried about implementing control structures, to the point that I am considering having
a special type of Command for them. I think that they are very different from the rest of the command
types, so I am unsure how to implement this well. Additionally, I am worried about implementing modules,
because they are very new to me and I am still a little shaky on how they work. I think that it would be
very beneficial to logic it through and figure out how they work. Additionally, I think they will be very
helpful in enforcing strict separations between front and back end. In the end, I know that they are 
good for our project, but I really just need to sit down and figure them out. 

4. Use Cases:
    1. The user inputs a simple command that is correct and with the right number of arguments.
    2. The user mistypes the command name or enters a command that does not exist.
    3. The user correctly types a command that exists, but has the wrong number of parameters.
    4. The user inputs nested commands (i.e. add add add 2 2 2 2)
    5. The user inputs correct commands in terms of the parser, but their math is invalid (i.e. divide by 0)

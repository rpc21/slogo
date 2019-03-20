SLogo
====

This project implements a command line interpreter

Names: Ryan Culhane, Anna Darwish, Louis Jensen, Megan Phibbons

### Timeline

* Start Date: February 17 2019
* Finish Date: March 8 2019
* Hours Spent: 300 hours

### Roles

* Ryan Culhane - Developed system of transferring and displaying information from backend to frontend
* Anna Darwish - Developed command hierarchy and system of communication from the backend to the front end
* Louis Jensen - Developed internal front end and interactive components with the turtles
* Megan Phibbons - Developed the parser, organized code into separate modules, and error checking system


### Resources 

* Benjamin Xu - He really helped us by challenging our ideas for design early on and explaining the benefit of separating our project into modules
* Visual Inspiration from https://www.calormen.com/jslogo/#
* Code provided in CS308 from https://coursework.cs.duke.edu/compsci308_2019spring/lab_advanced to help with reflection and lambda usage

### Running the Program

Main class: SLogoMain is in front_end/src/main

### Frontend Files

* Turtle Image files
    * basic_turtle1.jpg
    * gitlab-turtle.png
    * images.png
    * turtle.jpg
* Language properties files - displays visual text in different languages
    

### Backend Files

* Language properties files - allows commands to run in different languages
* Exceptions.properties - content of each exception type
* PackageLocation.properties - used for reflection in command factory
* Parameters.properties - used for command validation


### Handling Errors

* Invalid command key words
* Invalid variable formatting
* Invalid command arguments (too few/too many)
* Arithmetic Errors (i.e. dividing by zero)
* Infinite Loops

### Information About Using the Program

* All information regarding command arguments can be found in the help page

### Assumptions or Simplifications:

* A user can not use a method before first defining it (this entails running their desired method before running it)
* When toggling turtles, one can not set multiple to be active at once (setting one to be active makes the remaining turtles inactive)
* fd set :x + :x 10 - In the situation that we have a command such as the following, each active turtle will move the same distance, as the x value
 will be evaluated and then all of the active turtles will perform the forward movement

### Known Bugs:

* No known bugs

### Impressions

* Anna: This project was significantly more difficult from both a code design and functionality perspective. However, I felt 
 that the lectures and study of design patterns significantly helped in understanding how to tackle the problem. I was definitely very 
 frustrated at the beginning because I wrote very little code and spent a lot more time drawing out and discussing designs. 
 It felt like I was being unproductive, but in retrospect, I'm really glad we all worked hard to develop a flexible design rather 
 than worry about implementing all of the funtionalities in the sprints.
* Megan: This project was enjoyable, but the design was very challenging. For example, implementing modules was a very difficult 
  design choice, but in the end, it forced us to follow better design practices. For example, we could not have any 
  circular dependencies, and separating into modules made it much easier to ensure that we did not do this. Additionally, I 
  felt like the parser was a difficult problem to solve, but I appreciated  having parsing starter code. 
* Ryan: I was a bit apprehensive to be on visualization for this project, as I was back-end on Cell Society. It was really helpful 
  to learn about lambdas and consumers during these past couple weeks to refactor visualization code into separate areas, 
  because it was quite difficult to avoid circular dependencies. In the end, I really enjoyed seeing all of it come together!
* Louis: This project was very interesting for me. I worked on the front end which was enjoyable because on Cell Society I worked on 
  configuration and backend so I had never focused so much on UI before. After getting a little experience with both, I think that I 
  like working on back end better. However, I’m glad I tried front end to get some experience and learn how to work with JavaFX. I also 
  found consumers and reflection to be very interesting and helpful in our project.


# Vehicle Data Calculator
## Description
The Vehicle Data Calculator (VDC) is a specific purpose made calculator with a user interface. Originally based on Python for use in terminal, it was used in another project (MTA:SA RolePlay server), where there was a need to create scalable and balanced vehicles whose stats differed due to price and engine size difference. It calculates values based on input (spcified engine size and car price) and outputs values, based on internal limits and other variables. VDC not only allows you to calculate values, but also gives you the ability to add, remove and edit categories and other settings.

## Data sources
 - Config file, that stores information for calculation purposes. Can be edited;
 - Category and Bound classes, used in the program;
 - UI uses mouse and keyboard inputs;

## Results
User relevant results are shown visually (as numbers) in the UI, once valid values are provided. There is no saving mechanism (yet) for values to be accesses somewhere else later. The only file that is edited by the program is the config file.

## Conclusions and progress during the course
I started this course with some above-beginner knowledge about programming in general and small experience in OOP languages like JavaScript, Python and amateur-level knowledge in functional language Lua (MTA:SA API). In Lua I did designed Front-end UI's above else, therefore I had no problems understanding the logic of putting elements in places, although JFrame is pretty old and clunky in that matter. During the course, I also grasped more of the OOP concept, which expanded my vision of past projects (especialy ones in Lua). 
This course improved my coding abilities and understanding of programming. I still don't think my code is nowhere near perfect and there are things that could be improved so I am eager for the II part of the course to learn more about the tools I can use to code as well as good programming practices.

## Disclaimer
I am fully aware that there are some very bad practices in the code that I cannot fix as of now due to lack of proper knowledge about Java. I also know that there are things that could be improved and fixed (as example - multiple TxtField resetting (class extention)).

# Development 
## Known bugs
- Sometimes visual elements become invisible;

## To-Add
 - ~Fix the file writing, so it writes to specific lines;~
 - ~Ability to create new categories;~
 - Dynamic everything?

 ## To-Fix
 - ~Check if file exists;~
 - Somehow check if categories are valid in the file;
 - ~Button gets disabled, if values are incorrect;~
 - Rethink the mathematics so values are not out of bounds;
 - Make code more readable;
 - Rethink the JTextField operation methods (extending);

 ## Comments
 - ~ComboBox - to fix it, remove old one and add a NEW object, filled with new data.~

 ## Thoughts
 At this moment, at least on 25.11. the programm first reads and stores to objects the categories. Why this is good, but also - is not? Well, imagine, there are 51245343 categories. Then you would have to wait longer to use the program. That is why my first idea and code used to write data not in objects, but in hashmap for the specific category, that is used, but the catch? Every single time, when it was in a new category, it had to look for that data in the config. So there is a dillemma - make the program faster for short uses in one category, or faster-ish in case you want to use multiple categories all the time. But lets be real, this calculater had such a unique use, that it does not matter anymore and I can always limit the numer of categories.

 This project in was made as a coursework for Programming Basics I, Vidzeme University of Applied Sciences, 2022.


# VehlibCalculator
## To-Add
 - Fix the file writing, so it writes to specific lines;
    The logic goes along lines - as all category values are saved to objects, then, after editing, you edit the values in the specific category. After that, override everything in the config file. 
 - Ability to create new categories;

 ## To-Fix
 - Check if file exists;
 - Check if all categories are okay in the congfig file, if not, prompt to make a new config file;
 - Button gets disabled, if values are incorrect;

 ## Some thought about this projects
 At this moment, at least on 25.11. the programm first reads and stores to objects the categories. Why this is good, but also - is not? Well, imagine, there are 51245343 categories? Then you would have to wait longer to use the program. That is why my first idea and code used to write data not in objects, but in hashmap for the specific category, that is used, but the catch? Every single time, when it was in a new category, it had too look fr that data in the config. So there is a dillemma - make the program faster for short uses in one category, or fasterish in case you want to multiple categories all the time. But lets be real, this calculater had such a unique ue, that it does not matter anymore and I can always limit the numer of categories.



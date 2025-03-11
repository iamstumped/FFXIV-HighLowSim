# FFXIV High or Low Simulator
Simulate the results of a game of "High or Low" against Tista-bie in Eulmore. This is done by randomly selecting the face-down cards. 

At the default number of simulations (50 k), the results have just under 1% spread. Accuracy can be increased by running more simulations.

### Copying results
Users can optionally choose to automatically copy the cards and results to the clipboard after running the simulations.
- This will be in the format `TistaCard1 TistaCard2 YourCard HighChance LowChance DrawChance` separated by tabs.
- You will be prompted at the start of the program if you would like to enable this or not. Your choice will be used until you exit and reopen the program.
- This has been designed to make it easy to paste into a spreadsheet. Tested with LibreOffice Calc, should work just fine with other programs.


## Requirements
- JDK 23 or newer.
### Windows
For whatever reason, the default Windows command prompt doesn't support ANSI codes by default. This can be fixed with a registry tweak. 
A .bat file is included with each release that will set this for you, then run the program (assuming both are in the same directory). 
* This is not an issue if you use the combined Terminal app.
* This is not required to run the program, however it will make the output look messy if not set.

## Arguments
You can specify the number of simulations to run by passing a number from the command line. The default is 50000.\
Syntax: `java -jar HighLowSim.jar <number>` \
Example: The default value would be `java -jar HighLowSim.jar 50000` 

Card values are not passed as arguments. These are prompted in the program.

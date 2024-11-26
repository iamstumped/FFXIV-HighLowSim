# FFXIV High or Low Simulator
Simulate the results of a game of "High or Low" against Tista-bie in Eulmore. This is done by randomly selecting the face-down cards. 

At the default number of simulations (50 k), the results have just under 1% spread. Accuracy can be increased by running more simulations.

## Requirements
- JDK 23 or newer.

## Arguments
You can specify the number of simulations to run by passing a number from the command line. The default is 50000.\
Syntax: `java -jar HighLowSim.jar <number>` \
Example: The default value would be `java -jar HighLowSim.jar 50000` 

Card values are not passed as arguments. These are prompted in the program.

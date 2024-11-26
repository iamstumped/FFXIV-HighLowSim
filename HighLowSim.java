package highlowsim;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.InputMismatchException;

public class HighLowSim {
    
    public static void shuffle(ArrayList al) {
        Collections.shuffle(al);
    }
    
    public static int sumHand(Card[] hand) {
        int sum = 0;
        for (Card card : hand) {
            sum += card.getValue();
        }
        return sum;
    }
    
    public static void pause() {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Press Enter to exit...");
        keyboard.nextLine();
    }
    
    public static void main(String[] args) {
        //constants
        int SIM_COUNT = 50000;
        boolean SHOW_RESULTS = false;
        //variables
        int highCount, lowCount, drawCount;
        int input = 0;
        char repeat = 'y';
        boolean valid;
        
        //objects
        Scanner keyboard = new Scanner(System.in);
        
        Card[] tistaHand;
        Card[] playerHand;
        ArrayList<Card> deck;
        
        //command line arguments
        if (args.length > 0) {
            try {
                SIM_COUNT = Integer.parseInt(args[0]);
            }
            catch (NumberFormatException nfe) {
                System.out.println("\u001B[31mError: Command line arguments were detected, but could not be parsed. \u001B[0m\nDefault values will be used.");
            }
        }
        
        while (repeat == 'y') {
            //resetting
            highCount = lowCount = drawCount = 0;
            
            tistaHand = new Card[3];
            playerHand = new Card[3];
            deck = new ArrayList<>(9);
            
            //setup
            for (int i = 0; i < 9; i++) {
                deck.add(i, new Card(i + 1));
            }
            
            //set first given card
            valid = false;
            do {
                System.out.print("Enter Tista's first card: ");
                //try catch to ensure integer input
                try {
                    //get input
                    input = keyboard.nextInt();
                    //check if the card is in the deck
                    if (deck.remove(new Card(input))) {
                        //if it is, continue
                        tistaHand[0] = new Card(input);
                        valid = true;
                    } else {
                        //if not, print error and retry
                        System.out.println("\u001B[31mError: Invalid number. Enter an integer between 1 and 9 (inclusive) and do not repeat.\u001B[0m");
                    }
                } 
                catch (InputMismatchException ime) {
                    System.out.println("\u001B[31mError: Invalid input. Enter an integer between 1 and 9 (inclusive).\u001B[0m");
                }
                keyboard.nextLine();  
            } while (!valid);
            
            
            //set second given card
            valid = false;
            do {
                System.out.print("Enter Tista's second card: ");
                //try catch to ensure integer input
                try {
                    //get input
                    input = keyboard.nextInt();
                    //check if the card is in the deck
                    if (deck.remove(new Card(input))) {
                        //if it is, continue
                        tistaHand[1] = new Card(input);
                        valid = true;
                    } else {
                        //if not, print error and retry
                        System.out.println("\u001B[31mError: Invalid number. Enter an integer between 1 and 9 (inclusive) and do not repeat.\u001B[0m");
                    }
                } 
                catch (InputMismatchException ime) {
                    System.out.println("\u001B[31mError: Invalid input. Enter an integer between 1 and 9 (inclusive).\u001B[0m");
                }
                keyboard.nextLine();  
            } while (!valid);
            
            
            //set third given card
            valid = false;
            do {
                System.out.print("Enter your card: ");
                //try catch to ensure integer input
                try {
                    //get input
                    input = keyboard.nextInt();
                    //check if the card is in the deck
                    if (deck.remove(new Card(input))) {
                        //if it is, continue
                        playerHand[0] = new Card(input);
                        valid = true;
                    } else {
                        //if not, print error and retry
                        System.out.println("\u001B[31mError: Invalid number. Enter an integer between 1 and 9 (inclusive) and do not repeat.\u001B[0m");
                    }
                } 
                catch (InputMismatchException ime) {
                    System.out.println("\u001B[31mError: Invalid input. Enter an integer between 1 and 9 (inclusive).\u001B[0m");
                }
                keyboard.nextLine();  
            } while (!valid);

            //simulations
            long startTime = System.nanoTime();
            for (int i = 0; i < SIM_COUNT; i++) {
                //randomly deal face-down cards
                shuffle(deck); 

                tistaHand[2] = deck.get(0);
                playerHand[1] = deck.get(1);
                playerHand[2] = deck.get(2);

                //record results
                if (sumHand(playerHand) > sumHand(tistaHand)) {
                    if (SHOW_RESULTS) System.out.println("HIGH");
                    highCount++;
                }
                else if (sumHand(playerHand) < sumHand(tistaHand)) {
                    if (SHOW_RESULTS) System.out.println("LOW");
                    lowCount++;
                }
                else {
                    if (SHOW_RESULTS) System.out.println("DRAW");
                    drawCount++;
                }

            }
            long endTime = System.nanoTime();

            //show results
            System.out.println("\n--COMPLETE--\nSimulations run: " + SIM_COUNT + "\n" + "Elapsed time: " + String.format("%.03f", (endTime - startTime)/1000000.0) + " ms\n");

            if (highCount > lowCount) {System.out.print("\u001B[32m");}
            System.out.println("High: " + highCount + " time" + (highCount != 1 ? "s, " : ", ") + String.format("%7.03f", (highCount/(float)SIM_COUNT)*100) + "% chance\u001B[0m");

            if (lowCount > highCount) {System.out.print("\u001B[32m");}
            System.out.println("Low:  " + lowCount + " time" + (lowCount != 1 ? "s, " : ", ") + String.format("%7.03f", (lowCount/(float)SIM_COUNT)*100) + "% chance\u001B[0m");

            System.out.println("Draw: " + drawCount + " time" + (drawCount != 1 ? "s, " : ", ") + String.format("%7.03f", (drawCount/(float)SIM_COUNT)*100) + "% chance\n");

            System.out.print("Run again? (y/n): ");
            repeat = keyboard.nextLine().trim().charAt(0);
            
            System.out.println("------------------\n\n");
        }
    }
    
}

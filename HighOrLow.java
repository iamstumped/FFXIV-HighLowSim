package highorlow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class HighOrLow {
    
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

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
        System.out.print(ANSI_GREEN + "Press Enter to continue..." + ANSI_RESET);
        keyboard.nextLine();
    }
    
    public static void main(String[] args) {
        //variables
        int input = 0;
        String inputString = "";
        boolean valid = false;
        boolean play = true;
        boolean win;
        int tistaPoints, playerPoints;
        
        
        //constants
        String MM_REGEX = "['\\.\\?! \"]";
        
        //objects
        Scanner keyboard = new Scanner(System.in);
        
        Card[] tistaHand;
        Card[] playerHand;
        ArrayList<Card> deck;
        
        //Main menu
        System.out.println(ANSI_CYAN + "Tista-Bie: " + ANSI_RESET + "Most people buzz about during the day, whereas I am a creature of the night. AFter all, the best games are played after dark, wouldn't you agree?");
        System.out.println(ANSI_CYAN + "\nWhat will you say?" + ANSI_RESET + " (Enter the number to choose)\n"
                + "----------------------\n"
                + "1. Let's play \"High or Low\"!\n"
                + "2. Maybe another time.\n"
                + "3. What are the rules?");
        
        //loop until menu done
        do {
            //loop until valid input
            do {
                try {
                    input = keyboard.nextInt();
                } 
                catch (InputMismatchException ime) { //if not a valid number, checks if it's text of the listed option
                    inputString = keyboard.nextLine().replaceAll(MM_REGEX,"");
                    if (inputString.equalsIgnoreCase("Let's play \"High or Low\"!".replaceAll(MM_REGEX,""))) {input = 1;}
                    else if (inputString.equalsIgnoreCase("Maybe another time.".replaceAll(MM_REGEX,""))) {input = 2;}
                    else if (inputString.equalsIgnoreCase("What are the rules?".replaceAll(MM_REGEX,""))) {input = 3;}
                    else {System.out.println(ANSI_RED + "Error: Invalid number. Choose a listed option." + ANSI_RESET);}
                }
                System.out.println("----------------------");
                valid = !(input < 1 || input > 3);
                if (!valid) {
                    System.out.println(ANSI_RED + "Error: Invalid number. Choose a listed option." + ANSI_RESET);
                }
            } while (!valid);
            
            //menu results
            if (input == 3) {
                System.out.println("""
                                   "High or Low" is a guessing game. You must decide whether the total of your three cards is higher or lower than the total of your opponent's cards. 
                                   The deck holds nine cards, with the pictures printed thereon representing the numbers one through nine. Each number appears only once in the deck, so there are no repeats. 
                                   If you guess correctly, then you win the match. If both totals are the same, then another hand is dealt. """);
                pause();
                System.out.println(ANSI_CYAN + "\nWhat will you say?" + ANSI_RESET + " (Enter the number to choose)\n"
                        + "----------------------\n"
                        + "1. Let's play!\n"
                        + "2. Maybe another time.\n"
                        + "3. What are the rules?");
            }
        } while (input == 3);
        
        if (input == 2) {
            System.out.println(ANSI_CYAN + "Tista-Bie: " + ANSI_RESET + " Oh? Well, you know where to find me if you want to play a hand or two.");
        }
        else if (input == 1) {
            System.out.println(ANSI_CYAN + "Tista-Bie: " + ANSI_RESET + " Come then, honeybee. Let's have some fun.");
            
            //game start
            while (play) {
                //deal cards
                tistaHand = new Card[3];
                playerHand = new Card[3];
                deck = new ArrayList<>(9);
                for (int i = 0; i < 9; i++) {
                    deck.add(i, new Card(i + 1));
                }
                shuffle(deck);
                for (int i = 0; i < 3; i++) {
                    tistaHand[i] = deck.remove(0);
                    playerHand[i] = deck.remove(0);
                }
                tistaPoints = sumHand(tistaHand);
                playerPoints = sumHand(playerHand);
                
                System.out.println("\n|-------|\n"
                        + "| ? ? ? |\n|       |\n"
                        + "| ? ? ? |\n"
                        + "|-------|");
                System.out.println(ANSI_CYAN + "Tista-Bie: " + ANSI_RESET + "Three cards for you, three cards for me. You flip one of yours over, and I flip two of mine.");
                pause();
                
                System.out.println("\n|-------|\n"
                        + "| " + tistaHand[0].getValue() + " "+ tistaHand[1].getValue() +" ? |\n|       |\n"
                        + "| "+ playerHand[0].getValue() +" ? ? |\n"
                        + "|-------|");
                System.out.println(ANSI_CYAN + "Tista-Bie: " + ANSI_RESET + "Tista-Bie: Now what's your call? Will your total be higher or lower than mine?");
                System.out.println(ANSI_CYAN + "\nI choose..." + ANSI_RESET + " (Enter the number to choose)\n"
                + "----------------------\n"
                + "1. High!\n"
                + "2. Low!");
                //loop until valid input
                do {
                    try {
                        input = keyboard.nextInt();
                    } catch (InputMismatchException ime) { //if not a valid number, checks if it's text of the listed option
                        inputString = keyboard.nextLine().replaceAll(MM_REGEX, "");
                        if (inputString.equalsIgnoreCase("High!".replaceAll(MM_REGEX, ""))) {input = 1;} 
                        else if (inputString.equalsIgnoreCase("Low!".replaceAll(MM_REGEX, ""))) {input = 2;} 
                        else {System.out.println(ANSI_RED + "Error: Invalid number. Choose a listed option." + ANSI_RESET);}
                    }
                    System.out.println("----------------------");
                    valid = !(input < 1 || input > 2);
                    if (!valid) {
                        System.out.println(ANSI_RED + "Error: Invalid number. Choose a listed option." + ANSI_RESET);
                    }
                } while (!valid);
                
                System.out.println(ANSI_CYAN + "Tista-Bie: " + ANSI_RESET + "You're going with " + (input == 1 ? "high" : "low") + " then? Right, let's see what we've got...");
                pause();
                
                System.out.println("\n|-------|\n"
                        + "| " + tistaHand[0].getValue() + " "+ tistaHand[1].getValue() +" " + tistaHand[2].getValue() + " |\n|       |\n"
                        + "| "+ playerHand[0].getValue() +" " + playerHand[1].getValue() + " ? |\n"
                        + "|-------|");
                System.out.println(ANSI_CYAN + "Tista-Bie: " + ANSI_RESET + "My cards add up to " + tistaPoints + ". And you have...");
                pause();
                
                System.out.println("\n|-------|\n"
                        + "| " + tistaHand[0].getValue() + " "+ tistaHand[1].getValue() +" " + tistaHand[2].getValue() + " |\n|       |\n"
                        + "| "+ playerHand[0].getValue() +" " + playerHand[1].getValue() + " " + playerHand[2].getValue() + " |\n"
                        + "|-------|");
                System.out.println("...a total of " + playerPoints + ".");
                pause();
                System.out.println();
                
                //results of game
                if ((playerPoints > tistaPoints && input == 1) || (playerPoints < tistaPoints && input == 2)) {
                    win = true;
                    System.out.println(ANSI_YELLOW + "~~YOU WIN!~~" + ANSI_RESET);
                    System.out.println(ANSI_CYAN + "Tista-Bie: " + ANSI_RESET + "Congratulations, your guess was right!");
                    System.out.println(ANSI_CYAN + "Tista-Bie: " + ANSI_RESET + "How does victory taste? Sweet as nectar, yes?");
                } 
                else if (playerPoints == tistaPoints) {
                    win = false;
                    System.out.println(ANSI_BLUE + "~~DRAW~~" + ANSI_RESET);
                    System.out.println(ANSI_CYAN + "Tista-Bie: " + ANSI_RESET + "Oho, it seems we came out equal. But as lovely a coincidence as that may be. we still need a clear winner..");
                }
                else {
                    win = false;
                    System.out.println(ANSI_RED + "~~YOU LOSE...~~" + ANSI_RESET);
                    System.out.println(ANSI_CYAN + "Tista-Bie: " + ANSI_RESET + "Aw... Sorry, honeybee, but this match goes to me.");
                }
                
                pause();
                System.out.println(ANSI_CYAN + "Shall we play again?" + ANSI_RESET + " (Enter the number to choose)\n"
                + "----------------------\n"
                + "1. Yes\n"
                + "2. No");
                //loop until valid input
                do {
                    try {
                        input = keyboard.nextInt();
                    } catch (InputMismatchException ime) { //if not a valid number, checks if it's text of the listed option
                        inputString = keyboard.nextLine().replaceAll(MM_REGEX, "");
                        if (inputString.equalsIgnoreCase("Yes".replaceAll(MM_REGEX, ""))) {input = 1;} 
                        else if (inputString.equalsIgnoreCase("No".replaceAll(MM_REGEX, ""))) {input = 2;} 
                        else {System.out.println(ANSI_RED + "Error: Invalid number. Choose a listed option." + ANSI_RESET);}
                    }
                    System.out.println("----------------------");
                    valid = !(input < 1 || input > 2);
                    if (!valid) {
                        System.out.println(ANSI_RED + "Error: Invalid number. Choose a listed option." + ANSI_RESET);
                    }
                } while (!valid);
                
                if (input == 1) {
                    if (win) {
                        System.out.println(ANSI_CYAN + "Tista-Bie: " + ANSI_RESET + " Stay with me a while longer, my dear.");
                    }
                    else {
                        System.out.println(ANSI_CYAN + "Tista-Bie: " + ANSI_RESET + " That's what I like to hear. Let me shuffle the deck..."); 
                    }
                    pause();
                    play = true;
                }
                if (input == 2) {
                    if (win) {
                        System.out.println(ANSI_CYAN + "Tista-Bie: " + ANSI_RESET + " So you wish to end on a high note? Perfectly understandable.");
                    }
                    else {
                        System.out.println("Had enough for now? Fine, fine—I'll be sitting right here should the mood strike you."); 
                    }
                    pause();
                    play = false;
                }
            }
        }
    }
    
}

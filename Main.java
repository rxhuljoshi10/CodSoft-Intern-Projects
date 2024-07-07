import java.util.*;
import java.io.*;

class NumberGame{
    Scanner scanner = new Scanner(System.in);;
    private int maxRange;
    private boolean win = false;

    NumberGame(){
        maxRange = 25;
    }

    public void start(){
        while(true){
            int chances = 5;
            int correctNumber = new Random().nextInt(50);
            
            while(chances != 0){
                System.out.println("\n[Chances Left : "+chances+"]");
                System.out.print("Guess a number (1 - "+maxRange+") : ");
                int guessedNumber = scanner.nextInt();
                if(guessedNumber == correctNumber){
                    System.out.println("\nYou guessed it right!");
                    win = true;
                    break;
                }
                chances--;
            }

            if(!win){
                System.out.println("\n\tYou Lost..!");
            }
            System.out.println("\n\nDo you want to play again? (Y/N) : ");
            String userChoice = scanner.next();
            if(userChoice.equalsIgnoreCase("N")){
                System.exit(0);
            }
        }
    }
}

class Main{
    public static void main(String[] args){
        NumberGame numberGame = new NumberGame();
        numberGame.start();
    }
}
package assignment2;
/* EE422C Assignment #2 submission by
 * Replace <...> with your actual data.
 * Daniel Park
 * dp34433
 */
import java.util.*;
import java.util.Scanner;
import java.util.Random;

public class Game {
    private GameBoard gameBoard;
    private Scanner scanner;

    public Game(GameBoard gameBoard, Scanner scanner) {
        this.gameBoard = gameBoard;
        this.scanner = scanner;
    }
    public void playAgain(){
        // Ask if the player wants to play again
        System.out.print("Are you ready for another game? (Y/N): ");
        System.out.println();
        char playAgain = scanner.next().charAt(0);
        if (playAgain == 'Y') {
            Driver.continueGame = true;
            // reset the player guesses array
            gameBoard.resetFeedbackHistory();
            //gameBoard.resetPlayerGuesses();
        } else{
            Driver.continueGame = false;
        }
    }

    public void runGame() {
        //generate  secret code
        System.out.println("Generating secret code ...");
        System.out.println();
        while (!gameBoard.isGameOver()) {
            System.out.println("You have " + gameBoard.remainingGuesses + " guesses left.");
            System.out.println("What is your next guess?");
            System.out.println("Type in the characters for your guess and press enter.");
            System.out.print("Enter guess: ");
            String playerGuessInput = scanner.next();
            if(playerGuessInput.equals("HISTORY")){
                gameBoard.getFeedbackHistory();
                continue;
            }
            System.out.println();
            // Parse the player's input into a Code object (R,G,B,O,...)
            String[] playerGuessColors = new String[playerGuessInput.length()];
            for(int i = 0; i < playerGuessInput.length(); i++ ){
                playerGuessColors[i] = String.valueOf(playerGuessInput.charAt(i));
            }
            Code playerGuess = new Code(playerGuessColors, playerGuessInput);
            if(playerGuess.valid_guess) {
                gameBoard.addGuess(playerGuess);
                gameBoard.generateFeedback(playerGuess);
            }
        }
        if (gameBoard.isOutOfGuesses()) {
            System.out.println("Sorry, you are out of guesses. You lose, boo-hoo.");
        }
    }
}
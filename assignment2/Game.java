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

    public void runGame() {
        //generate  secret code
        System.out.println("Welcome to Mastermind!");
        System.out.println("You have " + GameConfiguration.guessNumber + " guesses to figure out the secret code.");

        while (!gameBoard.isGameOver()) {
            System.out.println("What is your next guess?");
            System.out.println("Type in the characters for your guess and press enter.");
            String playerGuessInput = scanner.nextLine();
            System.out.print("Enter guess: " + playerGuessInput);
            // Parse the player's input into a Code object (R,G,B,O,...)
            String[] playerGuessColors = playerGuessInput.split(" ");
            Code playerGuess = new Code(playerGuessColors);
            if (gameBoard.isGuessCorrect(playerGuess)) {
                gameBoard.addGuess(playerGuess);

                Feedback feedback = gameBoard.generateFeedback(playerGuess);
                System.out.println("Feedback: " + feedback);

                if (feedback.getBlackPegs() == GameConfiguration.pegNumber) {
                    System.out.println("Congratulations! You've guessed the secret code: " + String.join(" ", gameBoard.getSecretCode().getColors()));
                    break;
                } else {
                    System.out.println("Incorrect guess.");
                }
            } else {
                System.out.println("Invalid guess. Please enter " + GameConfiguration.pegNumber + " valid colors.");
            }
        }

        if (gameBoard.isOutOfGuesses()) {
            System.out.println("Out of guesses! The secret code was: " + String.join(" ", gameBoard.getSecretCode().getColors()));
        }
        // Ask if the player wants to play again
        System.out.print("Do you want to play again? (Y/N): ");
        char playAgain = scanner.next().charAt(0);
        if (playAgain == 'Y' || playAgain == 'y') {
            runGame(); // Restart game
        } else if (playAgain == 'N' || playAgain == 'n') {
            System.out.println("Thanks for playing!");
        }
    }
}
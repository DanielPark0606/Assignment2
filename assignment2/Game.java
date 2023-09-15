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
        System.out.println("Generating secret code ...");
        while (!gameBoard.isGameOver()) {
            System.out.println("What is your next guess?");
            System.out.println("Type in the characters for your guess and press enter.");
            String playerGuessInput = scanner.nextLine();
            if(playerGuessInput.equals("HISTORY")){
                gameBoard.getFeedbackHistory();
            }
            System.out.print(playerGuessInput);
            // Parse the player's input into a Code object (R,G,B,O,...)
            String[] playerGuessColors = new String[playerGuessInput.length()];
            for(int i = 0; i < playerGuessInput.length(); i++ ){
                playerGuessColors[i] = String.valueOf(playerGuessInput.charAt(i));
            }
            Code playerGuess = new Code(playerGuessColors);
            if(playerGuess.valid_guess) {
                gameBoard.addGuess(playerGuess);
                Feedback feedback = gameBoard.generateFeedback(playerGuess);
                if (feedback.getBlackPegs() == GameConfiguration.pegNumber) {
                    System.out.println("Congratulations! You've guessed the secret code: " + String.join(" ", gameBoard.getSecretCode().getColors()));
                }
            }
        }
        if (gameBoard.isOutOfGuesses()) {
            System.out.println("Out of guesses! The secret code was: " + String.join(" ", gameBoard.getSecretCode().getColors()));
        }
    }
}
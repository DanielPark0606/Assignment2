package assignment2;
/* EE422C Assignment #2 submission by
 * Replace <...> with your actual data.
 * Daniel Park
 * dp34433
 */
import java.util.*;
import java.util.Scanner;

public class Game {
    public GameBoard gameBoard;
    public static boolean continueGame;

    public Game() {
    }

    public void runGame(boolean mode){
        Scanner scanner = new Scanner(System.in);
        // initial start
        System.out.print("Are you ready to play? (Y/N): ");
        char playAgain = scanner.next().charAt(0);
        System.out.println();
        continueGame = playAgain == 'Y';

        while(continueGame) {
            // get the secret code
            String code = SecretCodeGenerator.getInstance().getNewSecretCode();
            String[] codeColors = new String[code.length()];
            for(int i = 0; i < code.length(); i++ ){
                codeColors[i] = String.valueOf(code.charAt(i));
            }
            // Initialize the game components
            Code secretCode = new Code(codeColors, code);
            if(mode){
                System.out.println(code);
            }
            gameBoard = new GameBoard(secretCode, GameConfiguration.guessNumber); // set the game board
            insertGuesses(scanner);
            playAgain(scanner);
        }
        scanner.close();
    }

    public void insertGuesses(Scanner scanner) {
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

    public void playAgain(Scanner scanner){
        // Ask if the player wants to play again
        System.out.print("Are you ready for another game? (Y/N): ");
        char playAgain = scanner.next().charAt(0);
        if (playAgain == 'Y') {
            continueGame = true;
            // reset the player guesses array
            gameBoard.resetFeedbackHistory();
            //gameBoard.resetPlayerGuesses();
        } else{
            continueGame = false;
        }
    }

}


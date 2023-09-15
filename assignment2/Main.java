package assignment2;

import java.util.Scanner;

public class Main {
    private static boolean testingMode;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        testingMode = true;
        // check for command line arguments
//        if(args.length > 0 && args[0].equals("1")){
//            testingMode = true;
//        }
        System.out.println("Welcome to Mastermind!");
        System.out.println("You have " + GameConfiguration.guessNumber + " guesses to figure out the secret code.");
       while(testingMode) {
           // get the secret code
           String code = SecretCodeGenerator.getInstance().getNewSecretCode();
           String[] codeColors = new String[code.length()];
           for(int i = 0; i < code.length(); i++ ){
               codeColors[i] = String.valueOf(code.charAt(i));
           }
           // Initialize the game components
           Code secretCode = new Code(codeColors);
           GameBoard gameBoard = new GameBoard(secretCode, GameConfiguration.guessNumber); // set the game board

           // Create a Game instance and start the game
           Game game = new Game(gameBoard, scanner);
           game.runGame();

            // Ask if the player wants to play again
           System.out.print("Do you want to play again? (Y/N): ");
           char playAgain = scanner.next().charAt(0);
           if (playAgain == 'Y' || playAgain == 'y') {
               testingMode = true;
               // reset the player guesses array
               gameBoard.resetFeedbackHistory();
               gameBoard.resetPlayerGuesses();
           } else if (playAgain == 'N' || playAgain == 'n') {
               testingMode = false;
               System.out.println("Thanks for playing!");
           }
       }
       // close scanner when all games are done
        scanner.close();
    }
}


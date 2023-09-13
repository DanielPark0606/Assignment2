package assignment2;

import java.util.Scanner;

public class Main {
    private static boolean testingMode = false;
    public static void main(String[] args) {
        String code = SecretCodeGenerator.getInstance().getNewSecretCode();
        String[] codeColors = code.split(" ");
        Scanner scanner = new Scanner(System.in);
        // check for command line arguments
        if(args.length > 0 && args[0].equals("1")){
            testingMode = true;
        }
        // Initialize the game components
        Code secretCode = new Code(codeColors);     // get secret code
        GameBoard gameBoard = new GameBoard(secretCode, GameConfiguration.guessNumber); // set the game board

        // Create a Game instance and start the game
        Game game = new Game(gameBoard, scanner);
        game.runGame();

        scanner.close();
    }
}


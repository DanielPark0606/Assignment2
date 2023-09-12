package assignment2;
/* EE422C Assignment #2 submission by
 * Replace <...> with your actual data. 
 * Daniel Park
 * dp34433
 */
import java.util.*;
import java.util.Scanner;
import java.util.Random;

public class Game{
    private boolean testingMode;
    private Scanner scanner;
    private Random random;
    private String[] secretCode;
    GameConfiguration game = new GameConfiguration();
    public Game(boolean testingMode, Scanner scanner){
        this.testingMode = testingMode;
        this.scanner = scanner;
        this.random = new Random();
    }
    public void runGame(){
        //generate  secret code
        generateSecretCode();
        int guessesLeft = game.guessNumber;
        //game loop single game
        boolean playAgain = true;
        while(playAgain){
            System.out.println("Welcome to the game!");
            // display game status and player guess


            System.out.println("Do you want to play again? (Y/N)");
            String playAgainInput = scanner.nextLine();

            // Check if the user wants to play again
            if (!playAgainInput.equals("yes")) {
                playAgain = false;
            }
        }
    }
    private void generateSecretCode(){
        secretCode = new String[game.pegNumber];
        for(int i = 0; i < game.pegNumber; i++){
            secretCode[i] = getRandomColor();
        }
    }
    private String getRandomColor(){
        return game.colors()
    }
}

    public static void main(String[] args) {
       boolean testingMode = false;
       Scanner scanner = new Scanner(System.in);
        // check for command line arguments
        if(args.length > 0 && args[0].equals("1")){
            testingMode = true;
        }
        // create instance of Game class
        Game game = new Game(testingMode);
        // start game
        game.start();
        // other main logic

        scanner.close();
    }
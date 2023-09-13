package assignment2;

import java.util.ArrayList;
import java.util.List;

public class GameBoard {
    private Code secretCode;
    private List<Code> playerGuesses;
    private List<Feedback> feedbackHistory;
    private int remainingGuesses;

    public GameBoard(Code secretCode, int maxGuesses) {
        this.secretCode = secretCode;
        this.remainingGuesses = maxGuesses;
        playerGuesses = new ArrayList<>();
        feedbackHistory = new ArrayList<>();
    }

    // Implement methods to add guesses, generate feedback, and check for game over
    public boolean isGameOver(){
        return isOutOfGuesses() || isGuessCorrect(playerGuesses.get(playerGuesses.size() - 1));
    }
    public boolean isOutOfGuesses() {
        return remainingGuesses <= 0;
    }
    public Code getSecretCode(){

        return secretCode;
    }
    public boolean isGuessCorrect(Code guess){
        return secretCode.equals(guess);
    }
    public void addGuess(Code guess) {
        playerGuesses.add(guess);
        remainingGuesses--;
    }
    public int getRemainingGuesses(){

        return remainingGuesses;
    }
    public List<Code> getPlayerGuesses() {

        return playerGuesses;
    }

    public List<Feedback> getFeedbackHistory() {

        return feedbackHistory;
    }
    public Feedback generateFeedback(Code guess) {
        // Implement logic to provide feedback (black and white pegs)
        int blackPegs = 0;
        int whitePegs = 0;
        // array keep track pegs
        boolean[] guessMatched = new boolean[GameConfiguration.pegNumber];
        boolean[] secretCodeMatched = new boolean[GameConfiguration.pegNumber];
        // check black pegs (correct color and position)
        for(int i = 0; i < GameConfiguration.pegNumber; i++){
            if(guess.getColors()[i].equals(secretCode.getColors()[i])){
                blackPegs++;
                guessMatched[i] = true;
                secretCodeMatched[i] = true;
            }
        }
        //check white pegs (correct color)
        for(int i = 0; i < GameConfiguration.pegNumber; i++){
            if(!guessMatched[i]) {
                for (int j = 0; j < GameConfiguration.pegNumber; j++) {
                    if (!secretCodeMatched[j] && guess.getColors()[i].equals(secretCode.getColors()[j])) {
                        whitePegs++;
                        secretCodeMatched[i] = true;
                        break;      // avoid counting same colors multiple times
                    }
                }
            }
        }

        // Update feedbackHistory with the new feedback.
        Feedback feedback = new Feedback(blackPegs, whitePegs);
        feedbackHistory.add(feedback);
        return feedback;
    }

}

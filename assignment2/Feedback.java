package assignment2;

public class Feedback {
    public int blackPegs;
    public int whitePegs;

    public Feedback(int blackPegs, int whitePegs) {
        this.blackPegs = blackPegs;
        this.whitePegs = whitePegs;
    }
    public int getBlackPegs(){
        return blackPegs;
    }
    public int getWhitePegs(){
        return whitePegs;
    }
    public String toString() {
        return  blackPegs + "B_" + whitePegs + "W";
    }
}

package assignment2;
import java.util.Arrays;

public class Code {
    public String[] colors;
    public boolean valid_guess = true;
    // have a boolean statement that shows if it is invalid guess or not
    public Code(String[] colors, String code) {
        if(colors.length != GameConfiguration.pegNumber){
            valid_guess = false;
            System.out.println(code + " -> INVALID GUESS");
            System.out.println();
        }
        else {
            for (String color : colors) {
                if (!isValidColor(color)) {
                    valid_guess = false;
                    System.out.println(code + " -> INVALID GUESS");
                    System.out.println();
                    break;
                }
            }
        }
        if(valid_guess){
            this.colors = colors;
        }
    }
    public String getStringColors(){
        return String.join("", colors);
    }
    public String[] getColors(){
        return colors;
    }

    public boolean equals(Object obj){
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()){
            return false;
        }
        Code otherCode = (Code) obj;
        return Arrays.equals(this.getColors(), otherCode.colors);
    }

    public boolean isValidColor(String color){
        for(String validColor : GameConfiguration.colors){
            if(validColor.equals(color)){
                return true;
            }
        }
        return false;
    }
}

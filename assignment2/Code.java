package assignment2;
import java.util.Arrays;

public class Code {
    private String[] colors;

    public Code(String[] colors) {
        if(colors.length != GameConfiguration.pegNumber){
            System.out.println("-> INVALID GUESS");
        }
        for (String color : colors) {
            if (!isValidColor(color)) {
                System.out.println("-> INVALID GUESS");
            }
        }
        this.colors = colors;
    }
    public String[] getColors(){
        return colors;
    }

    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Code otherCode = (Code) obj;
        return Arrays.equals(colors, otherCode.colors);
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

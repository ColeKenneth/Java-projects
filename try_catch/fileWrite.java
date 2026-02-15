package try_catch;
import java.io.FileWriter;
import java.io.IOException;

public class fileWrite {
    public static void main(String[] args) {
        try {
            FileWriter myFile = new FileWriter("mytextfile.txt");
            myFile.write("Hello World!");
            myFile.write("\nI'm writing using Java.");
            myFile.close();
 
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }
}

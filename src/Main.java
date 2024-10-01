import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("cookies.dat");
        Scanner input = new Scanner(file);
        Scanner test = input;
        String firstLine = test.nextLine();
        String[] temp = firstLine.split(" ");
        ArrayList<String> parts = new ArrayList<>();
        for (String s : temp) {
            if (!s.isEmpty()) {
                parts.add(s);
            }
        }
        System.out.println("Rows: " + parts.size());
        for (String s : parts) {
            System.out.println(s + " ");
        }
        while (input.hasNextLine()) {
            String line = input.nextLine();
            System.out.println(line);
        }
    }
}
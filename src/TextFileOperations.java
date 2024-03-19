import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class TextFileOperations {
    private final static String filePath = "textfile/";
    public static void readElectronicsData() {
        try {
            String fileName = "electronicItems.txt";
            File file = new File(filePath + fileName);
            if (!file.exists()) {
                throw new IOException();
            } else {
                Scanner input = new Scanner(file);
                String[] line;
                while (input.hasNext()) {
                    line = input.nextLine().split(";");
                    for (int i = 0; i < line.length; i ++) {
                        line[i] = line[i].strip();
                    }
                    new ElectronicItems(line[0], line[1], Integer.parseInt(line[2]));
                }
            }
        } catch (IOException ex) {
            System.out.println("electronicItems.txt is not found. Please inspect readElectronicsData() method.");
        }
    }
}

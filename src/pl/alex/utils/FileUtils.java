package pl.alex.utils;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileUtils {
    private final Scanner SCANNER = new Scanner(System.in);
    private String ORIGINAL_FILE_PATH;
    private final List<String> words = new ArrayList<>();

    public void start() {
        readFile();
    }

    private void readFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(getFileName()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    String[] s = line.split(" ");
                   populateWordsArray(s);
                }
            }
        } catch (IOException e) {
            printLine("File not found. Try again!");
            start();
        }

    }

    private void populateWordsArray(String[] s) {
        for (String str : s) {
            if (!str.isEmpty()) {
                words.add(str.replaceAll("[\\W]", "").toUpperCase().trim());
            }
        }
    }

    private String getFileName() {
        printLine("Enter the file name: ");
        ORIGINAL_FILE_PATH = SCANNER.nextLine();
        return ORIGINAL_FILE_PATH;
    }

    private void printLine(String lineToPrint) {
        System.out.println("\n_________________________________________________\n"
                + lineToPrint);
    }
}

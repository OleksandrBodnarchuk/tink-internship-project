package pl.alex.utils;


import java.util.Scanner;

public class FileUtils {
    private final Scanner SCANNER = new Scanner(System.in);
    private String ORIGINAL_FILE_PATH;

    public void start() {

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

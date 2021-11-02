package pl.alex.utils;


import pl.alex.exceptions.FileIsEmptyException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

// TODO: Program powinien wyświetlić (każdy punkt w nowej linii):
//  ● ile słów znalazło się w pliku txt, -> OK
//  ● najdłuższe słowo lub słowa, -> OK
//  ● najkrótsze słowo lub słowa, -> OK
//  ● średnia długość wszystkich słów,
//  ● nazwę pliku w którym znajduje się wyliczenie słów z liczbą wystąpień w pliku wejściowym,
//  ● checksum (skrót) - pliku wyjściowego.

public class FileUtils {
    private final Scanner SCANNER = new Scanner(System.in);
    private String ORIGINAL_FILE_PATH;
    private final List<String> words = new ArrayList<>();
    private final Map<String, Integer> wordsMap = new HashMap<>();


    public void start() {
        readFile();
        getTotalWordCount();
        populateWordsMap();
        printLongestAndShortest();
    }

    private void printLongestAndShortest() {
        words.sort(Comparator.comparing(String::length));
        int shortest = words.get(0).length();
        int longest = words.get(words.size()-1).length();
        printLine("Longest words:");
        wordsMap.keySet().stream().filter(w->w.length()==longest).sorted().forEach(w-> System.out.print(w+" "));
        printLine("Shortest words:");
        wordsMap.keySet().stream().filter(w->w.length()==shortest).sorted().forEach(w-> System.out.print(w+" "));
    }

    private void populateWordsMap() {
        if (words.size() > 0) {
            for (String w : words) {
                wordsMap.put(w, Collections.frequency(words, w));
            }
        } else {
            try {
                throw new FileIsEmptyException("File is empty. Please use different path name.");
            } catch (FileIsEmptyException e) {
                printLine(e.getMessage());
                start();
            }
        }
    }
    private void getTotalWordCount() {
        printLine("Total word count is: " + words.size());
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

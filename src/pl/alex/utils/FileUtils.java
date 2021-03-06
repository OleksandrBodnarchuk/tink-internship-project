package pl.alex.utils;


import pl.alex.exceptions.FileIsEmptyException;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

// TODO: Program powinien wyświetlić (każdy punkt w nowej linii):
//  ● ile słów znalazło się w pliku txt, -> OK
//  ● najdłuższe słowo lub słowa, -> OK
//  ● najkrótsze słowo lub słowa, -> OK
//  ● średnia długość wszystkich słów, -> OK
//  ● nazwę pliku w którym znajduje się wyliczenie słów z liczbą wystąpień w pliku wejściowym, -> OK
//  ● checksum (skrót) - pliku wyjściowego. -> OK

public class FileUtils {
    private final Scanner SCANNER = new Scanner(System.in);
    private String ORIGINAL_FILE_PATH;
    private final String NEW_FILE_NAME = "checksum.txt";
    private final List<String> words = new ArrayList<>();
    private final Map<String, Integer> wordsMap = new HashMap<>();


    public void start() {
        readFile();
        getTotalWordCount();
        populateWordsMap();
        printLongestAndShortest();
        averageWordLength();
        saveWordsToFile();
        SCANNER.close();
    }

    private void saveWordsToFile() {
        File oldFile = new File(ORIGINAL_FILE_PATH);
        File newFile = new File(oldFile.getParent() + "\\" + NEW_FILE_NAME);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(newFile))) {
            writeMapIntoTheFile(writer);
            printLine("New file with calculations: " + newFile.getName());
        } catch (IOException e) {
            printLine("Error while writing into the new file.");
            start();
        }
    }

    private void writeMapIntoTheFile(BufferedWriter writer) throws IOException {
        for (Map.Entry<String, Integer> entry : getSortedMap().entrySet()) {
            String k = entry.getKey();
            Integer v = entry.getValue();
            writer.write(String.format("%s%" + (20 - k.length()) + "d", k, v));
            writer.newLine();
        }
    }

    private Map<String, Integer> getSortedMap() {
        return wordsMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    private void averageWordLength() {
        // used https://docs.oracle.com/javase/tutorial/collections/streams/reduction.html
        double average = words.stream()
                .mapToInt(String::length)
                .average().getAsDouble();
        printLine("Average length of the words: " + String.format("%.2f", average));
    }

    private void printLongestAndShortest() {
        words.sort(Comparator.comparing(String::length));
        int shortest = words.get(0).length();
        int longest = words.get(words.size() - 1).length();
        printLine("Longest words:");
        wordsMap.keySet().stream().filter(w -> w.length() == longest).sorted().forEach(w -> System.out.print(w + " "));
        printLine("Shortest words:");
        wordsMap.keySet().stream().filter(w -> w.length() == shortest).sorted().forEach(w -> System.out.print(w + " "));
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

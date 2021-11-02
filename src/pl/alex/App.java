package pl.alex;

import pl.alex.utils.FileUtils;

public class App {

    // TODO: 1. Liczy ile słów znajduje się w pliku txt;
    //          - Get the file (if not available -> throw an exception)
    //          - Read the file (if empty -> throw an exception)
    //  2. Wyświetla najdłuższe i najkrótsze słowa;
    //  3. Liczy średnią długość słów;
    //  4. Tworze nowy plik txt, w którym wylistowane są wszystkie unikalne słowa + liczba wystąpień danego słowa.
    //  - Każda para, słowo + liczba wystąpień, powinna znaleźć się w nowym wierszu.
    //  - Wiersze powinny być posortowane wg liczby wystąpień słowa, od najczęstszych do najrzadszych.

    public static void main(String[] args) {
        FileUtils fileUtils = new FileUtils();
        fileUtils.start();
    }
}

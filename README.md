To start: 
1. javac -sourcepath ./src -d bin -encoding ISO-8859-1 src\pl\alex\App.java
2. java -classpath .\bin pl.alex.App

Program:

  - Liczy ile słów znajduje się w pliku txt;
  - Wyświetla najdłuższe i najkrótsze słowa;
  - Liczy średnią długość słów;
  - Tworze nowy plik txt, w którym wylistowane są wszystkie unikalne słowa + liczba wystąpień danego słowa, gdzie:
      - Każda para, słowo + liczba wystąpień, powinna znaleźć się w nowym wierszu.
       - Wiersze powinny być posortowane wg liczby wystąpień słowa, od najczęstszych do najrzadszych.

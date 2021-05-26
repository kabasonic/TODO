package com.kabasonic.todo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

}

/*
* Napisz prostą aplikację TODO APP.
1. Aplikacja będzie umożliwiała dodawanie zadań do listy Twoich zadań do wykonania.
2. Po wejściu do aplikacji widzimy listę zaplanowanych zadań oraz przycisk dodawania nowego
zadania.
3. W przypadku gdy nie dodaliśmy jeszcze żadnego zadania, powinien zostać wyświetlony
stosowny komunikat.
4. Po kliknięciu przycisku dodawania zadań aplikacja przenosi nas na nowe okno, gdzie
uzupełniamy dane: nazwę, datę wykonania oraz kategorię zadania (jedną z: praca, zakupy, inne).
Każda dana powinna być wprowadzana/wybierana w oddzielnej kontrolce.
5. Na ekranie dodatkowo obsługujemy dwa przyciski: Dodaj zadanie oraz Anuluj. Po ich kliknięciu
aplikacja cofa nas do okna z listą zadań.
6. Po poprawnym dodaniu zadania do listy wyświetlany jest komunikat o powodzeniu operacji (w
formie Toasta lub Snackbara).
7. W przypadku niepowodzenia zapisu wyświetlany jest Dialog z opcją ponowienia operacji.
8. Jeśli operacja zakończyła się powodzeniem, lista powinna zostać odświeżona automatycznie.
9. Zapisane zadania muszą być zapisywane w pamięci urządzenia.
10. Aplikacja musi działać poprawnie zarówno w orientacji pionowej jak i poziomej.
11. Zastosowanie wzorca MVP lub MVVM będzie dodatkowym atutem.
12. Zastosowanie Dependency Injection (Dagger2, Kodein lub Koin) będzie dodatkowym atutem.
13. Zadanie możesz zrealizować w języku Kotlin lub Java.
14. Jak rozwiązanie należy przesłać skompilowaną aplikację (plik .apk) oraz kod źródłowy
projektu.
* */
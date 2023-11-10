package org.example.app;

import java.util.Scanner;

import static javafx.application.Application.launch;

class Main {
    public static void main(String[] args) {
        int decision = Main.chooseGame();
        if (decision == 1) {
            launch(Checkers.class);
        }
        if (decision == 2) {
            launch(RegularChess.class);
        }
        main(args);
    }

    public static int chooseGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese: " + "\n" +
                "| 1) Para jugar Damas" + "\n" +
                "| 2) Para jugar Ajedrez Regular");
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            System.out.println("--------------------------");
            System.out.println("Ingrese un número porfavor");
            System.out.println("--------------------------");
            return Main.chooseGame();
        }
    }
}

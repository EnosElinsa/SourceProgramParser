package ui;

import java.io.File;
import java.util.Scanner;

import processor.MainProsessor;

public class CommandLineUI {
    public void run() {
        try (Scanner in = new Scanner(System.in)) {
            while (true) {
                System.out.println("------------------------MENU------------------------\n1. Analyze the Source Program File in the Directory\n2. View the Analysis Results\n0. Exit\n----------------------------------------------------");
                System.out.print(">Option: ");
                int option = in.nextInt();
                switch(option) {
                    case 0: return;
                    case 1:
                        System.out.print(">Input a directory: ");
                        String directory = in.next();
                        File file = new File(directory);
                        if (file.exists() == false || file.isDirectory() == false) {
                            System.out.println(">Error: " + "[" + directory + "]" + "is not a directory or does not exit");
                        }
                        else {
                            new MainProsessor(file).start();
                        }
                        break;
                    case 2:
                        // view the results
                        break;
                    default: System.out.println(">Invalid option!");
                }
            }
        }
    }
}

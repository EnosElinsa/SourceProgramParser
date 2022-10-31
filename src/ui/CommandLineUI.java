package ui;

import java.io.File;
import java.util.Scanner;

import processor.MainProsessor;
import processor.ResultHandler;

public class CommandLineUI {
    public void run() {
        try (Scanner in = new Scanner(System.in)) {
            while (true) {
                System.out.println("\n------------------------MENU------------------------\n1. Analyze the Source Program File in the Directory\n2. View the Analysis Results\n0. Exit\n----------------------------------------------------");
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
                            MainProsessor mainProsessor = new MainProsessor(file);
                            mainProsessor.start();
                            mainProsessor.showResults();
                        }
                        break;
                    case 2:
                        String resultInfo = new ResultHandler().getResultInfo();
                        if (resultInfo == null) {
                            System.out.println(">No result found!");
                        }
                        else {
                            System.out.println("\nCurrent result files in the directory:");
                            System.out.println("-".repeat(20) + "\n");
                            System.out.println(resultInfo);
                            System.out.println("-".repeat(20) + "\n");
                            System.out.print(">Choose a result to view(Enter 0 to exit): ");
                            int index = in.nextInt();
                            if (index == 0) {
                                break;
                            }
                            String result = new ResultHandler().getResult(index - 1);
                            System.out.println(result);
                        }
                        break;
                    default: System.out.println(">Invalid option!");
                }
            }
        }
    }
}

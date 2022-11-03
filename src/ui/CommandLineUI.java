package ui;

import java.io.File;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import processor.MainProsessor;
import processor.ResultHandler;

/**
 * <p>This is a command line user interface.
 * <p>When the option is analyzing file, the {@link MainProsessor#start() start}
 *  method of the {@code MainProsessor}
 * class is invoked for analysis. 
 * <p>When the option is viewing the result files, call the 
 * {@link ResultHandler#getResultInfo() getResultInfo} and 
 * {@link ResultHandler#getResult() getResult}
 * methods of the object of the {@code ResultHandler} class to view the processing results
 * <p> Option 0 to exit the program
 * <p> The {@code isValidInput} method is called to refrain from invaid but harmful input of the program
 * @author  Enos
 * @see MainProsser
 * @see ResultHandler
 */
public class CommandLineUI {

    private MainProsessor mainProsessor;
    private ResultHandler resultHandler = new ResultHandler();
    public void run() {
        try (Scanner in = new Scanner(System.in)) {
            while (true) {
                System.out.println("\n------------------------MENU------------------------\n1. Analyze the Source Program File in the Directory\n2. View the Analysis Results\n0. Exit\n----------------------------------------------------");
                System.out.print(">Option: ");
                String strOption = in.next();
                if (isValidInput(strOption, true) == false) {
                    System.out.println(">Error: Invalid option!");
                    continue;
                }
                int option = Integer.parseInt(strOption);

                switch(option) {
                    case 0: return;
                    case 1:
                        System.out.print(">Input a directory: ");
                        String directory = in.next();

                        File file = new File(directory);
                        if (file.exists() == false || file.isDirectory() == false) {
                            System.out.println(">Error: " + "[" + directory + "]" + "is not a directory or does not exit!");
                        }
                        else {
                            mainProsessor = new MainProsessor(file);
                            mainProsessor.start();
                            mainProsessor.showResults();
                        }
                        break;
                    case 2:
                        String resultInfo = resultHandler.getResultInfo();
                        if (resultInfo == null) {
                            System.out.println(">Error: No result found!");
                        }
                        else {
                            System.out.println("\nCurrent result files in the directory:");
                            System.out.println("-".repeat(20) + "\n");
                            System.out.println(resultInfo);
                            System.out.println("-".repeat(20) + "\n");

                            System.out.print(">Choose a result to view(Enter 0 to exit): ");
                            String strIndex = in.next();
                            while (isValidInput(strIndex, true) == false) {
                                System.out.println(">Error: Invalid index!");
                                System.out.print(">Choose a result to view(Enter 0 to exit): ");
                                strIndex = in.next();
                            }
                            int index = Integer.parseInt(strIndex);
                            if (index == 0) {
                                break;
                            }

                            String result = resultHandler.getResult(index - 1);
                            System.out.println(result);
                        }
                        break;
                    default: System.out.println(">Error: Invalid option!");
                }
            }
        }
    }

    /**
     * 
     * @param input an input int {@code String} type
     * @param isNumber to indicate that the input is whether a number or not
     * @return whether the input is valid
     */
    private boolean isValidInput(String input, boolean isNumber) {
        // In this case, the input is either a number or a directory
        Pattern pattern;
        if (isNumber) {
            // The regex for matching a number
            pattern = Pattern.compile("[\\d]");
        } else {
            // The regex for matching a directory
            // (This regular expression supports both absolute and relative paths in Windows and Linux)
            pattern = Pattern.compile("([\\s\\w+:]*?/?(/.+/)?)((\\w+)\\.(\\w+))$");
        }
        Matcher mather = pattern.matcher(input);
        return mather.find();
    }
}

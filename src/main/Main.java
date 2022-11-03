package main;

import ui.CommandLineUI;

/**
 * <p><strong>The Entrance of the program.</p></strong>
 * <p>PS: To refrain from inconsistent encoding pattern, all the commends are written in English.
 * <p>To see more info and to issue, click the URL below.
 * <p><a href="https://github.com/EnosElinsa/SourceProgramParser">
 * The Github Repositories</a>
 * @author  Enos
 * <p> 21SE R5 202125220501
 * @see     CommandLineUI
 */
public class Main {
    /**
     * The reference of the CLUI.
     */
    private static CommandLineUI commandLineUI;
    /**
     * Main method.
     * @param args arguments
     */
    public static void main(String[] args) {
        commandLineUI = new CommandLineUI();
        commandLineUI.run();
    }
}

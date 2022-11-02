package main;

import ui.CommandLineUI;

public class Main {
    private static CommandLineUI commandLineUI;
    public static void main(String[] args) {
        commandLineUI = new CommandLineUI();
        commandLineUI.run();
    }
}

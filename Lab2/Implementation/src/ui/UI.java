package ui;

import model.ProgramInternalForm;
import model.Scanner;
import model.SymbolTable;
import model.FiniteAutomata;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UI {
    Map<String, Runnable> commands = new HashMap<>();
    SymbolTable ST;
    ProgramInternalForm PIF;
    Scanner scanner;
    FiniteAutomata FA;
    boolean isRunning = true;
    java.util.Scanner keyboard = new java.util.Scanner(System.in);

    public UI(SymbolTable ST, ProgramInternalForm PIF, Scanner scanner, FiniteAutomata fa) {
        this.ST = ST;
        this.PIF = PIF;
        this.scanner = scanner;
        this.FA = fa;
        populateCommandsMap();
    }

    public void run() {
        printMenu();

        while(isRunning) {
            System.out.println(">> Command: ");
            commands.get(keyboard.next()).run();
        }
    }

    private void printMenu() {
        System.out.println(
                """
                0. Exit
                ---- Scanner ----
                1. Scan a given file
                ---- FA ----
                9. Read a file containing FA
                2. Display the set of states
                3. Display the alphabet
                4. Display all the transitions
                5. Display the initial state
                6. Display the set of final states
                7. Display 2-7 together
                8. Check if sequence is accepted
                """);

    }

    private void populateCommandsMap() {
        commands.put("0", this::stopProgram);
        commands.put("1", this::scanFile);
        commands.put("9", this::readFaFile);
        commands.put("2", () -> System.out.println(FA.getStates()));
        commands.put("3", () -> System.out.println(FA.getAlphabet()));
        commands.put("4", () -> System.out.println(FA.getTransitions()));
        commands.put("5", () -> System.out.println(FA.getInitialState()));
        commands.put("6", () -> System.out.println(FA.getFinalStates()));
        commands.put("7", () -> System.out.println(FA.toString()));
        commands.put("8", this::checkIfSequenceIsAccepted);
    }

    private void scanFile() {
        System.out.println("Enter filename with program to scan (without '/files'): ");
        String fileName = keyboard.next();
        try{
            scanner.scan(fileName);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private void readFaFile() {
        System.out.println("Enter name of file that contains FA (without '/files'): ");
        String fileName = keyboard.next();
        try {
            FA.readFaElementsFromFile(fileName);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private void checkIfSequenceIsAccepted() {
        System.out.println("Enter sequence: ");
        String sequence = keyboard.next();
        System.out.println(FA.isAccepted(sequence));
    }

    private void stopProgram() {
        this.isRunning = false;
    }
}
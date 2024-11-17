import model.FiniteAutomata;
import model.ProgramInternalForm;
import model.Scanner;
import model.SymbolTable;
import ui.UI;

public class Main {
    public static void main(String[] args) {
        SymbolTable ST = new SymbolTable();
        ProgramInternalForm PIF = new ProgramInternalForm();
        Scanner scanner = new Scanner(ST, PIF);
        FiniteAutomata FA = new FiniteAutomata();
        UI ui = new UI(ST, PIF, scanner, FA);
        ui.run();
    }
}

package model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProgramInternalForm {
    private final List<Pair> items = new ArrayList<>();
    private final String localPath = "src\\files\\";
    private final String outputFile = "PIF.out";

    public void add(String token, Integer position) {
        items.add(new Pair(token, position));
    }

    @Override
    public String toString() {
        //linie cu linie
        return "ProgramInternalForm{" +
                "items=" + items +
                '}';
    }

    public void writeToFile() {
        try {
            FileWriter myWriter = new FileWriter(localPath + outputFile);
            myWriter.write(prettyPrint());
            myWriter.close();
        } catch (IOException e) {
            System.err.println("Can't write to " + outputFile);
            e.printStackTrace();
        }
    }

    public void deleteContentOfFile() {
        try {
            FileWriter myWriter = new FileWriter(localPath + outputFile);
            myWriter.write("");
            myWriter.close();
        } catch (IOException e) {
            System.err.println("Can't write to " + outputFile);
            e.printStackTrace();
        }
    }

    public String prettyPrint() {
        StringBuilder text = new StringBuilder();
//        text.append("\n");
//        text.append(String.format("%10s %10s", "TOKEN", "ST_POS"));
//        text.append("\n");
//        text.append("--------------------------");
//        text.append("\n");

        for (Pair item : items) {
//            text.append(String.format("%10s %10s", item.getFirst(), item.getSecond()));
            text.append(item.getFirst()).append(" -> ").append(item.getSecond());
            text.append("\n");
        }

//        text.append("--------------------------");
        return text.toString();
    }
}
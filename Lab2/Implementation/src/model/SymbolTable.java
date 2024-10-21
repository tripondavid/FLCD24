package model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

public class SymbolTable {
    private final HashTable ST = new HashTable();
    private int ST_POS = 0;
    private final String localPath = "src\\files\\";
    private final String outputFile = "ST.out";

    public Integer add(Object key) {
        int pos = ST.put(key, ST_POS);
        if (pos == ST_POS)
            ST_POS++;
        return pos;
    }

    public Integer get(Object key) {
        return ST.get(key);
    }

    public void writeToFile() {
        try {
            FileWriter myWriter = new FileWriter(localPath + outputFile);
            myWriter.write(formattedPrint());
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

    public String formattedPrint() {
        SortedMap<Integer, String> elements = ST.getSortedNodes();

        StringBuilder text = new StringBuilder("Hashtable with identifiers and constants \n\n");
        text.append("--------------------------");
        text.append("\n");
        text.append(String.format("%10s %10s", "ST_POS", "SYMBOL"));
        text.append("\n");
        text.append("--------------------------");
        text.append("\n");

        Set<Map.Entry<Integer, String>> s = elements.entrySet();

        for (Map.Entry<Integer, String> entry : s) {
            text.append(String.format("%10s %10s", entry.getKey(), entry.getValue()));
            text.append("\n");
        }

        text.append("--------------------------");
        return text.toString();
    }
    @Override
    public String toString() {
        return "SymbolTable{" +
                "ST_POS=" + ST_POS +
                ", ST=" + ST +
                '}';
    }
}

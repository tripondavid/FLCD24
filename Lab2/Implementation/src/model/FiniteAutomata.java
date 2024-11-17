package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FiniteAutomata {
    private List<String> Q = new ArrayList<>();
    private List<String> E = new ArrayList<>();
    private String q0;
    private List<String> F = new ArrayList<>(); // final states
    private final Map<String, Map<String, List<String>>> S = new HashMap<>(); // transitions

    private final String localPath = "src\\files\\";

    public void readFaElementsFromFile(String fileName) throws IOException {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(localPath + fileName));
        } catch (FileNotFoundException exception) {
            System.err.println("File " + fileName + " not found!");
            return;
        }

        String line;

        Q = getLine(reader);
        E = getLine(reader);
        q0 = getLine(reader).get(0);
        F = getLine(reader);

        reader.readLine(); // S =

        // get all transitions
        String src, input, dest;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.trim().split("->");
            String left = parts[0].trim();
            left = left.substring(1, left.length() - 1);

            src = left.split(",")[0];
            input = left.split(",")[1];
            dest = parts[1].trim();

            // check if src exists...if not, add source
            if (!S.containsKey(src))
                S.put(src, new HashMap<>());
            // check if input exists...if not, add it
            if (!S.get(src).containsKey(input))
                S.get(src).put(input, new ArrayList<>());
            // check if dest exists...if not, add it
            if (!S.get(src).get(input).contains(dest))
                S.get(src).get(input).add(dest);
        }
    }

    private List<String> getLine(BufferedReader reader) throws IOException {
        String[] line = reader.readLine().trim().split("=");
        return Arrays.stream(line[1].trim().split(" ")).toList();
    }

    /**
     * In DFA, there is only one path for specific input from the current state to the next state.
     *
     * @return true if is DFA, false otherwise
     */
    public boolean isDFA() {
        for (String src : S.keySet()) {
            Map<String, List<String>> inputs = S.get(src);
            for (String input : inputs.keySet())
                if (inputs.get(input).size() > 1) return false;
        }
        return true;
    }

    /**
     * Go through each symbol from the sequence and check that it can be reached by the given FA's transitions
     *
     * @return the final state if the sequence is accepted, else null.
     */
    public boolean isAccepted(String sequence) {
        if (!this.isDFA()) return false;

        String currentState = this.q0;
        for (int i = 0; i < sequence.length(); i++) {
            String input = sequence.charAt(i) + "";
            if (S.containsKey(currentState) && S.get(currentState).containsKey(input))
                currentState = S.get(currentState).get(input).get(0); // get first transition
            else
                return false;
        }
        return F.contains(currentState);
    }

    public boolean isAccepted(String fileName, String sequence) {
        try {
            this.readFaElementsFromFile(fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return isAccepted(sequence);
    }

    public List<String> getStates() {
        return Q;
    }

    public List<String> getAlphabet() {
        return E;
    }

    public String getInitialState() {
        return q0;
    }

    public List<String> getFinalStates() {
        return F;
    }

    public Map<String, Map<String, List<String>>> getTransitions() {
        return S;
    }

    @Override
    public String toString() {
        return "--- FiniteAutomata --- \n" +
                "\tSet of states: " + Q + "\n" +
                "\tAlphabet: " + E + "\n" +
                "\tInitial state: " + q0 + '\n' +
                "\tFinal states: " + F + "\n" +
                "\tTransitions: " + S;
    }
}
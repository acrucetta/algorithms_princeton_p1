package homeworks.hw6;

import edu.princeton.cs.algs4.Digraph;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;

public class WordNet {

    private Map<Integer,String> synsets;
    private TreeSet<String> nouns;
    private Digraph digraph;
    private Map<Integer, List<Integer>> hypernyms;

    // constructor takes the name of the two input files (linearithmic or better)
    public WordNet(String synsets, String hypernyms) {
        // We will first load the synsets file and build a map
        // from synset id to synset.
        // Then we will load the hypernyms file and build a digraph.
        try {
            Scanner scanner = new Scanner(new FileReader(synsets));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split(",");
                int id = Integer.parseInt(fields[0]);
                String synset = fields[1];
                this.synsets.put(id, synset);
                this.nouns.add(synset);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Now we will load the hypernyms file and build a digraph.
        // Each vertex is the id of a synset; each directed edge
        // represents that w is a hypernym of v.
        try {
            Scanner scanner = new Scanner(new FileReader(hypernyms));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split(",");
                int id = Integer.parseInt(fields[0]);
                for (int i = 1; i < fields.length; i++) {
                    int hypernymId = Integer.parseInt(fields[i]);
                    if (this.digraph != null) {
                        this.digraph.addEdge(id, hypernymId);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return this.nouns;
    }

    // is the word a WordNet noun? (logarithmic or better)
    // logarithmic lookups are possible in a BST or a red-black BST
    public boolean isNoun(String word) {
        return this.nouns.contains(word);
    }

    // distance between nounA and nounB (defined below) (linear or better)
    public int distance(String nounA, String nounB) {
            return -1;
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below) (linear or better)
    // we want to do a BFS from nounA and nounB
    public String sap(String nounA, String nounB) {
        return null;
    }

    // do unit testing of this class
    public static void main(String[] args) {

            return;
    }
}
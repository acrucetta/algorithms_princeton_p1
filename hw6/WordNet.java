package homeworks.hw6;

import com.sun.source.tree.Tree;
import edu.princeton.cs.algs4.Digraph;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.*;

public class WordNet {

    private final Map<Integer,String> synsets = new TreeMap<>();
    private final Map<String, Integer> WordToIds = new TreeMap<>();
    private final TreeSet<String> nouns = new TreeSet<>();
    private Map<Integer, List<Integer>> hypernyms;
    private final SAP sap;

    // constructor takes the name of the two input files (linearithmic or better)
    public WordNet(String synsets, String hypernyms) {
        try {
            Scanner scanner = new Scanner(new FileReader(synsets));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split(",");
                int id = Integer.parseInt(fields[0]);
                String synset = fields[1];

                // We will build a set of all nouns
                this.synsets.put(id, synset);
                this.nouns.add(synset);

                // We will also build a map from each word to the set of synset ids
                this.WordToIds.putIfAbsent(synset, id);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Digraph digraph = new Digraph(this.synsets.size());
        try {
            Scanner scanner = new Scanner(new FileReader(hypernyms));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split(",");
                int id = Integer.parseInt(fields[0]);
                for (int i = 1; i < fields.length; i++) {
                    int hypernymId = Integer.parseInt(fields[i]);
                    digraph.addEdge(id, hypernymId);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Build the SAP object to compute shortest ancestral paths
        sap = new SAP(digraph);
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
        // We will first find the synset ids of nounA and nounB.
        // Then we will find the shortest ancestral path between
        int idNounA = this.WordToIds.getOrDefault(nounA, -1);
        int idNounB = this.WordToIds.getOrDefault(nounB, -1);
        if (idNounA == -1 || idNounB == -1) {
            throw new IllegalArgumentException("Noun not found");
        }
        return sap.length(idNounA, idNounB);
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below) (linear or better)
    // we want to do a BFS from nounA and nounB
    public String sap(String nounA, String nounB) {
        int idNounA = this.WordToIds.get(nounA);
        int idNounB = this.WordToIds.get(nounB);
        int ancestorId = sap.ancestor(idNounA, idNounB);
        return this.synsets.get(ancestorId);
    }

    // do unit testing of this class
    public static void main(String[] args) {
    }
}
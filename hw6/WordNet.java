package homeworks.hw6;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.Map;
import java.util.Scanner;

public class WordNet {

    private Map<Integer,String> synsets;

    // constructor takes the name of the two input files (linearithmic or better)
    public WordNet(String synsets, String hypernyms) {
        // We want to build a wordnet digraph. Each vertex v
        // is an integer that represents a synstet.
        // Each directed edge v->w represents that w is a hypernym of v.

        // We will first load the synsets file and build a map
        // from synset id to synset.
        // Then we will load the hypernyms file and build a digraph.
        try {
            Scanner scanner = new Scanner(new FileReader(synsets));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {

        return null;
    }

    // is the word a WordNet noun? (logarithmic or better)
    public boolean isNoun(String word) {

        return false;
    }

    // distance between nounA and nounB (defined below) (linear or better)
    public int distance(String nounA, String nounB) {

            return -1;
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below) (linear or better)
    public String sap(String nounA, String nounB) {

        return null;
    }

    // do unit testing of this class
    public static void main(String[] args) {

            return;
    }
}
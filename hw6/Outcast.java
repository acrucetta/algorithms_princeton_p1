package homeworks.hw6;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Map;
import java.util.TreeMap;

public class Outcast {

    WordNet wordnet;

    public Outcast(WordNet wordnet) {
        this.wordnet = wordnet;
    }

    // Given an array of WordNet nouns, return an outcast
    public String outcast(String[] nouns) {
        Map<String, Integer> nounDistances = new TreeMap<>();
        for (String s : nouns) {
            nounDistances.putIfAbsent(s, 0);
            for (String noun : nouns) {
                nounDistances.put(s, nounDistances.get(s) + wordnet.distance(s, noun));
            }
        }
        int maxDistance = -1;
        String outcast = null;
for (Map.Entry<String, Integer> entry : nounDistances.entrySet()) {
            if (entry.getValue() > maxDistance) {
                maxDistance = entry.getValue();
                outcast = entry.getKey();
            }
        }
        return outcast;
    }

    public static void main(String[] args) {
        WordNet wordnet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < args.length; t++) {
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }
}

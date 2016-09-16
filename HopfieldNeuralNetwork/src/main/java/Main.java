import javafx.util.Pair;
import structures.Node;

import java.util.HashMap;
import java.util.Hashtable;

/**
 * Created by ENG.AHMED HANI on 9/16/2016.
 */
public class Main {
    public static void main(String[] args) {
        int[] pattern = {0, 1, 1, 0, 1};

        Hopfield hopfield = new Hopfield(pattern);
        hopfield.makeNetwork();
        hopfield.train();

        Hashtable<Pair<Node, Node>, Integer> weights = hopfield.getWeights();

        for (Pair<Node, Node> edge : weights.keySet()) {
            System.out.println(edge.getKey().getId() + "->" + edge.getValue().getId() + " Value: " + weights.get(edge));
        }
    }
}

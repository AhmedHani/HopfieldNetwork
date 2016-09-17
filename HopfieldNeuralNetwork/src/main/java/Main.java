import javafx.util.Pair;
import structures.Node;

import java.util.HashMap;
import java.util.Hashtable;

/**
 * Created by ENG.AHMED HANI on 9/16/2016.
 */
public class Main {
    public static void main(String[] args) {
        int[] pattern = {1, 1, 1, 0, 1};

        Hopfield hopfield = new Hopfield(pattern);
        hopfield.makeNetwork();
        hopfield.train();
        hopfield.updateNodes();

        Hashtable<Pair<Integer, Integer>, Integer> weights = hopfield.getWeights();

        for (Pair<Integer, Integer> edge : weights.keySet()) {
            System.out.println(edge.getKey() + "->" + edge.getValue() + " Value: " + weights.get(edge));
        }

       /* int[] newPattern = {1, 0, 1, 0, 1};
        Node[] patternNodes = hopfield.getPatternNodes();
        hopfield.train(newPattern);
        hopfield.updateNodes();

        weights = hopfield.getWeights();

        System.out.println("----------");

        for (Pair<Integer, Integer> edge : weights.keySet()) {
            System.out.println(edge.getKey() + "->" + edge.getValue() + " Value: " + weights.get(edge));
        }*/

        Visualize visualize = new Visualize(hopfield.getNodesOutput(), weights, "TEST");
        //visualize.setGraphName("VER1");
        visualize.build();
        visualize.draw();
    }
}

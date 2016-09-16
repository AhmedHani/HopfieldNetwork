import javafx.util.Pair;
import structures.Node;

import java.util.*;

/**
 * Created by Ahmed Hani Ibrahim on 9/16/2016.
 */
public class Hopfield {
    private int numberOfNodes;
    private int[] input;
    private int[] normalizedInput;
    private Node[] inputNodes;
    private HashMap<Pair<Node, Node>, Integer> weights;

    public Hopfield(int numberOfNodes, int[] input) {
        assert numberOfNodes == this.input.length;

        this.numberOfNodes = numberOfNodes;
        this.input = input;

        this.normalizedInput = new int[numberOfNodes];
        this.inputNodes = new Node[numberOfNodes];
        this.weights = new HashMap<Pair<Node, Node>, Integer>();

        this.normalize(this.input);
        this.makeNodes(this.normalizedInput);
    }

    public void makeNetwork() {
        Random random = new Random();

        for (int i = 0; i < this.inputNodes.length; i++) {
            for (int j = 0; j < this.inputNodes.length; j++) {
                if (i == j) {
                    this.weights.put(new Pair<Node, Node>(this.inputNodes[i], this.inputNodes[j]), 0);
                } else {
                    int randValue = random.nextInt(10 + 1);
                    this.weights.put(new Pair<Node, Node>(this.inputNodes[i], this.inputNodes[j]), randValue);
                }
            }
        }
    }

    public void addPattern(int[] patternData) {
        // TODO

    }

    private void makeNodes(int[] data) {
        for (int i = 0; i < data.length; i++) {
            Node node = new Node(i + 1, data[i]);
            this.inputNodes[i] = node;
        }
    }

    private void normalize(int[] data) {
        Arrays.sort(data);
        int maxValue = data[data.length - 1];

        for (int i = 0; i < data.length; i++) {
            this.normalizedInput[i] = (int)data[i] / (int)maxValue;
        }
    }
}

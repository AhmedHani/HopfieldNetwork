import javafx.util.Pair;
import structures.Node;

import java.util.*;

/**
 * Created by Ahmed Hani Ibrahim on 9/16/2016.
 */
public class Hopfield {
    private int numberOfNodes;
    private int[] pattern;
    private int[] normalizedPattern;
    private Node[] patternNodes;
    private HashMap<Pair<Node, Node>, Integer> weights;

    public Hopfield(int[] pattern) {
        this.numberOfNodes = pattern.length;
        this.pattern = pattern;

        this.normalizedPattern = new int[numberOfNodes];
        this.patternNodes = new Node[numberOfNodes];
        this.weights = new HashMap<Pair<Node, Node>, Integer>();

        this.normalize(this.pattern);
        this.makeNodes(this.normalizedPattern);
    }

    public void makeNetwork() {
        Random random = new Random();

        for (int i = 0; i < this.patternNodes.length; i++) {
            for (int j = 0; j < this.patternNodes.length; j++) {
                if (i == j) {
                    this.weights.put(new Pair<Node, Node>(this.patternNodes[i], this.patternNodes[j]), 0);
                } else {
                    int randValue = random.nextInt(10 + 1);
                    this.weights.put(new Pair<Node, Node>(this.patternNodes[i], this.patternNodes[j]), randValue);
                }
            }
        }
    }

    public void train() {
        // TODO
    }

    public void train(int[] patternData) {
        // TODO
    }

    public void addPattern(int[] patternData) {
        // TODO
    }

    private void makeNodes(int[] data) {
        for (int i = 0; i < data.length; i++) {
            Node node = new Node(i + 1, data[i]);
            this.patternNodes[i] = node;
        }
    }

    private void normalize(int[] data) {
        Arrays.sort(data);
        int maxValue = data[data.length - 1];

        for (int i = 0; i < data.length; i++) {
            this.normalizedPattern[i] = (int)data[i] / (int)maxValue;
        }
    }
}

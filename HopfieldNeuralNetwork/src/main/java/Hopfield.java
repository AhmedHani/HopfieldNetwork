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
    private Hashtable<Pair<Integer, Integer>, Integer> weights;
    private Vector<int[]> recognizedPatterns;
    private Node[] nodesOutput;

    public Hopfield(int[] pattern) {
        this.numberOfNodes = pattern.length;
        this.pattern = pattern;

        this.normalizedPattern = new int[numberOfNodes];
        this.patternNodes = new Node[numberOfNodes];
        this.nodesOutput = new Node[numberOfNodes];
        this.weights = new Hashtable<Pair<Integer, Integer>, Integer>();

        this.normalize(this.pattern);
        this.makeNodes(this.normalizedPattern);

        this.recognizedPatterns = new Vector<int[]>();
        this.recognizedPatterns.add(pattern);
    }

    public void makeNetwork() {
        Random random = new Random();
        for (int i = 0; i < this.patternNodes.length; i++) {
            for (int j = 0; j < this.patternNodes.length; j++) {
                if (i == j)
                    continue;
                this.weights.put(new Pair<Integer, Integer>(this.patternNodes[i].getId(), this.patternNodes[j].getId()), 1);
            }
        }
    }

    public void train() {
        for (int i = 0; i < this.patternNodes.length; i++) {
            for (int j = 0; j < this.patternNodes.length; j++) {
                if (i == j)
                    continue;

                Node from = this.patternNodes[i];
                Node to = this.patternNodes[j];
                int newWeight = ((2 * from.getValue()) - 1) * ((2 * to.getValue()) - 1);
                this.weights.put(new Pair<Integer, Integer>(this.patternNodes[i].getId(), this.patternNodes[j].getId()), newWeight);
            }
        }
    }

    public void train(int[] patternData) {
        this.pattern = patternData;
        this.normalize(patternData);
        this.makeNodes(this.normalizedPattern);

        for (int i = 0; i < this.patternNodes.length; i++) {
            for (int j = 0; j < this.patternNodes.length; j++) {
                if (i == j)
                    continue;

                int newWeight = ((2 * this.patternNodes[i].getValue()) - 1) * ((2 * this.patternNodes[j].getValue()) - 1);
                int prevWeight = this.weights.get(new Pair<Integer, Integer>(this.patternNodes[i].getId(), this.patternNodes[j].getId()));

                this.weights.put(new Pair<Integer, Integer>(this.patternNodes[i].getId(), this.patternNodes[j].getId()), newWeight + prevWeight);
            }
        }

        this.recognizedPatterns.add(patternData);
    }

    public void updateNodes() {
        for (int i = 0; i < this.patternNodes.length; i++) {
            Node currentNode = this.patternNodes[i];
            int nodeValue = 0;

            for (Pair<Integer, Integer> edge : this.weights.keySet()) {
                if (edge.getKey() == currentNode.getId()) {
                    int targetNodeValue = 0;

                    for (int j = 0; j < this.patternNodes.length; j++) {
                        if (this.patternNodes[j].getId() == edge.getValue())
                            targetNodeValue = this.patternNodes[j].getValue();
                    }

                    nodeValue += this.weights.get(edge) * targetNodeValue;
                }
            }

            this.nodesOutput[i] = nodeValue >= 0 ? new Node(this.patternNodes[i].getId(), 1) : new Node(this.patternNodes[i].getId(), 0);
        }
    }

    private void makeNodes(int[] data) {
        for (int i = 0; i < data.length; i++) {
            Node node = new Node(i + 1, data[i]);
            this.patternNodes[i] = node;
        }
    }

    private void normalize(int[] data) {
        int maxValue = -1;

        for (int i = 0; i < data.length; i++) {
            if (maxValue < data[i])
                maxValue = data[i];
        }

        for (int i = 0; i < data.length; i++) {
            this.normalizedPattern[i] = (int)data[i] / (int)maxValue;
        }
    }

    public int getNumberOfNodes() {
        return numberOfNodes;
    }

    public void setNumberOfNodes(int numberOfNodes) {
        this.numberOfNodes = numberOfNodes;
    }

    public Hashtable<Pair<Integer, Integer>, Integer> getWeights() {
        return weights;
    }

    public void setWeights(Hashtable<Pair<Integer, Integer>, Integer> weights) {
        this.weights = weights;
    }

    public int[] getNormalizedPattern() {
        return normalizedPattern;
    }

    public void setNormalizedPattern(int[] normalizedPattern) {
        this.normalizedPattern = normalizedPattern;
    }

    public Node[] getPatternNodes() {
        return patternNodes;
    }

    public void setPatternNodes(Node[] patternNodes) {
        this.patternNodes = patternNodes;
    }

    public int[] getPattern() {
        return pattern;
    }

    public void setPattern(int[] pattern) {
        this.pattern = pattern;
    }

    public Vector<int[]> getRecognizedPatterns() {
        return recognizedPatterns;
    }

    public Node[] getNodesOutput() {
        return nodesOutput;
    }
}

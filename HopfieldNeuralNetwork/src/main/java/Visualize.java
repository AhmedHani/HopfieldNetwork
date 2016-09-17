import javafx.util.Pair;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import scala.Int;
import structures.Node;

import java.util.HashSet;
import java.util.Hashtable;

/**
 * Created by Ahmed Hani Ibrahim on 9/17/2016.
 */
public class Visualize {
    private String graphName;
    private Graph graph;
    private Hashtable<Pair<Integer, Integer>, Integer> network;

    public Visualize(Hashtable<Pair<Integer, Integer>, Integer> network) {
        this.network = network;
        this.graph = null;
    }

    public void setGraphName(String graphName) {
        this.graphName = graphName;
        this.graph = new SingleGraph(this.graphName);
    }

    public void build() {
        HashSet<Integer> visitedNodes = new HashSet<Integer>();

        for (Pair<Integer, Integer> edge : this.network.keySet()) {
            if (visitedNodes.contains(edge.getKey()))
                continue;
            visitedNodes.add(edge.getKey());
            this.graph.addNode(Integer.toString(edge.getKey()));
        }

        HashSet<Pair<Integer, Integer>> visitedEdges = new HashSet<Pair<Integer, Integer>>();
        for (Pair<Integer, Integer> edge : this.network.keySet()) {
            if (visitedEdges.contains(edge) || visitedEdges.contains(new Pair<Integer, Integer>(edge.getValue(), edge.getKey())))
                continue;
            visitedEdges.add(edge);
            String edgeName = Integer.toString(edge.getKey()) + "-" + Integer.toString(edge.getValue());
            this.graph.addEdge(edgeName, Integer.toString(edge.getKey()), Integer.toString(edge.getValue()));
        }
    }

    public void draw() {
        this.graph.display();
    }
}

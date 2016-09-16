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
    private Hashtable<Pair<Node, Node>, Integer> network;

    public Visualize(Hashtable<Pair<Node, Node>, Integer> network) {
        this.network = network;
        this.graph = null;
    }

    public void setGraphName(String graphName) {
        this.graphName = graphName;
        this.graph = new SingleGraph(this.graphName);
    }

    public void build() {
        HashSet<Node> visited = new HashSet<Node>();

        for (Pair<Node, Node> edge : this.network.keySet()) {
            if (visited.contains(edge.getKey()))
                continue;
            visited.add(edge.getKey());
            this.graph.addNode(Integer.toString(edge.getKey().getId()));
        }

        visited = new HashSet<Node>();
        for (Pair<Node, Node> edge : this.network.keySet()) {
            if (visited.contains(edge.getKey()) && visited.contains(edge.getValue()))
                continue;
            visited.add(edge.getKey());
            visited.add(edge.getValue());
            String edgeName = Integer.toString(edge.getKey().getId()) + "-" + Integer.toString(edge.getValue().getId());
            this.graph.addEdge(edgeName, Integer.toString(edge.getKey().getId()), Integer.toString(edge.getValue().getId()));
        }
    }

    public void draw() {
        this.graph.display();
    }
}

import javafx.util.Pair;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.graph.implementations.SingleNode;
import scala.Int;
import structures.Node;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Vector;

/**
 * Created by Ahmed Hani Ibrahim on 9/17/2016.
 */
public class Visualize {
    private String graphName;
    private Graph graph;
    private Hashtable<Pair<Integer, Integer>, Integer> network;
    private Node[] lastRecognizedPattern;

    public Visualize(Node[] lastRecognizedPattern , Hashtable<Pair<Integer, Integer>, Integer> network, String graphName) {
        this.network = network;
        this.graphName = graphName;
        this.graph = new SingleGraph(this.graphName);
        this.lastRecognizedPattern = lastRecognizedPattern;

        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        graph.setAttribute("stylesheet", "node { size: 40px; fill-color: yellow, orange; fill-mode: gradient-horizontal; text-size: 20px;} edge { z-index: 0; fill-color: #333; size: 3px; text-size: 30px; text-color: red; }");
        graph.addAttribute("ui.quality");
        graph.addAttribute("ui.antialias");
    }

    public void setGraphName(String graphName) {
        this.graphName = graphName;
        this.graph = new SingleGraph(this.graphName);
    }

    public void build() {
        HashSet<Integer> visitedNodes = new HashSet<Integer>();
        Vector<org.graphstream.graph.Node> graphNodes = new Vector<org.graphstream.graph.Node>();

        for (Pair<Integer, Integer> edge : this.network.keySet()) {
            if (visitedNodes.contains(edge.getKey()))
                continue;
            visitedNodes.add(edge.getKey());

            int nodeValue = 0;

            for (Node aLastRecognizedPattern : this.lastRecognizedPattern) {
                if (aLastRecognizedPattern.getId() == edge.getKey())
                    nodeValue = aLastRecognizedPattern.getValue();
            }
            this.graph.addNode(Integer.toString(edge.getKey())).addAttribute("ui.label", Integer.toString(nodeValue));
        }

        HashSet<Pair<Integer, Integer>> visitedEdges = new HashSet<Pair<Integer, Integer>>();
        for (Pair<Integer, Integer> edge : this.network.keySet()) {
            if (visitedEdges.contains(edge) || visitedEdges.contains(new Pair<Integer, Integer>(edge.getValue(), edge.getKey())))
                continue;
            visitedEdges.add(edge);
            String edgeName = Integer.toString(edge.getKey()) + "-" + Integer.toString(edge.getValue());
            this.graph.addEdge(edgeName, Integer.toString(edge.getKey()), Integer.toString(edge.getValue())).addAttribute("ui.label", "" + Integer.toString(this.network.get(edge)));
        }
    }

    public void draw() {
        this.graph.display();
    }
}

package sk.upjs.paz;

import sk.upjs.paz.graph.Graph;
import sk.upjs.paz.graph.Vertex;

import java.io.File;
import java.util.Set;

public class Party {

    public static Set<Vertex> partyBoys(Graph g, int i) {
        while (true) {
            Vertex kick = null;
            for (Vertex v : g.getVertices()) {
                if (v.getOutNeighbours().size() < i || v.getOutNeighbours().size() > g.getVertices().size() - 1 - i) {
                    kick = v;
                    break;
                }
            }
            if (kick != null) {
                g.removeVertex(kick);
            } else {
                break;
            }
        }
        return g.getVertices();
    }

    public static void main(String[] args) {
        Graph g = Graph.createFromFile("C:\\Users\\Mikuláš\\IdeaProjects\\paz1b\\cviko09\\src\\main\\resources\\file3.txt");
        System.out.println(partyBoys(g, -541651156));

    }
}





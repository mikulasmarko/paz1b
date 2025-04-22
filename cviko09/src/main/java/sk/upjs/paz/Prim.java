package sk.upjs.paz;

import sk.upjs.paz.graph.Edge;
import sk.upjs.paz.graph.Graph;
import sk.upjs.paz.graph.Vertex;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Prim {

    public static void main(String[] args) {
        Graph g = Graph.createFromFile("C:\\Users\\pc12\\IdeaProjects\\untitled\\cviko10\\src\\main\\resources\\file");
        System.out.println(g.toString());
        System.out.println(new Prim().PrimsAlg(g, g.getVertex("A")));

    }

    public Map<Vertex, Double> PrimsAlg(Graph graph, Vertex start) {

        Map<Vertex, Double> dlzkyHran = graph.createVertexMap(Double.POSITIVE_INFINITY);
        Set<Vertex> vrcholyNepouzite = dlzkyHran.keySet();
        dlzkyHran.put(start, 0d);
//        vrcholyNepouzite.remove(start);


        Vertex aktualny = start;
        while (!vrcholyNepouzite.isEmpty()) {


            double dlzka = Double.POSITIVE_INFINITY;
            Vertex coDamPrec = null;
            for (Edge edge : aktualny.getEdges()) {
                if (edge.getWeight() < dlzka && !vrcholyNepouzite.contains(edge.getTarget())) {
                    dlzka = edge.getWeight();
                    coDamPrec = edge.getTarget();
                }

            }
            vrcholyNepouzite.remove(coDamPrec);
            dlzkyHran.put(coDamPrec, dlzka);
        }


        return dlzkyHran;
    }


}

/*
A B : 10
A C : 5
B D : 1
C E : 5
D C : 10
D F : 2
E A : 4
E F : 2
F A : 2
F B : 20

 */
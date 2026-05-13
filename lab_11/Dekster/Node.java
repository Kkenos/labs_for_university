package lab_11.Dekster;

import java.util.ArrayList;

import static java.lang.Double.MAX_VALUE;

public class Node {
    double x, y;
    long id;
    double minDistance = MAX_VALUE;;
    Node parent = null;

   ArrayList<Edge> neighbors = new ArrayList<>();
    Node(long id, double x,double y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }
    public void addNeighbor(Long targetId, double weight) {
        neighbors.add(new Edge(this.id,targetId,weight));
    }
}

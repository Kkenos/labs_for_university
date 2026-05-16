package lab_11.Dekster;

import java.util.Comparator;
import java.util.PriorityQueue;

public class AStar extends Dijkstra {
    @Override
    public PriorityQueue<Node>createQueue(Node targetNode){
        return new PriorityQueue<>(Comparator.comparingDouble(n -> n.minDistance + n.distanceTo(targetNode)));
    }
}

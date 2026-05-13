package lab_11.Dekster;

import java.util.*;

import static java.lang.Double.MAX_VALUE;

public class Dijkstra {

    public void findPath(Node startNode, Map<Long,Node> allNodes) {
        startNode.minDistance = 0;

        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingDouble(n -> n.minDistance));
        queue.add(startNode);
        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            for(Edge e : currentNode.neighbors) {
                Node neighbor = findNodeById(e.idEnd, allNodes);
                double weight = e.weight;
                double newPath = weight + currentNode.minDistance ;
                if (newPath < neighbor.minDistance){
                    neighbor.minDistance = newPath;
                    neighbor.parent = currentNode;
                    queue.add(neighbor);
                }
            }

        }
    }
    public List<Node> getPath(Node target){

        if(target.minDistance  == Double.MAX_VALUE){ //если не добрались то верну пустоту
            return new ArrayList<>();
        }
        List<Node> path = new ArrayList<>();
        Node node = target;
        while(node != null){
            path.add(node);
            node = node.parent;
        }
        return path.reversed();
    }
    public Node findNodeById(Long id, Map<Long, Node> allNodes) {
        return allNodes.get(id);//достаем узел из мапы
    }

}

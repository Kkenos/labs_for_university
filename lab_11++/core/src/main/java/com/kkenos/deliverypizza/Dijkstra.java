package com.kkenos.deliverypizza;

import java.util.*;


public class Dijkstra {

    protected PriorityQueue<Node> createQueue(Node targetNode) {
        return new PriorityQueue<>(Comparator.comparingDouble(n -> n.minDistance));
    }

    public void findPath(Node startNode, Node targetNode, Map<Long, Node> allNodes) {
        startNode.minDistance = 0;
        int step = 0;
        PriorityQueue<Node> queue = createQueue(targetNode);
        Set<Long> visited = new HashSet<>();
        queue.add(startNode);

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();

            if (visited.contains(currentNode.id)) {
                continue;
            }
            if (currentNode.id == targetNode.id) {
                break;
            }
            visited.add(currentNode.id);
            for (Edge e : currentNode.neighbors) {
                Node neighbor = findNodeById(e.idEnd, allNodes);
                if (visited.contains(neighbor.id)) {
                    continue;
                }
                double weight = e.weight;
                double newPath = currentNode.minDistance + weight;
                if (newPath < neighbor.minDistance) {

                    queue.remove(neighbor);

                    neighbor.minDistance = newPath;
                    neighbor.parent = currentNode;

                    queue.add(neighbor);
                }
            }
            step++;
        }
        System.out.println("Количество итераций " + step);
    }

    public List<Node> getPath(Node target) {

        if (target.minDistance == Double.MAX_VALUE) { //если не добрались, то верну пустоту
            return new ArrayList<>();
        }
        List<Node> path = new ArrayList<>();
        Node node = target;
        while (node != null) {
            path.add(node);
            node = node.parent;
        }
        return path.reversed();
    }

    public Node findNodeById(Long id, Map<Long, Node> allNodes) {
        return allNodes.get(id);//достаем узел из мапы
    }

}

package lab_11.Dekster;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        GraphLoader loader = new GraphLoader();
        loader.loadNodes("C:\\Users\\filip\\IdeaProjects\\labs\\src\\lab_11\\omsk\\nodes.csv");
        loader.LoadEdges("C:\\Users\\filip\\IdeaProjects\\labs\\src\\lab_11\\omsk\\edges.csv");
        Dijkstra dijkstra  = new Dijkstra();
        Node start = loader.getNodeMap().get(177994364L);
        Node finish = loader.getNodeMap().get(210321482L);

        if( start != null && finish != null){
            dijkstra.findPath(start,loader.getNodeMap());
            List<Node> path = dijkstra.getPath(finish);
            System.out.println("кратчайшее расстояние" + finish.minDistance);
            System.out.print("Путь: ");
            for (Node n : path) {
                System.out.print(n.id + " -> ");
            }
        } else {
            System.out.println("Узлы не найдены!");
        }
    }

}

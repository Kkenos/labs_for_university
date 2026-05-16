package lab_11.Dekster;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GraphLoader loader = new GraphLoader();
        loader.loadNodes("C:\\Users\\filip\\IdeaProjects\\labs\\src\\lab_11\\omsk\\nodes.csv");
        loader.LoadEdges("C:\\Users\\filip\\IdeaProjects\\labs\\src\\lab_11\\omsk\\edges.csv");
        Node start = loader.getNodeMap().get(177994364L);
        Node finish = loader.getNodeMap().get(242922163L);

        Scanner input = new Scanner(System.in);
        System.out.println("Выбери алгоритм 1(Дейкстры), 2(A*)");
        int choice = input.nextInt();
        if (choice == 1) {
            Dijkstra dijkstra = new Dijkstra();
            if (start != null && finish != null) {
                dijkstra.findPath(start, finish, loader.getNodeMap());
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
        else if (choice == 2) {
            AStar aStar = new AStar();
            if (start != null && finish != null) {
                aStar.findPath(start, finish, loader.getNodeMap());
                List<Node> path = aStar.getPath(finish);
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
}

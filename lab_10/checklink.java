package lab_10;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class checklink {
    List<List<Integer>> adj = new ArrayList<>();

    boolean[] visited;



    public void addSublist(int n){
        for(int i = 0; i < n; i++){
            adj.add(new ArrayList<>());
        }
    }
    public void addEdge(Scanner input){
        while(input.hasNextInt()){
            int u = input.nextInt();
            if(!input.hasNextInt()){
                break;
            }
            int v = input.nextInt();
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
    }
    public void DFS(int u,boolean[] visited,List<Integer> component){
        visited[u] = true;
        component.add(u);
        for(int v : adj.get(u)){
            if(!visited[v]){
                DFS(v,visited,component);
            }
        }
    }
    public List<List<Integer>> findComponents(int n){
        boolean[] visited = new boolean[n];
        List<List<Integer>> component = new ArrayList<>();
        for(int i = 0; i < n; i++){
            if(!visited[i]){
                List<Integer> sublist = new ArrayList<>();
                DFS(i,visited,sublist);
                component.add(sublist);
            }
        }
        return component;
    }


}

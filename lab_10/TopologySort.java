package lab_10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class TopologySort {
    List<List<Integer>> adj;

    int[] state;

    public void init(int n){
        adj = new ArrayList<>();
        for(int i = 0; i < n; i++){
            adj.add(new ArrayList<>());
        }
        state = new int[n];
    }

    public void addEdge(Scanner input){
        while(input.hasNextInt()){
            int u = input.nextInt();
            if(!input.hasNextInt()){
                break;
            }
            int v = input.nextInt();
            adj.get(u).add(v);
        }
    }

    public boolean dfs(int u, List<Integer>result){
        state[u] = 1;
        for (int v : adj.get(u)) {
            if(state[v] == 1){
                return true;
            }
            if(state[v] == 0){
                if(dfs(v, result)){
                    return true;
                }
            }
        }
        state[u] = 2;
        result.add(u);
        return false;
    }
    public void sort(int n){
        List<Integer> result = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            if (state[i] == 0) {
                if (dfs(i, result)) {
                    System.out.println("Cycle");
                    return;
                }
            }
        }
        Collections.reverse(result);
        for(int node : result){
            System.out.print(node + " ");
        }
    }

}

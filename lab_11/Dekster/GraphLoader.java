package lab_11.Dekster;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


public class GraphLoader {
    private HashMap<Long,Node> nodeMap = new HashMap<>();
    public void loadNodes(String fileName) {
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                long id = Long.parseLong(parts[0]);
                double x = Double.parseDouble(parts[1]);
                double y = Double.parseDouble(parts[2]);
                Node node = new Node(id,x,y);
                nodeMap.put(id,node);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public Node getNode(Long id){
        return nodeMap.get(id);
    }
    public void LoadEdges(String fileName) {
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
            String line;
            br.readLine();
            while((line = br.readLine())!=null){
                String[] parts = line.split(",");
                Long vId = Long.parseLong(parts[0]);
                Long uId = Long.parseLong(parts[1]);

                Node v = nodeMap.get(vId);
                Node u = nodeMap.get(uId);

                if(v != null && u != null){
                    double weight = Math.sqrt(Math.pow(v.x - u.x, 2) +  Math.pow(v.y - u.y, 2));
                    u.addNeighbor(vId, weight);
                    v.addNeighbor(uId, weight);
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public  HashMap<Long, Node> getNodeMap() {
        return nodeMap;
    }
}


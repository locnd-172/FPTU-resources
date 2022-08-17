package graph;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class WeightedGraphList {
    private int V;
    
    public WeightedGraphList() {
    }
    
    public WeightedGraphList(int V) {
        this.V = V;
    }

    private ArrayList<ArrayList<ListEdge>> g = new ArrayList<>();
    
    public void loadGraph(String fileName) throws Exception {
        File f = new File(fileName);
        Scanner sc = new Scanner(f);
       
        try {
            if (!f.exists())
                throw new Exception("The file does not exist! Try another file name...");
            if (f.length() == 0)
                throw new Exception("The file is empty! Add some data and try again...");
            
            V = Integer.parseInt(sc.nextLine());
            for (int i = 0; i < V; ++i) 
                g.add(new ArrayList<ListEdge>());
            
            while (sc.hasNext()) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                int w = sc.nextInt();
                g.get(u).add(new ListEdge(v, w));
                g.get(v).add(new ListEdge(u, w));
            }
            
        } catch (IOException e) {
            return;
        }
    }

    public int getV() {
        return V;
    }
    
    private int[] vis;
    
    public void loadFile() throws Exception {
        String fileName;
        Scanner sc = new Scanner(System.in);
        System.out.print("File name: ");
        fileName = sc.nextLine();
        fileName += ".txt";
        loadGraph(fileName);
    }
    
    public void printDFS() {
        vis = new int[V];
        for (int i = 0; i < V; ++i) vis[i] = 0;
        for (int i = 0; i < V; ++i) {
            if (vis[i] == 0) DFS(i);
        }
        System.out.println("");
    }
    
    public void DFS(int u) {
        System.out.print(u + " ");
        vis[u] = 1;
        for (ListEdge edge : g.get(u)) {
            int v = edge.getV();
            if (vis[v] == 0) DFS(v);
        }
    }
    
    public int deg(int u) {
        return g.get(u).size();
    }
    
    
}

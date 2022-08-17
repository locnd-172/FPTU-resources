package hamilton;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class GraphList {
    private int V;
    
    public GraphList() {
    }
    
    public GraphList(int V) {
        this.V = V;
    }

    ArrayList<ArrayList<Integer>> g = new ArrayList<>();
    ArrayList<Integer> hmt = new ArrayList<>();
    ArrayList<Integer> path = new ArrayList<>();
    int[] vis;
    
    Scanner sc = new Scanner(System.in);
    
    public void loadGraph() throws Exception {
        System.out.print("File name: ");
        String fileName = sc.nextLine().trim() + ".txt";
        File f = new File(fileName);
        Scanner rf = new Scanner(f);
       
        try {
            if (!f.exists())
                throw new Exception("The file does not exist! Try another file name...");
            if (f.length() == 0)
                throw new Exception("The file is empty! Add some data and try again...");
            
             this.V = Integer.parseInt(rf.nextLine().trim());
            for (int i = 0; i < V; ++i) 
                g.add(new ArrayList<Integer>());
            
            while (rf.hasNext()) {
                int u = rf.nextInt();
                int v = rf.nextInt();
                u--; v--;
                g.get(u).add(v);
                g.get(v).add(u);
            }
            
        } catch (IOException e) {
            return;
        }
    }
    
    public int deg(int u, ArrayList<ArrayList<Integer>> graph) {
        return graph.get(u).size();
    }
    
    public int getStartNode() {
        int s = 0;
        for (int i = 0; i < V; ++i) 
            if (deg(s, g) > deg(i, g)) {
                s = i;
           }
        return s;
    }
    
    int cnt = 0;
    public void Hamilton() {
        vis = new int[V];
        Arrays.fill(vis, 0);
        int start = getStartNode();
        
        path.add(start);
        boolean res = findHamilton(start);
        if (!res) {
            System.out.println("Graph doesn't have Hamilton path/cycle.");
        }
        
        // print all Hamilton path
        System.out.println("");
        System.out.println("All Hamilton path: ");
        
        for (int i = 0; i < V; ++i) 
            if (vis[i] == 0)
                allHamiltonPath(i);
        System.out.println(cnt);
    }
    
    public void allHamiltonPath(int u) {
        if (hmt.size() == V) {
            vis[u] = 1;
            System.out.println(hmt.toString());
            ++cnt;
            return;
        }
        
        for (int v : g.get(u)) {
            if (hmt.indexOf(v) == -1) {
                
                hmt.add(v);
 
                allHamiltonPath(v);
 
                hmt.remove(hmt.size() - 1);
            }
        }
    }
     
    
    //ArrayList<Integer> path = new ArrayList<>();
    public boolean findHamilton(int u) {
        if (path.size() == V) {
            int last = path.get(path.size() - 1);
            int first = path.get(0);
            int pos = g.get(first).indexOf(last);
            
            ArrayList<Integer> nodeList = path;
            if (pos != -1) {
                System.out.println("Graph has a Hamilton cycle");
                nodeList.add(first);
            }
            else System.out.println("Graph has a Hamilton path");
            
            System.out.println(nodeList);
            return true;
        }
        for (int v : g.get(u)) {
            if (path.indexOf(v) == -1) {
                path.add(v);
                if (findHamilton(v)) return true;
                path.remove(path.size() - 1);
            }
        }
        return false;
    }
    
    /*private int V;
    ArrayList<ArrayList<Integer>> g = new ArrayList<>();
     
    public void addEdge(int u, int v) {
        u--; v--;
        g.get(u).add(v);
        g.get(v).add(u);
    }

    public void createGraph() {
        V = 6;
        
        for (int i = 0; i < V; ++i) {
            g.add(new ArrayList<Integer>());
        }
        addEdge(1, 2);
        addEdge(1, 3);
        addEdge(1, 4);
        addEdge(1, 5);
        addEdge(1, 6);
        addEdge(2, 3);
        addEdge(2, 4);
        addEdge(2, 5);
        addEdge(2, 6);
        addEdge(3, 4);
        addEdge(3, 5);
        addEdge(3, 6);
        addEdge(4, 5);
        addEdge(4, 6);
        addEdge(5, 6);
    }*/
    
}


package graph;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

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

    private ArrayList<ArrayList<Integer>> g = new ArrayList<>();
    
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
                g.add(new ArrayList<Integer>());
            
            while (sc.hasNext()) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                g.get(u).add(v);
                g.get(v).add(u);
            }
            
        } catch (IOException e) {
            return;
        }
    }

    public int getV() {
        return V;
    }
    
    private int[] vis;
    private ArrayList<ArrayList<Integer>> connectedComp; 
    
    public void loadFile() throws Exception {
        String fileName = "graphlist.txt";
//        Scanner sc = new Scanner(System.in);
//        System.out.print("File name: ");
//        fileName = sc.nextLine();
//        fileName += ".txt";
        loadGraph(fileName);
    }
    
    public void printDFS() {
        vis = new int[V];
        Arrays.fill(vis, 0);
        for (int i = 0; i < V; ++i) {
            if (vis[i] == 0) {
                GraphList.this.DFS(i);
                System.out.println("");
            }
        }
        
    }
    
    public void DFS(int u) {
        System.out.print(u + " ");
        vis[u] = 1;
        for (int v : g.get(u)) {
            if (vis[v] == 0) GraphList.this.DFS(v);
        }
    }
    
    public int deg(int u) {
        return g.get(u).size();
    }
    
    public void printNonRecDFS() {
        vis = new int[V];
        Arrays.fill(vis, 0);
        for (int i = 0; i < V; ++i) {
            if (vis[i] == 0) {
                nonRecDFS(i);
                System.out.println("");
            }
        }
    }
    
    public void nonRecDFS(int u) {
        Stack<Integer> St = new Stack<>();
        St.add(u);         
        vis[u] = 1;
        while (!St.isEmpty()) {
            int cur = St.pop();
            System.out.print(cur + " ");
            for (int v : g.get(cur)) {
                if (vis[v] == 0) {
                    vis[v] = 1;
                    St.add(v);
                }
            }
        }
    }

    public void printBFS() {
        vis = new int[V];
        Arrays.fill(vis, 0);
        for (int i = 0; i < V; ++i) {
            if (vis[i] == 0) {
                BFS(i);
                System.out.println("");
            }
        }
    }
    
    public void BFS(int cur) {
        Queue<Integer> Q = new LinkedList<>();
        Q.add(cur); vis[cur] = 1;
        while (!Q.isEmpty()) {
            int u = Q.poll();  
            System.out.print(u + " ");
            for (int v : g.get(u)) 
                if (vis[v] == 0) {
                    vis[v] = 1;
                    Q.add(v);
                }
        }
    }
    
    public void DFS(int u, int nCC) {
        connectedComp.get(nCC).add(u);
        vis[u] = 1;
        for (int v : g.get(u)) {
            if (vis[v] == 0) DFS(v, nCC);
        }
    }
    
    public int countCC() {
        vis = new int[V];
        Arrays.fill(vis, 0);
        int nCC = 0;
        for (int i = 0; i < V; ++i) {
            if (vis[i] == 0) {
                connectedComp.add(new ArrayList<Integer>());
                DFS(i, nCC);
                ++nCC;
            }
        }
        return nCC;
    }
    
    public boolean checkConnect() {
        connectedComp = new ArrayList<>();;
        return countCC() == 1;
    }
    
    public void printCC() {
        connectedComp = new ArrayList<>();;
        int nCC = countCC();
        
        for (int i = 0; i < nCC; ++i) {
            System.out.print((i + 1) + ": ");
            for (int u : connectedComp.get(i)) 
                System.out.print(u + " ");
            System.out.println("");
            
        }
    }
    
    public void BFSRec(LinkedList<Integer> Q) {
        if (Q.isEmpty()) return;
        int cur = Q.poll();
        System.out.print(cur + " ");
        for (int v : g.get(cur)) {
            if (vis[v] == 0) {
                vis[v] = 1;
                Q.add(v);
            }
        }
        BFSRec(Q);
    }
    
    public void printRecBFS() {
        vis = new int[V];
        Arrays.fill(vis, 0);
        for (int i = 0; i < V; ++i) {
            if (vis[i] == 0) {
                LinkedList<Integer> Q = new LinkedList<>();
                Q.add(i); vis[i] = 1;
                BFSRec(Q);
            }
        }
    }

   
}

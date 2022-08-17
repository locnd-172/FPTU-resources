package shortestpath;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.lang.Math;
import java.util.Arrays;
import java.util.Stack;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class GraphMatrix {
    private int V;
    
    public GraphMatrix() {
    }
    
    public GraphMatrix(int V) {
        this.V = V;
    }
    
    private int[][] a;
    private static final int INF = 1000000;
    Scanner sc = new Scanner(System.in);
    
    public int[][] loadGraph(String fileName) throws Exception {
        File f = new File(fileName);
        Scanner sc = new Scanner(f);
       
        try {
            if (!f.exists())
                throw new Exception("The file does not exist! Try another file name...");
            if (f.length() == 0)
                throw new Exception("The file is empty! Add some data and try again...");
            
            this.V = Integer.parseInt(sc.nextLine());
            int[][] mx = new int[V][V];
            
            for (int i = 0; i < V; i++) 
                for (int j = 0; j < V; j++) 
                    mx[i][j] = sc.nextInt();
            return mx;
        } catch (IOException e) {
            return null;
        }
    }

    public int getV() {
        return V;
    }

    public void loadFile() throws Exception {
        String fileName;
        Scanner sc = new Scanner(System.in);
        System.out.print("File name: ");
        fileName = sc.nextLine();
        fileName += ".txt";
        a = loadGraph(fileName);
    }
    
    public int deg(int u) {
        int d = 0;
        for (int i = 0; i < V; ++i) 
            d += (a[u][i] != 0 ? 1 : 0);
        return d;
    }
   
    
    private int[] vis;
    
    public void printDFS() {
        vis = new int[V + 1];
        Arrays.fill(vis, 0);
        for (int i = 0; i < V; ++i) {
            if (vis[i] == 0) DFS(i);
        }
        System.out.println("");
    }
    
    public void DFS(int u) {
        System.out.print(u + " ");
        vis[u] = 1;
        for (int i = 0; i < V; ++i) {
            if (i != u && a[u][i] == 1) 
                if (vis[i] == 0) DFS(i);
        }
    }
    
    public void Dijkstra2(int start, int finish) {
        boolean vis[] = new boolean[V + 1];
        int d[] = new int[V + 1];
        int trace[] = new int[V + 1];
        for (int i = 1; i <= V; i++) 
            for (int j = 1; j <= V; j++) 
                if (i != j && a[i][j] == 0) {
                    a[i][j] = INF;
                }
        for (int i = 1; i <= V; i++) {
            vis[i] = false;
            d[i] = INF;
            trace[i] = start;
        }
        int u = 0;
        d[start] = 0;
        while (vis[finish] == false) {
            int min = INF;
            for (int v = 1; v <= V; v++) 
                if (vis[v] == false && d[v] < min) { 
                    min = d[v];
                    u = v;
                }
            vis[u] = true;
            for (int v = 1; v <= V; v++) {
                if (vis[v] == false && d[v] > d[u] + a[u][v]) {
                    d[v] = d[u] + a[u][v];
                    trace[v] = u;
                }
              
            }
        }
        System.out.println("Shortest path: " + d[finish]);
        Stack<Integer> st = new Stack();
        System.out.print(start + " ");
        while (trace[finish] != start) {
            st.push(finish);
            finish = trace[finish];
        }
        st.push(finish);
        while (!st.empty()) {
            System.out.print(st.pop() + " ");
        }
    }
    
    public void Dijkstra(int start, int finish) {
        boolean vis[] = new boolean[V];
        int d[] = new int[V];
        int trace[] = new int[V];
        for (int i = 0; i < V; i++) 
            for (int j = 0; j < V; j++) 
                if (i != j && a[i][j] == 0) {
                    a[i][j] = INF;
                }
       
     
        for (int i = 0; i < V; i++) {
            vis[i] = false;
            d[i] = INF;
            trace[i] = start;
        }
        int u = 0;
        d[start] = 0;
        
        while (vis[finish] == false) {
            int min = INF;
            int minNode = -1;
            for (int v = 0; v < V; v++) 
                if (vis[v] == false && d[v] <= min) { 
                    min = d[v];
                    minNode = v;
                }
            u = minNode;
            vis[u] = true;
            for (int v = 0; v < V; v++) {
                if (vis[v] == false && d[v] > d[u] + a[u][v]) {
                    d[v] = d[u] + a[u][v];
                    trace[v] = u;
                }
            }
        }
        System.out.println("Shortest path: " + d[finish]);
        Stack<Integer> st = new Stack();
        System.out.print(start + " ");
        while (trace[finish] != start) {
            st.push(finish);
            finish = trace[finish];
        }
        st.push(finish);
        while (!st.empty()) {
            System.out.print(st.pop() + " ");
        }
    }
    
    public void shortestPath() {
        int s, t; 
        System.out.print("start: "); 
        s = sc.nextInt();
        System.out.print("finish: ");
        t = sc.nextInt();
        
        Dijkstra(s, t);
    }
    
    public void shortestPathAllNode() {
        for (int i = 0; i < V - 1; ++i) {
            for (int j = i + 1; j < V; j++) {
                System.out.println("From " + i +  " to " + j);
                Dijkstra(i, j);
                System.out.println("\n______________________");
            }
        }
    }

}

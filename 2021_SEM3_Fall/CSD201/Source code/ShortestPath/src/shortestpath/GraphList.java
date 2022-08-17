package shortestpath;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
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

    private ArrayList<ArrayList<ListEdge>> g = new ArrayList<>();
    private static final int INF = 1000000;
    Scanner sc = new Scanner(System.in);
    
    public void loadGraph(String fileName) throws Exception {
        File f = new File(fileName);
        Scanner sc = new Scanner(f);
       
        try {
            if (!f.exists())
                throw new Exception("The file does not exist! Try another file name...");
            if (f.length() == 0)
                throw new Exception("The file is empty! Add some data and try again...");
            
             this.V = Integer.parseInt(sc.nextLine().trim());
            for (int i = 0; i < V; ++i) 
                g.add(new ArrayList<ListEdge>());
            
            while (sc.hasNext()) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                int w = sc.nextInt();
                u--; v--;
                g.get(u).add(new ListEdge(v, w));
                g.get(v).add(new ListEdge(u, w));
            }
            
        } catch (IOException e) {
            return;
        }
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
        for (ListEdge edge : g.get(u)) {
            int v = edge.v;
            if (vis[v] == 0) DFS(v);
        }
    }
    
    public int deg(int u) {
        return g.get(u).size();
    }
    
    public void Dijkstra(int start, int finish) {
        boolean vis[] = new boolean[V];
        int d[] = new int[V];
        int trace[] = new int[V];
        
        Arrays.fill(d, INF);
        Arrays.fill(trace, start);

        int u = start;
        d[start] = 0;
        
        while (vis[finish] == false) {
            int min = INF;
            int minNode = u; 
            for (ListEdge edge : g.get(u)) {
                int v = edge.v;
                if (vis[v] == false && d[v] < min) { 
                    min = d[v];
                    minNode = v;
                }
            }
            u = minNode;
            vis[u] = true;
            for (ListEdge edge : g.get(u)) {
                int v = edge.v;
                int w = edge.w;
                if (vis[v] == false && d[v] > d[u] + w) {
                    d[v] = d[u] + w;
                    trace[v] = u;
                }
            }
        }
        System.out.println("Shortest path " + start + " to " + finish + ": " + d[finish]);
        Stack<Integer> st = new Stack();
        System.out.print(start);
        while (finish != start) {
            st.push(finish);
            finish = trace[finish];
        }
        while (!st.empty()) {
            System.out.print(" -> " + st.pop());
        }
        System.out.println("");
    }
    
    public void shortestPath() {
        int s, t; 
        System.out.print("start: "); 
        s = sc.nextInt();
        System.out.print("finish: ");
        t = sc.nextInt();
        
        Dijkstra(s, t);
        System.out.println("Dijkstra heap: ");
        DijkstraHeap(s, t);
        System.out.println("");
    }
    
    
    public void DijkstraHeap(int start, int finish) {
        int d[] = new int[V];
        int trace[] = new int[V];
        
        Arrays.fill(d, INF);
        Arrays.fill(trace, start);
	d[start] = 0;
        
        PriorityQueue<ListEdge> PQ = new PriorityQueue<ListEdge>();
        PQ.add(new ListEdge(start, 0));
	while (!PQ.isEmpty()) {
            ListEdge curEdge = PQ.poll();
            int u = curEdge.v;
            int du = curEdge.w;

            if (du != d[u])  continue; 
            
            for (ListEdge edge : g.get(u)) {
                int v = edge.v;
                int w = edge.w;
                if (d[v] > d[u] + w) {
                    d[v] = d[u] + w;
                    trace[v] = u;
                    PQ.add(new ListEdge(v, d[v]));
                }
            }
	}
        System.out.println("Shortest path " + start + " to " + finish + ": " + d[finish]);
        
        Stack<Integer> st = new Stack();
        System.out.print(start);
        while (finish != start) {
            st.push(finish);
            finish = trace[finish];
        }
        while (!st.empty()) 
            System.out.print(" -> " + st.pop());
        
    }
    
    
    
    public void findMST() {
        int s; 
        System.out.print("start: "); 
        s = sc.nextInt();
        prim(s);
    }
    
    public void prim(int start) {
        
        ArrayList<Edge> mst = new ArrayList<>();
        int d[] = new int[V];
        int inMst[] = new int[V];
        int trace[] = new int[V];
        int mstWeight = 0;
        
        Arrays.fill(d, INF);
        Arrays.fill(inMst, 0);
        Arrays.fill(trace, start);
        PriorityQueue<ListEdge> PQ = new PriorityQueue<ListEdge>();

        d[start] = 0;
        trace[start] = -1;
        PQ.add(new ListEdge(start, 0));
        
        while (!PQ.isEmpty()) {
            ListEdge curEdge = PQ.poll();
            int u = curEdge.v;
            int du = curEdge.w;
            if (du != d[u])  continue; 
            
            inMst[u] = 1;
            mstWeight += du;
            if (trace[u] != -1) mst.add(new Edge(trace[u], u, du));
            
            for (ListEdge edge : g.get(u)) {
                int v = edge.v;
                int w = edge.w;
                if (inMst[v] == 0 && d[v] >  w) {
                    d[v] = w;
                    trace[v] = u;
                    PQ.add(new ListEdge(v, d[v]));
                }
            }
        }
        System.out.println("MST weight: " + mstWeight);
        for (Edge edge : mst) {
            System.out.println(edge.u + " " + edge.v);
        }
    }
    

}

package toposort;

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class TopoSort {

    private static int V;
    private static ArrayList<ArrayList<Integer> > G;
    
    public TopoSort() {
    }
    
    public TopoSort(int V) {
        this.V = V;
        G = new ArrayList<>();
        for (int i = 0; i < V; ++i)
            G.add(new ArrayList<>());
    }
    
    static void addEdge(int u, int v) {
        G.get(u).add(v); 
    }
    
    static boolean[] vis;
    static Stack<Integer> st;
    
    static void DFS(int u) {
        vis[u] = true;
        for (int v : G.get(u)) {
            if (!vis[v]) DFS(v);
        }
        st.push(u);
    }
    
    static void topologySort() {
        st = new Stack<>();
        vis = new boolean[V];
        
        for (int i = 0; i < V; i++)
            if (!vis[i]) DFS(i);

        while (!st.empty())
            System.out.print(st.pop() + " ");
    }
    
    public static void main(String[] args) {
        TopoSort g = new TopoSort(6);
        TopoSort.addEdge(5, 2);
        TopoSort.addEdge(5, 0);
        TopoSort.addEdge(4, 0);
        TopoSort.addEdge(4, 1);
        TopoSort.addEdge(2, 3);
        TopoSort.addEdge(3, 1);
        
        TopoSort.topologySort();
        
        System.out.println("");
    }
}


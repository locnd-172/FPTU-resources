package graph;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

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

    public int[][] loadGraph(String fileName) throws Exception {
        File f = new File(fileName);
        Scanner sc = new Scanner(f);
       
        try {
            if (!f.exists())
                throw new Exception("The file does not exist! Try another file name...");
            if (f.length() == 0)
                throw new Exception("The file is empty! Add some data and try again...");
            
            V = Integer.parseInt(sc.nextLine());
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
    
    private int[][] a;
    
    public void loadFile() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("File name: ");
        String fileName = sc.nextLine().trim() + ".txt";
        a = loadGraph(fileName);
    }
    
    public int deg(int u) {
        int d = 0;
        for (int i = 0; i < V; ++i) 
            d += a[u][i];
        return d;
    }
    
    public void printMatrix() {
        for (int i = 0; i < V; ++i) {
            for (int j = 0; j < V; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println("");
        }
    }
    
    private int[] vis;
    
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
        for (int i = 0; i < V; ++i) {
            if (i != u && a[u][i] == 1) 
                if (vis[i] == 0) DFS(i);
        }
    }
    
    public void sumBound() {
        int s = 0;
        for (int i = 0; i < V; ++i) {
            s += a[0][i] + a[V -1][i] + a[i][0] + a[i][V - 1];
        }
        System.out.println(s);
    }
    
}

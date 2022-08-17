package eulertour;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
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

    private ArrayList<Integer> oddDegNode = new ArrayList<>();
    private ArrayList<Integer> euler = new ArrayList<>();
    private int[][] mx;
    private int[] degree;
    Scanner sc;
    
    public int[][] loadMatrix(String fileName) throws Exception {
        File f = new File(fileName);
        sc = new Scanner(f);
       
        try {
            if (!f.exists())
                throw new Exception("The file does not exist! Try another file name...");
            if (f.length() == 0)
                throw new Exception("The file is empty! Add some data and try again...");
            
            V = Integer.parseInt(sc.nextLine());
            mx = new int[V][V];
            
            for (int i = 0; i < V; i++) 
                for (int j = 0; j < V; j++) 
                    mx[i][j] = sc.nextInt();
            return mx;
        } catch (IOException e) {
            return null;
        }
    }
    
    public void loadFile() throws Exception {
        System.out.print("File name: ");
        String fileName = sc.nextLine().trim() + ".txt";
        loadMatrix(fileName);
    }
    
    public void calcDeg() {
        degree = new int[V];
        for (int i = 0; i < V; i++) 
            for (int j = 0; j < V; ++j) 
                degree[i] += mx[i][j];
    }
    
    public void fleuryMatrix() {
        calcDeg();
        int check = isEuler();
        switch (check) {
            case 0 -> {
                System.out.println("Graph doesn't have Euler cycle or Euler path");
                return;
            }
            case 1 -> System.out.println("Graph has an Euler cycle:");
            case 2 -> System.out.println("Graph has an Euler path:");
        }
        
        int start = 0;
        if (oddDegNode.size() == 2) start = oddDegNode.get(0);
        Stack<Integer> st = new Stack<>();
        st.add(start);
        while (!st.empty()) {
            int u = st.peek();
            int v;
            for (v = 0; v < V; ++v) 
                if (mx[u][v] == 1) break;
            if (v >= V) {
                euler.add(u);
                st.pop();
            } else {
                mx[u][v] = 0; mx[v][u] = 0;
                st.add(v);
            }
        }
        for (int i = euler.size() - 1; i >= 1; i--) 
            System.out.print(euler.get(i) + " - ");
        System.out.println(euler.get(0));
    }
            
    public int isEuler() {
        int notConnect = 0;
        for (int i = 0; i < V; ++i) {
            if (degree[i] == 0) notConnect = 1;
            if (degree[i] % 2 != 0) oddDegNode.add(i);
        }
        if (oddDegNode.size() > 2 || oddDegNode.size() == 1 || notConnect == 1) return 0;
        if (oddDegNode.size() == 2) return 2;
        return 1;
    }
    
}

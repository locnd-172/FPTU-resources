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
public class GraphList {
    private int V;
    
    public GraphList() {
    }
    
    public GraphList(int V) {
        this.V = V;
    }

   
    private static final int INF = 1000000;
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
    
    ArrayList<ArrayList<Integer>> g = new ArrayList<>();
    ArrayList<ArrayList<Integer>> G;
    ArrayList<Integer> oddDegNode = new ArrayList<>();
    ArrayList<Integer> euler = new ArrayList<>();
    
    public int deg(int u, ArrayList<ArrayList<Integer>> graph) {
        return graph.get(u).size();
    }
    
    public void fleuryList() {
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
        G = g;
        st.add(start);
        while (!st.empty()) {
            int u = st.peek();
            int v = INF;
            if (G.get(u).isEmpty()) v = -1;
            for (int i : G.get(u)) {
                v = Math.min(v, i);
            }
            
            if (v == -1) {
                euler.add(u);
                st.pop();
            } else {
                int pos1 = G.get(u).indexOf(v);
                int pos2 = G.get(v).indexOf(u);
                G.get(u).remove(pos1);
                G.get(v).remove(pos2);
                st.push(v);
            }
        }
        for (int i = euler.size() - 1; i >= 1; i--) 
            System.out.print(euler.get(i) + " - ");
        System.out.println(euler.get(0));
    }
    
    public int isEuler() {
        int notConnect = 0;
        for (int i = 0; i < V; ++i) {
            if (deg(i, g) == 0) notConnect = 1;
            if (deg(i, g) % 2 != 0) oddDegNode.add(i);
        }
        if (oddDegNode.size() > 2 || oddDegNode.size() == 1 || notConnect == 1) return 0;
        if (oddDegNode.size() == 2) return 2;
        return 1;
    }
    
}


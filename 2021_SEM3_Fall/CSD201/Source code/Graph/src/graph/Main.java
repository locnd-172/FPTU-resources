package graph;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class Main {
    public static void main(String[] args) throws Exception {
        //adjacentMatrixGraph();
        
        adjacentListGraph();
        
        //adjacentListWeightedGraph();
    }
    
    public static void adjacentMatrixGraph() throws Exception {
        GraphMatrix G = new GraphMatrix();
        G.loadFile();
        
        G.printMatrix();
        
        for (int i = 0; i < G.getV(); i++) {
            System.out.println("deg " + i + ": " + G.deg(i));
        }
        
        
        System.out.print("DFS: ");
        G.printDFS();
        
        System.out.print("Sum bound: ");
        G.sumBound();
    }

    public static void adjacentListGraph() throws Exception {
        GraphList G = new GraphList();
        G.loadFile();
        
        for (int i = 0; i < G.getV(); i++) {
            System.out.println("deg " + i + ": " + G.deg(i));
        }
        
        System.out.print("DFS: ");
        G.printDFS();
        
        System.out.print("DFS non-recursion: ");
        G.printNonRecDFS();
        
        System.out.print("BFS: ");
        G.printBFS();
        
        System.out.print("BFS recusive: ");
        G.printRecBFS();
        
        System.out.println("\n______");
        if (G.checkConnect()) System.out.println("Connected");
        else System.out.println("Not conncected");
        
        System.out.println("Number of connected component: " + G.countCC());
        G.printCC();
        
    }
    
    public static void adjacentListWeightedGraph() throws Exception {
        WeightedGraphList G = new WeightedGraphList();
        G.loadFile();
        
        for (int i = 0; i < G.getV(); i++) {
            System.out.println("deg " + i + ": " + G.deg(i));
        }
        
        System.out.print("DFS: ");
        G.printDFS();
        
    }
}

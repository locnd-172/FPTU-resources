package shortestpath;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class ShortestPath {

    public static void main(String[] args) throws Exception {
        
        //adjacentListGraph();
        //adjacentMatrixGraph();
        
        miniumSpanningTree();
        
    }
    
    public static void adjacentMatrixGraph() throws Exception {
        GraphMatrix G = new GraphMatrix();
        G.loadFile();
        
        for (int i = 0; i < G.getV(); i++) {
            System.out.println("deg " + i + ": " + G.deg(i));
        }
        
        System.out.print("DFS: ");
        G.printDFS();
        
        System.out.println("___________________________");
        System.out.println("SHORTEST PATH");
        G.shortestPath();
         System.out.println("");
        
//        System.out.println("");
//        System.out.println("___________________________");
//        System.out.println("SHORTEST PATH ALL NODE");
//        G.shortestPathAllNode();
    }
    
    public static void adjacentListGraph() throws Exception {
        GraphList G = new GraphList();
        G.loadFile();
        
        System.out.print("DFS: ");
        G.printDFS();
        
        System.out.println("___________________________");
        System.out.println("SHORTEST PATH");
        G.shortestPath();
        
        System.out.println("___________________________");
        System.out.println("MST");
        G.prim(0);
        
    }
     
    public static void miniumSpanningTree() throws Exception {
        GraphList G = new GraphList();
        G.loadFile();
        G.findMST();
    }
}

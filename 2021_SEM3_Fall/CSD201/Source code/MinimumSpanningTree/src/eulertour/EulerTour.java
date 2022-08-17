package eulertour;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class EulerTour {

    public static void main(String[] args) throws Exception {
        List();
    }
    
    public static void Matrix() throws Exception {
        GraphMatrix G = new GraphMatrix();
        G.loadFile();
        G.fleuryMatrix();
    }
    
    public static void List() throws Exception {
        GraphList G = new GraphList();
        G.loadGraph();
        G.fleuryList();
    }
}

package sample3;

import java.io.IOException;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class Sample3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        AlgoFPGrowth fpgr = new AlgoFPGrowth();
        
        fpgr.runAlgorithm("F:\\CSD\\ASS2\\Sample3\\apriori.txt", "F:\\CSD\\ASS2\\Sample3\\res.txt", 0.6);
        fpgr.printStats();
    }
    
}

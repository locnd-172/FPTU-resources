package sample2;

import java.io.IOException;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class main {
    
    public static void main(String[] args) throws IOException, Exception {
        AlgoFPGrowth fpgr = new AlgoFPGrowth();
        
        fpgr.runAlgorithm("F:\\CSD\\ASS2\\INPUT\\TXT\\Online Retail (Portugal).txt", 
                          "F:\\CSD\\ASS2\\OUTPUT\\portugal.txt", 0.05);
        fpgr.printStats();
        
        /*
        Sweden
        
        Code
        min supp: 3
        Total transaction from database: 47
        Total frequent itemsets count: 130
        Total time: 35ms
        
        Tool
        minsup: 3
        Transactions count from database : 47
        Frequent itemsets count : 134
        Total time ~ 39 ms
        _____________________________________
        
        Portugal
        
        Code
        min supp: 4
        Total transaction from database: 71
        Total frequent itemsets count: 2277
        Total time: 213ms
        
        Tool
        Transactions count from database : 71
        Frequent itemsets count : 3683
        Total time ~ 81 ms
        _____________________________________
        
        France
        
        Code
        min supp: 24
        Total transaction from database: 462
        Total frequent itemsets count: 137
        Total time: 93ms
        
        Transactions count from database : 462
        Frequent itemsets count : 137
        Total time ~ 100 ms
        _____________________________________
        
        UK
        Code
        min supp: 1175
        Total transaction from database: 23495
        Total frequent itemsets count: 14
        Total time: 535ms
       
        Tool
        Transactions count from database : 23495
        Frequent itemsets count : 14
        Total time ~ 536 ms
        */
        
     
    }
}

package sample2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class AlgoFPGrowth {

    private long startTime;
    private long endTime;
    private int cntTransaction = 0;
    private int itemsetCount;

    public int relativeMinsupp;

    BufferedWriter writer = null;

    public AlgoFPGrowth() {
    }

    private HashMap<String, Integer> itemSupport;
    
    public void runAlgorithm(String input, String output, double minsupp) throws Exception {
        startTime = System.currentTimeMillis();
        
        itemsetCount = 0;
        writer = new BufferedWriter(new FileWriter(output));

        // 1. Calc frequency of item
        calcFrequencyOfItem(input);

        this.relativeMinsupp = (int) Math.ceil(minsupp * cntTransaction);
        System.out.println("minsup: " + this.relativeMinsupp);
        // 2. Build FP-Tree
        FPTree trie = new FPTree();

        Scanner sc = new Scanner(new File(input));
        while (sc.hasNext()) {
            String data = sc.nextLine().trim().replaceAll("\\s{2,}", " ");
            String[] items = data.split(" ");

            ArrayList<String> freqItemset = new ArrayList<>();
            for (String item : items) {
                if (itemSupport.get(item) < relativeMinsupp) continue;
                freqItemset.add(item);
            }
            
            Collections.sort(freqItemset, (String o1, String o2) -> {
                int cmp = itemSupport.get(o2) - itemSupport.get(o1);
                return (cmp == 0 ? o1.compareTo(o2) : cmp);
            });
            
            trie.addTransaction(freqItemset);
        }

        // (3) Create Header table (head of node-link)
        trie.createHeaderTable(itemSupport);

        // (4) Mine the FP-Tree 
        String[] prefixAlpha = new String[0];
        if (trie.headerTable.size() > 0) {
            fpgrowth(trie, prefixAlpha, cntTransaction, itemSupport);
        }

        writer.close();
        endTime = System.currentTimeMillis();
    }
    
    private void calcFrequencyOfItem(String input) throws Exception {
        itemSupport = new HashMap<>();
        Scanner sc = new Scanner(new File(input));
        while (sc.hasNext()) {
            String data = sc.nextLine().trim().replaceAll("\\s{2,}", " ");
            String[] items = data.split(" ");
            for (String item : items) {
                if (!itemSupport.containsKey(item)) {
                    itemSupport.put(item, 1);
                } else {
                    int pos = itemSupport.get(item);
                    itemSupport.put(item, ++pos);
                }
            }
            cntTransaction++;
        }
    }

    /**
     * @param trie Prefix-Tree
     * @param prefix The current prefix "alpha"
     * @param suppList The frequency of each item in the prefix tree.
     */
    private void fpgrowth(FPTree trie, String[] prefixAlpha, int prefixSupport, HashMap<String, Integer> suppList) throws IOException {
        if (trie.multiPath == false) {
            addAllCombinationsForPathAndPrefix(trie.root.childs.get(0), prefixAlpha);

        } else { 
            mineFPTree(trie, prefixAlpha, prefixSupport, suppList);
        }
    }

    private void mineFPTree(FPTree trie, String[] prefixAlpha, int prefixSupport, HashMap<String, Integer> suppList) throws IOException {
        for (int i = trie.headerTable.size() - 1; i >= 0; i--) {
            String itemID = trie.headerTable.get(i);

            int supp = suppList.get(itemID);
            if (supp < relativeMinsupp) continue;

            // Create Beta by concatening Alpha with the current item
            // and add it to the list of frequent patterns
            String[] beta = new String[prefixAlpha.length + 1];
            System.arraycopy(prefixAlpha, 0, beta, 0, prefixAlpha.length);
            beta[prefixAlpha.length] = itemID;

            int betaSupport = Math.min(prefixSupport, supp);
            if (betaSupport >= relativeMinsupp)
                writeData(beta, betaSupport);

            // Beta's conditional pattern base (prefix paths)
            ArrayList<ArrayList<FPNode>> prefixPaths = new ArrayList<>();
            FPNode iNode = trie.mapItemNodes.get(itemID);
            while (iNode != null) {
                if (iNode.parent.itemID != null) {
                    ArrayList<FPNode> prefixPath = new ArrayList<>();
                    prefixPath.add(iNode);   

                    FPNode parent = iNode.parent;
                    while (parent.itemID != null) {
                        prefixPath.add(parent);
                        parent = parent.parent;
                    }
                    prefixPaths.add(prefixPath);
                }
                 
                // next occurency of this node
                iNode = iNode.next;
            }

            // (A) Accumulate the count for each item in the base
            HashMap<String, Integer> betaSuppList = new HashMap<>();
            for (ArrayList<FPNode> prefixPath : prefixPaths) {
                int pathSupp = prefixPath.get(0).supportCnt;
                
                // calc freq of nodes in prefix path
                for (int j = 1; j < prefixPath.size(); j++) {
                    FPNode node = prefixPath.get(j);
                    if (betaSuppList.get(node.itemID) == null) {
                        betaSuppList.put(node.itemID, pathSupp);
                    } else {
                        betaSuppList.put(node.itemID, betaSuppList.get(node.itemID) + pathSupp);
                    }
                }
            }

            // (B) Construct beta's conditional FP-Tree
            FPTree betaTrie = new FPTree();
            
            // add each prefix-path in the FP-tree
            // prefix path ~ freq itemset
            for (ArrayList<FPNode> prefixPath : prefixPaths) 
                betaTrie.addPrefixPath(prefixPath, betaSuppList, relativeMinsupp);
            
            betaTrie.createHeaderTable(betaSuppList);

            if (betaTrie.root.childs.size() > 0) 
                fpgrowth(betaTrie, beta, betaSupport, betaSuppList);
        }

    }

    private void addAllCombinationsForPathAndPrefix(FPNode node, String[] prefix) throws IOException {
        // Concatenate the node item to the current prefix
        String[] itemset = new String[prefix.length + 1];
        System.arraycopy(prefix, 0, itemset, 0, prefix.length);
        itemset[prefix.length] = node.itemID;

        if (node.supportCnt >= relativeMinsupp) 
            writeData(itemset, node.supportCnt);

        if (node.childs.size() != 0) {
            addAllCombinationsForPathAndPrefix(node.childs.get(0), itemset);
            addAllCombinationsForPathAndPrefix(node.childs.get(0), prefix);
        }
    }

    private void writeData(String[] itemset, int support) throws IOException {
        itemsetCount++;

        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < itemset.length; i++) {
            buffer.append(itemset[i]);
            if (i != itemset.length - 1) {
                buffer.append(" ");
            }
        }
        buffer.append(":");
        buffer.append(support);
        writer.write(buffer.toString());
        writer.newLine();
    }

    public void printStats() {
        long temps = endTime - startTime;
        System.out.println("=============  FP-GROWTH - STATS =============");
        System.out.println(" Transactions count from database : " + cntTransaction);
        System.out.println(" Frequent itemsets count : " + itemsetCount);
        System.out.println(" Total time ~ " + temps + " ms");
        System.out.println("===================================================");
    }
}

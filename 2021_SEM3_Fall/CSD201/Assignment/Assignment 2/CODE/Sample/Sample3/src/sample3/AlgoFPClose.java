package sample3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sample3.Itemset;
import sample3.Itemsets;

public class AlgoFPClose {

    // for statistics
    private long startTimestamp; // start time of the latest execution
    private long endTime; // end time of the latest execution
    private int transactionCount = 0; // transaction count in the database
    private int itemsetCount; // number of freq. itemsets found

    // parameter
    public int minSupportRelative;// the relative minimum support

    BufferedWriter writer = null; // object to write the output file

    // The  patterns that are found 
    // (if the user want to keep them into memory)
    protected Itemsets patterns = null;

    // This variable is used to determine the size of buffers to store itemsets.
    // A value of 2000 should be enough 
    final int BUFFERS_SIZE = 2000;

    // buffer for storing the current itemset that is mined when performing mining
    // the idea is to always reuse the same buffer to reduce memory usage.
    private int[] itemsetBuffer = null;
    // Buffer for storing the counts of the current itemset that is mined 
    private int[] countBuffer = null;

    // This is the CFI tree for storing closed itemsets
    public CFITree cfiTree = null;

    // Map to store the support of single items in the original databse
    private Map<Integer, Integer> originalMapSupport = null;

    // If set to true, the algorithm will show  information for debugging in the console
    private final boolean DEBUG = false;

    // Comparator to compare the items based on the order of decreasing support in the original DB.
    Comparator<Integer> comparatorOriginalOrder = new Comparator<Integer>() {
        public int compare(Integer item1, Integer item2) {
            // compare the frequency
            int compare = originalMapSupport.get(item2) - originalMapSupport.get(item1);
            // if the same frequency, we check the lexical ordering!
            if (compare == 0) {
                compare = (item1 - item2);
                return compare;
            }
            return compare;
        }
    };

    /**
     * Constructor
     */
    public AlgoFPClose() {

    }

    public Itemsets runAlgorithm(String input, String output, double minsupp) throws FileNotFoundException, IOException {
        startTimestamp = System.currentTimeMillis();
        itemsetCount = 0;

        // if the user want to keep the result into memory
        if (output == null) {
            writer = null;
            patterns = new Itemsets("FREQUENT ITEMSETS");
        } else { // if the user want to save the result to a file
            patterns = null;
            writer = new BufferedWriter(new FileWriter(output));
        }

        // (1) PREPROCESSING: Initial database scan to determine the frequency of each item
        // The frequency is stored in a map:
        //    key: item   value: support
        originalMapSupport = scanDatabaseToDetermineFrequencyOfSingleItems(input);

        // convert the minimum support as percentage to a
        // relative minimum support
        this.minSupportRelative = (int) Math.ceil(minsupp * transactionCount);

        // Create the CFI Tree
        cfiTree = new CFITree();

        // (2) Scan the database again to build the initial FP-Tree
        // Before inserting a transaction in the FPTree, we sort the items
        // by descending order of support.  We ignore items that
        // do not have the minimum support.
        FPTree tree = new FPTree();

        // read the file
        BufferedReader reader = new BufferedReader(new FileReader(input));
        String line;
        // for each line (transaction) until the end of the file
        while (((line = reader.readLine()) != null)) {
            // if the line is  a comment, is  empty or is a
            // kind of metadata
            if (line.isEmpty() == true || line.charAt(0) == '#' || line.charAt(0) == '%'
                    || line.charAt(0) == '@') {
                continue;
            }

            String[] lineSplited = line.split(" ");
            List<Integer> transaction = new ArrayList<Integer>();

            // for each item in the transaction
            for (String itemString : lineSplited) {
                Integer item = Integer.parseInt(itemString);
                // only add items that have the minimum support
                if (originalMapSupport.get(item) >= minSupportRelative) {
                    transaction.add(item);
                }
            }
            // sort item in the transaction by descending order of support
            Collections.sort(transaction, comparatorOriginalOrder);
            // add the sorted transaction to the fptree.
            tree.addTransaction(transaction);
        }

        cfiTree.setComparator(comparatorOriginalOrder);
        // close the input file
        reader.close();

        // We create the header table for the tree using the calculated support of single items
        tree.createHeaderList(originalMapSupport);

//		System.out.println(tree);
        // (5) We start to mine the FP-Tree by calling the recursive method.
        // Initially, the prefix alpha is empty.
        // if at least an item is frequent
        if (tree.headerList.size() > 0) {
            // initialize the buffer for storing the current itemset
            itemsetBuffer = new int[BUFFERS_SIZE];
            countBuffer = new int[BUFFERS_SIZE];
            // Next we will recursively generate frequent itemsets using the fp-tree
            fpclose(tree, itemsetBuffer, 0, transactionCount, originalMapSupport);
        }

        // close the output file if the result was saved to a file
        if (writer != null) {
            writer.close();
        }
        // record the execution end time
        endTime = System.currentTimeMillis();

        // return the result (if saved to memory)
        return patterns;
    }

    /**
     * Mine an FP-Tree having more than one path.
     *
     * @param tree the FP-tree
     * @param prefix the current prefix, named "alpha"
     * @param mapSupport the frequency of items in the FP-Tree
     * @throws IOException exception if error writing the output file
     */
    private void fpclose(FPTree tree, int[] prefix, int prefixLength, int prefixSupport, Map<Integer, Integer> mapSupport) throws IOException {
//		======= DEBUG ========
        if (DEBUG) {
            System.out.print("###### Prefix: ");
            for (int k = 0; k < prefixLength; k++) {
                System.out.print(prefix[k] + "  ");
            }
            System.out.println("\n");
            System.out.println(tree);
        }	//========== END DEBUG =======

        // We first check if the FPtree contains a single path
        // We will check if the FPtree contains a single path
        boolean singlePath = true;
        // This variable is used to count the number of items in the single path  (if there
        // is one + the prefix length
        int position = prefixLength;
        if (tree.root.childs.size() > 1) {
            // if the root has more than one child, than it is not a single path
            singlePath = false;
        } else {
            // if the root has exactly one child, we need to recursively check childs
            // of the child to see if they also have one child
            FPNode currentNode = tree.root.childs.get(0);
            while (true) {
                // if the current child has more than one child, it is not a single path!
                if (currentNode.childs.size() > 1) {
                    singlePath = false;
                    break;
                }
                // otherwise, we copy the current item in the buffer and move to the child
                itemsetBuffer[position] = currentNode.itemID;
                countBuffer[position] = currentNode.counter;
                position++;
                // if this node has no child, that means that this is the end of this path
                // and it is a single path, so we break
                if (currentNode.childs.size() == 0) {
                    break;
                }
                currentNode = currentNode.childs.get(0);
            }
        }

        // Case 1: the FPtree contains a single path
        // If this path has enough support:
        if (singlePath && countBuffer[position - 1] >= minSupportRelative) {
//			System.out.println();
            // generate all the CFIs from this path
            // for each CFI X generated, we will check if X is closed
            // by looking at the CFI-tree. If yes we will insert X in
            // the CFI-Tree
            for (int i = prefixLength; i <= position; i++) {
                // if  the last item
                if (i == position) {
                    int pathSupport = countBuffer[i - 1];

                    // if he current itemset passes the closure checking
                    // we save this as a closed itemset
                    int[] headWithP = new int[i];
                    System.arraycopy(itemsetBuffer, 0, headWithP, 0, i);
                    sortOriginalOrder(headWithP, i);

                    if (cfiTree.passSubsetChecking(headWithP, i, pathSupport)) {
                        saveItemset(headWithP, i, pathSupport);
                    }
                } else {
                    // if the counter of item in the i+1 th position is different
                    // from the counter of item in the i th position:
                    if (i > 0 && countBuffer[i - 1] != 0 && countBuffer[i - 1] != countBuffer[i]) {
                        int pathSupport = countBuffer[i - 1];  // NEW

                        // if he current itemset passes the closure checking
                        // we save this as a closed itemset
                        int[] headWithP = new int[i];
                        System.arraycopy(itemsetBuffer, 0, headWithP, 0, i);
                        sortOriginalOrder(headWithP, i);

                        if (cfiTree.passSubsetChecking(headWithP, i, pathSupport)) {
                            // if the itemset ending in the i th position passes
                            // the closure checking,
                            // we save the itemset ending in the i th position as a closed itemset
                            saveItemset(headWithP, i, pathSupport);
                        }
                    }
                }

            }
        } else {
            // Case 2: There are multiple paths.

            // For each frequent item in the header table list of the tree in reverse order. (in decreasing order of support...)
            for (int i = tree.headerList.size() - 1; i >= 0; i--) {
                // get the item
                Integer item = tree.headerList.get(i);

                // get the item support
                int support = mapSupport.get(item);

                // calculate the support of the new prefix beta
                int betaSupport = (prefixSupport < support) ? prefixSupport : support;

                // Create Beta by concatening item to the current prefix  alpha
                prefix[prefixLength] = item;
                countBuffer[prefixLength] = betaSupport;

                // === (A) Construct beta's conditional pattern base ===
                // It is a subdatabase which consists of the set of prefix paths
                // in the FP-tree co-occuring with the prefix pattern.
                List<List<FPNode>> prefixPaths = new ArrayList<List<FPNode>>();
                FPNode path = tree.mapItemNodes.get(item);

                // Map to count the support of items in the conditional prefix tree
                // Key: item   Value: support
                Map<Integer, Integer> mapSupportBeta = new HashMap<Integer, Integer>();

                while (path != null) {
                    // if the path is not just the root node
                    if (path.parent.itemID != -1) {
                        // create the prefixpath
                        List<FPNode> prefixPath = new ArrayList<FPNode>();
                        // add this node.
                        prefixPath.add(path);   // NOTE: we add it just to keep its support,
                        // actually it should not be part of the prefixPath

                        // ####
                        int pathCount = path.counter;

                        //Recursively add all the parents of this node.
                        FPNode parent = path.parent;
                        while (parent.itemID != -1) {
                            prefixPath.add(parent);

                            // FOR EACH PATTERN WE ALSO UPDATE THE ITEM SUPPORT AT THE SAME TIME
                            // if the first time we see that node id
                            if (mapSupportBeta.get(parent.itemID) == null) {
                                // just add the path count
                                mapSupportBeta.put(parent.itemID, pathCount);
                            } else {
                                // otherwise, make the sum with the value already stored
                                mapSupportBeta.put(parent.itemID, mapSupportBeta.get(parent.itemID) + pathCount);
                            }
                            parent = parent.parent;
                        }
                        // add the path to the list of prefixpaths
                        prefixPaths.add(prefixPath);
                    }
                    // We will look for the next prefixpath
                    path = path.nodeLink;
                }

                // ===== FP-CLOSE ======
                // concatenate Beta (Head) with the item "item" (i) to check 
                // for closure
                int[] headWithP = new int[prefixLength + 1];
                System.arraycopy(prefix, 0, headWithP, 0, prefixLength + 1);

                // Sort Head U {item} according to the original header list total order on items
                // sort item in the transaction by descending order of support
                sortOriginalOrder(headWithP, prefixLength + 1);

                //======= DEBUG ========
                if (DEBUG) {
                    System.out.println(" CHECK2 : " + Arrays.toString(headWithP) + " sup=" + betaSupport);
                }
                //========== END DEBUG =======

                // CHECK IF HEAD U P IS A SUBSET OF A CFI ACCORDING TO THE CFI-TREE
                if (cfiTree.passSubsetChecking(headWithP, prefixLength + 1, betaSupport)) {

                    if (DEBUG) {
                        System.out.println("    passed!");
                    }
                    // (B) Construct beta's conditional FP-Tree using its prefix path
                    // Create the tree.
                    FPTree treeBeta = new FPTree();
                    // Add each prefixpath in the FP-tree.
                    for (List<FPNode> prefixPath : prefixPaths) {
                        treeBeta.addPrefixPath(prefixPath, mapSupportBeta, minSupportRelative);
                    }
                    // Mine recursively the Beta tree if the root has child(s)
                    if (treeBeta.root.childs.size() > 0) {

                        // Create the header list.
                        treeBeta.createHeaderList(originalMapSupport);

                        // recursive call
                        fpclose(treeBeta, prefix, prefixLength + 1, betaSupport, mapSupportBeta);
                    }
                    // if the tree is empty we still need to try to save the 
                    // itemset
                    if (cfiTree.passSubsetChecking(headWithP, prefixLength + 1, betaSupport)) {
                        saveItemset(headWithP, prefixLength + 1, betaSupport);
                    }
                } else {
                    if (DEBUG) {
                        System.out.println("     failed!");
                    }
                    // OPTIMIZATION ONLY IN FPCLOSE:  IF THE CLOSURE CHECKING iS NOT PASSED
                    // WE STOP THIS LOOP BECAUSE THE NEXT ITEMS WILL NOT PASS IT EITHER
                    // break;
                }
            }
        }
    }

    private void saveItemset(int[] itemset, int itemsetLength, int support) throws IOException {

        // copy the itemset in the output buffer and sort items according to the
        // order of decreasing support in the original database
        int[] itemsetCopy = new int[itemsetLength];
        System.arraycopy(itemset, 0, itemsetCopy, 0, itemsetLength);
        sortOriginalOrder(itemsetCopy, itemsetLength);

        if (DEBUG) {
            //		//======= DEBUG ========
            System.out.print(" ##### SAVING : ");
            for (int i = 0; i < itemsetLength; i++) {
                System.out.print(itemsetCopy[i] + "  ");
            }
            System.out.println("\n");
            //		//========== END DEBUG =======
        }

        // add the itemset to the CFI-TREE
        cfiTree.addCFI(itemsetCopy, itemsetCopy.length, support);

        // increase the number of itemsets found for statistics purpose
        itemsetCount++;

        // if the result should be saved to a file
        if (writer != null) {

            // Create a string buffer
            StringBuilder buffer = new StringBuilder();
            // write the items of the itemset
            for (int i = 0; i < itemsetLength; i++) {
                buffer.append(itemsetCopy[i]);
                if (i != itemsetLength - 1) {
                    buffer.append(' ');
                }
            }
            // Then, write the support
            buffer.append(" #SUP: ");
            buffer.append(support);
            // write to file and create a new line
            writer.write(buffer.toString());
            writer.newLine();
        }// otherwise the result is kept into memory
        else {

            // sort the itemset so that it is sorted according to lexical ordering before we show it to the user
            Arrays.sort(itemsetCopy);

            Itemset itemsetObj = new Itemset(itemsetCopy);
            itemsetObj.setAbsoluteSupport(support);
            patterns.addItemset(itemsetObj, itemsetLength);
        }
    }

    public void sortOriginalOrder(int[] a, int length) {
        // Perform a bubble sort
        for (int i = 0; i < length; i++) {
            for (int j = length - 1; j >= i + 1; j--) {
                boolean test = comparatorOriginalOrder.compare(a[j], a[j - 1]) < 0;
                if (test) {
                    int temp = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = temp;
                }
            }
        }

    }

    private Map<Integer, Integer> scanDatabaseToDetermineFrequencyOfSingleItems(String input)
            throws FileNotFoundException, IOException {
        Map<Integer, Integer> mapSupport = new HashMap<Integer, Integer>();
        BufferedReader reader = new BufferedReader(new FileReader(input));
        String line;
        while (((line = reader.readLine()) != null)) {
            if (line.isEmpty() == true || line.charAt(0) == '#' || line.charAt(0) == '%' || line.charAt(0) == '@') {
                continue;
            }

            String[] lineSplited = line.split(" ");
            for (String itemString : lineSplited) {
                // increase the support count of the item
                Integer item = Integer.parseInt(itemString);
                // increase the support count of the item
                Integer count = mapSupport.get(item);
                if (count == null) {
                    mapSupport.put(item, 1);
                } else {
                    mapSupport.put(item, ++count);
                }
            }
            transactionCount++;
        }
        reader.close();

        return mapSupport;
    }

    public void printStats() {
        System.out.println("=============  FP-Close v0.96r14  - STATS =============");
        long temps = endTime - startTimestamp;
        System.out.println(" Transactions count from database : " + transactionCount);
        System.out.println(" Closed frequent itemset count : " + itemsetCount);
        System.out.println(" Total time ~ " + temps + " ms");
        System.out.println("===================================================");
    }

    public int getDatabaseSize() {
        return transactionCount;
    }
}

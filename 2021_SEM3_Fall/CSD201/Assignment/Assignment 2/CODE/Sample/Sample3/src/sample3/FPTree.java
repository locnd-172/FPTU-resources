package sample3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sample3.Itemset;

public class FPTree {

    List<Integer> headerList = null;

    Map<Integer, FPNode> mapItemNodes = new HashMap<Integer, FPNode>();

    Map<Integer, FPNode> mapItemLastNode = new HashMap<Integer, FPNode>();

    FPNode root = new FPNode();

    public FPTree() {

    }

    public void addTransaction(List<Integer> transaction) {
        FPNode currentNode = root;
        for (Integer item : transaction) {
            // look if there is a node already in the FP-Tree
            FPNode child = currentNode.getChildWithID(item);
            if (child == null) {
                // there is no node, we create a new one
                FPNode newNode = new FPNode();
                newNode.itemID = item;
                newNode.parent = currentNode;
                // we link the new node to its parrent
                currentNode.childs.add(newNode);

                // we take this node as the current node for the next for loop iteration 
                currentNode = newNode;

                // We update the header table.
                // We check if there is already a node with this id in the header table
                fixNodeLinks(item, newNode);
            } else {
                // there is a node already, we update it
                child.counter++;
                currentNode = child;
            }
        }
    }

    private void fixNodeLinks(Integer item, FPNode newNode) {
        // get the latest node in the tree with this item
        FPNode lastNode = mapItemLastNode.get(item);
        if (lastNode != null) {
            // if not null, then we add the new node to the node link of the last node
            lastNode.nodeLink = newNode;
        }
        // Finally, we set the new node as the last node 
        mapItemLastNode.put(item, newNode);

        FPNode headernode = mapItemNodes.get(item);
        if (headernode == null) {  // there is not
            mapItemNodes.put(item, newNode);
        }
    }

    void addPrefixPath(List<FPNode> prefixPath, Map<Integer, Integer> mapSupportBeta, int relativeMinsupp) {
        // the first element of the prefix path contains the path support
        int pathCount = prefixPath.get(0).counter;

        FPNode currentNode = root;
        // For each item in the transaction  (in backward order)
        // (and we ignore the first element of the prefix path)
        for (int i = prefixPath.size() - 1; i >= 1; i--) {
            FPNode pathItem = prefixPath.get(i);
            // if the item is not frequent we skip it
            if (mapSupportBeta.get(pathItem.itemID) >= relativeMinsupp) {

                // look if there is a node already in the FP-Tree
                FPNode child = currentNode.getChildWithID(pathItem.itemID);
                if (child == null) {
                    // there is no node, we create a new one
                    FPNode newNode = new FPNode();
                    newNode.itemID = pathItem.itemID;
                    newNode.parent = currentNode;
                    newNode.counter = pathCount;  // set its support
                    currentNode.childs.add(newNode);
                    currentNode = newNode;
                    // We update the header table.
                    // and the node links
                    fixNodeLinks(pathItem.itemID, newNode);
                } else {
                    // there is a node already, we update it
                    child.counter += pathCount;
                    currentNode = child;
                }
            }
        }
    }

    void addPrefixPathGRGrowth(List<FPNode> prefixPath, Map<Integer, Integer> mapSupportBeta, int relativeMinsupp) {
        // the first element of the prefix path contains the path support
        int pathCount = prefixPath.get(0).counter;

        FPNode currentNode = root;
        // For each item in the transaction  (in backward order)
        // (and we ignore the first element of the prefix path)
        for (int i = prefixPath.size() - 1; i >= 1; i--) {
            FPNode pathItem = prefixPath.get(i);
            // if the item is not frequent we skip it
            int support = mapSupportBeta.get(pathItem.itemID);
            if (support >= relativeMinsupp
                    // ============ GR-GROWTH ==========
                    && support < prefixPath.size()) {
                // ============ END GR-GROWTH ===========

                // look if there is a node already in the FP-Tree
                FPNode child = currentNode.getChildWithID(pathItem.itemID);
                if (child == null) {
                    // there is no node, we create a new one
                    FPNode newNode = new FPNode();
                    newNode.itemID = pathItem.itemID;
                    newNode.parent = currentNode;
                    newNode.counter = pathCount;  // set its support
                    currentNode.childs.add(newNode);
                    currentNode = newNode;
                    // We update the header table.
                    // and the node links
                    fixNodeLinks(pathItem.itemID, newNode);
                } else {
                    // there is a node already, we update it
                    child.counter += pathCount;
                    currentNode = child;
                }
            }
        }
    }

    void createHeaderList(final Map<Integer, Integer> mapSupport) {
        headerList = new ArrayList<Integer>(mapItemNodes.keySet());

        Collections.sort(headerList, new Comparator<Integer>() {
            public int compare(Integer id1, Integer id2) {
                int compare = mapSupport.get(id2) - mapSupport.get(id1);
                return (compare == 0) ? (id1 - id2) : compare;
            }
        });
    }

    @Override
    public String toString() {
        String temp = "F";
        temp += " HeaderList: " + headerList + "\n";
        temp += root.toString("");
        return temp;
    }

}

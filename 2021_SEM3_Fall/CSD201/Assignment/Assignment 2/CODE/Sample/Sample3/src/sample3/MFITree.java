package sample3;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sample3.Itemset;

public class MFITree {

    // List of pairs (item, frequency) of the header table
    Map<Integer, MFINode> mapItemNodes = new HashMap<Integer, MFINode>();

    Map<Integer, MFINode> mapItemLastNode = new HashMap<Integer, MFINode>();

    MFINode root = new MFINode(); 
    MFINode lastAddedItemsetNode = null;

    public MFITree() {

    }

    private void fixNodeLinks(Integer item, MFINode newNode) {
        // get the latest node in the tree with this item
        MFINode lastNode = mapItemLastNode.get(item);
        if (lastNode != null) {
            // if not null, then we add the new node to the node link of the last node
            lastNode.nodeLink = newNode;
        }
        // Finally, we set the new node as the last node 
        mapItemLastNode.put(item, newNode);

        MFINode headernode = mapItemNodes.get(item);
        if (headernode == null) {  // there is not
            mapItemNodes.put(item, newNode);
        }
    }

    public void addMFI(int[] itemset, int itemsetLength, int support) {

        MFINode currentNode = root;
        // For each item in the itemset
        for (int i = 0; i < itemsetLength; i++) {
            int item = itemset[i];

            // look if there is a node already in the FP-Tree
            MFINode child = currentNode.getChildWithID(item);
            if (child == null) {
                // there is no node, we create a new one
                MFINode newNode = new MFINode();
                newNode.itemID = item;
                newNode.parent = currentNode;
                // remember at which level in the tree that node appears
                newNode.level = i + 1;
                // we link the new node to its parrent
                currentNode.childs.add(newNode);

                // we take this node as the current node for the next for loop iteration 
                currentNode = newNode;

                // We update the header table.
                // We check if there is already a node with this id in the header table
                fixNodeLinks(item, newNode);
            } else {
                // there is a node already, we update it
//				child.counter++;
                currentNode = child;
            }
        }

//		 SET THE SUPPORT OF THE MFI (the last item)
//		currentNode.counter = support;
        // remember that this is the last added itemset
        lastAddedItemsetNode = currentNode;
    }

    public boolean passSubsetChecking(List<Integer> headWithP) {

        // Find the node list for the last item of the itemset
        Integer lastItem = headWithP.get(headWithP.size() - 1);

        // OPTIMIZATION:
        // We first check against the last added itemset
        if (lastAddedItemsetNode != null) {
            boolean isSubset = issASubsetOfPrefixPath(headWithP, lastAddedItemsetNode);
            // if the itemset is a subset of the last added itemset, we do not need to check further
            if (isSubset) {
                return false;
            }
        }

        // OTHERWISE, WE NEED TO COMPARE "headwithP" with all the patterns in the MFI-tree.
        MFINode node = mapItemNodes.get(lastItem);
        // if that last item is not yet in the MFI-tree, it means that "itemset" is not a subset 
        // of some itemset already in the tree
        if (node == null) {
            return true;
        }
        // we will loop over each node by following node links
        do {
            // for a node, we will check if "headwithP" is a subset of the path ending at node
            boolean isSubset = issASubsetOfPrefixPath(headWithP, node);
            // if it is a subset, then "headWithP" is in the MFI-tree, we return false
            if (isSubset) {
                return false;
            }
            // go to the next itemset to test
            node = node.nodeLink;
        } while (node != null);

        // the itemset is not in the MFI-TREE.  Itemset passed the test!
        return true;
    }

    private boolean issASubsetOfPrefixPath(List<Integer> headWithP, MFINode node) {
        // optimization proposed in the fpmax* paper: if there is less than itemset node in that branch,
        // we don't need to check it
        if (node.level >= headWithP.size()) {
            // check if "itemset" is contained in the prefix path ending at "node"
            // We will start comparing from the parent of "node" in the prefix path since
            // the last item of itemset is "node".
            MFINode nodeToCheck = node;
            int positionInItemset = headWithP.size() - 1;
            int itemToLookFor = headWithP.get(positionInItemset);
            // for each item in itemset
            do {
                if (nodeToCheck.itemID == itemToLookFor) {
                    positionInItemset--;
                    // we found the itemset completely, so the subset check test is failed
                    if (positionInItemset < 0) {
                        return true;
                    }
                    itemToLookFor = headWithP.get(positionInItemset);
                }
                nodeToCheck = nodeToCheck.parent;
            } while (nodeToCheck != null);
        }
        return false;
    }

    @Override
    public String toString() {
        return "M" + root.toString("");
    }

}

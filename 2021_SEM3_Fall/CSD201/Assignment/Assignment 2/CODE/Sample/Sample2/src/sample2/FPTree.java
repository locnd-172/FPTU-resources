package sample2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class FPTree {

    ArrayList<String> headerTable = null;
    boolean multiPath = false;

    // (item, freq) 
    HashMap<String, FPNode> mapItemNodes = new HashMap<>();
    HashMap<String, FPNode> mapItemLastNode = new HashMap<>();

    FPNode root = new FPNode();

    FPTree() {
    }

    private void updateNodeLink(String itemID, FPNode newNode) {
        FPNode lastNode = mapItemLastNode.get(itemID);
        if (lastNode != null) 
            lastNode.next = newNode;

        mapItemLastNode.put(itemID, newNode);

        FPNode headernode = mapItemNodes.get(itemID);
        if (headernode == null) { 
            mapItemNodes.put(itemID, newNode);
        }
    }

    public void addTransaction(ArrayList<String> freqItemset) {
        FPNode curNode = root;
        for (String itemID : freqItemset) {
            FPNode child = curNode.getChild(itemID);
            if (child == null) {
                // no child, create a new one
                FPNode newNode = new FPNode(itemID, curNode);
                curNode.childs.add(newNode);

                // check if more than one path
                if (!multiPath && curNode.childs.size() > 1) 
                    multiPath = true;

                curNode = newNode;
                updateNodeLink(itemID, newNode);
            } else {
                child.supportCnt++;
                curNode = child;
            }
        }
    }

    public void addPrefixPath(ArrayList<FPNode> prefixPath, HashMap<String, Integer> mapSupportBeta, int relativeMinsupp) {
        int pathCnt = prefixPath.get(0).supportCnt;

        FPNode curNode = root;
        for (int i = prefixPath.size() - 1; i >= 1; i--) {
            FPNode pathItem = prefixPath.get(i);
            
            if (mapSupportBeta.get(pathItem.itemID) < relativeMinsupp) continue;

            FPNode child = curNode.getChild(pathItem.itemID);
            if (child == null) {
                FPNode newNode = new FPNode(pathItem.itemID, curNode, pathCnt);
                curNode.childs.add(newNode);

                if (!multiPath && curNode.childs.size() > 1) multiPath = true;

                curNode = newNode;
                updateNodeLink(pathItem.itemID, newNode);
            } else {
                child.supportCnt += pathCnt;
                curNode = child;
            }
        }
    }

    public void createHeaderTable(final Map<String, Integer> itemSupport) {
        headerTable = new ArrayList<>(mapItemNodes.keySet());
        Collections.sort(headerTable, (String id1, String id2) -> {
            int cmp = itemSupport.get(id2) - itemSupport.get(id1);
            return (cmp == 0 ? id1.compareTo(id2) : cmp);
        });
    }
}

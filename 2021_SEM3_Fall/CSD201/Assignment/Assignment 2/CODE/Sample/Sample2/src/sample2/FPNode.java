package sample2;

import java.util.ArrayList;

public class FPNode {

    public String itemID = null;
    public int supportCnt = 1;

    public FPNode parent = null;
    public ArrayList<FPNode> childs = new ArrayList<FPNode>();

    public FPNode next = null; 

    public FPNode() {
    }

    public FPNode(String itemID, FPNode parent) {
        this.itemID = itemID;
        this.parent = parent;
    }
    
    public FPNode(String itemID, FPNode parent, int sup) {
        this.itemID = itemID;
        this.parent = parent;
        this.supportCnt = sup;
    }
    
    public FPNode getChild(String id) {
        for (FPNode child : childs) 
            if (child.itemID.equals(id)) return child;
        
        return null;
    }

}

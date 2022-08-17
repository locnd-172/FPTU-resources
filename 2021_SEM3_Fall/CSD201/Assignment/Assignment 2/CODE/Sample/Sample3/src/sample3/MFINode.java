package sample3;

import java.util.ArrayList;
import java.util.List;

import sample3.Itemset;

public class MFINode {

    int itemID = -1;  // item id
    int level;  // at which level in the MFI tree this node appears

    MFINode parent = null;
    List<MFINode> childs = new ArrayList<MFINode>();

    MFINode nodeLink = null; 

    MFINode() {

    }

    MFINode getChildWithID(int id) {
        for (MFINode child : childs) {
            if (child.itemID == id) {
                return child;
            }
        }
        // if not found, return null
        return null;
    }

    public String toString(String indent) {
        StringBuilder output = new StringBuilder();
        output.append("" + itemID);
//		output.append(" (count="+ counter);
        output.append(" level=" + level);
        output.append(")\n");
        String newIndent = indent + "   ";
        for (MFINode child : childs) {
            output.append(newIndent + child.toString(newIndent));
        }
        return output.toString();
    }

}

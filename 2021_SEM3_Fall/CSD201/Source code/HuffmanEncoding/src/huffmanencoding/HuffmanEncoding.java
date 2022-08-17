package huffmanencoding;

import java.util.HashMap;
import java.util.PriorityQueue;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
class Node {

    Character ch;
    Integer freq;
    Node left, right;

    public Node(Character ch, Integer freq) {
        this.ch = ch;
        this.freq = freq;
        this.left = null;
        this.right = null;
    }

    public Node(Character ch, Integer freq, Node left, Node right) {
        this.ch = ch;
        this.freq = freq;
        this.left = left;
        this.right = right;
    }

    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }
}

public class HuffmanEncoding {
    
    static Node root = null;
    static HashMap<Character, String> huffCode = new HashMap<>();
    
    public static void encode(Node curNode, String str) {
        if (curNode == null) return;
        if (curNode.isLeaf() && str.length() > 0)  
            huffCode.put(curNode.ch, str);

        encode(curNode.left, str + "0");
        encode(curNode.right, str + "1");
    }

    public static Node buildTrie(String str) {

        HashMap<Character, Integer> cnt = new HashMap<>();
        for (char c : str.toCharArray()) 
            cnt.merge(c, 1, (a, b) -> a + b);

        PriorityQueue<Node> PQ = new PriorityQueue<>((Node a, Node b) -> {
            return a.freq.compareTo(b.freq);
        });

        for (var entry : cnt.entrySet()) 
            PQ.add(new Node(entry.getKey(), entry.getValue()));

        while (PQ.size() > 1) {
            Node left = PQ.poll();
            Node right = PQ.poll();
            int par = left.freq + right.freq;
            PQ.add(new Node(null, par, left, right));
        }

        return PQ.peek();
    }
    
    public static void compress(String str) {
        
        encode(root, "");

        System.out.println("Code: " + huffCode);
        System.out.println("Origin: " + str);

        String endcoded = "";
        for (char c : str.toCharArray()) 
            endcoded += huffCode.get(c);
        
        // compression rate
        int eBit = 0, sBit = huffCode.size() * 8;;
        for (var entry : huffCode.entrySet()) 
            eBit += entry.getValue().length();
        
        System.out.println("Encode: " + endcoded);
        
        double ratio = (double)(sBit - eBit) / (double)sBit * 100;
        System.out.printf("Compression rate: %.2f%%", ratio);
    }

    public static void main(String[] args) {
        
        String text = "bbbdabbacaaacdddb";
        root = buildTrie(text);
        compress(text);
        
        System.out.println("");
    }

}

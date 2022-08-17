package arraytoheap;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class ArrayToHeap {

    static int [] a = {2, 8, 6, 1, 10, 15, 3, 12, 11};
    
    public static void main(String[] args) {
  
        int n = a.length;
        buildHeap(n);
        System.out.println("Array to Heap:");
        for (int i = 0; i < a.length; ++i) {
            System.out.print(a[i] + " ");
        }

        System.out.println();
    }

    static void btmUpHeapify(int n, int i) {
        int cur = i;
        int leftChild = 2 * i + 1;
        int rightChild = 2 * i + 2;

        if (leftChild < n && a[leftChild] > a[cur]) {
            cur = leftChild;
        }

        if (rightChild < n && a[rightChild] > a[cur]) {
            cur = rightChild;
        }

        if (cur != i) {
            int tmp = a[i];
            a[i] = a[cur];
            a[cur] = tmp;
            btmUpHeapify(n, cur);
        }
    }

    static void buildHeap(int n) {
        int startIdx = (int) Math.floor((n - 1) / 2);

        for (int i = startIdx; i >= 0; i--) {
            btmUpHeapify(n, i);
        }
    }

}

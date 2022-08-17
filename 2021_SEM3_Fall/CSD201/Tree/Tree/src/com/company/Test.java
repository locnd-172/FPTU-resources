package com.company;

public class Test {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode () {}
        TreeNode (int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        int[] nums = {-10,-3,0,5,9};

        System.out.println(sortedArrayToBST(nums));
    }
    public static TreeNode sortedArrayToBST(int[] nums) {
        int start = 0, end = nums.length - 1;
        return ans(nums, start, end);
    }
    public static TreeNode ans (int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = start + (end - start)/2;
         TreeNode root = new TreeNode(nums[mid]);
         root.left = ans(nums, start, mid - 1);
         root.right = ans(nums, mid + 1, end);
         return root;
    }
}

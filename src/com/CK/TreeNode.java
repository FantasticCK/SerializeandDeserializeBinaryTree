package com.CK;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    private List<TreeNode> bfArray;

    TreeNode(int x) {
        val = x;
    }

    void printTree() {
        bfArray = new ArrayList<>();
        bfArray.add(this);
        traverseBF();
    }

    public void traverseBF() {
        while (!bfArray.isEmpty()) {
            TreeNode node = this.shiftArray(bfArray);
            System.out.println(node.val);
            if (node.left != null) bfArray.add(node.left);
            if (node.right != null) bfArray.add(node.right);
            traverseBF();
        }
    }

    private TreeNode shiftArray(List<TreeNode> arr) {
        TreeNode res = new TreeNode(0);
        if (!arr.isEmpty()) {
            res = arr.get(0);
            arr.remove(0);
        }
        return res;
    }
}

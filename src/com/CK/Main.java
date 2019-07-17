package com.CK;

import com.sun.source.tree.Tree;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(-1);
        TreeNode node1 = new TreeNode(0);
        TreeNode node2 = new TreeNode(1);
//        TreeNode node3 = new TreeNode(6);
//        TreeNode node4 = new TreeNode(2);
//        TreeNode node5 = new TreeNode(0);
//        TreeNode node6 = new TreeNode(8);
//        TreeNode node6 = new TreeNode(4);
//        TreeNode node6 = new TreeNode(4);
//        TreeNode node9 = new TreeNode(7);
//        TreeNode node10 = new TreeNode(4);
//        TreeNode node6 = new TreeNode(4);
//        TreeNode node6 = new TreeNode(4);
//        TreeNode node13 = new TreeNode(9);
//        TreeNode node14 = new TreeNode(8);

        root.left = node1;
        root.right = node2;
//        node1.left = node3;
//        node1.right = node4;
//        node2.left = node5;
//        node2.right = node6;
//        node4.left = node9;
//        node4.right = node10;
//        node6.left = node13;
//        node6.right = node14;

        Codec2 solution = new Codec2();
        System.out.println(solution.serialize(root));
        solution.deserialize(solution.serialize(root)).printTree();
    }
}


class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "";
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> bfsQueue = new LinkedList<>();
        Queue<String> bfsChar = new LinkedList<>();
        bfsQueue.offer(root);
        bfsChar.offer(String.valueOf(root.val) + ",");
        BFS(bfsQueue, sb, bfsChar);

        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        StringBuilder sb = new StringBuilder(data);
        if (data.length() < 1) return null;
        if (data.length() == 1) return new TreeNode(Integer.valueOf(stringPoll(sb)));
        Queue<TreeNode> bfsQueue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.valueOf(stringPoll(sb)));
        bfsQueue.offer(root);
        return BFS(sb, bfsQueue, root);
    }

    private void BFS(Queue<TreeNode> bfsQueue, StringBuilder sb, Queue<String> bfsChar) {
        while (!bfsQueue.isEmpty()) {
            TreeNode node = bfsQueue.poll();
            String nodeS = bfsChar.poll();
            if (nodeS.equals("Empty")) {
                sb.append("e,");
            } else {
                sb.append(node.val + ",");
                if (node.left != null) {
                    bfsQueue.offer(node.left);
                    bfsChar.offer(String.valueOf(node.left.val));
                } else {
                    bfsQueue.offer(null);
                    bfsChar.offer("Empty");
                }

                if (node.right != null) {
                    bfsQueue.offer(node.right);
                    bfsChar.offer(String.valueOf(node.right.val));
                } else {
                    bfsQueue.offer(null);
                    bfsChar.offer("Empty");
                }

            }
            BFS(bfsQueue, sb, bfsChar);
        }
    }

    private TreeNode BFS(StringBuilder sb, Queue<TreeNode> bfsQueue, TreeNode root) {
        while (sb.length() != 0 && !bfsQueue.isEmpty()) {
            String cL, cR;
            cL = stringPoll(sb);
            cR = stringPoll(sb);
            TreeNode node = bfsQueue.poll();
            if (!cL.equals("e")) {
                TreeNode left = new TreeNode(Integer.valueOf(cL));
                bfsQueue.offer(left);
                node.left = left;
            }
            if (!cR.equals("e")) {
                TreeNode right = new TreeNode(Integer.valueOf(cR));
                bfsQueue.offer(right);
                node.right = right;
            }
            BFS(sb, bfsQueue, root);
        }
        return root;
    }

    private String stringPoll(StringBuilder sb) {
        int firstComma = sb.indexOf(",");
        String res = sb.substring(0, firstComma);
        sb.delete(0, firstComma + 1);
        return res;
    }
}

// BFS
class Codec2 {
    public String serialize(TreeNode root) {
        if (root == null) return "";
        Queue<TreeNode> q = new LinkedList<>();
        StringBuilder res = new StringBuilder();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node == null) {
                res.append("n ");
                continue;
            }
            res.append(node.val + " ");
            q.add(node.left);
            q.add(node.right);
        }
        return res.toString();
    }

    public TreeNode deserialize(String data) {
        if (data == "") return null;
        Queue<TreeNode> q = new LinkedList<>();
        String[] values = data.split(" ");
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        q.add(root);
        for (int i = 1; i < values.length; i++) {
            TreeNode parent = q.poll();
            if (!values[i].equals("n")) {
                TreeNode left = new TreeNode(Integer.parseInt(values[i]));
                parent.left = left;
                q.add(left);
            }
            if (!values[++i].equals("n")) {
                TreeNode right = new TreeNode(Integer.parseInt(values[i]));
                parent.right = right;
                q.add(right);
            }
        }
        return root;
    }
}